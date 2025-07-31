package com.phegondev.Phegon.Eccormerce.specification;

import com.phegondev.Phegon.Eccormerce.entity.OrderItem;
import com.phegondev.Phegon.Eccormerce.enums.OrderStatus;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

//OrderItemSpecification 클래스는 조건 검색을 위한 도구함
//예를들면 배송 상태가 shipped인 것만 찾고 싶을 때, 특정 날짜에 주문된 것만 보고 싶을 때, 특정 번호(id)의 주문 항목만 찾고 싶을 때
public class OrderItemSpecification {
    /**Specification to filter order items by status*/

    //주문 상태로 검색
    public static Specification<OrderItem> hasStatus(OrderStatus status){
        return ((root, query, criteriaBuilder) ->
                status != null ? criteriaBuilder.equal(root.get("status"), status) : null);

    }

    //날짜로 찾기 언제부터 언제까지
    /**Specification to filter order items by data range*/
    public static Specification<OrderItem> createdBetween(LocalDateTime startDate, LocalDateTime endDate){
        return ((root, query, criteriaBuilder) -> {
            if (startDate != null && endDate != null){
                return criteriaBuilder.between(root.get("createdAt"), startDate, endDate);
            } else if (startDate != null) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), startDate);
            } else if (endDate != null) {
                return criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), endDate);
            }else{
                return null;
            }
        });
    }

    //아이디로 찾기 예 : 아이디가 3인 것만
    /** Generate specification to filter orderitems by item id*/
    public static Specification<OrderItem> hasItemId(Long itemId){
        return ((root, query, criteriaBuilder) ->
                itemId != null ? criteriaBuilder.equal(root.get("id"), itemId) : null);
    }
}
