package com.devilpanda.task.adapter.rest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CollectionResponseDto<T> {
    private List<T> data;

    public CollectionResponseDto(List<T> data) {
        this.data = data;
    }
}
