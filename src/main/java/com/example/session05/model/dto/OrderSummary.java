package com.example.session05.model.dto;


public class OrderSummary {
    private String orderCode;
    private String customerName;
    private Double totalPrice;

    public OrderSummary() {
    }

    public OrderSummary(String orderCode, String customerName, Double totalPrice) {
        this.orderCode = orderCode;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}