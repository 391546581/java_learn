package com.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: wangcs
 * @Date: 2019/4/10 09:30
 * @Description:
 */
public class TestSet {

    public static void main(String[] args)
    {
        Set<Name> s = new HashSet<Name>();
        s.add(new Name("abc", "123"));
        System.out.println(
                s.contains(new Name("abc", "123")));
    }

    static class Name
    {
        private String first;
        private String last;

        public Name(String first, String last)
        {
            this.first = first;
            this.last = last;
        }

        public boolean equals(Object o)
        {
            if (this == o)
            {
                return true;
            }

            if (o.getClass() == Name.class)
            {
                Name n = (Name)o;
                return n.first.equals(first)
                        && n.last.equals(last);
            }
            return false;
        }

        // 根据 first 计算 Name 对象的 hashCode() 返回值
        public int hashCode()
        {
            return first.hashCode();
        }

        public String toString()
        {
            return "Name[first=" + first + ", last=" + last + "]";
        }
    }
}
