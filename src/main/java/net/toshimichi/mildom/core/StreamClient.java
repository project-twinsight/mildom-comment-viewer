package net.toshimichi.mildom.core;

public interface StreamClient<T> {

    void addStreamListener(StreamListener<T> listener);

    void start() throws ClientException;
}
