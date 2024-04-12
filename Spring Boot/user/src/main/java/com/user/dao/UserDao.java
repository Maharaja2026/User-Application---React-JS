package com.user.dao;

import com.user.entity.User;

import java.util.List;

public interface UserDao
{
    public void saveUser(User user);
    public User findUserById(int userId);
    public void updateUser(User user,int userId);
    public void deleteUser(int userId);
    public List<User> findAllUsers();
    public User findUserByEmail(String userEmail);
    public User findUserByContact(long userContact);

}
