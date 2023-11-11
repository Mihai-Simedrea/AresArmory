package com.aresarmory.aresarmory.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.io.Serializable;

@NamedQuery(name = "Product.getAllProduct", query = "select new com.aresarmory.aresarmory.wrapper.ProductWrapper(p.id, p.name, p.description, p.price, p.status, p.category.id, p.category.name) from Product p")
@NamedQuery(name = "Product.getProductByCategory", query = "select new com.aresarmory.aresarmory.wrapper.ProductWrapper(p.id, p.name, p.description, p.price, p.status, p.category.id, p.category.name) from Product p where p.category.id = :id and p.status='true'")
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "product")
public class Product implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_fk", nullable = false)
    private Category category;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private Integer price;
    @Column(name = "status")
    private String status;
}
