package com.Thread;

/**
 * @Auther: wangcs
 * @Date: 2019/4/9 18:23
 * @Description:
 */
public class TestSynchronized {

//JDK 1.6中默认是开启偏向锁和轻量级锁的，我们也可以通过-XX:-UseBiasedLocking来禁用偏向锁
    public static void main(String[] args) throws InterruptedException {
//        Demo d = new Demo();
//        for(int i=0;i<3;i++){
//            Thread t1 = new Thread(d);
//            t1.start();
//        }

        ChildDemo cd = new ChildDemo();
        for(int i=0;i<5;i++) {
            Thread ct1 = new Thread(cd);
            ct1.start();
//            ct1.join();
        }
    }



    static class Demo implements Runnable{
        boolean flag = true;

        @Override
        public synchronized void run() {
                if(flag){
                    System.out.println(Thread.currentThread().getName()+"I have a gift for U");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    flag = false;
                }else{
                    System.out.println(Thread.currentThread().getName()+"I lost a gift");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    static class ChildDemo extends TestSynchronized.Demo {
        @Override
        public void run() {
            if(flag){
                System.out.println(Thread.currentThread().getName()+"I have a gift for U");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = false;
            }else{
                System.out.println(Thread.currentThread().getName()+"I lost a gift");
            }
        }
    }

}
