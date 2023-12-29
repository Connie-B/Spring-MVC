package com.example.accessingdatamysql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@Entity
public class AppUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Pattern(regexp="^[A-Za-z]+$", message="Only alphabetic characters are allowed.")
  private String firstName;
  
  @Pattern(regexp="^[A-Za-z]+$", message="Only alphabetic characters are allowed.")
  private String lastName;
  
  @Email
  @NotBlank(message="Email must not be blank.")
  private String email;

  @Column(name = "address")
  @NotBlank(message="Address must not be blank.")
  private String address;

  @Column(name = "city")
  @NotBlank(message="City must not be blank.")
  private String city;

  @Column(name = "telephone")
  @Pattern(regexp="^[0-9]{10}$", message="Please enter a 10-digit phone number.")
  private String telephone;


  public AppUser() {
    this.firstName = "";
    this.lastName = "";
    this.address = "";
    this.city = "";
    this.telephone = "";
    this.email = "";
  }

  public AppUser(String firstName, String lastName, String address, String city, String telephone, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.city = city;
    this.telephone = telephone;
    this.email = email;
  }

  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getAddress() {
    return this.address;
  }
  public void setAddress(String address) {
    this.address = address;
  }

  public String getCity() {
    return this.city;
  }
  public void setCity(String city) {
    this.city = city;
  }

  public String getTelephone() {
    return this.telephone;
  }
  public void setTelephone(String telephone) {
    this.telephone = telephone;
  }

  public String getEmail() {
    return email;
  }
  public void setEmail(String email) {
    this.email = email;
  }

  public String toString() {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      return objectMapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      return "{id:" + id + ", firstName:" + firstName + ", lastName:" + lastName + ", address:" + address + ", city:" + city + ", telephone:" + telephone + ", email:" + email + "}";
    }  
  }
}
