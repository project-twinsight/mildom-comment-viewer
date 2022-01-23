package net.toshimichi.mildom.core;

public interface StreamListener<T> {

    default void onOpen() {}

    default void onClose(String reason) {}

    default void onMessage(T message) {}

    default void onError(Exception exception) {}
}
