import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

    @Test
    void fillFormTest() {

        Faker faker = new Faker();

        String name = faker.name().firstName();
        String surname = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String phone = faker.phoneNumber().subscriberNumber(10);
        String address = faker.address().fullAddress();
        String mounth = "June";
        String year = "1994";
        String date = "12";


        open("https://demoqa.com/automation-practice-form");
        $("#firstName").val(name);
        $("#lastName").val(surname);
        $("#userEmail").val(email);
        $("#userNumber").val(phone);
        $("#currentAddress").val(address);
        $("label[for='gender-radio-1']").click();
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOptionContainingText(mounth);
        $(".react-datepicker__year-select").selectOptionContainingText(year);
        $$(".react-datepicker__week div").findBy(text(date)).click();
        $("#subjectsInput").setValue("en");
        $("#react-select-2-option-1").click();
        $("label[for='hobbies-checkbox-1']").click();
        $("#uploadPicture").uploadFile(new File("./src/test/resources/file.jpg"));
        $("#currentAddress").setValue(address);
        chooseDropDownList("Select State", "NCR");
        chooseDropDownList("Select City", "Delhi");
        $(byText("NCR")).click();
        $("#react-select-3-option-2").click();
        $("#city").click();
        $("#react-select-4-option-1").click();
        $("#submit").submit();

        ElementsCollection elements = $$(".table tbody tr");
        elements.get(0).shouldHave(text(name +" "+ surname));
        elements.get(1).shouldHave(text(email));
        elements.get(2).shouldHave(text("Male"));
        elements.get(3).shouldHave(text(phone));
        elements.get(4).shouldHave(text(date +" "+ mounth +","+ year));
        elements.get(5).shouldHave(text("Computer Science"));
        elements.get(6).shouldHave(text("Sports"));
        elements.get(7).shouldHave(text("file.jpg"));
        elements.get(8).shouldHave(text(address));
        elements.get(9).shouldHave(text("Haryana Panipat"));
        $("#closeLargeModal").click();
    }

    void chooseDropDownList(String placeholderName, String chooseValue) {
        $(byText(placeholderName)).click();
        $(byText(chooseValue)).click();
    }
}
