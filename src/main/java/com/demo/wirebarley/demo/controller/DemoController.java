package com.demo.wirebarley.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    /**
     * Index page
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    public String demoIndexPage() {
        return "index";
    }

}
