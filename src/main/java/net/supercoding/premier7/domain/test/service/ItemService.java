package net.supercoding.premier7.domain.test.service;

import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.supercoding.premier7.domain.test.dto.ItemDto.ItemCreateRequest;
import net.supercoding.premier7.domain.test.dto.ItemDto.ItemResponse;
import net.supercoding.premier7.domain.test.entity.ItemEntity;
import net.supercoding.premier7.domain.test.repository.ItemRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public ItemResponse itemCreate(ItemCreateRequest itemCreateRequest) {

        return ItemResponse.fromEntity(
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

    @Transactional(readOnly = true)
    public List<ItemResponse> itemList() {

        List<ItemResponse> itemResponseList = new ArrayList<>();

        for(ItemEntity itemEntity : itemRepository.findAll()) {
            itemResponseList.add(ItemResponse.fromEntity(itemEntity));
        }

        return itemResponseList;
    }

}
