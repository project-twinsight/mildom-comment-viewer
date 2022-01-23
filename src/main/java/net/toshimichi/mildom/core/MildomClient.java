package net.toshimichi.mildom.core;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.xxtea.XXTEA;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

public class MildomClient extends WebSocketClient implements StreamClient<MildomMessage> {

    private static final String CHAT_URL = "wss://jp-room1.mildom.com/?roomId=%d";
    private static final String ENCRYPT_KEY = "32l*!i1^l56e%$xnm1j9i@#$cr&";
    private final Gson gson = new Gson();
    private final ArrayList<StreamListener<MildomMessage>> streamListeners = new ArrayList<>();
    private final int roomId;

    public MildomClient(int roomId) throws URISyntaxException {
        super(new URI(CHAT_URL.formatted(roomId)));
        this.roomId = roomId;
    }

    private byte[] decodeHex(String hexString) {
        byte[] array = new BigInteger(hexString, 16).toByteArray();
        if (array[0] == 0) {
            byte[] output = new byte[array.length - 1];
            System.arraycopy(array, 1, output, 0, output.length);
            return output;
        }
        return array;
    }

    private byte[] encrypt(String message) {
        byte[] byteMessages = XXTEA.encrypt(message.getBytes(), ENCRYPT_KEY);
        byte[] result = new byte[byteMessages.length + 8];
        ByteBuffer.wrap(result)
                .order(ByteOrder.BIG_ENDIAN)
                .put(new byte[]{0, 4, 1, 1, 0, 0})
                .put(decodeHex(Integer.toHexString(byteMessages.length)));
        System.arraycopy(byteMessages, 0, result, 8, byteMessages.length);
        return result;
    }

    private String decrypt(byte[] array) {
        array = Arrays.copyOfRange(array, 8, array.length);
        return XXTEA.decryptToString(array, "32l*!i1^l56e%$xnm1j9i@#$cr&");
    }

    @Override
    public void addStreamListener(StreamListener<MildomMessage> listener) {
        streamListeners.add(listener);
    }

    @Override
    public void start() throws ClientException {
        try {
            connectBlocking();
            send(encrypt(gson.toJson(new EnterRoom(roomId))));
        } catch (InterruptedException e) {
            throw new ClientException("Could not connect to the server", e);
        }
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        streamListeners.forEach(StreamListener::onOpen);
    }

    @Override
    public void onMessage(String message) {}

    @Override
    public void onMessage(ByteBuffer bytes) {
        String message = decrypt(bytes.array());
        JsonObject object = gson.fromJson(message, JsonObject.class);
        if (!object.get("cmd").getAsString().equals("onChat")) return;

        MildomMessage mildomMessage = gson.fromJson(object, MildomMessage.class);
        streamListeners.forEach(it -> it.onMessage(mildomMessage));
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        streamListeners.forEach(it -> it.onClose("コメントの取得に失敗しました(" + code + "): " + reason));
    }

    @Override
    public void onError(Exception ex) {
        streamListeners.forEach(it -> it.onError(ex));
    }
}
