package net.supercoding.premier7.domain.test.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.supercoding.premier7.domain.test.entity.ItemEntity;

public class ItemDto {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemCreateRequest {

        private String itemName;

        public static ItemEntity toEntity(
                ItemDto.ItemCreateRequest itemCreateRequest
        ){
            return ItemEntity.builder()
                    .itemName(itemCreateRequest.getItemName())
                    .build();
        }
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ItemResponse {

        private Long itemPk;

        public static ItemResponse fromEntity(ItemEntity itemEntity) {

            return ItemResponse.builder()
                    .itemPk(itemEntity.getItemPk())
                    .build();
        }
    }
}

