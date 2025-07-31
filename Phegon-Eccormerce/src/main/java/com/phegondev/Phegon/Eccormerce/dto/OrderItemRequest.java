package com.phegondev.Phegon.Eccormerce.dto;

import lombok.Data;

@Data
public class OrderItemRequest {
    private Long productId;
    //해당 상품을 몇개 주문했는지
    private int quantity;

}
