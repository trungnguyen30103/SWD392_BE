package com.blindbox.service;

import com.blindbox.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Integer id);
    Role createRole(Role role);
    Role updateRole(Integer id, Role role);
    boolean deleteRole(Integer id);
}
