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
import org.apache.camel.component.websocket.WebsocketComponent;

import static org.apache.camel.example.weibo.Utils.getAccessToken;

public class WeiboMeetingRoute extends RouteBuilder {

    // setup the access token


    @Override
    public void configure() throws Exception {
        // setup Camel web-socket component on the port we have defined
        WebsocketComponent wc = getContext().getComponent("websocket", WebsocketComponent.class);
        wc.setPort(9090);
        // we can serve static resources from the classpath: or file: system
        wc.setStaticResources("classpath:.");
        // Using the position of Beijing
        Position myPosition = new Position(116.327621, 39.982563, 0.1);
        // create the client with a weibo client
        fromF("weibo://timeline/mentions/?accessToken=%s&delay=30&initialDelay=30&lastId=%s", getAccessToken(), "3516005995671734")
                .choice().when().method(myPosition, "isNearBy")
                    .to("direct:meeting")
                .otherwise()
                    .to("log:there");

        from("direct:meeting").convertBodyTo(Attendance.class).to("log:here").to("websocket:camel-weibo?sendToAll=true");

    }
}
