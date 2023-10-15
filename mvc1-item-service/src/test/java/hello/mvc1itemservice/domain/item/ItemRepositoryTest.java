package hello.mvc1itemservice.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {
    private static final ItemRepository itemRepository = new ItemRepository();
    @AfterEach
    void afterEach(){
        itemRepository.clear();
    }
    @Test
    void save() {
        Item item = new Item("item", 100, 1000);
        itemRepository.save(item);

        Item findItem = itemRepository.findById(item.getId());

        assertThat(findItem).isEqualTo(item);
    }

    @Test
    void findAll() {
        Item item1 = new Item("item1", 100, 2000);
        Item item2 = new Item("item2", 300, 1000);
        itemRepository.save(item1);
        itemRepository.save(item2);
        List<Item> itemList = itemRepository.findAll();
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).contains(item1, item2);
    }

    @Test
    void update(){
        Item item = new Item("item", 100, 1000);
        itemRepository.save(item);
        Item paramItem = new Item("paramItem", 2000, 3000);
        itemRepository.update(item.getId(), paramItem);

        assertThat(item.getItemName()).isEqualTo(paramItem.getItemName());
        assertThat(item.getPrice()).isEqualTo(paramItem.getPrice());
        assertThat(item.getQuantity()).isEqualTo(paramItem.getQuantity());

    }
}