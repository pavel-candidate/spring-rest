package com.dsv.work.around.rest.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "companies")
public class Company implements java.io.Serializable {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "name", length = 50)
  private String name;

  @Column(name = "api_key", length = 50)
  private String apiKey;

  @Column(name = "api_enabled")
  private boolean apiEnabled;

  public Company(Company company) {
    setId(company.getId());
    if (company.getName() != null) {
      setName(company.getName());
    }
    if (company.getApiKey() != null) {
      setApiKey(company.getApiKey());
    }
    setApiEnabled(company.isApiEnabled());
  }



}
