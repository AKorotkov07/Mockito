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
    public void testLocaleForRussia() {
        LocalizationService localizationServiceMock = mock(LocalizationService.class);
        when(localizationServiceMock.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }

    @Test
    public void testLocaleForUSA() {
        LocalizationService localizationServiceMock = mock(LocalizationService.class);
        when(localizationServiceMock.locale(Country.USA)).thenReturn("Welcome");

        assertEquals("Welcome", localizationService.locale(Country.USA));
    }
}
