package com.phegondev.Phegon.Eccormerce.controller;

import com.phegondev.Phegon.Eccormerce.dto.OrderRequest;
import com.phegondev.Phegon.Eccormerce.dto.Response;
import com.phegondev.Phegon.Eccormerce.enums.OrderStatus;
import com.phegondev.Phegon.Eccormerce.service.interf.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderItemController {
    private final OrderItemService orderItemService;

    @PostMapping("/create")
    public ResponseEntity<Response> placeOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.ok(orderItemService.placeOrder(orderRequest));
    }

    @PutMapping("/update-item-status/{orderItemId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateOrderItemStatus(@PathVariable Long orderItemId,  @RequestParam String status){
        return ResponseEntity.ok(orderItemService.updateOrderItemStatus(orderItemId, status));
    }

    //리액트에서는 getAllOrders() 함수로 호출
    //만약 required = false 안하고 오직 String status에만 값을 넣는다면 나머지 값엔 널 에러가 발생하여 404 Bad request 가 발생함
    @GetMapping("/filter")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> filterOrderItems(
            @RequestParam(required = false, name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false, name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime endDate,
            @RequestParam(required = false, name = "status") String status,
            @RequestParam(required = false, name = "itemId") Long itemId,
            @RequestParam(defaultValue = "0", name = "page") int page,
            @RequestParam(defaultValue = "1000", name = "size") int size

    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        OrderStatus orderStatus = status != null ? OrderStatus.valueOf(status.toUpperCase()) : null;

        return ResponseEntity.ok(orderItemService.filterOrderItems(orderStatus, startDate, endDate, itemId, pageable));
    }
}
