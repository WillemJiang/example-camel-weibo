package org.apache.camel.example.weibo;

import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;

public class Main {

    public static void main(String args[]) throws Exception {
        CamelContext context = new DefaultCamelContext();
        context.addRoutes(new WeiboMeetingRoute());
        context.start();
        Thread.sleep(12000);
        context.stop();
    }
}
