package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("employers")
public class EmployerController {

    @Autowired
    private EmployerRepository employerRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("title", "All Employers");
        model.addAttribute("employers", employerRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddEmployerForm(Model model) {
        model.addAttribute(new Employer());
        return "employers/add";
    }

    @PostMapping("add")
    public String processAddEmployerForm(@ModelAttribute @Valid Employer newEmployer,
                                    Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute(new Employer());
            return "employers/add";
        }
        employerRepository.save(newEmployer);

        return "redirect:";
    }

    @GetMapping("view/{employerId}")
    public String displayViewEmployer(Model model, @PathVariable Integer employerId) {

        Optional optEmployer = employerRepository.findById(employerId);
        if (optEmployer.isPresent()) {
            Employer employer = (Employer) optEmployer.get();
            model.addAttribute("employer", employer);
            return "employers/view";
        } else {
            return "redirect:../";
        }
//        if (employerId == null) {
//            model.addAttribute("title", "All Events");
//            model.addAttribute("events", employerRepository.findAll());
//        } else {
//            Optional<Employer> result = employerRepository.findById(employerId);
//            if (result.isEmpty()) {
//                model.addAttribute("title", "Invalid Category ID: " + employerId);
//            } else {
//                Employer category = result.get();
//                model.addAttribute("title", "Events in category: " + category.getName());
//                model.addAttribute("events", category.getLocation());
//
//            }
//        }
//        return "view";
   }
}
