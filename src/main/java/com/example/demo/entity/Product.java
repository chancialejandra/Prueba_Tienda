package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name ="p_id")
  private Integer pId;

  private String name;

  private Integer priceFinal;

  private String description;

  private String imageUrl;

  @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST})
  @JoinColumn(name = "c_id")
  private Category category;

}
