package dao;

import entity.Role;

import java.util.List;

public interface RoleDAO {
    List<Role> getAll();

    Role getById(int roleId);

    int add(Role role);

    int update(Role role);

    Role getByRoleName(String roleName);
}
