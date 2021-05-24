package com.devilpanda.task.adapter.users;

import com.devilpanda.task.app.api.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class UserServiceAdapter implements UsersService {
    private final UsersFeignClient usersFeignClient;

    @Override
    public List<String> getOrganizationsNames(String ownerId) {
        return usersFeignClient.getOrganizationsByOwner(ownerId).stream()
                .map(OrganizationDto::getOrganizationName)
                .collect(toList());
    }
}
