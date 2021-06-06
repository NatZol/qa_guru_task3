package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;

public class FormTests {

    @BeforeAll
    static void setUpConfig() {
        Configuration.browser = "chrome";
        Configuration.startMaximized = true;
    }

    @Test
    void successSubmitFormTest() {
        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue("Natalia");
        $("#lastName").setValue("Malakhov");
        $("#userEmail").setValue("test@email.com");
        $("input[value='Female']").parent().click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__year-select").selectOption("1990");
        $("div[aria-label='Choose Sunday, December 23rd, 1990']").click();
        $("#subjectsInput").setValue("Comp").pressEnter();
        $("#hobbies-checkbox-2").parent().click();
        $("#uploadPicture").uploadFile(new File("src/test/java/resources/1.jpeg"));
        $("textarea#currentAddress").setValue("Rishon leZion");
        $("#state input").setValue("NCR").pressEnter();
        $("#city input").setValue("Delhi").pressEnter();

        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text("Label"), text("Values"));
        $(".table-responsive").shouldHave(text("Student Name"), text("Natalia Malakhov"));
        $(".table-responsive").shouldHave(text("Student Email"), text("test@email.com"));
        $(".table-responsive").shouldHave(text("Gender"), text("Female"));
        $(".table-responsive").shouldHave(text("Mobile"), text("1234567890"));
        $(".table-responsive").shouldHave(text("Date of Birth"), text("23 December,1990"));
        $(".table-responsive").shouldHave(text("Subjects"), text("Computer Science"));
        $(".table-responsive").shouldHave(text("Hobbies"), text("Reading"));
        $(".table-responsive").shouldHave(text("Picture"), text("1.jpeg"));
        $(".table-responsive").shouldHave(text("Address"), text("Rishon leZion"));
        $(".table-responsive").shouldHave(text("State and City"), text("NCR Delhi"));
    }
}
