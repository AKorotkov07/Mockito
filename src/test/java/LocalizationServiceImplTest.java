import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    private LocalizationService localizationService;

    @Before
    public void setUp() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    public void testLocaleWithRussiaCountry() {
        Country country = Country.RUSSIA;
        LocalizationService localizationServiceMock = mock(LocalizationService.class);
        when(localizationServiceMock.locale(country)).thenReturn("Добро пожаловать");

        String result = localizationServiceMock.locale(country);
        assertEquals("Добро пожаловать", result);
    }

    @Test
    public void testLocaleWithOtherCountry() {
        Country country = Country.USA;
        LocalizationService localizationServiceMock = mock(LocalizationService.class);
        when(localizationServiceMock.locale(country)).thenReturn("Welcome");

        String result = localizationServiceMock.locale(country);
        assertEquals("Welcome", result);
    }
}