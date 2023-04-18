package io.github.webhook.config;


import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author EalenXie Created on 2019/10/11 11:33.
 */
public class FileConvert {


    private FileConvert() {
    }

    private static final DecimalFormat DF;

    private static final String B = "B";

    private static final String KB = "KB";

    private static final String MB = "MB";

    private static final String GB = "GB";

    static {
        // 设置数字格式，保留一位有效小数
        DF = new DecimalFormat("#0.00");
        //四舍五入
        DF.setRoundingMode(RoundingMode.HALF_UP);
        //设置数字的分数部分中允许的最小位数。
        DF.setMinimumFractionDigits(2);
        //设置数字的分数部分中允许的最大位数。
        DF.setMaximumFractionDigits(2);
    }


    /**
     * 根据文件长度得到文件大小(比如 : 1.81GB,1.83MB)
     *
     * @param length 文件长度
     * @return 返回一个表示文件大小的字符串 比如 1.51G、1.82MB
     */
    public static String getFormatFileSize(long length) {
        double size = ((double) length) / (1 << 30);
        if (size >= 1) return DF.format(size) + GB;
        size = ((double) length) / (1 << 20);
        if (size >= 1) return DF.format(size) + MB;
        size = ((double) length) / (1 << 10);
        if (size >= 1) return DF.format(size) + KB;
        return length + B;
    }


}
