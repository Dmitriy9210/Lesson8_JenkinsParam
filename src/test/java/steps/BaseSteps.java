package steps;

import com.codeborne.selenide.ElementsCollection;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class BaseSteps {
    private Faker faker = new Faker();

    private String name = faker.name().firstName();
    private String surname = faker.name().lastName();
    private String email = faker.internet().emailAddress();
    private String phone = faker.phoneNumber().subscriberNumber(10);
    private String address = faker.address().fullAddress();
    private String mounth = "June";
    private String year = "1994";
    private String date = "12";
    private String subjectValue = "English";

    private String selectorMounth = ".react-datepicker__month-select";
    private String selectorYear = ".react-datepicker__year-select";
    private String selectorDay = ".react-datepicker__week div";
    private String selectorState = "Select State";
    private String selectorCity = "Select City";

    private String choseState = "NCR";
    private String choseCity = "Delhi";
    private String sports = "Sports";

    @Step("Open registration form")
    public void openPage() {
        open("https://demoqa.com/automation-practice-form");
    }

    @Step("Fill user data")
    public void fillUserFio() {
        $("#firstName").val(name);
        $("#lastName").val(surname);
        $("#userEmail").val(email);
        $("#userNumber").val(phone);
        $("#currentAddress").val(address);
    }

    @Step("Gender selection")
    public void choseGender() {
        $(byText("Male")).click();
    }

    @Step("Fill date of birthday")
    public void chooseDate() {
        $("#dateOfBirthInput").click();
        $(selectorMounth).selectOptionContainingText(mounth);
        $(selectorYear).selectOptionContainingText(year);
        $$(selectorDay).findBy(text(date)).click();
    }

    @Step("Choose Subjects")
    public void chooseSubjects() {
        $("#subjectsInput").setValue(subjectValue).pressEnter();
    }

    @Step("Choose Hobbies")
    public void chooseHobbies() {
        $(byText(sports)).click();
    }

    @Step("Upload File")
    public void uploadFile() {
        $("#uploadPicture").uploadFile(new File("./src/test/resources/file.jpg"));
    }

    @Step("Fill Address")
    public void fillAddress() {
        $("#currentAddress").setValue(address);
        $(byText(selectorState)).click();
        $(byText(choseState)).click();
        $(byText(selectorCity)).click();
        $(byText(choseCity)).click();
    }

    @Step("Click btn Subbmit")
    public void subbmitBtn() {
        $("#submit").submit();
    }

    @Step("Check register form")
    public void checkRegisterForm() {
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
