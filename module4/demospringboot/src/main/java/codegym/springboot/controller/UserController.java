package codegym.springboot.controller;

import codegym.springboot.entity.User;
import codegym.springboot.service.iml.UserServiceImpl2;
import codegym.springboot.validator.AgeCustomValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    UserServiceImpl2 userService;

    @Autowired
    public UserController(UserServiceImpl2 userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String index(@RequestParam(required = false) String nameSearch,
            @RequestParam(required = false) String ageSearch, Model model) {
        List<User> users;
        if (nameSearch != null && !nameSearch.isEmpty()) {
            users = userService.getListUserByName(nameSearch);

        } else if(ageSearch != null && !ageSearch.isEmpty()) {
            users = userService.getListUserByAge(Integer.parseInt(ageSearch));
        } else {
            users = userService.getListUser();
        }

        model.addAttribute("users", users);
        model.addAttribute("user1", "Linh");
        model.addAttribute("user2", "Bao");
        return "user/index";
    }

    @GetMapping("/create")
    public String displayCreate(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("action", "create");
        return "user/create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute User user, BindingResult bindingResult, Model model) {
        new AgeCustomValidation().validate(user, bindingResult);
        if (bindingResult.hasFieldErrors()) {
            return "user/create";
        }
        userService.saveOrEdit(user);
        return "redirect:/user/index";
    }

    @GetMapping("/edit/{id}")
    public String displayEdit(@PathVariable int id ,Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("action", "edit");
        return "user/create";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        userService.deleteUser(userService.getUser(id));
        return "redirect:/user/index";
    }

    @GetMapping("/detail/{id}")
    public String displayDetail(@PathVariable("id") User user ,Model model) {
//        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user/detail";
    }
}
