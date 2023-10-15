package hello.mvc1itemservice.web.basic;

import hello.mvc1itemservice.domain.item.Item;
import hello.mvc1itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.ExitMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {
    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addFormV1(
            @RequestParam String itemName,
            @RequestParam int price,
            @RequestParam int quantity,
            Model model
    ){
        Item item = new Item(itemName, price, quantity);
        itemRepository.save(item);
        model.addAttribute("item", item);
        return "basic/item";

    }

//    @PostMapping("/add")
    public String addFormV2(@ModelAttribute("item") Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addFormV3(Item item) {
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addFormV4(Item item){
        itemRepository.save(item);
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addFormV5(Item item, RedirectAttributes redirectAttributes){
        Item saved = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", saved.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping({"/{itemId}/edit"})
    public String editForm(@PathVariable Long itemId,Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";

    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute("item") Item itemParam){
        itemRepository.update(itemId,itemParam);
        return "redirect:/basic/items/{itemId}/";
    }

    @PostConstruct
    public void init(){
        Item itemA = new Item("itemA", 1000, 10);
        Item itemB = new Item("itemB", 3000, 10);
        itemRepository.save(itemA);
        itemRepository.save(itemB);
    }
}
