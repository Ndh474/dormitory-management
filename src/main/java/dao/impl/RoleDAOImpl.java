package dao.impl;

import connection.*;
import dao.*;
import entity.*;
import mapper.RowMapper;
import mapper.RowMapperImpl;

import java.sql.*;
import java.util.*;

import static dao.statement.RoleSQLStatements.*;

public class RoleDAOImpl implements RoleDAO {
    MySQLConnection mySQLConnection = new MySQLConnection();
    RowMapper rowMapper = new RowMapperImpl();

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getAllRole);
                ResultSet rs = st.executeQuery()
        ) {
            while (rs.next()) {
                roles.add(rowMapper.roleMapper(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return roles;
    }

    @Override
    public Role getById(int roleId) {
        Role role = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getRoleById)
        ) {
            st.setInt(1, roleId);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) role = rowMapper.roleMapper(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return role;
    }

    @Override
    public int add(Role role) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(insertRole)
        ) {
            st.setString(1, role.getRoleName());
            result = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public int update(Role role) {
        int result = 0;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(updateRole)
        ) {
            st.setString(1, role.getRoleName());
            st.setInt(2, role.getRoleID());
            result = st.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Role getByRoleName(String roleName) {
        Role role = null;
        try (
                Connection connection = mySQLConnection.getConnection();
                PreparedStatement st = connection.prepareStatement(getByRoleName)
        ) {
            st.setString(1, roleName);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) role = rowMapper.roleMapper(rs);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return role;
    }

    public static void main(String[] args) {
        RoleDAO roleDAO = new RoleDAOImpl();
        System.out.println(roleDAO.getAll());
        System.out.println(roleDAO.getById(1));

    }
}
