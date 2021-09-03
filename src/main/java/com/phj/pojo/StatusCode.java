package com.phj.pojo;

/**
 * 返回码
 */
public class StatusCode {
    public static final int OK = 20000;//成功
    public static final int ERROR = 20001;//失败
    public static final int LOGINERROR = 20002;//用户名或密码错误
    public static final int NOTLOGIN = 20003;//未登录
    public static final int ACCESSERROR = 20004;//权限不足
    public static final int REMOTEERROR = 20005;//远程调用失败
    public static final int REPERROR = 20006;//重复操作
    public static final int NOTFOUNDERROR = 20007;//没有对应的抢购数据
}
