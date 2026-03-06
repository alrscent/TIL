package cache;

import cache.model.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class SimpleLRUCacheTest {
    @Test
    @DisplayName("LinkedList 사용 테스트")
    void test() {
        LinkedList<String> linkedList = new LinkedList<>();
        int maxSize = 10;
        for(int i = 0; i < maxSize; i++) {
            linkedList.add(UUID.randomUUID().toString());
        }

        assertThat(maxSize).isEqualTo(linkedList.size());
    }

    @Test
    @DisplayName("캐시 기본동작 - 값 저장 및 꺼내오기")
    void lruCacheGetSetTest() {
        Item item = new Item(1L, "아이템");
        SimpleLRUCache<Long, Item> cache = new SimpleLRUCache<>();

        cache.put(item.getId(), item);
        assertThat(cache.size()).isEqualTo(1);

        Item itemByCache = cache.get(1L);
        assertThat(itemByCache).isEqualTo(item);
    }

    @Test
    @DisplayName("캐시 - 데이터 저장시 중복을 허용하지 않는다")
    void duplicateTest() {
        Item tangerine = new Item(1L, "귤");
        Item strawberry = new Item(1L, "딸기");

        SimpleLRUCache<Long, Item> cache = new SimpleLRUCache<>();
        cache.put(tangerine.getId(), tangerine);
        cache.put(strawberry.getId(), strawberry);

        assertThat(cache.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("LRU 캐시 - 캐시 사이즈 초과시 오래된 항목이 제거되어야 한다")
    void lruCacheRemoveTest() {
        SimpleLRUCache<Long, Item> cache = new SimpleLRUCache<>(2);

        Item tangerine = new Item(1L, "귤");
        Item strawberry = new Item(2L, "딸기");
        Item grape = new Item(3L, "포도");

        cache.put(tangerine.getId(), tangerine);
        cache.put(strawberry.getId(), strawberry);
        cache.put(grape.getId(), grape);

        assertThat(cache.size()).isEqualTo(2);
        assertThrows(NoSuchElementException.class, () -> {
            cache.get(tangerine.getId());
        });
    }

    @Test
    @DisplayName("LRU 캐시 - 데이터 추가/갱신시 최근 사용한 것으로 순서가 변경되어야 한다")
    void lruCacheOrderChangeWhenAddedAndUpdatedTest() {
        Item tangerine = new Item(1L, "귤");
        Item strawberry = new Item(2L, "딸기");
        Item grape = new Item(3L, "포도");

        SimpleLRUCache<Long, Item> cache = new SimpleLRUCache<>();
        cache.put(tangerine.getId(), tangerine);
        assertThat(cache.getFirst()).isEqualTo(tangerine);
        assertThat(cache.getLast()).isEqualTo(tangerine);

        cache.put(strawberry.getId(), strawberry);
        assertThat(cache.getFirst()).isEqualTo(strawberry);
        assertThat(cache.getLast()).isEqualTo(tangerine);

        cache.put(grape.getId(), grape);
        assertThat(cache.getFirst()).isEqualTo(grape);
        assertThat(cache.getLast()).isEqualTo(tangerine);

        Item tangerine2 = new Item(1L, "한라봉");
        cache.put(tangerine2.getId(), tangerine2);
        assertThat(cache.size()).isEqualTo(3);
        assertThat(cache.getFirst().getId()).isSameAs(1L);
        assertThat(cache.getFirst().getName()).isEqualTo("한라봉");
    }

    @Test
    @DisplayName("LRU 캐시 - 데이터를 읽었을 때 최근 사용한 것으로 순서가 변경되어야 한다")
    void lruCacheOrderChangeWhenReadTest() {
        Item tangerine = new Item(1L, "귤");
        Item strawberry = new Item(2L, "딸기");
        Item grape = new Item(3L, "포도");

        SimpleLRUCache<Long, Item> cache = new SimpleLRUCache<>();
        cache.put(tangerine.getId(), tangerine);
        cache.put(strawberry.getId(), strawberry);
        cache.put(grape.getId(), grape);

        Item itemByCache = cache.get(tangerine.getId());
        assertThat(itemByCache).isEqualTo(tangerine);
        assertThat(itemByCache).isEqualTo(cache.getFirst());

        Item itemByCache2 = cache.get(strawberry.getId());
        assertThat(itemByCache2).isEqualTo(strawberry);
        assertThat(itemByCache2).isEqualTo(cache.getFirst());
    }

}
