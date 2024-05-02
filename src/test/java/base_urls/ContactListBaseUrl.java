package base_urls;

import io.cucumber.java.Before;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static utilities.Authentication.generateToken;

public class ContactListBaseUrl {

    private static RequestSpecification spec;


    public static RequestSpecification setUp(){
        spec = new RequestSpecBuilder()
                .setBaseUri("https://thinking-tester-contact-list.herokuapp.com")
                .setContentType(ContentType.JSON)
                .addHeader("Authorization","Bearer "+generateToken())
                .build();
        return spec;
    }
}