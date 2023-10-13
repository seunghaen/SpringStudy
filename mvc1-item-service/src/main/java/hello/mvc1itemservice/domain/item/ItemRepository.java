package hello.mvc1itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static Map<Long, Item> store = new HashMap<>();
    private static Long itemId = 0L;

    public void save(Item item){
        item.setId(++itemId);
        store.put(itemId, item);
    }

    public Item findById(Long itemId) {
        return store.get(itemId);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item paramItem) {
        Item findItem = store.get(itemId);
        findItem.setItemName(paramItem.getItemName());
        findItem.setPrice(paramItem.getPrice());
        findItem.setQuantity(paramItem.getQuantity());
    }

    public void clear(){
        store.clear();
    }
}
