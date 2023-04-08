package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {
    MessageSender messageSender;
    GeoService geoService;
    LocalizationService localizationService;


    @BeforeEach
    void init() {
        geoService = Mockito.mock(GeoService.class);
        localizationService = Mockito.mock(LocalizationService.class);
        messageSender = new MessageSenderImpl(geoService, localizationService);
    }

    @Test
    void testSendRu() {
        String testIP = "172.";

        Map<String, String> testHeaders = new HashMap<>();
        testHeaders.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIP);

        Location testLocation = new Location("Sochi", Country.RUSSIA, null, 0);

        String testMessage = "Добро пожаловать";

        Mockito.when(geoService.byIp(testIP)).thenReturn(testLocation);
        Mockito.when(localizationService.locale(testLocation.getCountry())).thenReturn(testMessage);

        Assertions.assertEquals("Добро пожаловать", messageSender.send(testHeaders));
    }

    @Test
    void testSendEng() {
        String testIP = "96.";

        Map<String, String> testHeaders = new HashMap<>();
        testHeaders.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIP);

        Location testLocation = new Location("Dallas", Country.USA, null, 0);

        String testMessage = "Welcome";

        Mockito.when(geoService.byIp(testIP)).thenReturn(testLocation);
        Mockito.when(localizationService.locale(testLocation.getCountry())).thenReturn(testMessage);

        Assertions.assertEquals("Welcome", messageSenderпше .send(testHeaders));
    }
}