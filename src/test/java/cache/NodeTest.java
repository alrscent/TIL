package cache;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class NodeTest {
    @Test
    @DisplayName("중간 노드의 이전/다음 노드를 설정하면 이전 노드의 다음, 다음 노드의 이전이 자동으로 중간 노드로 설정된다")
    public void intermediateNodeTest() {
        Node a = new Node();
        Node b = new Node();
        Node c = new Node();

        b.setPrev(a);
        b.setNext(c);

        assertSame(b, c.getPrev());
        assertSame(c, b.getNext());
    }
}
