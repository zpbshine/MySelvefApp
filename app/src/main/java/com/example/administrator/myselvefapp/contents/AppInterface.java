package com.example.administrator.myselvefapp.contents;

/**
 * Created by Administrator on 2018/1/24.
 */

public class AppInterface {
    public static String BASE_URL = "http://192.168.3.6:8080/MyFirstApp";
    //登录
    public static String USER_LOGIN = BASE_URL+"/servlet/LoginServlet";
    //注册
    public static String USER_REG = BASE_URL+"/servlet/RegisterServlet";
    //获取首页特惠商品
    public static String GET_BENIFIT_GOODS = BASE_URL+"/servlet/BenifitServlet";

}
