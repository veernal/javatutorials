package model;

import java.time.LocalDate;
import java.util.List;

public class Order {

    private Integer order_id;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Item> items;
    private Customer cus;

    public Order(Integer order_id, String status, LocalDate orderDate, LocalDate deliveryDate, List<Item> items, Customer cus) {
        this.order_id = order_id;
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.items = items;
        this.cus = cus;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }
}
