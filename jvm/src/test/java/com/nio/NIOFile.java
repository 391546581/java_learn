package com.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Auther: wangcs
 * @Date: 2019/4/9 17:06
 * @Description:
 */
public class NIOFile {
    public static void method1(){
        RandomAccessFile aFile = null;
        FileInputStream fin = null;
        try{
//            aFile = new RandomAccessFile("./nio.txt","rw");
//            aFile = new RandomAccessFile("/nio.txt","r");

            fin = new FileInputStream(new File("D:\\GitHUB\\IdeaProjects\\springcloud-book\\springcloud-book\\jvm\\target\\classes\\nio.txt"));
            FileChannel fileChannel = fin.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while(bytesRead != -1)
            {
                buf.flip();
                while(buf.hasRemaining())
                {
                    System.out.print((char)buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(aFile != null){
                    aFile.close();
                }
                if(fin != null){
                    fin.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        method1();
    }
}
