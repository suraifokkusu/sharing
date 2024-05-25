package com.thewhite.blank.api;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
public class CollectionDto<T> {

    private List<T> items;
    private long totalCount;

    public CollectionDto() {
        items = Collections.emptyList();
        totalCount = 0;
    }

    public CollectionDto(List<T> items) {
        this.items = items;
        this.totalCount = items.size();
    }

    public static <T> CollectionDto<T> empty() {
        return new CollectionDto<>();
    }
}
