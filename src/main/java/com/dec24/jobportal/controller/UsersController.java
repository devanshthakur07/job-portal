package com.dec24.jobportal.controller;

import com.dec24.jobportal.entity.Users;
import com.dec24.jobportal.entity.UsersType;
import com.dec24.jobportal.services.UsersService;
import com.dec24.jobportal.services.UsersTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }

    @GetMapping("/register")
    public String register(Model model) {
        List<UsersType> usersTypes = usersTypeService.allUsers();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid Users user, Model model) {

        Optional<Users> optionalUsers = usersService.getUserByEmail(user.getEmail());
        if(optionalUsers.isPresent()) {
            model.addAttribute("error", "Email already exists, please login or register with another email");
            List<UsersType> usersTypes = usersTypeService.allUsers();
            model.addAttribute("getAllTypes", usersTypes);
            model.addAttribute("user", new Users());
            return "register";
        }

       usersService.addNew(user);
       return "dashboard";
    }
}
