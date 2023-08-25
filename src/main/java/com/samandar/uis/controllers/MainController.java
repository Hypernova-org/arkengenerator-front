package com.samandar.uis.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

  @GetMapping("/")
  public String index(Model model) {
    return "index";
  }

  @PostMapping("/contact")
  public ResponseEntity<Map<String, String>> contact(@RequestParam String company_name, @RequestParam String name,
      @RequestParam String position, @RequestParam String phone, @RequestParam String message) {
    String apiUrl = "https://api.telegram.org/bot6163681114:AAEWD_XoEVWUro-ANnlPGQQNZk2WonFkksc/sendMessage";

    try {
      URL url = new URL(apiUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setDoOutput(true);
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
      connection.setConnectTimeout(200);
      connection.setReadTimeout(200);
      String requestBody = "chat_id=-1001917320693&text=New Contact: company_name - " + company_name + ", name - "
          + name
          + ", position - " + position + ", phone - " + phone + ", message - " + message;
      try (OutputStream os = connection.getOutputStream()) {
        byte[] input = requestBody.getBytes("UTF-8");
        os.write(input, 0, input.length);
      }

      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      reader.close();
      connection.disconnect();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Map<String, String> responses = new HashMap<>();
    responses.put("msg", "Success");
    return ResponseEntity.ok(responses);

  }

  @GetMapping("/ru")
  public String ru(Model model) {
    return "ru/index";
  }

  @GetMapping("/ru/products")
  public String ruProducts(Model model) {
    return "ru/products";
  }

  @GetMapping("/ru/docs")
  public String ruDocs() {
    return "ru/docs";
  }

  @GetMapping("/ru/about")
  public String ruAbout() {
    return "ru/about";
  }

  @GetMapping("/ru/blog")
  public String ruBlog() {
    return "ru/blog";
  }

  @GetMapping("/ru/contact")
  public String ruContact() {
    return "ru/contact";
  }

  @GetMapping("/ru/baudoin/generators")
  public String ruBaudoinGenerators() {
    return "ru/baudoin/generators";
  }

  @GetMapping("/ru/baudoin/linz_catalog")
  public String ruBaudoinLinzCatalog() {
    return "ru/baudoin/linz_catalog";
  }

  @GetMapping("/ru/baudoin/nekra_catalog")
  public String ruBaudoinNekraCatalog() {
    return "ru/baudoin/nekra_catalog";
  }

  @GetMapping("/ru/cummins/generators")
  public String rucumminsGenerators() {
    return "ru/cummins/generators";
  }

  @GetMapping("/ru/cummins/linz_catalog")
  public String rucumminsLinzCatalog() {
    return "ru/cummins/linz_catalog";
  }

  @GetMapping("/ru/cummins/nekra_catalog")
  public String rucumminsNekraCatalog() {
    return "ru/cummins/nekra_catalog";
  }

  @GetMapping("/ru/mitsubishi/generators")
  public String rumitsubishisGenerators() {
    return "ru/mitsubishi/generators";
  }

  @GetMapping("/ru/mitsubishi/linz_catalog")
  public String rumitsubishiLinzCatalog() {
    return "ru/mitsubishi/linz_catalog";
  }

  @GetMapping("/ru/mitsubishi/nekra_catalog")
  public String rumitsubishiNekraCatalog() {
    return "ru/mitsubishi/nekra_catalog";
  }

  @GetMapping("/ru/perkins/generators")
  public String ruperkinsGenerators() {
    return "ru/perkins/generators";
  }

  @GetMapping("/ru/perkins/linz_catalog")
  public String ruperkinsLinzCatalog() {
    return "ru/perkins/linz_catalog";
  }

  @GetMapping("/ru/perkins/nekra_catalog")
  public String ruperkinsNekraCatalog() {
    return "ru/perkins/nekra_catalog";
  }

  @GetMapping("/ru/yanmar/generators")
  public String ruyanmarsGenerators() {
    return "ru/yanmar/generators";
  }

  @GetMapping("/ru/yanmar/linz_catalog")
  public String ruyanmarLinzCatalog() {
    return "ru/yanmar/linz_catalog";
  }

  @GetMapping("/ru/yanmar/nekra_catalog")
  public String ruyanmarNekraCatalog() {
    return "ru/yanmar/nekra_catalog";
  }

  @GetMapping("/ru/portative/catalog")
  public String ruportativeaCatalog() {
    return "ru/portative/catalog";
  }

  @GetMapping("/ru/sdec/catalog")
  public String rusdecCatalog() {
    return "ru/sdec/catalog";
  }

  @GetMapping("/uz")
  public String uzIndex() {
    return "uz/index";
  }

  @GetMapping("/uz/products")
  public String uzProducts() {
    return "uz/products";
  }

  @GetMapping("/uz/docs")
  public String uzDocs() {
    return "uz/docss";
  }

  @GetMapping("/uz/about")
  public String uzAbout() {
    return "uz/about";
  }

  @GetMapping("/uz/blog")
  public String uzBlog() {
    return "uz/blogs";
  }

  @GetMapping("/uz/contact")
  public String uzContact() {
    return "uz/contact";
  }

  @GetMapping("uz/portative/catalog")
  public String uzportativecatalog() {
    return "uz/portative/catalog";
  }

  @GetMapping("/uz/baudoin/generators")
  public String uzbaudoingenerators() {
    return "/uz/baudoin/generators";
  }

  @GetMapping("/uz/baudoin/linz_catalog")
  public String uzbaudoinlinzctalog() {
    return "/uz/baudoin/linz_catalog";
  }

  @GetMapping("/uz/baudoin/nekra_catalog")
  public String uzbaudoinnekractalog() {
    return "/uz/baudoin/nekra_catalog";
  }

  @GetMapping("/uz/cummins/generators")
  public String uzcumminsgenerators() {
    return "/uz/cummins/generators";
  }

  @GetMapping("/uz/cummins/linz_catalog")
  public String uzcumminslinzctalog() {
    return "/uz/cummins/linz_catalog";
  }

  @GetMapping("/uz/cummins/nekra_catalog")
  public String uzcumminsnekractalog() {
    return "/uz/cummins/nekra_catalog";
  }

  @GetMapping("/uz/mitsubishi/generators")
  public String uzmitsubishigenerators() {
    return "/uz/mitsubishi/generators";
  }

  @GetMapping("/uz/mitsubishi/linz_catalog")
  public String uzmitsubishislinzctalog() {
    return "/uz/mitsubishi/linz_catalog";
  }

  @GetMapping("/uz/mitsubishi/nekra_catalog")
  public String uzmitsubishinekractalog() {
    return "/uz/mitsubishi/nekra_catalog";
  }

  @GetMapping("/uz/perkins/generators")
  public String uzperkinsgenerators() {
    return "/uz/perkins/generators";
  }

  @GetMapping("/uz/perkins/linz_catalog")
  public String uzperkinsslinzctalog() {
    return "/uz/perkins/linz_catalog";
  }

  @GetMapping("/uz/perkins/nekra_catalog")
  public String uzperkinsnekractalog() {
    return "/uz/perkins/nekra_catalog";
  }

  @GetMapping("/uz/sdec/catalog")
  public String uzsdeccatalog() {
    return "/uz/sdec/catalog";
  }

  @GetMapping("/uz/yanmar/generators")
  public String uzyanvargenerators() {
    return "/uz/yanmar/generators";
  }

  @GetMapping("/uz/yanmar/linz_catalog")
  public String uzyanmarslinzctalog() {
    return "/uz/yanmar/linz_catalog";
  }

  @GetMapping("/uz/yanmar/nekra_catalog")
  public String uzyanmarnekractalog() {
    return "/uz/yanmar/nekra_catalog";
  }
}
