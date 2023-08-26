package com.samandar.uis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/ru")
public class RuController {

  private static final String RU_PREFIX = "ru/";

  @GetMapping
  public String index(Model model) {
    return RU_PREFIX + "index";
  }

  @GetMapping("/products")
  public String products(Model model) {
    return RU_PREFIX + "products";
  }

  @GetMapping("/docs")
  public String docs() {
    return RU_PREFIX + "docs";
  }

  @GetMapping("/about")
  public String about() {
    return RU_PREFIX + "about";
  }

  @GetMapping("/blog")
  public String blog() {
    return RU_PREFIX + "blog";
  }

  @GetMapping("/contact")
  public String contact() {
    return RU_PREFIX + "contact";
  }

  @GetMapping("/{brand}/{category}")
  public String getCatalog(@PathVariable String brand, @PathVariable String category) {
    return String.format("ru/%s/%s", brand, category);
  }
}