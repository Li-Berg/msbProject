package com.msb.tank.bullet;

import com.msb.tank.enums.Dir;
import com.msb.tank.enums.Group;
import com.msb.tank.explode.Explode;
import com.msb.tank.frame.TankFrame;
import com.msb.tank.tank.Tank;

import java.awt.*;

/**
 * @Auther: AaronBerg
 * @Date: 2021/9/24 - 09 - 24  6:14 下午
 * @Description:
 * @Version:1.0
 */
public class SuperBullet extends Bullet {
    public SuperBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        super(x-150+WIDTH/2, y-300-HEIGHT, dir, tf, group);
    }

    @Override
    public void collideWith(Tank tank) {
        if(super.getGroup() == tank.getGroup()) return;
        Rectangle rectBullet = new Rectangle(super.getX(),super.getY(),300,300);
        Rectangle rectTank = new Rectangle(tank.getX(),tank.getY(),tank.WIDTH,tank.HEIGHT);
        if (rectBullet.intersects(rectTank)){
//            super.die();
            tank.die();
            super.getTf().getExplodeList().add(new Explode(tank.getX(), tank.getY(), super.getTf()));
        }
    }

    @Override
    public void paintBullet(Graphics g) {
        g.drawRect(getX(), getY(), 300, 300);
        super.move();
    }
}
