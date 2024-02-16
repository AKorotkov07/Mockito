package ru.netology.sender;

import java.util.Map;

import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

public class MessageSenderImpl implements MessageSender {

    public static final String IP_ADDRESS_HEADER = "x-real-ip";
    private final GeoService geoService;

    private final LocalizationService localizationService;

    public MessageSenderImpl(GeoService geoService, LocalizationService localizationService) {
        this.geoService = geoService;
        this.localizationService = localizationService;
    }

    public String send(Map<String, String> headers) {
        String ipAddress = headers.get(IP_ADDRESS_HEADER);
        Location location = geoService.byIp(ipAddress);

        if (location == null) {
            return localizationService.locale(Country.USA);
        }

        // Добавляем проверку на null для полей локации
        String city = location.getCity();
        if (city == null) {
            city = "";
        }
        String street = location.getStreet();
        if (street == null) {
            street = "";
        }

        Country country = location.getCountry();
        String message = localizationService.locale(country);
        return message + " (" + city + ", " + street + ")";
    }
}
