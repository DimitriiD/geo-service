import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

class GeoServiceImplTest {

    private Country expectedCountry;
    private Country resultTestCountry;

    @BeforeEach
    void createStubsForExternalServices() {

        var geoService = new GeoServiceImpl();
        var expectedLocation = new Location("", Country.RUSSIA, "", 0);
        expectedCountry = expectedLocation.getCountry();
        resultTestCountry = geoService.byIp(GeoServiceImpl.MOSCOW_IP).getCountry();
    }

    @Test
    void byIpShouldReturnCorrectLocation() {
        Assertions.assertEquals(expectedCountry, resultTestCountry);
    }
}