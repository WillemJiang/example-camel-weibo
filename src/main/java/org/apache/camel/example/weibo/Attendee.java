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


import java.util.Date;

public class Attendee {

    private final String name;
    private final Date date;
    private String question;


    public Attendee(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    public String toString() {
        String answer;
        if (question == null) {
            answer = name +  " joined the meeting at " + date;
        } else {
            answer = name +  " joined the meeting at " + date + "with question " + question;
        }
        return answer;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

}
