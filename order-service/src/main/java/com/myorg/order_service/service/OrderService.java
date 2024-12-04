package com.myorg.order_service.service;

import com.myorg.order_service.dto.OrderLineItemsDto;
import com.myorg.order_service.dto.OrderRequest;
import com.myorg.order_service.dto.OrderResponse;
import com.myorg.order_service.model.Order;
import com.myorg.order_service.model.OrderLineItems;
import com.myorg.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
       Order order = new Order();

       order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems>orderLineItems = orderRequest.getOrderLineItemsDtoList().stream()
                .map(OrderLineItemsDto -> mapToDto(OrderLineItemsDto)).toList();
       order.setOrderLineItemsList(orderLineItems);

       orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto)
    {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }

    public List<Order> fetchAllOrders() {
        List<Order>orderList    = orderRepository.findAll();
        return orderList;

    }
}
