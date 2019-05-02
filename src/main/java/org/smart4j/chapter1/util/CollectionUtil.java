package org.smart4j.chapter1.util;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * @author chen
 * @date 2019/5/2
 * @description
 */
public final class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection){

        return CollectionUtils.isEmpty(collection);
    }
}
