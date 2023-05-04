package i18n;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @DisplayName("Проверка текста сообщения в зависимоти от региона")
    @ParameterizedTest(name = "Eсли регион {0}, то ожидаемый текст {1}")
    @CsvSource(value = {"RUSSIA, Добро пожаловать","USA, Welcome"})
    void  byLocationShouldReturnCorrectText(Country country,String expectedText) {
        var localizationService = new LocalizationServiceImpl();
        assertEquals(expectedText, localizationService.locale(country));
    }
}