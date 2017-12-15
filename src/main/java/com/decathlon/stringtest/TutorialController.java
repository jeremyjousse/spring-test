package com.decathlon.stringtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping(path = "/tutorials")
public class TutorialController {
    @Autowired
    private TutorialRepository tutorialRepository;

    @Autowired
    private YAMLConfig myConfig;

    @GetMapping()
    public String tutorialsIndex (Model model) {
        System.out.println("using environment: " + myConfig.getEnvironment());
        System.out.println("name: " + myConfig.getName());
        model.addAttribute("tutorials", tutorialRepository.findAll());
        return "tutorials/index";
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Tutorial> getAllTutorials () {
        return tutorialRepository.findAll();

    }

    @GetMapping(path = "/add")
    public String addTutorialForm(Model model) {
        model.addAttribute("tutorial", new Tutorial());
        return "tutorials/add";
    }


    @GetMapping(path = "/edit", params = { "id" })
    public String editTutorialForm(@RequestParam("id") int id, Model model) {
        model.addAttribute("tutorial", tutorialRepository.findById(id));
        return "tutorials/edit";
    }

    @PostMapping()
    public RedirectView addTutorial(@ModelAttribute("tutorial") Tutorial t, RedirectAttributes attributes) {
        tutorialRepository.save(t);
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        return new RedirectView("/tutorials");
    }


    @PostMapping(path = "/update")
    public RedirectView updateTutorial(@ModelAttribute("tutorial") Tutorial t) {
        tutorialRepository.save(t);
        return new RedirectView("/tutorials");
    }

    @PostMapping(path = "/delete")
    public RedirectView deleteTutorial(@RequestParam("id") int id, RedirectAttributes attributes) {
        tutorialRepository.delete(id);
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        return new RedirectView("/tutorials");
    }
}
