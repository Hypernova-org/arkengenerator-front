package com.samandar.uis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ch.qos.logback.core.model.Model;

@Controller
@RequestMapping("/tr")
public class TrController {
  private static final String TR_PREFIX = "tr/";

  @GetMapping
  public String index(Model model) {
    return TR_PREFIX + "index";
  }

  @GetMapping("/products")
  public String products(Model model) {
    return TR_PREFIX + "products";
  }

  @GetMapping("/docs")
  public String docs() {
    return TR_PREFIX + "docs";
  }

  @GetMapping("/about")
  public String about() {
    return TR_PREFIX + "about";
  }

  @GetMapping("/blog")
  public String blog() {
    return TR_PREFIX + "blog";
  }

  @GetMapping("/contact")
  public String contact() {
    return TR_PREFIX + "contact";
  }

  @GetMapping("/{brand}/{category}")
  public String getCatalog(@PathVariable String brand, @PathVariable String category) {
    return String.format("tr/%s/%s", brand, category);
  }
}
