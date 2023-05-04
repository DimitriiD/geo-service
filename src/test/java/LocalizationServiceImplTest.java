import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    private static final String EXPECTED_DEFAULT_RUS_TEXT = "Добро пожаловать";
    private static final String EXPECTED_DEFAULT_ENG_TEXT = "Welcome";

    @ParameterizedTest()
    @MethodSource("location")
    void locationRus(Location location) {
        var localizationService = new LocalizationServiceImpl();
        assertEquals(location.getExpectedText(), localizationService.locale(location.getCountry()));
    }

    public static Stream<Location> location() {
        return Stream.of(new Location(Country.RUSSIA, EXPECTED_DEFAULT_RUS_TEXT),
                new Location(Country.USA, EXPECTED_DEFAULT_ENG_TEXT));
    }

    private static class Location {
        private Country country;
        private String expectedText;

        public Location(Country country, String expectedText) {
            this.country = country;
            this.expectedText = expectedText;
        }

        public Country getCountry() {
            return country;
        }

        public String getExpectedText() {
            return expectedText;
        }
    }
}