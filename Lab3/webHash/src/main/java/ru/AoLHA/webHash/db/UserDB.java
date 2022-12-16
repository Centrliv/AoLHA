package ru.AoLHA.webHash.db;

import ru.AoLHA.webHash.entity.User;
import ru.AoLHA.webHash.service.CryptoService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

public class UserDB {
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
    public static boolean checkUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String login = user.getLogin();
        String password = user.getPass();
        if (users.containsKey(login)) {
            User chUser = users.get(login);
            return CryptoService.validatePassword(password, chUser.getPass());
        }
        else
            return false;
    }
}
