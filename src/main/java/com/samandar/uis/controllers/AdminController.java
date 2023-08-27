package com.samandar.uis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
  private static final String ADMIN_PREFIX = "admin/";

  @GetMapping()
  public String index() {
    return ADMIN_PREFIX + "index";
  }
}
