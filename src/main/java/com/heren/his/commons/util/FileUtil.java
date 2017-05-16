package com.heren.his.commons.util;

import com.google.common.base.Strings;
import com.heren.his.commons.exceptions.SystemException;
import sun.misc.BASE64Decoder;

public class FileUtil {

    public static byte[] generateFileForString(String str) {
        BASE64Decoder decoder = new BASE64Decoder();
        if (Strings.isNullOrEmpty(str)) {
            return new byte[]{};
        }
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            return b;
        } catch (Exception e) {
            throw new SystemException("解析文件失败") ;
        }
    }
}
