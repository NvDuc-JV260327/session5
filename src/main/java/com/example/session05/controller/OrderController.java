package com.example.session05.controller;

import com.example.session05.model.dto.OrderSummary;
import com.example.session05.model.dto.PaginationResponse;
import com.example.session05.model.entity.Order;
import com.example.session05.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/searchStatus")
    public ResponseEntity<List<Order>> findByStatus(@RequestParam("status") String status) {
        return new ResponseEntity<>(orderService.findByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/searchByCustomer")
    public ResponseEntity<List<Order>> findByCustomerName(@RequestParam("customerName") String customerName) {
        return new ResponseEntity<>(orderService.findByCustomerName(customerName), HttpStatus.OK);
    }

    @GetMapping("/sort")
    public ResponseEntity<List<Order>> sort(@RequestParam("sortBy") String sortBy , @RequestParam("dir") String dir) {
        return new ResponseEntity<>(orderService.getAllOrdersSorted(sortBy, dir), HttpStatus.OK);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<Order>> findByPage(@RequestParam("page") int page , @RequestParam("size") int size){
        return new ResponseEntity<>(orderService.getOrdersPaged(page,size),HttpStatus.OK);
    }

    @GetMapping("/findOrderHighPrice")
    public ResponseEntity<List<Order>> findOrderHighPrice(){
        return new ResponseEntity<>(orderService.findOrderHighPrice(), HttpStatus.OK);
    }

    @GetMapping("/findAllAndSearch")
    public ResponseEntity<PaginationResponse<OrderSummary>> findAllAndSearch(@RequestParam("page") int page, @RequestParam("size") int size){
        Pageable pageable = PageRequest.of(page,size);
        return new ResponseEntity<>(orderService.findAllAndPagination(pageable),HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<PaginationResponse> filterOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "createdAt") String sortBy,
            @RequestParam(defaultValue = "desc") String dir) {

        // Xử lý hướng sắp xếp
        Sort sort = dir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // Tạo Pageable
        Pageable pageable = PageRequest.of(page, size, sort);

        // Trả về kết quả
        return new ResponseEntity<>(orderService.filterOrders(status, minPrice, pageable), HttpStatus.OK);
    }
}
