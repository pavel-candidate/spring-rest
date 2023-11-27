package com.dsv.work.around.rest.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table( name = "users" )
public class User implements java.io.Serializable {
  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "company_id")
  private Long companyId;


  @Column(name = "name", length = 50)
  private String name;

  @Column(name = "email", length = 50)
  private String email;

  @Column(name = "enabled")
  private boolean enabled;


  public User() {}

  public User(Long id, String name, String email, boolean enabled) {
    setId(id);
    setName(name);
    setEmail(email);
    setEnabled(enabled);
  }

  public User(User user) {
    setId(user.getId());
    if (user.getName() != null) {
      setName(user.getName());
    }
    if (user.getEmail() != null) {
      setEmail(user.getEmail());
    }
    setEnabled(user.isEnabled());
  }
}