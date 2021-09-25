package com.msb.tank.bullet;

import com.msb.tank.enums.Dir;
import com.msb.tank.enums.Group;
import com.msb.tank.frame.TankFrame;
import com.msb.tank.game.factory.DefaultFactory;
import com.msb.tank.game.factory.GameFactory;
import com.msb.tank.tank.Tank;
import com.msb.tank.utils.ResourceMgr;
import com.msb.tank.utils.TankUtil;

import java.awt.*;

/**
 * @Auther: AaronBerg
 * @Date: 2021/7/25 - 07 - 25  8:13 下午
 * @Description:
 * @Version:1.0
 */
public class Bullet extends BaseBullet {
    private GameFactory gf = new DefaultFactory();
    private static final int SPEED = 10;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private boolean isAlive = true;
    private final TankFrame tf;
    private int x, y;
    private Dir dir;
    private Group group = Group.BAD;

    public TankFrame getTf() {
        return tf;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Bullet(int x, int y, Dir dir,TankFrame tf,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        tf.getBulletList().add(this);
    }

    public void paintBullet(Graphics g) {
        switch (dir) {
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
        }
        move();
    }

    protected void move() {
        int[] res = TankUtil.changeLocation(dir, x, y, SPEED,true);
        x = res[0];
        y = res[1];
        if(x < 0 || y < 0 || x > tf.getGameWidth() || y > tf.getGameHeight()){isAlive = false;}
        if(isAlive) {
        }else{
            tf.getBulletList().remove(this);
        }
    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()) return;
        Rectangle rectBullet = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rectTank = new Rectangle(tank.getX(),tank.getY(),tank.WIDTH,tank.HEIGHT);
        if (rectBullet.intersects(rectTank)){
            this.die();
            tank.die();
            tf.getExplodeList().add(gf.createExplode(tank.getX(), tank.getY(), tf));
        }
    }

    protected void die() {
        this.isAlive = false;
    }
}
