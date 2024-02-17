import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceImplTest {
    private LocalizationServiceImpl localizationService;

    @Before
    public void setUp() {
        localizationService = new LocalizationServiceImpl();
    }

    @Test
    public void testLocaleForRussia() {
        assertEquals("Добро пожаловать", localizationService.locale(Country.RUSSIA));
    }

    @Test
    public void testLocaleForUSA() {
        assertEquals("Welcome", localizationService.locale(Country.USA));
    }
}
