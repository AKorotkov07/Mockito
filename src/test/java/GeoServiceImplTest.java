import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceImplTest {
    private GeoService geoService;

    @Before
    public void setUp() {
        geoService = new GeoServiceImpl();
    }

    @Test
    public void testByIpWithLocalhostIp() {
        String ip = GeoServiceImpl.LOCALHOST;

        Location result = geoService.byIp(ip);
        assertEquals(new Location(null, null, null, 0), result);
    }

    @Test
    public void testByIpWithMoscowIp() {
        String ip = GeoServiceImpl.MOSCOW_IP;
        GeoService geoServiceMock = mock(GeoService.class);
        when(geoServiceMock.byIp(ip)).thenReturn(new Location("Moscow", Country.RUSSIA, "Lenina", 15));

        Location result = geoServiceMock.byIp(ip);
        assertEquals(new Location("Moscow", Country.RUSSIA, "Lenina", 15), result);
    }

    @Test
    public void testByIpWithNewYorkIp() {
        String ip = GeoServiceImpl.NEW_YORK_IP;
        GeoService geoServiceMock = mock(GeoService.class);
        when(geoServiceMock.byIp(ip)).thenReturn(new Location("New York", Country.USA, "10th Avenue", 32));

        Location result = geoServiceMock.byIp(ip);
        assertEquals(new Location("New York", Country.USA, "10th Avenue", 32), result);
    }

    @Test
    public void testByIpWithOtherIp() {
        String ip = "192.168.0.1";
        GeoService geoServiceMock = mock(GeoService.class);
        when(geoServiceMock.byIp(ip)).thenReturn(null);

        Location result = geoServiceMock.byIp(ip);
        assertEquals(null, result);
    }
}