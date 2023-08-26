package codegym.controller;

import codegym.entity.User;
import codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/index")
    public String index(Model model) {
        List<User> users = userService.getListUser();
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
    public String create(@ModelAttribute User user, @RequestParam String action, RedirectAttributes redirectAttributes) {
        userService.saveOrEdit(user);
        if ("create".equals(action)) {
            redirectAttributes.addFlashAttribute("message", "Thêm mới thành công");
        } else if ("edit".equals(action)) {
            redirectAttributes.addFlashAttribute("message", "Update thành công");
        }

        return "redirect:/user/index";
    }

    @GetMapping("/edit/{id}")
    public String displayEdit(@PathVariable String id ,Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        model.addAttribute("action", "edit");
        return "user/create";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable String id) {
        userService.deleteUser(userService.getUser(id));
        return "redirect:/user/index";
    }

    @GetMapping("/detail/{id}")
    public String displayDetail(@PathVariable String id ,Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user/detail";
    }
}
