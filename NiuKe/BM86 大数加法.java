// //https://www.nowcoder.com/practice/11ae12e8c6fe48f883cad618c2e81475?tpId=295&tqId=1061819&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E9%259D%25A2%25E8%25AF%2595%26topicId%3D295

// 描述
// 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。

// 数据范围：
// s
// .
// l
// e
// n
// g
// t
// h
// ,
// t
// .
// l
// e
// n
// g
// t
// h
// ≤
// 100000
// s.length,t.length≤100000，字符串仅由'0'~‘9’构成
// 要求：时间复杂度 
// O
// (
// n
// )
// O(n)
// 示例1
// 输入：
// "1","99"
// 复制
// 返回值：
// "100"
// 复制
// 说明：
// 1+99=100       
// 示例2
// 输入：
// "114514",""
// 复制
// 返回值：
// "114514"

import java.util.*;


public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 计算两个数之和
     * @param s string字符串 表示第一个整数
     * @param t string字符串 表示第二个整数
     * @return string字符串
     */
    public String solve(String s, String t) {
        // 初始化结果存储
        StringBuilder result = new StringBuilder();

        // 两个指针分别指向s和t的末尾
        int i = s.length() - 1;
        int j = t.length() - 1;

        int carry = 0; // 进位

        // 遍历s和t，逐位相加
        while (i >= 0 || j >= 0 || carry != 0) {
            int digitS = i >= 0 ? s.charAt(i) - '0' : 0; // 获取s的当前位
            int digitT = j >= 0 ? t.charAt(j) - '0' : 0; // 获取t的当前位

            int sum = digitS + digitT + carry; // 当前位的总和
            carry = sum / 10; // 计算新的进位
            result.append(sum % 10); // 当前位的结果

            i--; // 移动指针
            j--; // 移动指针
        }

        // 因为结果是从末尾开始加的，需要反转字符串
        return result.reverse().toString();
    }
}
