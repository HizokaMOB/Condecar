package crm.automotive.Controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.lowagie.text.DocumentException;

import crm.automotive.Models.Dao.ISalesDao;
import crm.automotive.Models.Entity.Sales;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("sales")
public class SalesController {
    
    @Autowired 
    private ISalesDao salesDao;

    @GetMapping("/SalesRegistration")
    public String list(Model model){
        
        model.addAttribute("title", "Sales list");
        model.addAttribute("sales",salesDao.findAll());

        return "SalesRegistration";
    }

    @GetMapping("/registersale")
    public String crear(Model model) {
        Sales sales = new Sales();

        model.addAttribute("sales", sales);
        model.addAttribute("title", "Register");
        return "registersale";
    }

    @PostMapping("/registersale")
    public String log(@Valid Sales sales, BindingResult bindingResult, SessionStatus status) {

        salesDao.save(sales);
        status.setComplete();

        return "redirect:/SalesRegistration";
    }

     @GetMapping("/editsale")
    public String create(Model model) {

        Sales sales = new Sales();

        model.addAttribute("title", "Edit sale");
        model.addAttribute("sales", sales);

        return "editsale";
    }

    @PostMapping("/editsale")
    public String save(@Valid Sales sales, BindingResult bindingResult, SessionStatus status) {
        
        if (bindingResult.hasErrors()) {
            return "editsale";
        }
        salesDao.save(sales);
        status.setComplete();
        return "redirect:SalesRegistration";
    }

    @GetMapping("/editsale/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model) {

        Sales sales = null;

        if (id > 0) {
            sales = salesDao.findOne(id);
        } else {
            return "redirect:/SalesRegistration";
        }
        model.addAttribute("title", "Edit");
        model.addAttribute("sales", sales);

        return "editsale";
    }

    @GetMapping("/deletesale/{id}")
    public String delete(@PathVariable Long id)
    {
        if(id>0)

            salesDao.delete(id);

        return "redirect:/SalesRegistration";
    }

    @GetMapping("/exportPDF")
    public void ExportSaleRegistration(HttpServletResponse response) throws DocumentException, IOException{
        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String actualDate = dateFormatter.format(new Date());

        String header = "Content-Disposition";
        String value = "attachment; filename = Sales_" +  actualDate + ".pdf";

        response.setHeader(header, value);

        List<Sales> sales = salesDao.findAll();

        SalesExporterPDF exporter = new SalesExporterPDF(sales);
        exporter.export(response);
    }

    
}
