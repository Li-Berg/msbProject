package com.msb.tank.fire.strategy.impl;

import com.msb.tank.bullet.Bullet;
import com.msb.tank.tank.Tank;
import com.msb.tank.fire.strategy.service.IFireStrategy;

/**
 * @Auther: AaronBerg
 * @Date: 2021/9/24 - 09 - 24  5:41 下午
 * @Description:
 * @Version:1.0
 */
public class DefaultFire implements IFireStrategy {
    private DefaultFire(){};
    private static DefaultFire fire = null;
    public static DefaultFire getDefaultFire(){
        if(fire == null){
            synchronized (DefaultFire.class){
                if(fire == null){
                    fire = new DefaultFire();
                };
            }
        }
        return fire;
    }
    @Override
    public void fire(Tank t) {
        int[] xy = IFireStrategy.createXY(t);
        Bullet b = new Bullet(xy[0], xy[1],t.getDir(), t.getTf(), t.getGroup());
    }


}
