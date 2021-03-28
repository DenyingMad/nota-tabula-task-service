package com.devilpanda.ntabula_task.adapter.rest;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Участники эпика", parent = EpicDto.class)
public class MemberDto {
    private String name;
    private String role;
}
