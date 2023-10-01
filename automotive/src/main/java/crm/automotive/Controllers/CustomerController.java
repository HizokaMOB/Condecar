package crm.automotive.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import crm.automotive.Models.Dao.IUsersDao;
import crm.automotive.Models.Entity.Users;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@SessionAttributes("user")
public class CustomerController {

    @Autowired
    private IUsersDao usersDao;

    @GetMapping("/Customerlist")
    public String list(Model model)
    {
        model.addAttribute("title", "Customers list");
        model.addAttribute("customers", usersDao.findAll());

        return "Customerlist";
    }


    @GetMapping("/Edit")
    public String create(Model model) {

        Users user = new Users();

        model.addAttribute("title", "Edit customer");
        model.addAttribute("customer", user);

        return "Edit";
    }

    @PostMapping("/Edit")
    public String save(@Valid Users user, BindingResult bindingResult, SessionStatus status) {
        
        if (bindingResult.hasErrors()) {
            return "Edit";
        }
        usersDao.save(user);
        status.setComplete();
        return "redirect:Customerlist";
    }

    @GetMapping("/Edit/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model) {

        Users user = null;

        if (id > 0) {
            user = usersDao.findOne(id);
        } else {
            return "redirect:/Customerlist";
        }
        model.addAttribute("title", "Edit");
        model.addAttribute("user", user);

        return "Edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        if(id>0)

            usersDao.delete(id);

        return "redirect:/Customerlist";
    }

}