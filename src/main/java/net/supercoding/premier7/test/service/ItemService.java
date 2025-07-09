package net.supercoding.premier7.test.service;

import lombok.RequiredArgsConstructor;
import net.supercoding.premier7.test.dto.ItemDto.ItemCreateRequest;
import net.supercoding.premier7.test.dto.ItemDto.ItemCreateResponse;
import net.supercoding.premier7.test.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public ItemCreateResponse itemCreate(ItemCreateRequest itemCreateRequest) {

        return ItemCreateResponse.fromEntity(
                itemRepository.save(
                        ItemCreateRequest.toEntity(itemCreateRequest)
                )
        );
    }

}
