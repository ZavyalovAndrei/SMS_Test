import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GeoServiceImplTests {

    @BeforeEach
    public void init() {
    }

    @BeforeAll
    public static void started() {
        System.out.println("Tests started.");
    }

    @AfterEach
    public void finished() {
        System.out.println("test completed.");
    }

    @AfterAll
    static void finishedAll() {
        System.out.println("Tests completed.");
    }

    @ParameterizedTest
    @MethodSource("source")
    public void testByIp(String ip, Country data) {
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        //act
        Country result = geoService.byIp(ip).getCountry();
        //assert
        assertEquals(data, result);
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of("172.123.12.19", Country.RUSSIA),
                Arguments.of("96.889.55.32", Country.USA),
                Arguments.of("95.215.65.72", Country.FINLAND),
                Arguments.of("93.662.61.60", Country.FRANCE),
                Arguments.of("185.466.78.37", Country.ITALY),
                Arguments.of("102.335.21.20", Country.GERMANY),
                Arguments.of("104.552.13.44", Country.BRAZIL)
        );
    }

    @Test
    public void testByCoordinates() {
        //arrange
        double latitude = 38.870987;
        double longitude = -77.055964;
        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);
        //act
        RuntimeException thrown = Assertions.assertThrows(RuntimeException.class, () -> {
            geoService.byCoordinates(latitude, longitude);
        });
        //assert
        Assertions.assertEquals("Not implemented", thrown.getMessage());
    }
}