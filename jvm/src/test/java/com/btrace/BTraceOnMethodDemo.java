package com.btrace;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: wangcs
 * @Date: 2019/4/9 11:16
 * @Description:
 */
public class BTraceOnMethodDemo {

    public static void main(String[] args) {

        try {

            TimeUnit.SECONDS.sleep(15);

        } catch (InterruptedException e) {

            e.printStackTrace();

        }

        System.out.println("start main method...");

        new Thread(() -> {

            while (true) {

                try {

                    TimeUnit.SECONDS.sleep(1);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

            }

        }).start();

    }
}
