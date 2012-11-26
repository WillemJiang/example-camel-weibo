package org.apache.camel.example.weibo;

import org.apache.camel.Converter;
import weibo4j.model.Status;

import java.util.regex.Pattern;

@Converter
public final class MyConverter {
    private MyConverter() {
        // Helper class
    }

    public static Position getCoordinates(String geo) throws Exception {
        Pattern p1 = Pattern.compile(".*:\\[*");
        Pattern p2 = Pattern.compile("]}");
        geo = p1.matcher(geo).replaceAll("");
        geo = p2.matcher(geo).replaceAll("");

        String[] result = geo.split(",");
        return new Position(Double.valueOf(result[1]), Double.valueOf(result[0]));
    }


    @Converter
    public static Position toPosition(Status status) {
        if (status.getGeo() != null) { // try to get the position from status Geo String
           try {
               return getCoordinates(status.getGeo());
           } catch (Exception ex) {
               // do nothing here
           }
        }
        // using the status information by default
        return new Position(status.getLatitude(), status.getLongitude());

    }


}
