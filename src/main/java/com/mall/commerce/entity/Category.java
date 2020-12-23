package com.mall.commerce.entity;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "category_no")
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "parent_no")
    private Long parentId;

    @Column(name = "depth")
    private Integer depth;

    public void update(String categoryName) {
        this.categoryName = categoryName;
    }

    @Builder
    public Category(String categoryName, Long parentId, Integer depth) {
        this.categoryName = categoryName;
        this.parentId = parentId;
        this.depth = depth;
    }
}
