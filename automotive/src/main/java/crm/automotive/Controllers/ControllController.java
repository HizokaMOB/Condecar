package crm.automotive.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ch.qos.logback.core.model.Model;

@Controller
@SessionAttributes("users")
public class ControllController {
    @GetMapping("/Control")
    public String listar(Model model){

        return "Control";
    }
}
