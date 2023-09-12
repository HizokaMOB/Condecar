package crm.automotive.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import crm.automotive.Models.Dao.IUsersDao;
import crm.automotive.Models.Entity.Users;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;


@Controller
@SessionAttributes("users")
public class LoginController 
{
    @Autowired //inyectar dependencias, campo, metodo, constructor
    private IUsersDao usersDao;

    @GetMapping("/login")
    public String login(Model model) 
    {
        Users user = new Users();

        model.addAttribute("user", user);
        
        return "login";
    }

    @PostMapping("/login")
    public String Validate(@NotEmpty Users user, BindingResult bindingResult, SessionStatus status){
        List<Users> lista = usersDao.findAll();

        if(!lista.isEmpty()){

            for(int i=0; i<lista.size(); i+=1){
                if(lista.get(i).getUsername().equals(user.getUsername()) && lista.get(i).getPass().equals(user.getPass()))
                    {
                        return "redirect:/Control";
                    }
                }
            }   
        
        else{
            System.out.println("Empty list!");
        }
        
        return "login";
    }
    
}
