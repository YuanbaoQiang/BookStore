/**
 * <h3>MyProject</h3>
 * <p></p>
 *
 * @author : YuanbaoQiang
 * @date : 2020-10-04 11:03
 **/

package com.yuanbaoqiang.web;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServeletTest {


    public void regist(){
        System.out.println("这是regist()方法");
    }

    public void login(){
        System.out.println("这是login()方法");
    }

    public void get(){
        System.out.println("这是get()方法");
    }

    public void post(){
        System.out.println("这是post()方法");
    }

    // 利用反射 执行 对应的方法
    public static void main(String[] args) {
        String action = "post";
        try {
            Method method = ServeletTest.class.getDeclaredMethod(action);
            method.invoke(new ServeletTest());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }


}
