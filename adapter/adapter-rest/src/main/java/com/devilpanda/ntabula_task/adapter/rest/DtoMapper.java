package com.devilpanda.ntabula_task.adapter.rest;

import com.devilpanda.ntabula_task.domain.Epic;
import org.modelmapper.ModelMapper;

public class DtoMapper {
    private final ModelMapper mapper;

    public DtoMapper() {
        mapper = new ModelMapper();
        mapper.typeMap(Epic.class, EpicDto.class)
        .addMapping(Epic::getUuid, EpicDto::setUuid);
    }

    public EpicDto mapDtoFromEpic(Epic source) {
        return mapper.map(source, EpicDto.class);
    }
}
