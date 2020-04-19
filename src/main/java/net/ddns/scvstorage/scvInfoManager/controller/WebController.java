package net.ddns.scvstorage.scvInfoManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/contentlist")
    public String selectContentList() {

        return "contentlist";
    }

    @GetMapping("/digitallist")
    public String selectDigitalContentList() {

        return "contentlist";
    }
}