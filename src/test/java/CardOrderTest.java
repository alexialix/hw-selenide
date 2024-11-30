import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardOrderTest {

    @Test
    void shouldSubmitFormSuccessfully() {
        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Иван");
        form.$("[data-test-id=phone] input").setValue("+79112345678");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(Condition.text("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
}