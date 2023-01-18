package com.wiqrug.MyFirstWebApp.login;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


//So this value can be saved when we change pages
@SessionAttributes("name")
@Controller
public class WelcomeController {


    @RequestMapping(value = "/" , method = RequestMethod.GET)
    public String gotoWelcomePage (ModelMap model)
    {
        model.put("name",getLoggedinUsername());
        return "welcome";
    }


    private String getLoggedinUsername()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();

    }



}
