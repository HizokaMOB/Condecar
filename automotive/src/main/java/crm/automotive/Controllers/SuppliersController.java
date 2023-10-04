package crm.automotive.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import crm.automotive.Models.Dao.ISuppliersDao;
import crm.automotive.Models.Entity.Suppliers;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("suppliers")
public class SuppliersController {

    @Autowired
    private ISuppliersDao suppliersDao;

    @GetMapping("/SupplierRegistration")
    public String list(Model model) {

        model.addAttribute("title", "Suppliers list");
        model.addAttribute("suppliers", suppliersDao.findAll());

        return "SupplierRegistration";
    }

    @GetMapping("/registersuppliers")
    public String crear(Model model) {
        Suppliers suppliers = new Suppliers();

        model.addAttribute("suppliers", suppliers);
        model.addAttribute("title", "Register");
        return "registersuppliers";
    }

    @PostMapping("/registersuppliers")
    public String log(@Valid Suppliers suppliers, BindingResult bindingResult, SessionStatus status) {

        suppliersDao.save(suppliers);
        status.setComplete();

        return "redirect:/SupplierRegistration";
    }

    @GetMapping("/EditSupplier")
    public String create(Model model) {

        Suppliers suppliers = new Suppliers();

        model.addAttribute("title", "Edit suppliers");
        model.addAttribute("suppliers", suppliers);

        return "EditSupplier";
    }

    @PostMapping("/EditSupplier")
    public String save(@Valid Suppliers suppliers, BindingResult bindingResult, SessionStatus status) {

        if (bindingResult.hasErrors()) {
            return "EditSupplier";
        }
        suppliersDao.save(suppliers);
        status.setComplete();
        return "redirect:SupplierRegistration";
    }

    @GetMapping("/EditSupplier/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model) {

        Suppliers suppliers = null;

        if (id > 0) {
            suppliers = suppliersDao.findOne(id);
        } else {
            return "redirect:/SupplierRegistration";
        }
        model.addAttribute("title", "Edit");
        model.addAttribute("suppliers", suppliers);

        return "EditSupplier";
    }

    @GetMapping("/deletesupplier/{id}")
    public String delete(@PathVariable Long id) {
        if (id > 0)

            suppliersDao.delete(id);

        return "redirect:/SupplierRegistration";
    }

}
