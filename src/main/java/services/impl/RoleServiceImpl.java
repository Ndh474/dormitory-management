package services.impl;

import dao.*;
import dao.impl.*;
import entity.*;
import services.*;

import java.util.*;

public class RoleServiceImpl implements RoleService {
    private RoleDAO roleDao = new RoleDAOImpl();

    @Override
    public List<Role> getAll() {
        return roleDao.getAll();
    }

    @Override
    public Role getById(int roleId) {
        return roleDao.getById(roleId);
    }

    @Override
    public int getRoleId(String roleName) {
        List<Role> roles = roleDao.getAll();
        for (Role r : roles) {
            if (r.getRoleName().equals(roleName)) {
                return r.getRoleID();
            }
        }
        return -1;
    }

    @Override
    public int add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public int update(Role role) {
        return roleDao.update(role);
    }

    @Override
    public Role getByRoleName(String roleName) {
        return roleDao.getByRoleName(roleName);
    }

    public static void main(String[] args) {
        RoleService roleService = new RoleServiceImpl();
        List<Role> roles = roleService.getAll();
        for (Role r : roles) {
            System.out.println(r);
        }
    }
}
