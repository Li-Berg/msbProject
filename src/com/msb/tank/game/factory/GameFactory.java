package com.msb.tank.game.factory;

import com.msb.tank.bullet.BaseBullet;
import com.msb.tank.enums.Dir;
import com.msb.tank.enums.Group;
import com.msb.tank.explode.BaseExplode;
import com.msb.tank.frame.TankFrame;
import com.msb.tank.tank.BaseTank;

/**
 * @Auther: AaronBerg
 * @Date: 2021/9/25 - 09 - 25  4:43 下午
 * @Description:
 * @Version:1.0
 */
public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group);
}
