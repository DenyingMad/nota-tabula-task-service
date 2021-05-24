package com.devilpanda.task.app.api;

import java.util.List;

public interface UsersService {
    List<String> getOrganizationsNames(String userLogin);
}
