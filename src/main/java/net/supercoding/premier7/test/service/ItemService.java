package net.supercoding.premier7.test.service;

import lombok.RequiredArgsConstructor;
import net.supercoding.premier7.test.dto.ItemDto.ItemCreateRequest;
import net.supercoding.premier7.test.dto.ItemDto.ItemCreateResponse;
import net.supercoding.premier7.test.entity.ItemEntity;
import net.supercoding.premier7.test.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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

    @Transactional
    public String itemDelete(
            Long itemPk
    ) {
        ItemEntity itemEntity = itemRepository.findById(itemPk)
                .orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "아이템을 찾을 수 없습니다."));

        itemRepository.delete(itemEntity);
        return itemPk + " 삭제 완료되었습니다.";
    }



}
