package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class HelloController {

    // CHAPTER 13 EXERCISES (cleaned up the code from previous exercises)

    // Responds to /hello?name=<user form input>
    @RequestMapping(value = "hello", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody - delete now that we're returning a template
    public String hello (@RequestParam String name, Model model){
        String theGreeting = "Hello, " + name + "!";
        // in order to hand the greeting to the template it must be put inside the model object
        model.addAttribute("greeting", theGreeting); // attribute name is literally just the name
        return "hello";
    }

    // Responds to /hello/<user form input>
    @GetMapping("hello/{name}")
//    @ResponseBody
    public String helloAgain(@PathVariable String name, Model model){
        // same behavior as the method above but used the alternative shorter syntax here
        model.addAttribute("greeting", "Hello, " + name + "!"); // name is the name of attribute referenced in template
        return "hello";
    }

    // Responds to /form
    @GetMapping("form")
    // we don't want to use @ResponseBody here because we want springboot to look for our template
    public String helloForm(){
        // tells springboot to look for the form template
        return "form";
    }

    // Responds to /hello-names
    // create a handler method that will give the template a list of names
    @GetMapping("hello-names")
    public String helloNames(Model model){
        List<String> names = new ArrayList<>();
        // add names
        names.add("LaunchCode");
        names.add("Julian");
        names.add("Alex");
        names.add("Christin");
        names.add("Sophia");
        // need model
        model.addAttribute("names", names);
        return "hello-list";
    }


//    @RequestMapping(value="hello", method=RequestMethod.POST)
//    @ResponseBody
//    public String helloPost(@RequestParam String name, @RequestParam String language){
//        if (name == null) {
//            name = "World";
//        }
//        return createMessage(name, language); // method is coded below
//    }
//
//    // create method to handle input and reply
//    public static String createMessage(String n, String l){
//        // declare empty greeting
//        String greeting = "";
//
//        if (l.equals("En")) {
//            greeting = "Hello";
//        } else if (l.equals("Fr")) {
//            greeting = "Bonjour";
//        } else if (l.equals("Es")) {
//            greeting = "Hola";
//        } else if (l.equals("Ru")) {
//            greeting = "Привет";
//        } else if (l.equals("Ua")) {
//            greeting = "Привіт";
//        }
//
//        return greeting + ", " + n + "!";
//    }


}

