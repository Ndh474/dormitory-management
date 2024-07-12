package services;

import entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    Role getById(int roleId);

    int getRoleId(String roleName);

    int add(Role role);

    int update(Role role);

    Role getByRoleName(String roleName);
}
