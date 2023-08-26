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
    return "ru/index";
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
      String requestBody = "chat_id=-1001917320693&text=🌍 Saytdan xabar keldi:\n 🏢 Kompaniya nomi: " + company_name
          + " \n🙋🏻‍♂️ Ism :" + name
          + "\n ⬆️ Lavozimi :" + position + "\n☎️ Telefon raqami:" + phone + "\n📩 Xabar : " + message;
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
}