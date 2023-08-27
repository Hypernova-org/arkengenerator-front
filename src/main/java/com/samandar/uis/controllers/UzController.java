package com.samandar.uis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/uz")
public class UzController {
  private static final String UZ_PREFIX = "uz/";

  @GetMapping
  public String index(Model model) {
    return UZ_PREFIX + "index";
  }

  @GetMapping("/products")
  public String products(Model model) {
    return UZ_PREFIX + "products";
  }

  @GetMapping("/docs")
  public String docs() {
    return UZ_PREFIX + "docss";
  }

  @GetMapping("/about")
  public String about() {
    return UZ_PREFIX + "about";
  }

  @GetMapping("/blog")
  public String blog() {
    return UZ_PREFIX + "blogs";
  }

  @GetMapping("/contact")
  public String contact() {
    return UZ_PREFIX + "contact";
  }

  @GetMapping("/{brand}/{category}")
  public String getCatalog(@PathVariable String brand, @PathVariable String category) {
    return String.format("uz/%s/%s", brand, category);
  }
}
