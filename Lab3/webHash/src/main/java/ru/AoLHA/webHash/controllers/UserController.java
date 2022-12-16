package ru.AoLHA.webHash.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.AoLHA.webHash.db.UserDB;
import ru.AoLHA.webHash.entity.User;
import ru.AoLHA.webHash.service.CryptoService;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@Controller
public class UserController {

	@GetMapping("/add-user")
	public String addUser(Model model) {
		return "add-user";
	}

	@PostMapping("/add-user")
	public String addUserPost(@RequestParam String login, @RequestParam String password, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = new User(login, CryptoService.generateStorngPasswordHash(password));
		boolean submitFlag = UserDB.addUser(user);
		if (!submitFlag)
			model.addAttribute("message", "User with this login already exists");
		else
			model.addAttribute("message", "Successful registration");
		return "add-user";
	}

	@GetMapping("/authorization")
	public String authorization(Model model) {
		return "authorization";
	}

	@PostMapping("/authorization")
	public String checkUserPost(@RequestParam String login, @RequestParam String password, Model model) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = new User(login, password);
		boolean submitFlag = UserDB.checkUser(user);
		if (!submitFlag)
			model.addAttribute("message", "Authorization failed");
		else
			model.addAttribute("message", "Successful authorization");
		return "authorization";
	}
}

