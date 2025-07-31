package com.phegondev.Phegon.Eccormerce.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
//Response 클래스는 모든 API 응답을 이 하나로 처리하기 위해 만든 통합 Response 객체.
//각 상황에 맞는 데이터만 담기고 나머지는 null로 유지되어 @JsonInclude.NON_NULL에 의해 자동 생략

@Builder
@Data
//null인 필드는 빼고 Json으로 반환하는 역할
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private int status;
    private String message;
    private final LocalDateTime timestamp = LocalDateTime.now();

    private String token;
    private String role;
    private String expirationTime;

    //기본값은 0이 저장된다.
    private int totalPage;
    private long totalElement;

    private AddressDto address;

    private UserDto user;
    private List<UserDto> userList;

    private CategoryDto category;
    private List<CategoryDto> categoryList;

    private ProductDto product;
    private List<ProductDto> productList;

    private OrderItemDto orderItem;
    private List<OrderItemDto> orderItemList;

    private OrderDto order;
    private List<OrderDto> orderList;


}
