import org.junit.jupiter.api.Test;
import steps.BaseSteps;

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
