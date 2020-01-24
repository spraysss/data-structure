package com.ds.dp;

import java.util.Arrays;

/**
     B  D  C  A  B  A
  0, 0, 0, 0, 0, 0, 0
A 0, 0, 0, 0, 1, 1, 1
B 0, 1, 1, 1, 1, 2, 2
C 0, 1, 1, 2, 2, 2, 2
B 0, 1, 1, 2, 2, 3, 3
D 0, 1, 2, 2, 2, 3, 3
A 0, 1, 2, 2, 3, 3, 4
B 0, 1, 2, 2, 3, 4, 4
 */
public class LCS {
    public static void doLCS(String X, String Y) {
        int m = X.length();
        int n = Y.length();
        String Z = "";
        int[][] c = new int[m + 1][n + 1];
        char[][] path = new char[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    c[i][j] = c[i - 1][j - 1] + 1;
                    path[i][j]='↖';
                } else if (c[i - 1][j] >= c[i][j - 1]) {
                    c[i][j] = c[i - 1][j];
                    path[i][j] = '←';
                } else {
                    c[i][j] = c[i][j - 1];
                    path[i][j] = '↑';
                }
            }
        }

        Arrays.stream(c).forEach(e-> System.out.println(Arrays.toString(e)));

        System.out.print("最长公共子序列:");
        printLCS(path,X,m,n);
        System.out.println();
        System.out.println("最长公共子序列长度:"+c[m][n]);


    }

    public static String s;
    public static void printLCS(char[][] b, String X, int i, int j) {
        
        if (i == 0 || j == 0) {
            return;
        }
        if (b[i][j] == '↖') {
            printLCS(b, X, i - 1, j - 1);
            System.out.print(X.charAt(i-1));
        } else if (b[i][j] ==  '←') {
            printLCS(b, X, i - 1, j);

        } else {
            printLCS(b, X, i, j - 1);
        }
    }

    public static void main(String[] args) {
        doLCS("ABCBDAB", "BDCABA");

    }
}
