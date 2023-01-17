import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {

    public String generateDate() {
        return LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    public void shouldChooseADate (){

        open("http://localhost:9999/");
        $("[placeholder=\" Город\"]").setValue("Казань");
        $("[data-test-id=\"date\"] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id=\"date\"]").setValue(generateDate());
        $("[name=\"name\"]").setValue("Иванов Иван");
        $("[name=\"phone\"]").setValue("+79879879879");
        $("[data-test-id=\"agreement\"]").click();
        $$("button").findBy(Condition.text("Забронировать")).click();
        $(byText("Встреча успешно забронирована на ")).shouldBe(Condition.visible, Duration.ofSeconds(15));

    }

}