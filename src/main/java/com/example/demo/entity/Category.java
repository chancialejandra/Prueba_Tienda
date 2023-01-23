package com.example.demo.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

  @Id
  @Column(name = "c_id")
  private Integer cId;
  private String title;
  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
  private List<Product> products;

}
