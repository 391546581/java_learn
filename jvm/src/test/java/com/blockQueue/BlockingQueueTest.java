package com.blockQueue;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueueTest {

    private static final int queueSize = 3;
    private static final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(queueSize);
    private static final int produceSpeed = 2000;//生产速度(越小越快)
    private static final int consumeSpeed = 10;//消费速度(越小越快)

    public static void main(String[] args) {
        Thread producer = new Producer();
        Thread consumer = new Consumer();
        producer.start();
        consumer.start();
    }

    static class Producer extends Thread {
        public void run() {
            while (true) {
                try {
                    System.out.println("老板准备炸油条了，架子上还能放：" + (queueSize - queue.size()) + "根油条");
                    queue.put("1根油条");
                    System.out.println("老板炸好了1根油条，架子上还能放：" + (queueSize - queue.size()) + "根油条");
                    Thread.sleep(produceSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer extends Thread {
        public void run() {
            while (true) {
                try {
                    System.out.println("A 准备买油条了，架子上还剩" + queue.size() + "根油条");
                    queue.take();
                    System.out.println("A 买到1根油条，架子上还剩" + queue.size() + "根油条");
                    Thread.sleep(consumeSpeed);

                    System.out.println("B 准备买油条了，架子上还剩" + queue.size() + "根油条");
                    queue.take();
                    System.out.println("B 买到1根油条，架子上还剩" + queue.size() + "根油条");
                    Thread.sleep(consumeSpeed);

                    System.out.println("C 准备买油条了，架子上还剩" + queue.size() + "根油条");
                    queue.take();
                    System.out.println("C 买到1根油条，架子上还剩" + queue.size() + "根油条");
                    Thread.sleep(consumeSpeed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
