package com.aresarmory.aresarmory.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.List;

@NamedQuery(name = "Cart.getByUser", query = "select c from Cart c where c.user.id=:id")
@NamedQuery(name = "Cart.getById", query = "select c from Cart c where c.id=:id")
@Data
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "cart")
public class Cart implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;
}
