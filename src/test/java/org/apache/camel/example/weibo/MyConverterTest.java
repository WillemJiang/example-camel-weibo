package org.apache.camel.example.weibo;

import org.junit.Assert;
import org.junit.Test;

public class MyConverterTest extends Assert {
    @Test
    public void testGetCoordinates() throws Exception {
        Position position = MyConverter.getCoordinates("{\"type\":\"Point\",\"coordinates\":[39.983764,116.32695]}");
        assertEquals(116.32695, position.getLatitude(), 0);
        assertEquals(39.983764, position.getLongitude(), 0);
    }
}
