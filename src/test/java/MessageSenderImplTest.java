    import org.junit.Before;
    import org.junit.Test;
    import static org.junit.Assert.assertEquals;
    import static org.junit.Assert.assertTrue;
    import static org.mockito.Mockito.mock;
    import static org.mockito.Mockito.when;
    import java.util.HashMap;
    import java.util.Map;
    import ru.netology.entity.Country;
    import ru.netology.entity.Location;
    import ru.netology.geo.GeoService;
    import ru.netology.i18n.LocalizationService;
    import ru.netology.sender.MessageSender;
    import ru.netology.sender.MessageSenderImpl;

    public class MessageSenderImplTest {
        private MessageSender messageSender;
        private GeoService geoService;
        private LocalizationService localizationService;



        @Before
        public void setUp() {
            geoService = mock(GeoService.class);
            localizationService = mock(LocalizationService.class);
            messageSender = new MessageSenderImpl(geoService, localizationService);
        }

        @Test
        public void testSendWithRussianIp() {
            String ipAddress = "172.0.32.11";
            Location location = new Location("Moscow", Country.RUSSIA, "Lenina", 15);
            when(geoService.byIp(ipAddress)).thenReturn(location);
            when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

            Map<String, String> headers = new HashMap<>();
            headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);
            String result = messageSender.send(headers);

            assertTrue(result.contains("Добро пожаловать"));
        }

        @Test
        public void testSendWithAmericanIp() {
            String ipAddress = "96.44.183.149";
            Location location = new Location("New York", Country.USA, "10th Avenue", 32);
            when(geoService.byIp(ipAddress)).thenReturn(location);
            when(localizationService.locale(Country.USA)).thenReturn("Welcome (New York, 10th Avenue)");

            Map<String, String> headers = new HashMap<>();
            headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);
            String result = messageSender.send(headers);

            assertTrue(result.contains("Welcome (New York, 10th Avenue)"));
        }

        @Test
        public void testSendWithInvalidIp() {
            String ipAddress = "192.168.0.1";
            when(geoService.byIp(ipAddress)).thenReturn(null);
            when(localizationService.locale(Country.USA)).thenReturn("Welcome");

            Map<String, String> headers = new HashMap<>();
            headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);
            String result = messageSender.send(headers);

            assertEquals("Welcome", result);
        }

        @Test
        public void testSendWithEmptyIp() {
            String ipAddress = "";
            when(localizationService.locale(Country.USA)).thenReturn("Welcome");

            Map<String, String> headers = new HashMap<>();
            headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipAddress);
            String result = messageSender.send(headers);

            assertEquals("Welcome", result);
        }
    }