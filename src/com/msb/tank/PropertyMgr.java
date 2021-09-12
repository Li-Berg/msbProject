package com.msb.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: AaronBerg
 * @Date: 2021/9/11 - 09 - 11  10:31 下午
 * @Description:
 * @Version:1.0
 */
public class PropertyMgr {
    static Properties prop = new Properties();
    static {
        try {
            prop.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config/tank.pro"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if (prop == null) return null;
        return prop.get(key);
    }

    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }
}
