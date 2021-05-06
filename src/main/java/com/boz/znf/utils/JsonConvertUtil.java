package com.boz.znf.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * json数据格式转换
 * @author ZhangNanFu
 * @date 2021/5/3 20:32
 */
public class JsonConvertUtil {
    /**
     * 读取请求传递过来的JSON格式数据，返回JSON字符串
     * @param request
     * @return
     */
    public static String readJSONData(HttpServletRequest request) {
        StringBuilder json = new StringBuilder();
        String lineString;
        try {
            BufferedReader reader = request.getReader();
            while ((lineString = reader.readLine()) != null) {
                json.append(lineString);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }
}
