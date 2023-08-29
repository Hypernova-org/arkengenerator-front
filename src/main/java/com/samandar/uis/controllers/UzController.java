package com.samandar.uis.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import org.springframework.http.HttpStatus;

import com.samandar.uis.models.Blog;
import com.samandar.uis.models.Sertificate;
import com.samandar.uis.repo.BlogRepository;
import com.samandar.uis.repo.SertificateRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/uz")
public class UzController {
  private static final String UZ_PREFIX = "uz/";

  @Autowired
  private SertificateRepository sertificateRepository;

  @GetMapping
  public String index(Model model) {
    return UZ_PREFIX + "index";
  }

  @GetMapping("/products")
  public String products(Model model) {
    return UZ_PREFIX + "products";
  }

  @GetMapping("/docs")
  public String docs(Model model) {
    Iterable<Sertificate> certi = sertificateRepository.findAll();
    model.addAttribute("sert", certi);
    return UZ_PREFIX + "docss";
  }

  @GetMapping("/about")
  public String about() {
    return UZ_PREFIX + "about";
  }

  @Autowired
  private BlogRepository blogRepository;

  @GetMapping("/blog")
  public String blog(org.springframework.ui.Model model) {
    Iterable<Blog> blogIterable = blogRepository.findAll();
    List<Blog> blogs = new ArrayList<>();
    blogIterable.forEach(blogs::add); // Convert Iterable to List
    Collections.reverse(blogs); // Reverse the list
    model.addAttribute("blogs", blogs);
    return UZ_PREFIX + "blogs";
  }

  @GetMapping("/blog/{id}")
  public String displayBlogId(@PathVariable String id, Model model) {
    Blog blog = blogRepository.findById(Long.parseLong(id))
        .orElseThrow(() -> new BlogNotFoundException(Long.parseLong(id)));
    model.addAttribute("blog", blog);
    return "uz/blogDetails"; // Assuming "blog" is the name of the template to render
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  public class BlogNotFoundException extends RuntimeException {
    public BlogNotFoundException(Long id) {
      super("Blog not found with ID: " + id);
    }

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
