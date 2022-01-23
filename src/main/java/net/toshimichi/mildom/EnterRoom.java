package net.toshimichi.mildom;

final public class EnterRoom {

    private final int roomId;

    private final int userId = 0;
    private final int level = 1;
    private final String username = "guest100000";
    private final String guestId = "pc-gp-mildomcv-0000-0000-0000-100000000000";
    private final String nonopara = "ua=mildomcv";
    private final int reqId = 1;
    private final String cmd = "enterRoom";
    private final int reConnect = 0;
    private final int nobleLevel = 0;
    private final int avatarDecoration = 0;
    private final int enterroomEffect = 0;
    private final int nobleClose = 0;
    private final int nobleSeatClose = 0;

    public EnterRoom(int roomId) {
        this.roomId = roomId;
    }
}
