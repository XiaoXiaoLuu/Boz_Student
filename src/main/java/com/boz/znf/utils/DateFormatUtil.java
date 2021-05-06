package com.boz.znf.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author ZhangNanFu
 * @date 2021年05月03日 9:46
 */
public class DateFormatUtil {
    public static String toDateString (LocalDate day) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return day.format(fmt);
    }

    public static LocalDate toDateString (String dayTime) {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(dayTime, fmt);
    }
}
