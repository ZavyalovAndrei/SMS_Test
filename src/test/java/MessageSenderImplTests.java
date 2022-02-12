import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class MessageSenderImplTests {

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
    public void testMessageSenderImpl(Map<String, String> ip, String expected) {
        LocalizationServiceImpl localizationService = Mockito.spy(LocalizationServiceImpl.class);

        GeoServiceImpl geoService = Mockito.spy(GeoServiceImpl.class);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String current = messageSender.send(ip);
        Assertions.assertEquals(expected, current);
    }

    public static Stream<Arguments> source() {
        return Stream.of(Arguments.of(new HashMap<String, String>() {{
                                          put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
                                      }},
                        "Добро пожаловать"),
                Arguments.of(new HashMap<String, String>() {{
                                 put(MessageSenderImpl.IP_ADDRESS_HEADER, "95.92.15.94");
                             }},
                        "Tervetuloa"),
                Arguments.of(new HashMap<String, String>() {{
                                 put(MessageSenderImpl.IP_ADDRESS_HEADER, "93.562.76.98");
                             }},
                        "Bienvenue"),
                Arguments.of(new HashMap<String, String>() {{
                                 put(MessageSenderImpl.IP_ADDRESS_HEADER, "104.222.17.88");
                             }},
                        "Bienvenido"),
                Arguments.of(new HashMap<String, String>() {{
                                 put(MessageSenderImpl.IP_ADDRESS_HEADER, "102.102.88.19");
                             }},
                        "Herzlich willkommen"),
                Arguments.of(new HashMap<String, String>() {{
                                 put(MessageSenderImpl.IP_ADDRESS_HEADER, "185.118.47.31");
                             }},
                        "Benvenuti"),
                Arguments.of(new HashMap<String, String>() {{
                                 put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.183.55.63");
                             }},
                        "Welcome"));
    }
}