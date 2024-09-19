// //https://www.nowcoder.com/practice/55fb3c68d08d46119f76ae2df7566880?tpId=295&tqId=1024725&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3D%25E7%25AE%2597%25E6%25B3%2595%25E9%259D%25A2%25E8%25AF%2595%26topicId%3D295

// 描述
// 编写一个函数来验证输入的字符串是否是有效的 IPv4 或 IPv6 地址

// IPv4 地址由十进制数和点来表示，每个地址包含4个十进制数，其范围为 0 - 255， 用(".")分割。比如，172.16.254.1；
// 同时，IPv4 地址内的数不会以 0 开头。比如，地址 172.16.254.01 是不合法的。

// IPv6 地址由8组16进制的数字来表示，每组表示 16 比特。这些组数字通过 (":")分割。比如,  2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。而且，我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。所以， 2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。

// 然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。 比如， 2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。
// 同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如， 02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。

// 说明: 你可以认为给定的字符串里没有空格或者其他特殊字符。

// 数据范围：字符串长度满足 
// 5
// ≤
// n
// ≤
// 50
// 5≤n≤50
// 进阶：空间复杂度 
// O
// (
// n
// )
// O(n)，时间复杂度 
// O
// (
// n
// )
// O(n)
// 示例1
// 输入：
// "172.16.254.1"
// 复制
// 返回值：
// "IPv4"
// 复制
// 说明：
// 这是一个有效的 IPv4 地址, 所以返回 "IPv4" 
// 示例2
// 输入：
// "2001:0db8:85a3:0:0:8A2E:0370:7334"
// 复制
// 返回值：
// "IPv6"
// 复制
// 说明：
// 这是一个有效的 IPv6 地址, 所以返回 "IPv6" 
// 示例3
// 输入：
// "256.256.256.256"
// 复制
// 返回值：
// "Neither"
// 复制
// 说明：
// 这个地址既不是 IPv4 也不是 IPv6 地址 
// 备注：
// ip地址的类型，可能为
// IPv4,   IPv6,   Neither

import java.util.*;
import java.util.regex.*;

public class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 验证IP地址
     * @param IP string字符串 一个IP地址字符串
     * @return string字符串
     */
    public String solve(String IP) {
        if (isValidIPv4(IP)) {
            return "IPv4";
        } else if (isValidIPv6(IP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }

    // 检查是否是有效的 IPv4 地址
    private boolean isValidIPv4(String IP) {
        String ipv4Pattern =
            "^((25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])\\.){3}(25[0-5]|2[0-4][0-9]|1[0-9]{2}|[1-9]?[0-9])$";
        return Pattern.matches(ipv4Pattern, IP);
    }

    // 检查是否是有效的 IPv6 地址
    private boolean isValidIPv6(String IP) {
        String ipv6Pattern = "^([0-9a-fA-F]{1,4}:){7}([0-9a-fA-F]{1,4})$";
        return Pattern.matches(ipv6Pattern, IP);
    }
}


public class Solution {
    public String solve(String IP) {
        if (isValidIPv4(IP)) {
            return "IPv4";
        } else if (isValidIPv6(IP)) {
            return "IPv6";
        } else {
            return "Neither";
        }
    }

    // 验证 IPv4 地址
    private boolean isValidIPv4(String IP) {
        String[] parts = IP.split("\\.", -1);  // 按 "." 分割，-1 确保保留空字符串
        if (parts.length != 4) {
            return false;  // IPv4 必须有 4 段
        }
        
        for (String part : parts) {
            if (!isValidIPv4Part(part)) {
                return false;
            }
        }
        return true;
    }

    // 验证 IPv4 的每一段
    private boolean isValidIPv4Part(String part) {
        // 检查是否为空，或者段的长度大于1但以 '0' 开头（即不允许前导 0）
        if (part.length() == 0 || (part.length() > 1 && part.charAt(0) == '0')) {
            return false;
        }
        
        // 检查是否为纯数字
        for (char c : part.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }

        // 将该段转换为数字并检查是否在 0-255 范围内
        try {
            int num = Integer.parseInt(part);
            if (num < 0 || num > 255) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    // 验证 IPv6 地址
    private boolean isValidIPv6(String IP) {
        String[] parts = IP.split(":", -1);  // 按 ":" 分割
        if (parts.length != 8) {
            return false;  // IPv6 必须有 8 段
        }
        
        for (String part : parts) {
            if (!isValidIPv6Part(part)) {
                return false;
            }
        }
        return true;
    }

    // 验证 IPv6 的每一段
    private boolean isValidIPv6Part(String part) {
        // IPv6 的每段应为 1 到 4 个 16 进制字符
        if (part.length() == 0 || part.length() > 4) {
            return false;
        }

        // 检查每个字符是否是合法的 16 进制字符
        for (char c : part.toCharArray()) {
            if (!isHexadecimalCharacter(c)) {
                return false;
            }
        }

        return true;
    }

    // 检查字符是否是合法的 16 进制字符
    private boolean isHexadecimalCharacter(char c) {
        return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }
}
