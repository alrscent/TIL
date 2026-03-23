package cache;

import java.util.*;

public class SimpleLRUCache<K, V> {
    private final Map<K, V> mapForCache = new HashMap<>();
    private final LinkedList<K> listForCache = new LinkedList<>();
    private final LinkedHashMap<K,V> cache = new LinkedHashMap<>();
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

        V result = cache.putFirst(key, value);

        if (cache.size() > cacheSize) {
            K removeTarget = cache.lastEntry().getKey();
            cache.remove(removeTarget);
        }
    }

    public V get(K key) {
        V item = cache.get(key);
        if (item != null) {
            cache.putFirst(key,  item);
            return item;
        }

        throw new NoSuchElementException(key.toString() + "에 해당하는 데이터를 찾을 수 없습니다.");
    }

    public int size() {
        return cache.size();
    }

    public V getLast() {
        return cache.lastEntry().getValue();
    }

    public V getFirst() {
        return cache.firstEntry().getValue();
    }
}
