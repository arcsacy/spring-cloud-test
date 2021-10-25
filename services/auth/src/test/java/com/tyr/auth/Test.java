package com.tyr.auth;

import java.util.Arrays;
import java.util.List;

/**
 * @Class : Test
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/12 10:33
 * @Version : 1.0
 */
public class Test {

    public static void main(String[] args) {
       String str = "jjjj";
       String[] arr = str.split(",");
        List<String> list = Arrays.asList(arr);
       for(int i=0;i<list.size();i++)
       System.out.println(list.get(i));
    }

}
