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
        String subjectValue = "English";

        String selectorMounth = ".react-datepicker__month-select";
        String selectorYear = ".react-datepicker__year-select";
        String selectorDay = ".react-datepicker__week div";
        String selectorState = "Select State";
        String selectorCity = "Select City";

        String choseState = "NCR";
        String choseCity = "Delhi";
        String sports = "Sports";

        open("https://demoqa.com/automation-practice-form");
        $("#firstName").val(name);
        $("#lastName").val(surname);
        $("#userEmail").val(email);
        $("#userNumber").val(phone);
        $("#currentAddress").val(address);
        $(byText("Male")).click();
        $("#dateOfBirthInput").click();
        $(selectorMounth).selectOptionContainingText(mounth);
        $(selectorYear).selectOptionContainingText(year);
        $$(selectorDay).findBy(text(date)).click();
        $("#subjectsInput").setValue(subjectValue).pressEnter();
        $(byText(sports)).click();
        $("#uploadPicture").uploadFile(new File("./src/test/resources/file.jpg"));
        $("#currentAddress").setValue(address);
        $(byText(selectorState)).click();
        $(byText(choseState)).click();
        $(byText(selectorCity)).click();
        $(byText(choseCity)).click();
        $("#submit").submit();

        ElementsCollection elements = $$(".table tbody tr");
        elements.get(0).shouldHave(text(name + " " + surname));
        elements.get(1).shouldHave(text(email));
        elements.get(2).shouldHave(text("Male"));
        elements.get(3).shouldHave(text(phone));
        elements.get(4).shouldHave(text(date + " " + mounth + "," + year));
        elements.get(5).shouldHave(text(subjectValue));
        elements.get(6).shouldHave(text(sports));
        elements.get(7).shouldHave(text("file.jpg"));
        elements.get(8).shouldHave(text(address));
        elements.get(9).shouldHave(text(String.format("%s %s", choseState, choseCity)));
        $("#closeLargeModal").click();
    }
}
