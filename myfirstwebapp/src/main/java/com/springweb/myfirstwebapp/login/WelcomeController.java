package com.springweb.myfirstwebapp.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {

    //http://localhost:8080/login?name=joao
    //Adding a parameter in the url
    //use @ModelMap to use the variables in the jsp html as in as javascript file ${}
//    @RequestMapping("login")
//    public String loginJsp(@RequestParam String name, ModelMap model){
//        String testando = "ALALLALA";
//        model.put("name", name); //the first parameter is the name to use in the jsp file and the second is the value
//        model.put("teste", testando);
//        model.put("numero", 123);
//        System.out.println(name);
//        return "login";
//    }
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String welcomePage(ModelMap model){
        model.put("name", getLoggedinUsername());
        return "welcome";
    }

    private String getLoggedinUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

}
