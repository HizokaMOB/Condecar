package crm.automotive.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import crm.automotive.Models.Dao.IEmployeeDao;
import crm.automotive.Models.Entity.Employee;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

@Controller
@SessionAttributes("employees")
public class LoginController {
    @Autowired // inyectar dependencias, campo, metodo, constructor
    private IEmployeeDao EmployeeDao;

    @GetMapping("/login")
    public String login(Model model) {
        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        return "login";
    }

    @PostMapping("/login")
    public String Validate(@NotEmpty Employee employee, BindingResult bindingResult, SessionStatus status) {
        List<Employee> lista = EmployeeDao.findAll();

        if (!lista.isEmpty()) {

            for (int i = 0; i < lista.size(); i += 1) {
                if (lista.get(i).getUsername().equals(employee.getUsername())
                        && lista.get(i).getPass().equals(employee.getPass())) {
                    return "redirect:/Control";
                }
            }
        }

        else {
            System.out.println("Empty list!");
        }

        return "redirect:login";
    }

    @GetMapping("/register")
    public String crear(Model model) {
        Employee employee = new Employee();

        model.addAttribute("employee", employee);
        model.addAttribute("title", "Register");
        return "register";
    }

    @PostMapping("/register")
    public String log(@Valid Employee employee, BindingResult bindingResult, SessionStatus status) {

        EmployeeDao.save(employee);
        status.setComplete();

        return "redirect:/login";
    }

    @GetMapping("/Control")
    public String listar(Model model){

        return "Control";
    }

}
