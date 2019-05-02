package org.smart4j.chapter1.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author chen
 * @date 2019/5/2
 * @description
 */
public final class StringUtil {

    public static boolean isEmpty(String str){
        if(str!=null){
            str=str.trim();
        }
        return StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str){

        return !isEmpty(str );
    }
}
