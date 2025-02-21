package com.blindbox.service;

import com.blindbox.model.Role;
import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getAllRoles();

    Optional<Role> getRoleById(Integer id);

    Role createRole(Role role);

    Role updateRole(Integer id, Role role);

    void deleteRole(Integer id);
}
