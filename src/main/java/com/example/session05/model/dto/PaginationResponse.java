package com.example.session05.model.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PaginationResponse<T> {
    private List<T> data;
    private int totalPage;
    private long totalElement;
    private int currentPage;

    public PaginationResponse() {
    }

    public PaginationResponse(Page<T> page) {
        this.data = page.getContent();
        this.totalPage = page.getTotalPages();
        this.totalElement = page.getTotalElements();
        this.currentPage = page.getNumber();
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public long getTotalElement() {
        return totalElement;
    }

    public void setTotalElement(long totalElement) {
        this.totalElement = totalElement;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
