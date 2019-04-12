package com.Thread;

/**
 * @Auther: wangcs
 * @Date: 2019/4/9 18:08
 * @Description:
 */
public class TestVolatileMain {

    public static void main(String[] args) {

        ThreadDemo td = new ThreadDemo();
        ThreadDemo td1 = new ThreadDemo();
        ThreadDemo td2 = new ThreadDemo();
        new Thread(td).start();
        new Thread(td1).start();
        new Thread(td2).start();

        while (true) {
            if (td.isFlag()) {
                System.out.println("-----------------");
                break;
            }
        }
    }

   static class ThreadDemo implements Runnable {
        private volatile boolean flag = false;

        public void run() {
            try {
                // 该线程 sleep(200), 导致了程序无法执行成功
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            flag = true;

            System.out.println("flag=" + isFlag());
        }

        public boolean isFlag() {
            return flag;
        }

        public void setFlag(boolean flag) {
            this.flag = flag;
        }
    }
}
