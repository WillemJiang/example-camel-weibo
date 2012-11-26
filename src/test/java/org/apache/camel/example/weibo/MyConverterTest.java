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
