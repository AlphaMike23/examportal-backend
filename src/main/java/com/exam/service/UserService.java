package com.exam.service;

import java.util.Set;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {

    // Creating Users
    public User createUser(User user, Set<UserRole> userRoles) throws Exception;
    
    //Getting user by username
    
    public User getUser(String userName);
    
    //Deleting the user by userId
     public void deleteUser(Long userId);
}
