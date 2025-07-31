package com.phegondev.Phegon.Eccormerce.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.phegondev.Phegon.Eccormerce.entity.OrderItem;
import com.phegondev.Phegon.Eccormerce.entity.Payment;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderRequest {
    private BigDecimal totalPrice;

    private List<OrderItemRequest> items;
    private Payment paymentInfo;

}

