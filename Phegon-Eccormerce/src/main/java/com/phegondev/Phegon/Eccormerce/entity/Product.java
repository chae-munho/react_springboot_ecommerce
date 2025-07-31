package com.phegondev.Phegon.Eccormerce.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "create_at")
    private final LocalDateTime createdAt = LocalDateTime.now();

}
