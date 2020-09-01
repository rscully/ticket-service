package com.rob.scully.samplespring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The type Index controller.
 */
@Controller
public class IndexController {

    /**
     * Index string.
     *
     * @return the string
     */
    @RequestMapping("/")
    public String index() {
        return "/docs/api-guide.html";
    }


}
