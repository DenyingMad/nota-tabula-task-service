package com.devilpanda.task.adapter.rest;

import com.devilpanda.task.adapter.rest.dto.*;
import com.devilpanda.task.domain.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@Component
public class DtoMapper {
    private final ModelMapper mapper;

    public DtoMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.typeMap(Project.class, ProjectDto.class)
                .addMapping(Project::getUuid, ProjectDto::setProjectId)
                .addMapping(Project::getName, ProjectDto::setProjectName)
                .addMapping(Project::getDescription, ProjectDto::setProjectDescription);
        mapper.typeMap(Epic.class, EpicDto.class)
                .addMapping(Epic::getUuid, EpicDto::setEpicId)
                .setPostConverter(epicDtoPostConverter());
        mapper.typeMap(Member.class, MemberDto.class);
        mapper.typeMap(TaskList.class, TaskListDto.class)
                .addMapping(TaskList::getId, TaskListDto::setTaskListId)
                .addMapping(TaskList::getName, TaskListDto::setTaskListName);
        mapper.typeMap(Task.class, TaskDto.class)
                .addMapping(Task::getUuid, TaskDto::setTaskId)
                .addMapping(Task::getName, TaskDto::setTaskName)
                .addMapping(Task::getDescription, TaskDto::setTaskDescription)
                .setPostConverter(taskDtoPostConverter());
    }

    public ProjectDto mapDtoFromProject(Project source) {
        return mapper.map(source, ProjectDto.class);
    }

    public EpicDto mapDtoFromEpic(Epic source) {
        return mapper.map(source, EpicDto.class);
    }

    public TaskListDto mapDtoFromTaskList(TaskList source) {
        return mapper.map(source, TaskListDto.class);
    }

    public TaskDto mapDtoFromTask(Task source) {
        return mapper.map(source, TaskDto.class);
    }

    // =-----------------------------------------------------
    // Implementation
    // =-----------------------------------------------------

    private Converter<Epic, EpicDto> epicDtoPostConverter() {
        return context -> {
            Epic source = context.getSource();
            EpicDto destination = context.getDestination();
            DetailsDto detailsDto = new DetailsDto();

            detailsDto.setEpicName(source.getName());
            detailsDto.setEpicDescription(source.getDescription());

            Set<MemberDto> memberDtos = new HashSet<>();
            if(source.getTeams() != null) {
                source.getTeams().forEach(team -> team.getMembers()
                        .forEach(teamMember -> {
                            String login = teamMember.getMember().getLogin();
                            String role = teamMember.getRole().toString();
                            memberDtos.add(new MemberDto(login, role));
                        }));
            }
            detailsDto.setMembers(new ArrayList<>(memberDtos));

            Set<TaskList> taskListSet = source.getTaskLists();
            if (taskListSet != null) {
                Integer taskListCount = taskListSet.size();
                Integer taskCount = taskListSet.stream()
                        .map(TaskList::getTasks)
                        .filter(Objects::nonNull)
                        .mapToInt(Set::size)
                        .sum();

                detailsDto.setTotalTaskList(taskListCount);
                detailsDto.setTotalTasks(taskCount);

                destination.setTaskLists(taskListSet.stream()
                        .map(taskList -> mapper.map(taskList, TaskListDto.class))
                        .collect(toList()));
            } else {
                detailsDto.setEpicDescription("");
                detailsDto.setTotalTasks(0);
                detailsDto.setTotalTaskList(0);

                destination.setTaskLists(new ArrayList<>());
            }

            destination.setDetails(detailsDto);

            return destination;
        };
    }

    private Converter<Task, TaskDto> taskDtoPostConverter() {
        return context -> {
            Task source = context.getSource();
            TaskDto destination = context.getDestination();

            MemberDto assigned = mapper.map(source.getAssigned(), MemberDto.class);
            destination.setAssigned(assigned);

            return destination;
        };
    }
}
