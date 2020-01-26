package com.ds.dp;

import java.util.Arrays;

/****
 i
 0   1   2   3   4   5   6   7   8
 A   A   B   A   A   B   A   A   A
 0   1   0   1   2   3   4   5

 i
 0   1   2   3   4   5   6   7   8
 A   A   B   A   A   B   A   A   A
 j

 0   1   oo
 A   A   B   A   A   B
 A   A   B   A   A   A
 */

public class KMP {


    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
//        String str2 = "AABAABAAA";
        String str2 = "ABCDABD";
        int[] prefixTable = computePrefixFunction(str2);
        System.out.println(Arrays.toString(prefixTable));
        System.out.println(kmpSearch(str1, str2, prefixTable));

    }

    /**
     * @param str 字符串
     * @return 字符串的最大公共前后缀
     */
    public static int[] computePrefixFunction(String str) {
        int[] next = new int[str.length()];
        next[0] = 0;
        for (int i = 1, j = 0; i < str.length(); i++) {
            //对不上
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                //hard to understand
                j = next[j - 1];

            }


            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;

    }

    public static int kmpSearch(String str1, String str2, int[] prefixTable) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            //匹配不上时，查询前缀表
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                //根据前缀表对齐str2的 j下标，减少比较
                j = prefixTable[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1;
            }

        }
        return -1;
    }


}
