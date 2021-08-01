package com.wk.mq;

import lombok.var;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        LongStream longStream = Stream.generate(new A()).mapToLong(Long::parseLong);
        longStream.limit(30).forEach(System.out::println);

        System.out.println(2_000_000);
        System.out.println(0b100000);
        System.out.println(0xff);
        System.out.println(2e5);
        System.out.println('中');
        int a = '中';
        System.out.println(a);
        var b = "wwwww ";
        System.out.println(b);
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }

        System.out.println(sum);
        float x = 1.5f;
        System.out.println(x/0 );

        String h = "profile=native";
        String[] s1 = h.split("\\=");
        String[] s2 = h.split("\\=",1);

        for (String s : s1) {
            System.out.println(s);
        }

        System.out.println("=========================================");
        for (String s : s2) {
            System.out.println(s);
        }
    }

    static class A implements Supplier<String> {

        private Long first;
        private Long val;

        @Override
        public String get() {
            if (first == null){
                first = 1L;
                return first.toString();
            }

            if (val == null){
                val = 1L;
                return val.toString();
            }

            long tmp = first + val;
            first = val;
            val = tmp;
            return Long.toString(tmp);
        }
    }
}
