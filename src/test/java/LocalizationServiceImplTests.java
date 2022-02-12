import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

public class LocalizationServiceImplTests {
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
    public void testLocale (Country country, String expected) {
        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);
        //act
        String current = localizationService.locale(country);
        //assert
        Assertions.assertEquals(expected, current);
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of(Country.RUSSIA, "Добро пожаловать"), Arguments.of(Country.USA, "Welcome"),
                Arguments.of(Country.BRAZIL, "Bienvenido"), Arguments.of(Country.FINLAND, "Tervetuloa"),
                Arguments.of(Country.FRANCE, "Bienvenue"), Arguments.of(Country.GERMANY, "Herzlich willkommen"),
                Arguments.of(Country.ITALY, "Benvenuti"));
    }
}