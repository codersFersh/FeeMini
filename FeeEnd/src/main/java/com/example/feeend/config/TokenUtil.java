package com.example.feeend.config;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TokenUtil {
    // token有效期，单位为秒，时间为一小时
    private static final int TOKEN_EXPIRE_TIME = 3600;

    // 存储token的map，key为token，value为用户id
    public static Map<String, Integer> tokenMap = new HashMap<>();

    /**
     * 用户登录，生成并返回token
     * @param userId 用户id
     * @return token
     */
    public static String login(int userId) {
        // 生成唯一的token
        //replace("-", "") 从字符串中去掉所有的连字符（"-"）
        //获取随机对象转为字符串赋值给token
            String token = UUID.randomUUID().toString().replace("-", "");
        // 将token和用户id存入map中
        tokenMap.put(token, userId);
        System.out.println(token);
        return token;
    }

    /**
     * 验证token是否有效，如果有效则返回对应的用户id
     * @param token
     * @return 用户id，如果token无效则返回false
     */
    public static boolean verifyToken(String token) {
        // 检查token是否存在
        if (!tokenMap.containsKey(token)) {
            return false;
        }

        /**
         * 1、token.substring(0, 8) 从字符串 token 中提取前 8 个字符。
         * 因为每个十六进制数字占据 4 位，所以提取的部分应该是一个或多个十六进制数字。
         * 2、Long.parseLong(..., 16) 将提取的十六进制字符串解析为一个长整型。
         * 第二个参数 16 表示使用十六进制进行解析。
         *3、最后，将得到的长整型时间戳乘以 1000，以将其转换为毫秒（milliseconds）。
         */

        // 检查token是否过期
        long createTime = Long.parseLong(token.substring(0, 8), 16) * 1000;
        //获取当前系统时间
        long nowTime = System.currentTimeMillis();
        //除以 1000 就得到了秒数。
        if ((nowTime - createTime) / 1000 > TOKEN_EXPIRE_TIME) {
            // 过期了，需要删除后端的token记录
            logout(token);
            return false;
        }

        // 返回验证成功
        return true;
    }

    /**
     * 用户退出登录，删除对应的token
     * @param token
     */
    public static void logout(String token) {
        tokenMap.remove(token);
    }
}
