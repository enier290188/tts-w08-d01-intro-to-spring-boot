package com.tts.crud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.tts.crud.entity.UserEntity;
import com.tts.crud.repository.UserRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/crud/user")
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "")
    public String list(Model model) {
        List<UserEntity> users = new ArrayList<UserEntity>();
        this.userRepository.findAll().forEach(users::add);
        model.addAttribute("users", users);
        return "crud/user/list";
    }

    @GetMapping(value = "/create")
    public String createShow(Model model) {
        UserEntity userEntity = new UserEntity();
        model.addAttribute("error", false);
        model.addAttribute("user", userEntity);
        return "crud/user/create";
    }

    @PostMapping(value = "/create")
    public String create(@Valid UserEntity user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", false);
            model.addAttribute("user", user);
            return "crud/user/create";
        }
        try {
            userRepository.save(user);
            return "redirect:/crud/user/";
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "User is null or empty");
            return "crud/user/create";
        }
    }

    @GetMapping(value = "/{id}/detail")
    public String detailShow(@PathVariable("id") Long id, Model model) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "We cannot find a User with ID: " + id);
        } else {
            model.addAttribute("error", false);
            model.addAttribute("user", userEntity);
        }
        return "crud/user/detail";
    }

    @GetMapping(value = "/{id}/update")
    public String updateShow(@PathVariable("id") Long id, Model model) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "We cannot find a User with ID: " + id);
        } else {
            model.addAttribute("error", false);
            model.addAttribute("user", userEntity);
        }
        return "crud/user/update";
    }

    @PostMapping(value = "/{id}/update")
    public String update(@PathVariable("id") Long id, @Valid UserEntity user, BindingResult result, Model model) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "We cannot find a User with ID: " + id);
        } else {
            model.addAttribute("error", false);
            user.setId(userEntity.getId());
            user.setCreated(userEntity.getCreated());
            if (!result.hasErrors()) {
                userRepository.save(user);
            }
            model.addAttribute("user", user);
        }
        return "crud/user/update";
    }

    @GetMapping(value = "/{id}/delete")
    public String deleteShow(@PathVariable("id") Long id, Model model) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "We cannot find a User with ID: " + id);
        } else {
            model.addAttribute("error", false);
            model.addAttribute("user", userEntity);
        }
        return "crud/user/delete";
    }

    @PostMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") Long id, Model model) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null) {
            model.addAttribute("error", true);
            model.addAttribute("errorMessage", "We cannot find a User with ID: " + id);
            return "crud/user/delete";
        } else {
            userRepository.delete(userEntity);
            return "redirect:/crud/user/";
        }
    }
}
