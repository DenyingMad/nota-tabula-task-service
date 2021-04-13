package com.devilpanda.task.adapter.rest;

import com.devilpanda.task.domain.Epic;
import com.devilpanda.task.domain.Member;
import com.devilpanda.task.domain.Task;
import com.devilpanda.task.domain.TaskList;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        mapper.typeMap(Epic.class, EpicDto.class)
                .addMapping(Epic::getUuid, EpicDto::setEpicId)
                .setPostConverter(epicDtoPostConverter());
        mapper.typeMap(Member.class, MemberDto.class)
                .addMapping(Member::getLogin, MemberDto::setLogin)
                .addMapping(Member::getRole, MemberDto::setRole);
        mapper.typeMap(TaskList.class, TaskListDto.class)
                .addMapping(TaskList::getId, TaskListDto::setTaskListId)
                .addMapping(TaskList::getName, TaskListDto::setTaskListName)
                .setPostConverter(taskListDtoPostConverter());
        mapper.typeMap(Task.class, TaskDto.class)
                .addMapping(Task::getUuid, TaskDto::setTaskId)
                .addMapping(Task::getName, TaskDto::setTaskName)
                .addMapping(Task::getDescription, TaskDto::setTaskDescription)
                .addMapping(Task::getChecked, TaskDto::setChecked)
                .addMapping(Task::getPriority, TaskDto::setPriority)
                .setPostConverter(taskDtoPostConverter());
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

            Set<Member> members = source.getMembers();
            if (members != null) {
                detailsDto.setMembers(members.stream()
                        .map(member -> mapper.map(member, MemberDto.class))
                        .collect(toList()));
            }
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
                detailsDto.setEpicName("");
                detailsDto.setEpicDescription("");
                detailsDto.setMembers(new ArrayList<>());
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

            // todo здесь будет загрузка объекта Member assigned, пока отправляется только id исполнителя
            destination.setAssigned(source.getAssigned());

            return destination;
        };
    }

    private Converter<TaskList, TaskListDto> taskListDtoPostConverter() {
        return context -> {
            TaskList source = context.getSource();
            TaskListDto destination = context.getDestination();

            Set<Task> tasks = source.getTasks();
            if (tasks != null) {
                destination.setTasks(tasks.stream()
                        .map(task -> mapper.map(task, TaskDto.class))
                        .collect(toList()));
            } else {
                destination.setTasks(new ArrayList<>());
            }

            return destination;
        };
    }
}
