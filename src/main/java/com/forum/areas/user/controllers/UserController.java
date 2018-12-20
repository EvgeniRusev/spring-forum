package com.forum.areas.user.controllers;

import com.forum.areas.user.models.binding.UserRegisterBindingModel;
import com.forum.areas.user.models.service.UserServiceModel;
import com.forum.abstractions.controller.BaseController;
import com.forum.areas.user.models.view.UserViewModel;
import com.forum.areas.user.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserController extends BaseController {

    private final UserService userService;

    private final ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute UserRegisterBindingModel userRegisterBindingModel) {
        return super.view("views/users/register", "Register");
    }


    @PostMapping("/register")
    public ModelAndView registerConfirm(@Valid @ModelAttribute UserRegisterBindingModel userRegisterBindingModel,
                                        BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return super.view("views/users/register", "Register");
        }

        UserServiceModel userServiceModel = this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        this.userService.createUser(userServiceModel);
        return super.redirect("/login");
    }

    @GetMapping("/users/{username}")
    public ModelAndView userProfile(@PathVariable String username) {
        UserServiceModel userServiceModel = this.userService.findByUsername(username);
        UserViewModel userViewModel = this.modelMapper.map(userServiceModel, UserViewModel.class);
        return super.view("/views/users/profile", userViewModel);
    }

    @GetMapping("/login")
    public ModelAndView login(String error, ModelAndView mav) {
        mav.addObject("viewName", "/views/users/login");
        mav.setViewName("layout");
        if (error != null) {
            mav.addObject("error", "Wrong username or password");
        }
        return mav;
    }
}