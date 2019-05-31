package ru.stqa.pft.soap;

import com.lavasoft.GeoIPService;
import com.lavasoft.GeoIPServiceSoap;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GeoIpServiceTests {
    @Test
    public void testMyIp() {
        String ipLocation = new GeoIPService().getGeoIPServiceSoap12().getIpLocation("109.86.174.236");
        Assert.assertEquals(ipLocation,"UA");
    }
}
