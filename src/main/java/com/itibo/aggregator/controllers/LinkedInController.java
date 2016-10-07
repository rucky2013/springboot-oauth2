package com.itibo.aggregator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by karpukdm on 10/7/16.
 */
@Controller
public class LinkedInController {

    @Autowired
    private LinkedIn linkedIn;

    @Autowired
    private ConnectionRepository connectionRepository;

    @RequestMapping(value = "/login/linkedin", method = RequestMethod.GET)
    public String helloLinkedIn(Model model) {
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }

        model.addAttribute("profile", linkedIn.profileOperations().getUserProfile().getFirstName());
        return "helloLinkedIn";
    }

}
