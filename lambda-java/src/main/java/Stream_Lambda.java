
import model.Customer;
import model.Item;
import model.Order;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Stream_Lambda {

    public static void main(String args[]) {

        ArrayList<Item> itemArrayList = new ArrayList<>();
        itemArrayList.add(new Item(134, "Moto24", (double) 15000, "Mobiles"));
        itemArrayList.add(new Item(157, "Spiderman", (double) 50, "Books"));
        itemArrayList.add(new Item(204, "Acer", (double) 70000, "Laptops"));
        itemArrayList.add(new Item(210, "iPhone", (double) 45000, "Mobiles"));
        itemArrayList.add(new Item(215, "Shirt", (double) 1500, "Shirts"));

        ArrayList<Customer> customerArrayList = new ArrayList<>();
        customerArrayList.add(new Customer(1, "Tom", "male", "Bangalore"));
        customerArrayList.add(new Customer(2, "Mike", "male", "Delhi"));
        customerArrayList.add(new Customer(3, "Miley", "female", "Pune"));
        customerArrayList.add(new Customer(4, "Kunal", "male", "Delhi"));
        customerArrayList.add(new Customer(5, "Sakshi", "female", "Delhi"));



    ArrayList<Order> orderArrayList = new ArrayList<>();
        List<Item> itemList1 = new ArrayList<>();
        itemList1.add(itemArrayList.get(0));
        itemList1.add(itemArrayList.get(2));

        List<Item> itemList2 = new ArrayList<>();
        itemList2.add(itemArrayList.get(2));

        List<Item> itemList3 = new ArrayList<>();
        itemList3.add(itemArrayList.get(3));
        itemList3.add(itemArrayList.get(4));

        List<Item> itemList4 = new ArrayList<>();
        itemList4.add(itemArrayList.get(0));
        itemList4.add(itemArrayList.get(1));
        itemList4.add(itemArrayList.get(2));
        itemList4.add(itemArrayList.get(4));

        List<Item> itemList5 = new ArrayList<>();
        itemList5.add(itemArrayList.get(0));


        orderArrayList.add(new Order(1, "delivered", LocalDate.of(2021,11,12),
                LocalDate.of(2021,11,14), itemList1, customerArrayList.get(0)));
        orderArrayList.add(new Order(2, "delivered", LocalDate.of(2022,5,19),
                LocalDate.of(2022,5,24), itemList2, customerArrayList.get(3)));
        orderArrayList.add(new Order(3, "delivered", LocalDate.of(2021,7,31),
                LocalDate.of(2021,8,4),itemList3, customerArrayList.get(1)));
        orderArrayList.add(new Order(4, "pending", LocalDate.of(2022,10,29),
                null, itemList4, customerArrayList.get(2)));
        orderArrayList.add(new Order(5, "pending", LocalDate.of(2021, Month.OCTOBER,30),
                null, itemList5 , customerArrayList.get(4)));


        getItem_categoryMobiles(itemArrayList);

        //getItem_categoryMobiles_PriceGreaterThan20000(itemArrayList);

        //getOrder_categoryMobiles(orderArrayList);

        //getItem_categoryLaptops(itemArrayList);

        //getItem_OrderedByFemale(orderArrayList);

        //getItem_OrderedBetweenSpecificDates(orderArrayList);



    }

    private static List<Item> getItem_categoryMobiles(ArrayList<Item> arrayList){
      List<Item> itemWithCategoryMobiles =  arrayList.stream()
              .filter(arr-> arr.getCategory().contains("Mobiles"))
              .collect(Collectors.toList());
      for (Item item: itemWithCategoryMobiles){
          System.out.println("Item Name:" +item.getName()+" ,Category: "+item.getCategory());
      }
      return itemWithCategoryMobiles;
    }


    private static List<Item> getItem_categoryMobiles_PriceGreaterThan20000(ArrayList<Item> arrayList){
        List<Item> itemWithCategoryMobiles =  arrayList.stream()
                .filter(arr-> arr.getCategory().contains("Mobiles"))
                .filter(arr -> arr.getPrice()>20000)
                .collect(Collectors.toList());
        for (Item item: itemWithCategoryMobiles){
            System.out.println("Item Name:" +item.getName()+" ,Category: "+item.getCategory()+" ,Price:" +item.getPrice());
        }
        return itemWithCategoryMobiles;
    }


    private static List<Order> getOrder_categoryMobiles(ArrayList<Order> arrayList) {
        List<Order> orderList = new ArrayList<>();
        for (Order order : arrayList) {
            if (!order.getItems().isEmpty()) {
            List<Item> itemList = order.getItems().stream().filter(itm-> itm.getCategory().contains("Mobiles")).collect(Collectors.toList());
            if(!itemList.isEmpty()){
                orderList.add(order);
            }
                }
            }
        for(Order order: orderList){
            for(Item item: order.getItems())
                if(item.getCategory().contains("Mobiles")){
                    System.out.println("Order ID:" +order.getOrder_id()+ " ,Item Name:" +item.getName()+" ,Category "+ item.getCategory());
                }
        }

        return orderList;
    }


    private static List<Item> getItem_categoryLaptops(ArrayList<Item> arrayList){
        List<Item> itemWithCategoryLaptops =  arrayList.stream()
                .filter(arr-> arr.getCategory().contains("Laptops"))
                .collect(Collectors.toList());
        for (Item item: itemWithCategoryLaptops){
            double discount = item.getPrice() * 0.05;
            item.setPrice(item.getPrice()-discount);
            System.out.println("Item Name:" +item.getName()+" ,Category: "+item.getCategory()+" ,Price after discount " +item.getPrice());
        }
        return itemWithCategoryLaptops;
    }


    private static List<Order> getItem_OrderedByFemale(ArrayList<Order> arrayList) {
        List<Order> orderList = new ArrayList<>();
        List<Item> itemList = new ArrayList<>();
        for (Order order : arrayList) {
                if(order.getCus().getGender().equalsIgnoreCase("female")) {
                  itemList  = order.getItems().stream().collect(Collectors.toList());
                }
                if(!itemList.isEmpty()){
                    orderList.add(order);
                }

        }
        for(Order order: orderList){
            for(Item item: order.getItems())
                    System.out.println("Order ID:" +order.getOrder_id()+ " ,Item Name:" +item.getName()+" ,Ordered By "+order.getCus().getName());

        }

        return orderList;
    }


    private static List<Order> getItem_OrderedBetweenSpecificDates(ArrayList<Order> arrayList){
        LocalDate dateFrom = LocalDate.of(2022, Month.JANUARY, 1);
        LocalDate dateTo = LocalDate.of(2022, Month.OCTOBER, 31);
        List<Order> orderList = new ArrayList<>();
        List<Item> itemList = new ArrayList<>();
        for (Order order : arrayList) {
           if(order.getOrderDate().isAfter(dateFrom) && order.getOrderDate().isBefore(dateTo)) {
               itemList = order.getItems().stream().collect(Collectors.toList());

               if (!itemList.isEmpty()) {
                   orderList.add(order);
               }
           }

        }
        for(Order order: orderList){
            for(Item item: order.getItems())
                System.out.println("Order ID:" +order.getOrder_id()+ " ,Item Name:" +item.getName()+" ,Ordered On "+order.getOrderDate());

        }
        return orderList;
    }







}
