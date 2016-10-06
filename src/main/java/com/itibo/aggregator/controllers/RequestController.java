package com.itibo.aggregator.controllers;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.PagedList;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.linkedin.api.LinkedIn;
import org.springframework.social.twitter.api.CursoredList;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class RequestController {

    private Twitter twitter;
    private Facebook facebook;
    private LinkedIn linkedIn;

    private ConnectionRepository connectionRepository;

    @Inject
    public RequestController(Twitter twitter, Facebook facebook, LinkedIn linkedIn, ConnectionRepository connectionRepository) {
        this.twitter = twitter;
        this.facebook = facebook;
        this.linkedIn = linkedIn;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(value = "/login/facebook",method=RequestMethod.GET)
    public String helloTwitter(Model model) {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            return "redirect:/connect/facebook";
        }

       // model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());
        PagedList<Post> feed = facebook.feedOperations().getFeed();
        model.addAttribute("feed", feed);
        return "helloFacebook";
    }

    @RequestMapping(value = "/login/twitter",method=RequestMethod.GET)
    public String helloFacebook(Model model) {
        if (connectionRepository.findPrimaryConnection(Twitter.class) == null) {
            return "redirect:/connect/twitter";
        }

        model.addAttribute(twitter.userOperations().getUserProfile());
        CursoredList<TwitterProfile> friends = twitter.friendOperations().getFriends();
        model.addAttribute("friends", friends);
        return "helloTwitter";
    }

    @RequestMapping(value = "/login/linkedin",method=RequestMethod.GET)
    public String helloLinkedIn(Model model) {
        if (connectionRepository.findPrimaryConnection(LinkedIn.class) == null) {
            return "redirect:/connect/linkedin";
        }

        model.addAttribute("profile", linkedIn.profileOperations().getUserProfile().getFirstName());
        return "helloLinkedIn";
    }

}
