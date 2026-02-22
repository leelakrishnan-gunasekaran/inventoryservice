package com.application.InventoryService.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UIController {

    @GetMapping("/")
    public String forward() {
        return "forward:/index.html";
    }
}