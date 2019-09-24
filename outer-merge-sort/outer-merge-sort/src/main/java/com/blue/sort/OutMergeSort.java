package com.blue.sort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * @author BlueWang
 * @ClassName: OutMergeSort
 * @Description: 外部 归并排序
 * 指定JVM参数 java -Xms10m -Xmx10m
 * @date 2019/9/23 16:42
 */
public class OutMergeSort {

    public static void main(String[] args) throws IOException {
//        generateFile();

        readFile();
    }

    static void generateFile() throws IOException {
        final BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("src/main/resources/gene1.txt"),UTF_8);
        int count = 100000;
        while (count-->0) {
            final Random random = new Random();
            final int bits = 7;
            StringBuffer s = new StringBuffer();
            for(int i=1;i<=bits;i++ ){
                s.append(random.nextInt(10));
            }
            bufferedWriter.write(Integer.valueOf(s.toString()));
            bufferedWriter.newLine();
        }
        bufferedWriter.flush();
    }

    /**
     * 外部归并排序
     */
    static void readFile() throws IOException {
        BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("src/main/resources/res.txt"), StandardOpenOption.TRUNCATE_EXISTING);
        bufferedWriter.write("");
        bufferedWriter.flush();

        bufferedWriter = Files.newBufferedWriter(Paths.get("src/main/resources/repeat.txt"), StandardOpenOption.TRUNCATE_EXISTING);
        bufferedWriter.write("");
        bufferedWriter.flush();

        List result = new ArrayList(40000);
        int count = 0;
            final BufferedReader reader = Files.newBufferedReader(Paths.get("src/main/resources/gene.txt"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                if (result.size() < 40000) {
                    result.add(Integer.valueOf(line));
                } else {
                    splitFile(result, count++ );
                    result.clear();
                }
            }
            if(result.size()>0){
                splitFile(result, count++ );
                result.clear();
            }

            readCountFile(count);
    }

    private static void splitFile(List result, int i) {
        Collections.sort(result);
        final BufferedWriter bufferedWriter;
        try {
            final Path path = Paths.get("src/main/resources/" + i + ".txt");
            bufferedWriter = Files.newBufferedWriter(path, StandardOpenOption.CREATE);
            for (int i1 = 0; i1 < result.size(); i1++) {
                bufferedWriter.write(result.get(i1).toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void readCountFile(int count) {
        try {
            List<BufferedReader> list = new ArrayList();
            List<int[]> temp = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                final Path path = Paths.get("src/main/resources/" + i + ".txt");
                list.add(Files.newBufferedReader(path));
                temp.add(new int[10000]);
            }
            int[] begin = new int[count];

            sortMulti(list,temp,begin);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sortMulti(final List<BufferedReader> list, List<int[]> temp, int[] index) {
        while(true) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    String line = null;
                    for (int i1 = 0; i1 < temp.get(i).length; i1++) {
                        if((line =  list.get(i).readLine())!=null){
                            temp.get(i)[i1] = Integer.valueOf(line).intValue();
                        }else{

                            int[] last = new int[i1];
                            System.arraycopy(temp.get(i),0,last,0,i1);
                            temp.set(i,last);
                            break;
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            boolean flag = false;
            for (int i = 0; i < temp.size(); i++) {
                System.out.print(String.format("%s,",temp.get(i).length));
                if(temp.get(i).length!=0){
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
            System.out.println("");
            temp = sortAndWrite(temp, index);
        }
    }


    private static List<int[]> sortAndWrite(List<int[]> temp, int[] index) {
        List sortR = new ArrayList();
        List repeatR = new ArrayList();
        boolean flag =true;
        while (flag) {
            int r = 0;
            for (int i = 1; i < temp.size(); i++) {
                if(temp.get(r).length<=index[r]){
                    r = i;
                }
                if ((temp.get(i)!=null)
                    && (temp.get(i).length>index[i])){
                    if(temp.get(i)[index[i]] < temp.get(r)[index[r]] ){
                        r = i ;
                    }else if(temp.get(i)[index[i]]== temp.get(r)[index[r]]){
                        repeatR.add(temp.get(i)[index[i]]);
                    }
                }

            }
            sortR.add(temp.get(r)[index[r]]);
            index[r] ++;
            if(index[r]==temp.get(r).length){
                flag =false;
                for (int i = 0; i < temp.size(); i++) {
                    if(index[i]<temp.get(i).length){
                        int[] last = new int[temp.get(i).length];
                        System.arraycopy(temp.get(i),index[i],last,0,temp.get(i).length-index[i]);
                        temp.set(i, last);
                    }else{
                        temp.set(i, new int[temp.get(i).length]);
                    }
                    index[i]=0;
                }
            }

        }

        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("src/main/resources/res.txt"), StandardOpenOption.APPEND);
            for (int i = 0; i < sortR.size(); i++) {
                bufferedWriter.write(sortR.get(i).toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            sortR.clear();

            bufferedWriter = Files.newBufferedWriter(Paths.get("src/main/resources/repeat.txt"), StandardOpenOption.APPEND);
            for (int i = 0; i < repeatR.size(); i++) {
                bufferedWriter.write(repeatR.get(i).toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
            repeatR.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }

}
