/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
