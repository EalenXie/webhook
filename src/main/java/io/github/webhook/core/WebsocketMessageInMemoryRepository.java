package io.github.webhook.core;

import io.github.webhook.clinet.view.vo.WebhookWebsocketMessage;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class WebsocketMessageInMemoryRepository {

    private final Deque<WebhookWebsocketMessage> cache = new LinkedList<>();
    /**
     * 默认缓存300条
     */
    private int cacheSize = 300;

    public void setMaxSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public synchronized void save(WebhookWebsocketMessage message) {
        cache.addFirst(message);
        if (cache.size() > cacheSize) {
            cache.removeLast();
        }
    }

    public synchronized List<WebhookWebsocketMessage> list() {
        return new ArrayList<>(cache);
    }

    public synchronized void clear() {
        cache.clear();
    }

}
