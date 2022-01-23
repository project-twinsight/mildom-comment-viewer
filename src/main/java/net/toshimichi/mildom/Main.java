package net.toshimichi.mildom;

public class Main {
    public static void main(String[] args) throws Exception {
        MildomClient client = new MildomClient(11415385);
        client.addStreamListener(new StreamListener<>() {
            @Override
            public void onMessage(MildomMessage message) {
                System.out.println(message);
            }
        });
        client.start();
    }
}
