package com.msb.tank.fire.strategy.impl;

import com.msb.tank.tank.Tank;
import com.msb.tank.bullet.SuperBullet;
import com.msb.tank.fire.strategy.service.IFireStrategy;

/**
 * @Auther: AaronBerg
 * @Date: 2021/9/24 - 09 - 24  6:00 下午
 * @Description:
 * @Version:1.0
 */
public class SuperFire implements IFireStrategy {
    private SuperFire() {};

    @Override
    public void fire(Tank t) {
        int[] xy = IFireStrategy.createXY(t);
//        Bullet b = new Bullet(xy[0], xy[1],t.getDir(), t.getTf(), t.getGroup());
        SuperBullet b = new SuperBullet(xy[0], xy[1],t.getDir(), t.getTf(), t.getGroup());
    }

    private static class FireHolder{
        private static final SuperFire fire = new SuperFire();
    }
    public static SuperFire getFier(){
        return FireHolder.fire;
    }
}
