package com.example.session05.service;

import com.example.session05.model.dto.OrderSummary;
import com.example.session05.model.dto.PaginationResponse;
import com.example.session05.model.entity.Order;
import com.example.session05.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    // Bài  1
    public List<Order> findByStatus(String status) {
        return  orderRepository.findByStatus(status);
    }

    public List<Order> findByCustomerName(String customerName) {
        return orderRepository.findByCustomerNameContaining(customerName);
    }

    // Bài 2

    public List<Order> getAllOrdersSorted(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(field).ascending() : Sort.by(field).descending();
        return orderRepository.findAll(sort);
    }

    // Bài 3

    public Page<Order> getOrdersPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());
        return orderRepository.findAll(pageable);
    }
    public List<Order> findOrderHighPrice(){
        return orderRepository.findOrderHighPrice();
    };

    public PaginationResponse<OrderSummary> findAllAndPagination(Pageable pageable){
        Page<OrderSummary> page = orderRepository.findAllAndPagination(pageable);
        PaginationResponse paginationResponse = new PaginationResponse(page);

        return paginationResponse;
    }

    public PaginationResponse filterOrders(String status, Double minPrice, Pageable pageable) {
        // Gọi repository xử lý lọc, phân trang và projection
        Page<OrderSummary> page = orderRepository.filterOrders(status, minPrice, pageable);

        // Chuyển đổi Page thành PaginationResponse custom
        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setData(page.getContent());
        paginationResponse.setTotalPage(page.getTotalPages());
        paginationResponse.setTotalElement(page.getTotalElements());
        paginationResponse.setCurrentPage(page.getNumber());

        return paginationResponse;
    }
}
