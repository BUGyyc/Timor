package com.linkerlab.housemanager.account;

import java.io.Serializable;

/**
 * Created by x-man on 16/7/30.
 * 登录用户单例类
 * 双向加锁验证
 */
public class User implements Serializable {
    public static final int PHONE_USER_TYPE = 1;//手机登录
    public static final int ID_USER_TYPE = 2;//工号登录
    private volatile static User self;

    private User(){};

     /**
     * 双重同步锁
     * @return 唯一的单例子实例
     */
    public static User getInstance(){
        if(self == null){
            synchronized (User.class){
                if(self == null){
                    self = new User();
                }
            }
        }
        return  self;
    }
}
