package org.otus.education.cachehw;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * @author sergey
 * created on 14.12.18.
 */
public class MyCache<K, V> implements HwCache<K, V> {

    private final WeakHashMap<K,V> hashMap;
    private final List<HwListener<K,V>> listenerList = new ArrayList<>();

    public MyCache() {
        this.hashMap = new WeakHashMap<>();
    }
    public MyCache(int initialCapacity){
        this.hashMap = new WeakHashMap<>(initialCapacity);
    }

    //Надо реализовать эти методы

    @Override
    public void put(K key, V value) {
        hashMap.put(key, value);
        notify(key,"put");
    }

    @Override
    public void remove(K key) {
        notify(key,"remove");
        hashMap.remove(key);
    }

    @Override
    public V get(K key) {
        V v = hashMap.get(key);
        notify(key,"get");
        return v;
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        listenerList.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        listenerList.remove(listener);
    }

    @Override
    public int size() {
        return hashMap.size();
    }

    private void notify(K key, String action){
        listenerList.forEach(listener->listener
                .notify(key,hashMap.getOrDefault(key,null),action));
    }

}
