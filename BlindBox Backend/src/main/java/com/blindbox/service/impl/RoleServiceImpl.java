package com.blindbox.service.imlp;

import com.blindbox.model.Role;
import com.blindbox.repository.RoleRepository;
import com.blindbox.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Integer id, Role role) {
        Optional<Role> existing = roleRepository.findById(id);
        if (existing.isPresent()) {
            Role existingRole = existing.get();
            existingRole.setUser(role.getUser());
            existingRole.setRoleName(role.getRoleName());
            return roleRepository.save(existingRole);
        } else {
            return null;
        }
    }

    @Override
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }
}
