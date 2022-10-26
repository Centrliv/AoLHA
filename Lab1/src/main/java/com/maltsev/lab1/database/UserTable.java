package com.maltsev.lab1.database;

import com.maltsev.lab1.entity.User;

import java.util.HashMap;
import java.util.Map;

public class UserTable {
    private static Map<String, User> users = new HashMap<>();

    public static boolean addUser(User user)
    {
        String login = user.getLogin();
        if (!users.containsKey(login))
        {
            users.put(user.getLogin(), user);
            return true;
        }
        else
            return false;
    }
    public static boolean checkUser(User user)
    {
        String login = user.getLogin();
        String password = user.getPass();
        if (users.containsKey(login))
        {
            User chUser = users.get(login);
            if (chUser.getPass().equals(password))
                return true;
            else
                return false;
        }
        else
            return false;
    }
}
