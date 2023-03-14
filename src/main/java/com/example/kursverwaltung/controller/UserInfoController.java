package com.example.kursverwaltung.controller;

import com.example.kursverwaltung.domain.Person;
import com.example.kursverwaltung.service.UserInfoUserDetailsService;
import com.example.kursverwaltung.domain.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoUserDetailsService serviceInfoUser;

    @GetMapping("/newUser")
    public String addNewUser(Model model) {
        model.addAttribute("user", new UserInfo());
        return "newUser";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") @Valid UserInfo userInfo, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            // Behandeln Sie hier die Validierungsfehler.
            //bindingResult.rejectValue("username","invalide","User muss großer als 6 Zeichen sein");
            return "newUser";
        }
        List<UserInfo> listUser = serviceInfoUser.listAll();
        if(!listUser.isEmpty()) {
            for (UserInfo u : listUser) {
                boolean isSame = u.equals(userInfo);

                if (isSame) {
                    model.addAttribute("isSame", isSame);
                    model.addAttribute("error", "Der Benutzer existiert bereits.");
                    return "newUser";
                }
            }
        }
        serviceInfoUser.addUser(userInfo);
        return "redirect:/user/users";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/users")
    public String viewHomePageUser(Model model, String keyword) {
        List<UserInfo> listUsers = serviceInfoUser.listAll();
        if (keyword != null) {
            model.addAttribute("listUsers", serviceInfoUser.findByKeyword(keyword));
        } else {
            model.addAttribute("listUsers", listUsers);

        }
        return "users";
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping("/deleteUser/{id}")
    public String deleteUser( @PathVariable(name = "id") int id) {
        serviceInfoUser.delete(id);
        return "redirect:/user/users";
    }


}
