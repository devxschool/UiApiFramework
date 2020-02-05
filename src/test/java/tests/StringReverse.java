package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringReverse {
    public static void main(String[] args) {
        String str = "I study at DevX";
       String[] str1 = str.split(" ");
        List<String> list = new ArrayList<>();

        for (String s:str1) {
            StringBuilder sb = new StringBuilder(s);
            sb.reverse();
            list.add(sb.toString());
        }
        System.out.println( Arrays.toString(list.toArray()));

        }

    }

