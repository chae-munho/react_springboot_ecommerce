package com.phegondev.Phegon.Eccormerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;

import java.time.LocalDateTime;
import java.util.List;

//특정 사용자 User의 주소 정보를 표현하는 엔티티
@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB에서 자동증가 방식으로 ID 생성
    private Long id;

    @Column(unique = true)
    private String name;


    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> productList;

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();
}
