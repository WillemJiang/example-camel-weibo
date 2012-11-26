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


import org.apache.camel.builder.RouteBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class WeiboMeetingRoute extends RouteBuilder {

    // setup the access token
    public String getAccessToken() {

        URL url = getClass().getResource("/weibo.properties");

        InputStream inStream;
        try {
            inStream = url.openStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalAccessError("weibo.properties could not be found");
        }

        Properties properties = new Properties();
        try {
            properties.load(inStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new IllegalAccessError("weibo.properties could not be found");
        }

        return properties.get("access.token").toString();
    }

    @Override
    public void configure() throws Exception {
        // Using the position of Beijing
        Position myPosition = new Position(116.327621, 39.982563, 0.1);
        // create the client with a weibo client
        fromF("weibo://timeline/mentions/?accessToken=%s&delay=30&lastId=%s", getAccessToken(), "3516005995671734")
                .choice().when().method(myPosition, "isNearBy")
                    .to("log:here")
                .otherwise()
                    .to("log:there");
    }
}