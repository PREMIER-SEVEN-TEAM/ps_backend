package net.supercoding.premier7.domain.test.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import net.supercoding.premier7.domain.test.dto.ItemDto.ItemCreateRequest;
import net.supercoding.premier7.domain.test.dto.ItemDto.ItemResponse;
import net.supercoding.premier7.domain.test.service.ItemService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("")
    public ItemResponse itemCreate(
            @RequestBody ItemCreateRequest itemCreateRequest
    ){
        return itemService.itemCreate(itemCreateRequest);
    }

    @DeleteMapping("/{itemPk}")
    public String itemDelete(
            @PathVariable("itemPk") Long itemPk
    ) {
        return itemService.itemDelete(itemPk);
    }

    @GetMapping("")
    public List<ItemResponse> itemList() {
        return itemService.itemList();
    }
}
