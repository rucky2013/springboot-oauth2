package com.itibo.aggregator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by karpukdm on 10/7/16.
 */
@Controller
public class GithubController {

    @RequestMapping(value = "/login/github", method = RequestMethod.GET)
    public String helloGithub(Model model) {

            return "redirect:/connect/github";
    }
}
