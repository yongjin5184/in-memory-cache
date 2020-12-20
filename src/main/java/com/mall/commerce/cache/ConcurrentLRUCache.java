package com.mall.commerce.cache;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLRUCache<K, V> {

    int cacheSize = 0;

    private ConcurrentLinkedQueue<K> queue = new ConcurrentLinkedQueue<>();

    private ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    private Lock writeLock = lock.writeLock();

    private Lock readLock = lock.readLock();

    public ConcurrentLRUCache(final int size) {
        this.cacheSize = size;
    }

    public V get(K key) {

        readLock.lock();
        try {
            V val = null;
            if (map.containsKey(key)) {
                queue.remove(key);
                val = map.get(key);
                queue.add(key);
            }
            return val;
        } finally {
            readLock.unlock();
        }
    }

    public void put(K key, V value) {
        writeLock.lock();
        try {
            if (map.containsKey(key)) {
                queue.remove(key);
            }
            if (queue.size() == cacheSize) {
                K queueKey = queue.poll();
                map.remove(queueKey);
            }
            queue.add(key);
            map.put(key, value);

        } finally {
            writeLock.unlock();
        }
    }

    public V evict(K key) {
        writeLock.lock();
        try {
            V val = null;
            if (map.contains(key)) {
                val = map.remove(key);
                queue.remove(key);
            }
            return val;
        } finally {
            writeLock.unlock();
        }
    }
}

