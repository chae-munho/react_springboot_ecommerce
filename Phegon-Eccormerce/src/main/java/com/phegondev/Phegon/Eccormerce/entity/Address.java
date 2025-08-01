package com.phegondev.Phegon.Eccormerce.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private String city;
    private String state;
    private String zipCode;
    private String country;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    /*
    @OneToOne(mappedBy = "address") // User 엔티티의 address 필드가 연관관계 주인
    private User user;

     */

    @Column(name = "created_at")
    private final LocalDateTime createdAt = LocalDateTime.now();
}
