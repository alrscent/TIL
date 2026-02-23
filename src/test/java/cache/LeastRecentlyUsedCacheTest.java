package cache;

import cache.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.UUID;

public class LeastRecentlyUsedCacheTest {
    @Test
    @DisplayName("LinkedList 사용 테스트")
    public void test() {
        LinkedList<String> linkedList = new LinkedList();
        for(int i = 0; i < 10; i++) {
            linkedList.add(UUID.randomUUID().toString());
        }
    }

    @Test
    @DisplayName("캐시 기본동작 - 값 저장 및 꺼내오기")
    public void lruCacheTest1() {
        Item item = new Item(1L, "아이템");
        LeastRecentlyUsedCache cache = new LeastRecentlyUsedCache();
        //TODO cache put/get/size 구현
//        cache.put(item.getId(), item);

//        Assertions.assertEquals(cache.size(), 1);

//        Item itemByCache = cache.get(1L);
//        Assertions.assertEquals(item, itemByCache);
    }

    @Test
    @DisplayName("LRU 캐시 - 캐시 사이즈 초과시 오래된 항목이 제거되어야 한다")
    public void lruCacheTest2() {
    }

    @Test
    @DisplayName("LRU 캐시 - 데이터 추가/갱신시 최근 사용한 것으로 순서가 변경되어야 한다")
    public void lruCacheTest3() {}

    @Test
    @DisplayName("LRU 캐시 - 데이터를 읽었을 때 최근 사용한 것으로 순서가 변경되어야 한다")
    public void lruCacheTest4() {}

    @Test
    @DisplayName("LRU 캐시 - 데이터를 조회했으나 캐시에 존재하지 않을 경우 DB에서 데이터를 조회 후 캐시에 데이터를 추가해야 한다")
    public void lruCacheTest5() {}

}
