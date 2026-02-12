package com.example.Project.kyra_cosmetics.controller;

import com.example.Project.controller.OrderController;
import com.example.Project.model.Order;
import com.example.Project.model.OrderStatus;
import com.example.Project.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    MockMvc mvc;

    @Mock
    OrderService orderService;

    @InjectMocks
    com.example.Project.controller.OrderController orderController;

    ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    void placeOrder_returnsOrder() throws Exception {
        Order o = Order.builder().id(11L).build();
        when(orderService.placeOrder(7L)).thenReturn(o);

        mvc.perform(post("/api/orders/7")).andExpect(status().isOk()).andExpect(jsonPath(".id").value(11));
    }

    @Test
    void getUserOrders_returnsList() throws Exception {
        when(orderService.getUserOrders(7L)).thenReturn(List.of(Order.builder().id(12L).build()));

        mvc.perform(get("/api/orders/user/7")).andExpect(status().isOk()).andExpect(jsonPath("[0].id").value(12));
    }

    @Test
    void updateStatus_callsService() throws Exception {
        Order o = Order.builder().id(20L).status(OrderStatus.SHIPPED).build();
        when(orderService.updateStatus(20L, OrderStatus.SHIPPED)).thenReturn(o);

        mvc.perform(patch("/api/orders/20/status").param("status","SHIPPED")).andExpect(status().isOk()).andExpect(jsonPath(".status").value("SHIPPED"));
    }
}
