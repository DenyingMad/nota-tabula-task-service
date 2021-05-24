package com.devilpanda.task.adapter.users;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "user-service")
public interface UsersFeignClient {
    @GetMapping()
    List<OrganizationDto> getOrganizationsByOwner(@RequestBody Object ownerName);
}
