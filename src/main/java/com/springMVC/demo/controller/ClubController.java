package com.springMVC.demo.controller;

import com.springMVC.demo.repository.ClubRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClubController {

    private final ClubRepository clubRepository;

    public ClubController(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @GetMapping("/clubs")
    public String listClubs(Model model) {

        model.addAttribute("clubs", clubRepository.findAll());

        return "clubs";
    }
}