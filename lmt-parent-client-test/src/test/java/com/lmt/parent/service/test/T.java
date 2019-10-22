package com.lmt.parent.service.test;

/**
 * @author bazhandao
 * @description 测试类
 * @date 2019/1/24 14:11
 * @since JDK1.8
 */
public class T {

    public static void main(String[] args) {
        String x = "u咕噜一边去！";
        System.out.println(toBinary(x));
        System.out.println(toReverseHex(x));
    }

    public static String toReverseHex(String str) {
        char[] arr = str.toCharArray();
        String s = "";
        for (char c : arr) {
            String binaryStr = Integer.toHexString(c);
            //填充补0
            int n = binaryStr.length() % 4;
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    binaryStr = "0" + binaryStr;
                }
            }
            s = s + " " + new StringBuilder(binaryStr).reverse().toString();
        }
        return s.trim();
    }

    public static String toBinary(String str) {
        StringBuilder sb = new StringBuilder();
        char[] arr = str.toCharArray();
        for (char c : arr) {
            String binaryStr = Integer.toBinaryString(c);
            //填充补0
            int n = binaryStr.length() % 4;
            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    binaryStr = "0" + binaryStr;
                }
            }
            char[] chars = binaryStr.toCharArray();
            StringBuilder c1 = new StringBuilder(" ");
            String tmp = "";
            for (char s : chars) {
                tmp = tmp + s;
                if (tmp.length() % 4 == 0) {
                    c1.append(tmp).append(" ");
                    tmp = "";
                }
            }
            sb.append(c1);
        }
        return sb.toString().trim();
    }

}
