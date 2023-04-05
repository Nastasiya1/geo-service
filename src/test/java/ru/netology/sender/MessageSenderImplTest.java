package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {
    MessageSenderImpl messageSenderImpl;
    GeoServiceImpl geoServiceImpl;
    LocalizationServiceImpl localizationServiceImpl;


    @BeforeEach
    void init() {
        geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        localizationServiceImpl = Mockito.mock(LocalizationServiceImpl.class);
        messageSenderImpl = new MessageSenderImpl(geoServiceImpl, localizationServiceImpl);
    }

    @Test
    void testSendRu() {
        String testIP = "172.";

        Map<String, String> testHeaders = new HashMap<>();
        testHeaders.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIP);

        Location testLocation = new Location("Sochi", Country.RUSSIA, null, 0);

        String testMessage = "Добро пожаловать";

        Mockito.when(geoServiceImpl.byIp(testIP)).thenReturn(testLocation);
        Mockito.when(localizationServiceImpl.locale(testLocation.getCountry())).thenReturn(testMessage);

        Assertions.assertEquals("Добро пожаловать", messageSenderImpl.send(testHeaders));
    }

    @Test
    void testSendEng() {
        String testIP = "96.";

        Map<String, String> testHeaders = new HashMap<>();
        testHeaders.put(MessageSenderImpl.IP_ADDRESS_HEADER, testIP);

        Location testLocation = new Location("Dallas", Country.USA, null, 0);

        String testMessage = "Welcome";

        Mockito.when(geoServiceImpl.byIp(testIP)).thenReturn(testLocation);
        Mockito.when(localizationServiceImpl.locale(testLocation.getCountry())).thenReturn(testMessage);

        Assertions.assertEquals("Welcome", messageSenderImpl.send(testHeaders));
    }
}