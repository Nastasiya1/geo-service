package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    GeoServiceImpl geoService = new GeoServiceImpl();

    @Test
    void RussiaByIpTest() {
        assertEquals(geoService.byIp("172.").getCountry(), Country.RUSSIA);
        assertNotEquals(geoService.byIp("172.").getCountry(), Country.USA);
    }

    @Test
    void USAByIpTest() {
        assertEquals(geoService.byIp("96.").getCountry(), Country.USA);
        assertNotEquals(geoService.byIp("96.").getCountry(), Country.RUSSIA);
    }

    @Test
    void byCoordinates() {
        Assertions.assertThrows(RuntimeException.class, () -> geoService.byCoordinates(0, 0)
        );
    }
}