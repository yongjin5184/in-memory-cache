package com.mall.commerce.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "product_no")
    private Long id;

    @Column(name = "brand_name")
    private String brandName;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @ManyToOne
    @JoinColumn(name = "category_no")
    private Category category;

    public void update(String productName, BigDecimal productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }
}
