package com.example.session05;

import com.example.session05.model.entity.Order;
import com.example.session05.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class Session05Application implements CommandLineRunner {
    @Autowired
    private OrderRepository orderRepository;
// Đoạn mã naỳ tự động insert 20 bản ghi vào bảng order khi khởi chạy dự án
    @Override
    public void run(String... args) {
//        for (int i = 1; i <= 20; i++) {
//            Order order = new Order();
//            order.setOrderCode("ORD-" + i);
//            order.setCustomerName("Customer " + i);
//            order.setTotalPrice(100 * i);
//            order.setStatus(i % 2 == 0 ? "DELIVERED" : "PENDING");
//            order.setCreatedAt(LocalDateTime.now().minusDays(i));
//            orderRepository.save(order);
//        }
    }
    public static void main(String[] args) {
        SpringApplication.run(Session05Application.class, args);
    }

}
