package com.samandar.uis.controllers;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.samandar.uis.models.Blog;
import com.samandar.uis.models.Sertificate;
import com.samandar.uis.repo.BlogRepository;
import com.samandar.uis.repo.SertificateRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminController {
  private static final String ADMIN_PREFIX = "admin/";

  @Autowired
  private BlogRepository blogRepository;

  @Autowired
  private SertificateRepository sertificateRepository;

  @GetMapping()
  public String index(Model model) {
    Iterable<Blog> blogs = blogRepository.findAll();
    model.addAttribute("blogs", blogs);
    return ADMIN_PREFIX + "index";
  }

  @GetMapping("/certi")
  public String cer(Model model) {
    Iterable<Sertificate> certi = sertificateRepository.findAll();
    model.addAttribute("sert", certi);
    return ADMIN_PREFIX + "sertificat";
  }

  @PostMapping()
  public ResponseEntity<Map<String, String>> postIndex(@RequestParam String username, @RequestParam String password) {
    if ("arkengeneratoruz18_23".equals(username) && "arkenadmin_2023".equals(password)) {
      Map<String, String> responses = new HashMap<>();
      responses.put("msg", "Success");
      responses.put("username", "admin");
      return ResponseEntity.ok(responses);
    }

    Map<String, String> responses = new HashMap<>();
    responses.put("msg", "Error");
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responses);
  }

  @PostMapping("/news/add")
  public ResponseEntity<Map<String, String>> postNewsAdd(@RequestParam String title, @RequestParam String description,
      @RequestParam String file, Model model, RedirectAttributes redirectAttributes) {
    Map<String, String> responses = new HashMap<>();
    ZonedDateTime currentDateTime = ZonedDateTime.now(ZoneId.of("Asia/Tashkent"));
    String formattedDateTime = currentDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    Blog blog = new Blog(title, description, formattedDateTime, file);
    blogRepository.save(blog);
    return ResponseEntity.ok(responses);
  }
  

  @PostMapping("/certi/add")
  public ResponseEntity<Map<String, String>> postCertiAdd(@RequestParam String file, @RequestParam String url, @RequestParam String text, Model model,
      RedirectAttributes redirectAttributes) {
    Map<String, String> responses = new HashMap<>();
    Sertificate certi = new Sertificate(file, url, text);
    sertificateRepository.save(certi);
    return ResponseEntity.ok(responses);
  }

  @PostMapping("/news/delete")
  public ResponseEntity<Map<String, String>> postNewsDelete(@RequestParam Long id) {
    blogRepository.deleteById(id);
    Map<String, String> responses = new HashMap<>();
    responses.put("msg", "Success");
    return ResponseEntity.ok(responses);
  }

  @PostMapping("/certi/delete")
  public ResponseEntity<Map<String, String>> postCertiDelete(@RequestParam Long id) {
    sertificateRepository.deleteById(id);
    Map<String, String> responses = new HashMap<>();
    responses.put("msg", "Success");
    return ResponseEntity.ok(responses);
  }

  @GetMapping("/login")
  public String login() {
    return ADMIN_PREFIX + "login";
  }

  @GetMapping("/news/add")
  public String addNews() {
    return ADMIN_PREFIX + "newsAdd";
  }

  @GetMapping("/certi/add")
  public String addCerti() {
    return ADMIN_PREFIX + "addCer";
  }
}
