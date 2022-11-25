package model;

public class Customer {

    private Integer customer_id;
    private String name;
    private String gender;
    private String city;

    public Customer(Integer customer_id, String name, String gender, String city) {
        this.customer_id = customer_id;
        this.name = name;
        this.gender = gender;
        this.city = city;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
