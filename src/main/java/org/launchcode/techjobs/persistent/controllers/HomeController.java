package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {
    @Autowired
    private EmployerRepository employerRepository;
    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private JobRepository jobRepository;
    @RequestMapping("")
    public String index(Model model) {

        model.addAttribute("title", "My Jobs");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("jobs", jobRepository.findAll());
        return "index";
    }

    @GetMapping("add")
    public String displayAddJobForm(Model model) {
        model.addAttribute("title", "Add Job");
        model.addAttribute("employers", employerRepository.findAll());
        model.addAttribute("skills", skillRepository.findAll());
        model.addAttribute(new Job());
        return "add";
    }

    @PostMapping("add")
    public String processAddJobForm(@ModelAttribute @Valid Job newJob,
                                       Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {
        Optional<Employer> results = employerRepository.findById(employerId);
        if (errors.hasErrors() || !results.isPresent()) {
            model.addAttribute("title", "Add Job");

            return "add";
        }

        newJob.setEmployer(results.get());
        List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
        newJob.setSkills(skillObjs);
        System.out.println(newJob);
        jobRepository.save(newJob);

        return "redirect:";
    }

    @GetMapping("view/{jobId}")
    public String displayViewJob(Model model, @PathVariable(required = false)  Integer jobId) {
        Optional id = jobRepository.findById(jobId);
        if (id.isPresent()) {
            Job jobs = (Job) id.get();
            model.addAttribute("jobs",jobs );
            return "view";
        } else {
            return "redirect:../";
        }

//        if (jobId == null) {
//            model.addAttribute("title", "All Events");
//            model.addAttribute("events", jobRepository.findAll());
//        } else {
//            Optional<Job> result = jobRepository.findById(jobId);
//            if (result.isEmpty()) {
//                model.addAttribute("title", "Invalid Category ID: " + jobId);
//            } else {
//                Job category = result.get();
//                model.addAttribute("title", "Events in category: " + category.getName());
//                model.addAttribute("events", category.getId());
//
//            }
//        }
//        return "view";
    }


}
