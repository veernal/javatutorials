package com.demo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.demo.models.Book;

public class MyBookHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    List<Book> books = Arrays.asList(
        new Book(24, "Spiderman is not red", "Mark", 24),
        new Book(51, "Hulk is not green", "Clark", 29),
        new Book(15, "Ironman is not iron", "Mike", 35),
        new Book(12, "Antman is not ant", "Thomus", 24),
        new Book(24, "Captain America is not America", "Arun", 31)
    );

    @Autowired
    private Environment env;

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent input, Context context) {

        // System.out.println("Env: "+env.getProperty("sun"));
        System.out.println("Env: "+env);

        try{

            // api-endpoint?price=25
            String sPrice = input.getQueryStringParameters().get("price"); 

            // Double.valueOf(sPrice);
            double price = Double.parseDouble(sPrice);

            List<Book> newList = books
                .stream()
                .filter(book->book.getPrice()>price)
                .collect(Collectors.toList());

            return new APIGatewayProxyResponseEvent()
            .withBody(newList.toString())
            .withStatusCode(200);

        }catch(Exception e){
            return new APIGatewayProxyResponseEvent()
            .withBody(books.toString())
            .withStatusCode(200);
        }
    }

}
