package com.jb.repair.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by SLF on 2015/11/6.
 */
public class TimeUtil {
    public static String generateFilenameString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date()) + "_"
                + new Random().nextInt(1000);
    }
}
