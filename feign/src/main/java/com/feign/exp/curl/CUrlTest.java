package com.feign.exp.curl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Auther: wangcs
 * @Date: 2019/4/11 20:03
 * @Description:
 */
public class CUrlTest {

//    static String[] cmds = {"curl", "-H", "Host: www.chineseconverter.com", "-H", "Cache-Control: max-age=0", "--compressed", "https://www.chineseconverter.com/zh-cn/convert/chinese-stroke-order-tool"};
    static String[] cmds = {"curl","-X","POST", "-H", "Content-Type: application/json", "--compressed", "http://192.168.101.53/branch_v3.4/index.php/b2cphone/order/showList?plat=c2b_android&ver=5.8.3&osUUID=95a584ebc40f8370&key=A3767CA20E88419F6C0FA769277BA2D5&pamAccount=13066891692"};



    public static String execCurl(String[] cmds){
        ProcessBuilder process = new ProcessBuilder(cmds);
        Process p;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            return builder.toString();

        } catch (IOException e) {
            System.out.print("error");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println( execCurl(CUrlTest.cmds) );
    }
}
