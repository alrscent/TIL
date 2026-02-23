package cache.model;

public class Item {
    private final Long id;
    private final String name;

    public Item(long id, String itemName) {
        this.id = id;
        this.name = itemName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
