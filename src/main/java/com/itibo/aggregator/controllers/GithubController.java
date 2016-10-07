package com.itibo.aggregator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.github.api.GitHub;
import org.springframework.social.github.api.impl.GitHubTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by karpukdm on 10/7/16.
 */
@Controller
public class GithubController {

    private GitHub gitHub;

    @Autowired
    private ConnectionRepository connectionRepository;

    public GithubController(GitHub gitHub) {
        this.gitHub = gitHub;
    }

    public GithubController() {
        this.gitHub = new GitHubTemplate();
    }

    @RequestMapping(value = "/login/github", method = RequestMethod.GET)
    public String helloGithub(Model model) {

        if (!this.gitHub.isAuthorized()) {

            return "redirect:/connect/github";
        }
        return "helloGithub";
    }

    @RequestMapping(value = "/connect/github", method = RequestMethod.GET)
    public String connectGithub(Model model) {

        if (!this.gitHub.isAuthorized()) {
            return "/connect/githubConnect";
        }

        return "helloGithub";
    }

    @RequestMapping(value = "/connect/github", method = RequestMethod.POST)
    public String connectToGithub(Model model) {

        if (!this.gitHub.isAuthorized()) {
            return "/connect/githubConnect";
        }

        return "helloGithub";
    }
}
