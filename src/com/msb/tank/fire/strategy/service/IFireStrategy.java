package com.msb.tank.fire.strategy.service;

import com.msb.tank.bullet.Bullet;
import com.msb.tank.tank.Tank;

/**
 * @Auther: AaronBerg
 * @Date: 2021/9/24 - 09 - 24  5:14 下午
 * @Description:
 * @Version:1.0
 */
public interface IFireStrategy {
    public void fire(Tank t);
    public static int[] createXY(Tank t) {
        int bX = t.getX() + (Tank.WIDTH - Bullet.WIDTH)/2 + 1;
        int bY = t.getY() + (Tank.HEIGHT - Bullet.HEIGHT)/2 - 1;
        return new int[]{bX,bY};
    }
}
