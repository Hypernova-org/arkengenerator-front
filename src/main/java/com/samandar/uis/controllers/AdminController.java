package com.samandar.uis.controllers;

import java.time.LocalDateTime;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

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
    if ("admin".equals(username) && "admin".equals(password)) {
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
      @RequestParam("file") MultipartFile file, Model model, RedirectAttributes redirectAttributes) {
    Map<String, String> responses = new HashMap<>();

    if (file.isEmpty()) {
      responses.put("msg", "Error");
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responses);
    }
    try {
      String filePath = "~/arkengenerator/src/main/resources/static/blog";
      String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
      byte[] bytes = file.getBytes();
      Path path = Paths
          .get(filePath + fileName);
      Files.write(path, bytes);

      redirectAttributes.addFlashAttribute("message",
          "You successfully uploaded '" + file.getOriginalFilename() + "'");
      System.out.println("File Uploaded");
      LocalDateTime currentDateTime = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
      String formattedDateTime = currentDateTime.format(formatter);
      String filePAth = fileName;
      Blog blog = new Blog(title, description, formattedDateTime, filePAth);
      blogRepository.save(blog);
      return ResponseEntity.ok(responses);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responses);
    }
  }

  @PostMapping("/certi/add")
  public ResponseEntity<Map<String, String>> postCertiAdd(@RequestParam("file") MultipartFile file, Model model,
      RedirectAttributes redirectAttributes) {
    Map<String, String> responses = new HashMap<>();

    if (file.isEmpty()) {
      responses.put("msg", "Error");
      redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responses);
    }
    try {
      String filePath = "~/arkengenerator/src/main/resources/static/blog";
      String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
      byte[] bytes = file.getBytes();
      Path path = Paths
          .get(filePath + fileName);
      Files.write(path, bytes);

      redirectAttributes.addFlashAttribute("message",
          "You successfully uploaded '" + file.getOriginalFilename() + "'");
      System.out.println("File Uploaded");
      Sertificate certi = new Sertificate(fileName);
      sertificateRepository.save(certi);
      return ResponseEntity.ok(responses);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responses);
    }
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
