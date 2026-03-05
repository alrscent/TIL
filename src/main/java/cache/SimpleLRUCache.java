package cache;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

public class SimpleLRUCache<K, V> {
    private final Map<K, V> mapForCache = new HashMap<>();
    private final LinkedList<K> listForCache = new LinkedList<>();
    private final int cacheSize;
    private static final int DEFAULT_SIZE = 100;

    public SimpleLRUCache() {
        this.cacheSize = DEFAULT_SIZE;
    }
    public SimpleLRUCache(int cacheSize) {
        this.cacheSize = cacheSize;
    }

    public void put(K key, V value) {
        if (value == null) {
            throw new NullPointerException("캐시에 저장할 데이터가 null입니다");
        }

        V result = mapForCache.put(key, value);
        if (result != null) {
            listForCache.remove(key);
        }

        if (listForCache.size() == cacheSize) {
            K removeTarget = listForCache.removeLast();
            mapForCache.remove(removeTarget);
        }

        listForCache.addFirst(key);
    }

    public V get(K key) {
        V item = mapForCache.get(key);
        if (item != null) {
            listForCache.remove(key);
            listForCache.addFirst(key);
            return item;
        }

        throw new NoSuchElementException(key.toString() + "에 해당하는 데이터를 찾을 수 없습니다.");
    }

    public int size() {
        return mapForCache.size();
    }

    public V getLast() {
        K key = listForCache.getLast();
        return mapForCache.get(key);
    }

    public V getFirst() {
        K key = listForCache.getFirst();
        return mapForCache.get(key);
    }
}
