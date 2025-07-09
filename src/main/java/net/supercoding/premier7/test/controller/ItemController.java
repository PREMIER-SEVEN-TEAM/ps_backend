package net.supercoding.premier7.test.controller;

import lombok.RequiredArgsConstructor;
import net.supercoding.premier7.test.dto.ItemDto.ItemCreateRequest;
import net.supercoding.premier7.test.dto.ItemDto.ItemCreateResponse;
import net.supercoding.premier7.test.service.ItemService;
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
    public ItemCreateResponse itemCreate(
            @RequestBody ItemCreateRequest itemCreateRequest
    ){
        return itemService.itemCreate(itemCreateRequest);
    }

}
