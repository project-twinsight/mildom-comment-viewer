package net.toshimichi.mildom;

public interface StreamClient<T> {

    void addStreamListener(StreamListener<T> listener);

    void start() throws ClientException;
}
