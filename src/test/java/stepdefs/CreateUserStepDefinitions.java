package stepdefs;

import base_urls.ContactListBaseUrl;
import com.github.javafaker.Faker;
import groovyjarjarantlr4.v4.codegen.model.SrcOp;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pages.ContactListAddUserPage;
import pages.ContactListLogInPage;
import pojos.UserResponsePojo;
import utilities.Driver;


import static io.restassured.RestAssured.given;
import static junit.framework.TestCase.assertEquals;
import static utilities.Authentication.generateToken;
import static utilities.ObjectMapperUtilities.convertJsonToJava;

public class CreateUserStepDefinitions {
    public static String firstName;
    public static String lastName;
    public static String email;
    public static String password;

    ContactListLogInPage logInPage = new ContactListLogInPage();
    ContactListAddUserPage addUserPage = new ContactListAddUserPage();
    @Given("user goes to {string}")
    public void userGoesTo(String url) {
        Driver.getDriver().get(url);
        // Driver.getDriver().get(ConfigReader.getProperty("contact_list_url")); // this returns always same url
    }

    @When("user clicks on sign up button")
    public void userClicksOnSignUpButton() {
        logInPage.signupButton.click();


    }

    @And("User enters firstname, lastname, email, password")
    public void userEntersFirstnameLastnameEmailPassword() {
        Faker faker = new Faker();
        firstName = faker.name().firstName();
        lastName = faker.name().lastName();
        email = faker.internet().emailAddress();
        password ="Password.123";

        addUserPage.firstNameField.sendKeys(firstName);
        addUserPage.lastNameField.sendKeys(lastName);
        addUserPage.emailField.sendKeys(email);
        addUserPage.passwordField.sendKeys(password);

    }

    @And("user clicks on submit button")
    public void userClicksOnSubmitButton() {
        addUserPage.submitButton.click();
    }

    @And("user closes browser")
    public void userClosesBrowser() {
        Driver.tearDown();
    }

    @Then("verify the user via API request and delete")
    public void verifyTheUserViaAPIRequestAndDelete() {
        // Set Url
        RequestSpecification spec =ContactListBaseUrl.setUp();
        spec.pathParams("first","users"
                ,"second","me");

        // Set Expected Data
        UserResponsePojo expectedData = new UserResponsePojo(firstName,lastName,1,null,email);

        // Send Request and Get Response
        Response response= given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do Assertions
        UserResponsePojo actualData = convertJsonToJava(response.asString(), UserResponsePojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getLastName(),actualData.getLastName());
        assertEquals(expectedData.getEmail(),actualData.getEmail());

        given(spec).when().delete("{first}/{second}").then().statusCode(200);
    }

    @Then("verifies the user via API request and delete")
    public void verifiesTheUserViaAPIRequestAndDelete() {
        // Set Url
        RequestSpecification spec =ContactListBaseUrl.setUp();
        spec.pathParams("first","users"
                ,"second","me");

        // Set Expected Data
        UserResponsePojo expectedData = new UserResponsePojo(firstName,lastName,1,null,email);

        // Send Request and Get Response
        Response response= given(spec).when().get("{first}/{second}");
        response.prettyPrint();

        // Do Assertions
        UserResponsePojo actualData = convertJsonToJava(response.asString(), UserResponsePojo.class);

        assertEquals(200,response.statusCode());
        assertEquals(expectedData.getFirstName(),actualData.getFirstName());
        assertEquals(expectedData.getLastName(),actualData.getLastName());
        assertEquals(expectedData.getEmail(),actualData.getEmail());

        given(spec).when().delete("{first}/{second}").then().statusCode(200);
    }
}