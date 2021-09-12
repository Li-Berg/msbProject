package com.msb.tank;

import java.awt.*;

/**
 * @Auther: AaronBerg
 * @Date: 2021/7/25 - 07 - 25  8:13 下午
 * @Description:
 * @Version:1.0
 */
public class Bullet {
    private static final int SPEED = 10;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    private boolean isAlive = true;
    private final TankFrame tf;
    private int x, y;
    private Dir dir;
    private Group group = Group.BAD;

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

    private void move() {
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
            tf.getExplodeList().add(new Explode(tank.getX(), tank.getY(), tf));
        }
    }

    private void die() {
        this.isAlive = false;
    }
}
