package com.samandar.uis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/en")
public class EnController {
  private static final String EN_PREFIX = "en/";

  @GetMapping
  public String index(Model model) {
    return EN_PREFIX + "index";
  }

  @GetMapping("/products")
  public String products(Model model) {
    return EN_PREFIX + "products";
  }

  @GetMapping("/blog_detail")
  public String blog_detail() {
    return EN_PREFIX + "blog_detail";
  }

  @GetMapping("/docs")
  public String docs() {
    return EN_PREFIX + "docss";
  }

  @GetMapping("/about")
  public String about() {
    return EN_PREFIX + "about";
  }

  @GetMapping("/blog")
  public String blog() {
    return EN_PREFIX + "blog";
  }

  @GetMapping("/contact")
  public String contact() {
    return EN_PREFIX + "contact";
  }

  @GetMapping("/{brand}/{category}")
  public String getCatalog(@PathVariable String brand, @PathVariable String category) {
    return String.format("en/%s/%s", brand, category);
  }
}
