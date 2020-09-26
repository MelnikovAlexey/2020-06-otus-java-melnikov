package org.otus.education.cachehw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V> {

    private final Map<K,V> storage;
    private final List<HwListener<K,V>> listeners = new ArrayList<>();

    public MyCache() {
        this.storage = new WeakHashMap<>();
    }
    public MyCache(int initialCapacity){
        this.storage = new WeakHashMap<>(initialCapacity);
    }

    //Надо реализовать эти методы

    @Override
    public void put(K key, V value) {
        storage.put(key, value);
        notify(key,"put");
    }

    @Override
    public void remove(K key) {
        notify(key,"remove");
        storage.remove(key);
    }

    @Override
    public V get(K key) {
        V v = storage.get(key);
        notify(key,"get");
        return v;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listeners.remove(listener);
    }

    @Override
    public int size() {
        return storage.size();
    }

    private void notify(K key, String action){
        listeners.forEach(listener->{
                try {
                    listener
                            .notify(key, storage.getOrDefault(key, null), action);
                }
                catch (Exception e){
                    throw new ListenerException(e.getMessage());
                }
        });
    }

}
