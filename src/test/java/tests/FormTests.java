package tests;

import com.github.javafaker.Faker;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import pages.RegistrationPage;

public class FormTests {

    Faker faker = new Faker();

    String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            gender = "Female",
            phoneNumber = faker.phoneNumber().subscriberNumber(10),
            month = "December",
            year = "1990",
            day = "23",
            subject = "Computer Science",
            hobby = "Reading",
            pathname = "src/test/java/resources/1.jpeg",
            picture = "1.jpeg",
            address = faker.address().fullAddress(),
            state = "NCR",
            city = "Delhi";

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void setUpConfig() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }

    @Test
    void successSubmitFormTest() {
        registrationPage.openPage();

        registrationPage.typeFirstName(firstName);
        registrationPage.typeLastName(lastName);
        registrationPage.typeEmail(email);
        registrationPage.chooseGender(gender);
        registrationPage.typeUserNumber(phoneNumber);
        registrationPage.chooseDateOfBirth(day, month, year);
        registrationPage.chooseSubject(subject);
        registrationPage.chooseHobby(hobby);
        registrationPage.upLoadPicture(pathname);
        registrationPage.typeAddress(address);
        registrationPage.chooseState(state);
        registrationPage.chooseCity(city);
        registrationPage.submitForm();

        registrationPage.checkRegistrationResults(firstName, lastName, email, gender, phoneNumber, day, month, year, subject, hobby, picture, address, state, city);
    }
}
