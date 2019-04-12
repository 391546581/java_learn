package com.example.demo;

/**
 * @Auther: wangcs
 * @Date: 2019/4/9 09:22
 * @Description:
 */
public class ShowSpaceDemo {

    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime(); // 获取 Runtime 实例化对象
        System.out.println("MAX_MEMBER：" + (runtime.maxMemory()/1024/1024)); // 最大可用内存 maxMemory：默认大小为当前物理内存的1/4。
        System.out.println("TOTAL_MEMBER：" + (runtime.totalMemory()/1024/1024)); // 默认的可用内存 默认大小为当前物理内存的1/64

//   针对整个堆内存: -Xms：设置初始化的内存分配大小；-Xmx：设置最大的可用内存空间。
//　　年轻代：
//　　所以在这个环节里面就需要考虑两个技术名词：BTP、TLAB
//　　BTP：在伊甸园区采用栈的形式将最晚创建的对象保存在栈顶。
//　　TLAB：分块保存，适合于多线程的处理操作上。
//　　-Xmn：设置年轻代的空间大小，默认采用的是物理内存的1/64
//　　-Xss：设置每一个线程所占用的栈的大小［大约3000多个，一般都完全够用，这块不用太关注和调整］
//　　-X:SurvivorRatio：设置伊甸园区与两个存活区之间的内存分配比，默认8:1:1。
//        老年代：
//　　与年轻代比率：-XX:NewRatio
//　　当对象很大的时候往往不在年轻代进行保存，而是直接晋级到老年代，可利用“-XX:PretenureSizeThreshold”设置。
//        JDK1.8 之后取消了所谓的永久代，而变为了元空间（不在堆内存里面保存，而是直接利用物理内存保存。）
//        输出GC详细过程，以前使用的：-Xms48m -Xmx48m -XX:+PrintGCDetails，新版本需使用：-Xms48m -Xmx48m -Xlog:gc*

//　a.串行GC：-Xms48m -Xmx48m -Xlog:gc* -XX:+UseSerialGC
//        b.并行 GC：-Xms48m -Xmx48m -Xlog:gc* -XX:+UseParallelGC
//        c.并行年轻代 GC：-Xms48m -Xmx48m -Xlog:gc* -XX:+UseParallelNewGC
//　　d.并行老年代 GC：-Xms48m -Xmx48m -Xlog:gc* -XX:+UseParallelOldGC

//        采用G1收集参数为：-Xms48m -Xmx48m -Xlog:gc* -XX:+UseG1GC
    }
}
