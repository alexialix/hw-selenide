import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class CardOrderTest  {
    @Test
    void shouldSubmitFormSuccessfully() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        $("[data-test-id=order-success]")
                .shouldBe(visible)
                .shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void shouldErrorWhenInvalidName() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Ivan Ivanov");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        $("[data-test-id=name].input_invalid .input__sub")
                .shouldBe(visible)
                .shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldErrorWhenNameIsEmpty() {
        open("http://localhost:9999");

        $("[data-test-id=phone] input").setValue("+79012345678");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        $("[data-test-id=name].input_invalid .input__sub")
                .shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldErrorWhenPhoneIsInvalid() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("12345");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldBe(visible)
                .shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldErrorWhenPhoneIsEmpty() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=agreement]").click();
        $(".button").click();

        $("[data-test-id=phone].input_invalid .input__sub")
                .shouldBe(visible)
                .shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    void shouldErrorWhenCheckboxNotSelected() {
        open("http://localhost:9999");

        $("[data-test-id=name] input").setValue("Иван Иванов");
        $("[data-test-id=phone] input").setValue("+79012345678");
        $(".button").click();

        $("[data-test-id=agreement].input_invalid")
                .shouldBe(visible);
    }
}


