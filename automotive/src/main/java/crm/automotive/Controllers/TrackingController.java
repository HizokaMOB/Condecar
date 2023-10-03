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

import crm.automotive.Models.Dao.ITrackingDao;
import crm.automotive.Models.Entity.Tracking;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("trackings")
public class TrackingController {
    
    @Autowired 
    private ITrackingDao trackingDao;

    @GetMapping("/SalesTracking")
    public String list(Model model){
        
        model.addAttribute("title", "Sales Tracking list");
        model.addAttribute("trackings",trackingDao.findAll());

        return "SalesTracking";
    }

    @GetMapping("/registertracking")
    public String crear(Model model) {
        Tracking tracking = new Tracking();

        model.addAttribute("trackings", tracking);
        model.addAttribute("title", "Register");
        return "registertracking";
    }

    @PostMapping("/registertracking")
    public String log(@Valid Tracking tracking, BindingResult bindingResult, SessionStatus status) {

        trackingDao.save(tracking);
        status.setComplete();

        return "redirect:/SalesTracking";
    }

     @GetMapping("/editsaletracking")
    public String create(Model model) {

        Tracking tracking = new Tracking();

        model.addAttribute("title", "Edit sale tracking");
        model.addAttribute("trackings", tracking);

        return "editsaletracking";
    }

    @PostMapping("/editsaletracking")
    public String save(@Valid Tracking tracking, BindingResult bindingResult, SessionStatus status) {
        
        if (bindingResult.hasErrors()) {
            return "editsaletracking";
        }
        trackingDao.save(tracking);
        status.setComplete();
        return "redirect:SalesTracking";
    }

    @GetMapping("/editsaletracking/{id}")
    public String edit(@PathVariable(value = "id") Long id, Model model) {

        Tracking tracking = null;

        if (id > 0) {
            tracking = trackingDao.findOne(id);
        } else {
            return "redirect:/SalesTracking";
        }
        model.addAttribute("title", "Edit");
        model.addAttribute("trackings", tracking);

        return "editsaletracking";
    }

    @GetMapping("/deletesaletracking/{id}")
    public String delete(@PathVariable Long id)
    {
        if(id>0)

            trackingDao.delete(id);

        return "redirect:/SalesTracking";
    }
}
