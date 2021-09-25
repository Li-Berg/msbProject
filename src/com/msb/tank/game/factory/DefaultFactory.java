package com.msb.tank.game.factory;

import com.msb.tank.bullet.BaseBullet;
import com.msb.tank.bullet.Bullet;
import com.msb.tank.enums.Dir;
import com.msb.tank.enums.Group;
import com.msb.tank.explode.BaseExplode;
import com.msb.tank.explode.Explode;
import com.msb.tank.frame.TankFrame;
import com.msb.tank.tank.BaseTank;
import com.msb.tank.tank.Tank;

/**
 * @Auther: AaronBerg
 * @Date: 2021/9/25 - 09 - 25  4:43 下午
 * @Description:
 * @Version:1.0
 */
public class DefaultFactory extends GameFactory{
    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new Tank(x,y,dir,tf,group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new Explode(x, y, tf);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new Bullet(x, y, dir, tf, group);
    }
}
