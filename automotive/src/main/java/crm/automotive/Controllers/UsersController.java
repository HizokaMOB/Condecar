package crm.automotive.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import crm.automotive.Models.Dao.IUsersDao;
import crm.automotive.Models.Entity.Users;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("user")
public class UsersController {

    @Autowired
    private IUsersDao usersDao;

    @GetMapping("/registercustomer")
    public String crear(Model model) {
        Users user = new Users();

        model.addAttribute("user", user);
        model.addAttribute("title", "Register");
        return "registercustomer";
    }

    @PostMapping("/registercustomer")
    public String log(@Valid Users user, BindingResult bindingResult, SessionStatus status) {

        usersDao.save(user);
        status.setComplete();

        return "redirect:/login";
    }

}
