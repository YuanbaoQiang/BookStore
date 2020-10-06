/**
 * <h3>MyProject</h3>
 * <p>
 *     所用到的第三方jar包：
 *
 *     BeanUtils工具类：
 *          1. commons-beanutils-1.8.0.jar
 *          2. commons-logging-1.1.1.jar
 *
 * </p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-04 10:51
 **/

package com.yuanbaoqiang.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {
    /*
     * @description: 将Map中的值 注入到对于的JavaBean属性中
     * @author: yuanbaoqiang
     * @date: 2020/10/4 上午10:56
     * @param value
     * @param bean
     * @return: T
     */
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            // 将所有的请求都注入到bean对象中
            BeanUtils.populate(bean,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }


    /*
     * @description: 将字符串转成int类型
     * @author: yuanbaoqiang
     * @date: 2020/10/4 下午2:41
     * @param str
     * @param defaultValue
     * @return: int
     */
    public static int parse(String str, int defaultValue){
        if(str == null) return defaultValue;
        return Integer.parseInt(str);
    }
}
