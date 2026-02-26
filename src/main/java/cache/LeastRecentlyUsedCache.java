package cache;

import cache.model.Item;

import java.util.HashMap;
import java.util.Map;

//TODO sentinel(dummy) node(LRU에서 많이 쓴다고 함) 조사
//TODO LRU 캐시의 요구사항 조사
public class LeastRecentlyUsedCache {
    Map<Long, Item> mapForCache = new HashMap<>();
    public void put(Item item) {
        mapForCache.put(item.getId(), item);
    }
    public Item get(long id) {
        return mapForCache.get(id);
    }

    public int size() {
        return mapForCache.size();
    }
}
