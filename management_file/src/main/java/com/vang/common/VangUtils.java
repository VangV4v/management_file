package com.vang.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * CreatedDate: 21/12/2024
 * UpdatedDate:
 * Version: 1.0
 * Author: Quang
 * Name: VangUtils
 */
public class VangUtils {

    /**
     * Get current date time of system
     * @return {{@link Date}}
     */
    public static Date getSystemDateTime() {
        return new Date();
    }

    /**
     * Get current date time of system by your format
     * @param {{@link Date}}
     * @param {{@link String}}
     * @return {{@link String}}
     */
    public static String getStringDateByFormat(Date date, String format) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.now().format(formatter);
    }
}