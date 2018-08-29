package com.terrylmay.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

public class HelloWorldController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldController.class);

    public static void logging() {
        LOGGER.info("HelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorldHelloWorld");
    }

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("log4j2.contextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
        Long startTime = System.currentTimeMillis();
        int threadCount = 16;
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int index = 0; index < threadCount; index++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 100000; i++) {
                        logging();
                    }
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        Long endTime = System.currentTimeMillis();
        LOGGER.info("total cost time is:{}", (endTime - startTime));
    }
}
