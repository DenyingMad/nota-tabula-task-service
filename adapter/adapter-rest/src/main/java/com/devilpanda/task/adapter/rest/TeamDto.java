package com.devilpanda.task.adapter.rest;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Данные команды")
public class TeamDto {
    private String name;
    private List<MemberDto> members;
}
