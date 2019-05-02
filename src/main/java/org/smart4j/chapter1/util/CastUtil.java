package org.smart4j.chapter1.util;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.OBJ_ADAPTER;

/**
 * @author chen
 * @date 2019/5/2
 * @description
 */
public final class CastUtil {

    public static String castString(Object obj){
        return CastUtil.castString(obj,"");

    }

    public static String castString(Object obj,String defaultValue){
        return obj!=null?String.valueOf(obj):defaultValue;
    }

    public static double castDouble(Object obj){
        return CastUtil.castDouble(obj,0);
    }

    public static double castDouble(Object obj,double defaultValue){
        double doubleValue =defaultValue;
        if(obj!=null){
            String strValue=castString(obj);
            if(StringUtils.isNotEmpty(strValue)){
                try{
                    doubleValue=Double.parseDouble(strValue);

                }
                catch (NumberFormatException e){
                    doubleValue=defaultValue;
                }
            }
        }
        return doubleValue;

    }


}
