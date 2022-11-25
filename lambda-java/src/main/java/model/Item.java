package model;

public class Item {
    private Integer item_id;
    private String name;
    private Double price;
    private String category;

    public Item(Integer item_id, String name, Double price, String category) {
        this.item_id = item_id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Integer getItem_id() {
        return item_id;
    }

    public void setItem_id(Integer item_id) {
        this.item_id = item_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
