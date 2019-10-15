package com.test;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author BlueWang
 * @ClassName: FutureTest
 * @Description:
 * @date 2019/9/24 17:11
 */
public class FutureTest {


    public static void main(String[] args) {
//        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        ThreadFactory factory = new NameTreadFactory();
        RejectedExecutionHandler handler = new MyIgnorePolicy();

        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 20, 60, TimeUnit.SECONDS, queue, factory, handler);
        executor.prestartAllCoreThreads(); // 预启动所有核心线程
        final Future[] result = new Future[20];
        for (int i = 0; i < 20; i++) {
            MyTask task = new MyTask(String.valueOf(i));
            result[i] = executor.submit(task);
        }

        ScheduledThreadPoolExecutor scheduled = new ScheduledThreadPoolExecutor(3);
        scheduled.scheduleAtFixedRate(() -> {
            final Future[] result1 = result;
            System.out.println(String.format("monitor queue size: %s", queue.size()));
        }, 0, 2, TimeUnit.SECONDS);

        ScheduledFuture<Future[]> schedule = scheduled.schedule(() -> {
            Future[] result1 = result;
            for (int i = 0; i < result1.length; i++) {
                if (result1[i].isDone()) {
                    try {
                        System.out.println(String.format("result[%s] get:%s", i, result[i].get().toString()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                    //把最后一个元素替代指定的元素
                    result1[i] = result1[result1.length - 1];
                    //数组缩容
                    result1 = Arrays.copyOf(result1, result1.length - 1);
                }
            }
            return result1;
        }, 10, TimeUnit.SECONDS);

        scheduled.scheduleAtFixedRate(() -> {
            while (schedule.isDone()) {
                final Future[] futures;
                try {
                    futures = schedule.get();
                    for (int i = 0; i < futures.length; i++) {
                            System.out.println(String.format("result[%s] get:%s", i, futures[i].get().toString()));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        }, 0,3, TimeUnit.SECONDS);

    }

    static class NameTreadFactory implements ThreadFactory {
        private final AtomicInteger mThreadNum = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r, "my-thread-" + mThreadNum.getAndIncrement());
            System.out.println(t.getName() + " has been created");
            return t;
        }
    }

    public static class MyIgnorePolicy implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            doLog(r, e);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            // 可做日志记录等
            System.err.println(r.toString() + " rejected");
//          System.out.println("completedTaskCount: " + e.getCompletedTaskCount());
        }
    }

    static class MyTask implements Callable {
        private String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public Integer call() {
            try {
                System.out.println(this.toString() + " is running!");
                Thread.sleep(3000); //让任务执行慢点
                return new Random().nextInt(999);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "MyTask [name=" + name + "]";
        }
    }
}
