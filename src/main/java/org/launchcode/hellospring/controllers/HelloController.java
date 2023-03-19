package org.launchcode.hellospring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// any controller we make needs a controller declaration
@Controller
@ResponseBody // if all methods use this annotation, we can put it at the top of the class and remove it from each individual method below
//@RequestMapping("hello") // now all methods below will live at path /hello
public class HelloController {

    //ROUTES THAT CREATE STATIC RESPONSES
    // need to add annotations to use method as a request handler
//    @GetMapping("hello") // specifies that this method should handle GET requests; parentheses handles request at path /hello
//    @ResponseBody // won't need once we are using templates
//    public String hello(){
//        return "Hello, Spring!";
//    }

    // try adding another method at another path
    @GetMapping("goodbye") // this will now live at path /hello/goodbye (after adding annotation at top of class)
//    @ResponseBody
    public String goodbye(){
        return "Goodbye, Spring!";
    }

    //ROUTES THAT CREATE DYNAMIC RESPONSES
    // routes that produce responses that will be different based on data that is part of the request

    // now lives at path hello/hello (after adding annotation at top of class)
    // create a handler that handles requests of the form /hello?name=LaunchCode
    // query parameter is called name
//    @GetMapping("hello") // removed so we could create a handler for post requests
//    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST}) //, value = "hello") - don't need this now (after adding annotation at top of class) // now it can handle both get and post requests at route hello
////    @ResponseBody
//    public String helloWithQueryParam(@RequestParam String name){ // the annotation tells spring to expect a query parameter called name
//        return "Hello, " + name + "!";
//    }

    // another way of creating get request handlers that accept data (dynamic)
    // handles requests of the form /hello/LaunchCode (path parameters)
//    @GetMapping("hello/{name}")
    @GetMapping("{name}") // don't need the hello/ part now that we added annotation at top of class
//    @ResponseBody
    public String helloWithPathParam(@PathVariable String name){ // this annotation says that this handler method is
        // looking for a request to /hello/name where the name can be anything
        return "Hello, " + name + "!";
    }

    // build the form
//    @GetMapping("form")
//    @ResponseBody
//    public String helloForm(){
//        return "<html>" +
//                "<body>" +
//                "<form action='hello'>" + // tells it to submit a request to /hello
//                "<input type='text' name='name'>" + // uses the input route from helloWithQueryParam
//                "<input type='submit' value='Greet me!'>" +
//                "</form>" +
//                "</body>" +
//                "</html>";
//    }

    // lives at /hello/form
    // submitting a post request
//    @GetMapping("form")
////    @ResponseBody
//    public String helloForm(){
//        return "<html>" +
//                "<body>" +
//                "<form action='hello' method='post'>" + // will now make a post request when submitted
//                "<input type='text' name='name'>" + // all handlers currently only accept get requests, so need to make a handler for post requests
//                "<input type='submit' value='Greet me!'>" +
//                "</form>" +
//                "</body>" +
//                "</html>";
//    }

    // CHAPTER 10 EXERCISES

    // TODO 1: Modify your HelloController class to display a form on a GET request that asks the user for both their name and the language they would like to be greeted in.

    @GetMapping("form")
    public String helloForm(){
        return "<html>" +
                "<body>" +
                "<form action='hello' method='post'>" +
                "<input type='text' name='name'>" +
                // add a dropdown for language
                "<select name='language'>" +
                "<option value=''>Choose a Language</option>" +
                "<option value='En'>English</option>" +
                "<option value='Fr'>French</option>" +
                "<option value='Es'>Spanish</option>" +
                "<option value='Ru'>Russian</option>" +
                "<option value='Ua'>Ukrainian</option>" +
                "</select>" +
                "<input type='submit' value='Greet me!'>" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    // TODO 2: When the user submits the form (via a POST request), they should be greeted in the selected language.

    @RequestMapping(value="hello", method=RequestMethod.POST)
    // don't need @ResponseBody because it's above the class
    public String helloPost(@RequestParam String name, @RequestParam String language){
        if (name == null) {
            name = "World";
        }
        return createMessage(name, language); // method is coded below
    }

    // create method to handle input and reply
    public static String createMessage(String n, String l){
        // declare empty greeting
        String greeting = "";

        if (l.equals("En")) {
            greeting = "Hello";
        } else if (l.equals("Fr")) {
            greeting = "Bonjour";
        } else if (l.equals("Es")) {
            greeting = "Hola";
        } else if (l.equals("Ru")) {
            greeting = "Привет";
        } else if (l.equals("Ua")) {
            greeting = "Привіт";
        }

        return greeting + ", " + n + "!";
    }


}
