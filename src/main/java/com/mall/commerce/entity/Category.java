package com.mall.commerce.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "category_no")
    private Integer id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "parent_no")
    private Integer parentNo;

    @Column(name = "depth")
    private String depth;
}
