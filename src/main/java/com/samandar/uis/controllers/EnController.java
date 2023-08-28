package com.samandar.uis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.samandar.uis.models.Blog;
import com.samandar.uis.repo.BlogRepository;

import org.springframework.ui.Model;

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

  @Autowired
  private BlogRepository blogRepository;

  @GetMapping("/blog")
  public String blog(org.springframework.ui.Model model) {
    model.addAttribute("blogs", blogRepository.findAll());
    return EN_PREFIX + "blog";
  }

  @GetMapping("/blog/{id}")
  public String displayBlogId(@PathVariable String id, Model model) {
    Blog blog = blogRepository.findById(Long.parseLong(id))
        .orElseThrow(() -> new BlogNotFoundException(Long.parseLong(id)));
    model.addAttribute("blog", blog);
    return "en/blogDetails"; // Assuming "blog" is the name of the template to render
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException(Long id) {
      super("Blog not found with ID: " + id);
    }
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
