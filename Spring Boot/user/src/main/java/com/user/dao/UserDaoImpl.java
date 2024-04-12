package com.user.dao;

import com.user.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao
{
    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate)
    {
        this.jdbcTemplate = jdbcTemplate;
    }

    String userInsertQuery = "INSERT INTO user (user_name,user_contact,user_email,user_password) VALUES (?,?,?,?)";
    String findUserByIdQuery = "SELECT * FROM user WHERE user_id = ?";
    String updateUserQuery = "UPDATE user SET user_name=?, user_contact=?, user_email=?, user_password=? WHERE user_id=?";
    String deleteUserQuery = "DELETE FROM user WHERE user_id=?";
    String findAllUsersQuery = "SELECT * FROM user";
    String findUserByEmailQuery = "SELECT * FROM user WHERE user_email=?";
    String findUserByContactQuery = "SELECT * FROM user WHERE user_contact=?";

    public void saveUser(User user)
    {
        jdbcTemplate.update(userInsertQuery,user.getUserName(),user.getUserContact(),user.getUserEmail(),user.getUserPassword());
    }

    public User findUserById(int userId) {
        return jdbcTemplate.queryForObject(findUserByIdQuery, new Object[]{userId}, (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUserName(rs.getString("user_name"));
            user.setUserContact(rs.getLong("user_contact"));
            user.setUserEmail(rs.getString("user_email"));
            user.setUserPassword(rs.getString("user_password"));
            return user;
        });
    }

    public void updateUser(User user,int userId)
    {
        jdbcTemplate.update(updateUserQuery,user.getUserName(),user.getUserContact(),user.getUserEmail(),user.getUserPassword(),userId);
    }

    public void deleteUser(int userId)
    {
        jdbcTemplate.update(deleteUserQuery,userId);
    }

    public List<User> findAllUsers() {
        return jdbcTemplate.query(findAllUsersQuery, (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUserName(rs.getString("user_name"));
            user.setUserContact(rs.getLong("user_contact"));
            user.setUserEmail(rs.getString("user_email"));
            user.setUserPassword(rs.getString("user_password"));
            return user;
        });
    }

    public User findUserByEmail(String userEmail) {
        return jdbcTemplate.queryForObject(findUserByEmailQuery, new Object[]{userEmail}, (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUserName(rs.getString("user_name"));
            user.setUserContact(rs.getLong("user_contact"));
            user.setUserEmail(rs.getString("user_email"));
            user.setUserPassword(rs.getString("user_password"));
            return user;
        });
    }
    public User findUserByContact(long userContact) {
        return jdbcTemplate.queryForObject(findUserByContactQuery, new Object[]{userContact}, (rs, rowNum) -> {
            User user = new User();
            user.setUserId(rs.getInt("user_id"));
            user.setUserName(rs.getString("user_name"));
            user.setUserContact(rs.getLong("user_contact"));
            user.setUserEmail(rs.getString("user_email"));
            user.setUserPassword(rs.getString("user_password"));
            return user;
        });
    }

}
