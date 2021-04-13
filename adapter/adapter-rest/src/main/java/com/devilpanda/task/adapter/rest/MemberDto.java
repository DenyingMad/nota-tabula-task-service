package com.devilpanda.task.adapter.rest;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Участник эпика", parent = EpicDto.class)
public class MemberDto {
    private String login;
    private String role;
}
