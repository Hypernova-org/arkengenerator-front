package com.samandar.uis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sertificate {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String photo;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public Sertificate() {
  }

  public Sertificate(String photo) {
    this.photo = photo;
  }
}
