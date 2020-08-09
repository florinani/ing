package com.florinani.ing.controllers;

import com.florinani.ing.domain.SavingAccount;
import com.florinani.ing.domain.User;
import com.florinani.ing.repository.SavingAccountReporsitory;
import com.florinani.ing.repository.UserRepository;
import com.florinani.ing.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {

    private final UserRepository userRepository;

    private final SavingAccountReporsitory savingAccountReporsitory;

    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService, SavingAccountReporsitory savingAccountReporsitory) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.savingAccountReporsitory = savingAccountReporsitory;
    }

    @RequestMapping("/user")
    public String getUsers(Model model) {

        User user = userRepository.findByUserName(userService.getCurrentlyLoggedinUser());
        List<SavingAccount> savingAccountList = savingAccountReporsitory.findByUser(user);
        model.addAttribute("user", user);
        model.addAttribute("savingAccountList", savingAccountList);

        return "users/successfulLogin";

    }
}
