import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MessageSenderImplTest {
    private MessageSender messageSender;
    private GeoService geoService;
    private LocalizationService localizationService;
    private Map<String, String> headers =  new HashMap<>();

    private static final String EXPECTED_TEXT_RUS = "Привет";
    private static final String LOCATION_MOSCOW = "Moscow";
    private static final String EXPECTED_TEXT_ENG = "Hello";
    private static final String LOCATION_NEW_YORK = "New York";


    @BeforeEach
    void createStubsForExternalServices() {
        geoService = Mockito.mock(GeoServiceImpl.class);
        localizationService = Mockito.mock(LocalizationServiceImpl.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    @DisplayName("Проверка отправки текста на русском языке для российского ip")
    void sendShouldReturnRussianTextForRussianIP() {
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.MOSCOW_IP);

        Mockito.when(geoService.byIp(GeoServiceImpl.MOSCOW_IP))
                .thenReturn(new Location(LOCATION_MOSCOW, Country.RUSSIA, null, 0));
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn(EXPECTED_TEXT_RUS);

        assertEquals(EXPECTED_TEXT_RUS, messageSender.send(headers));
    }

    @Test
    @DisplayName("Проверка отправки английского текста для не российского ip")
    void sendShouldReturnEnglishTextForNotRussianIP() {
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, GeoServiceImpl.NEW_YORK_IP);

        Mockito.when(geoService.byIp(GeoServiceImpl.NEW_YORK_IP))
                .thenReturn(new Location(LOCATION_NEW_YORK, Country.USA, null, 0));
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn(EXPECTED_TEXT_ENG);

        assertEquals(EXPECTED_TEXT_ENG, messageSender.send(headers));
    }
}