import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm extends TestBase{

    @Test
    void fillFormTest() {
        BaseSteps steps = new BaseSteps();
        steps.openPage();
        steps.fillUserFio();
        steps.choseGender();
        steps.chooseDate();
        steps.chooseSubjects();
        steps.chooseHobbies();
        steps.uploadFile();
        steps.fillAddress();
        steps.subbmitBtn();

        steps.checkRegisterForm();
    }
}
