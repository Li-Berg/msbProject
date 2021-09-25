package com.msb.tank.tank;

import com.msb.tank.enums.Dir;
import com.msb.tank.enums.Group;
import com.msb.tank.fire.strategy.impl.DefaultFire;
import com.msb.tank.fire.strategy.service.IFireStrategy;
import com.msb.tank.frame.TankFrame;
import com.msb.tank.utils.ResourceMgr;
import com.msb.tank.utils.TankUtil;

import java.awt.*;
import java.util.Random;

/**
 * @Auther: AaronBerg
 * @Date: 2021/7/25 - 07 - 25  4:38 下午
 * @Description:
 * @Version:1.0
 */
public class Tank extends BaseTank {
    private static final int SPEED = 5;
    private boolean moving = true;
    private int x, y;
    private Dir dir;

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    private TankFrame tf;
    public static final int WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankD.getHeight();
    private boolean isAlive = true;
    private Random random = new Random();
    private Group group = Group.BAD;


    public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void paintTank(Graphics g) {
        if(isAlive) {
            move();
            switch (dir) {
                case UP:
                    g.drawImage(this.group == Group.GODD?ResourceMgr.goodTankU:ResourceMgr.badTankU, x, y, null);
                    break;
                case DOWN:
                    g.drawImage(this.group == Group.GODD?ResourceMgr.goodTankD:ResourceMgr.badTankD, x, y, null);
                    break;
                case LEFT:
                    g.drawImage(this.group == Group.GODD?ResourceMgr.goodTankL:ResourceMgr.badTankL, x, y, null);
                    break;
                case RIGHT:
                    g.drawImage(this.group == Group.GODD?ResourceMgr.goodTankR:ResourceMgr.badTankR, x, y, null);
                    break;
            }
            if(this.group == Group.BAD && random.nextInt(100) > 95){
                this.fire(DefaultFire.getDefaultFire());
            }
            randomDir();
        }
    }

    private void randomDir() {
        if(this.group == Group.BAD  && random.nextInt(100) > 85){
            this.dir = Dir.values()[random.nextInt(4)];
        }
    }

    private void move() {
        if (moving) {
            int[] res = TankUtil.changeLocation(dir, x, y, SPEED,false);
            x = res[0];
            y = res[1];
        }
    }

    public void setTankDir(boolean isUp, boolean isDown, boolean isLeft, boolean isRight) {
        if (isUp) dir = Dir.UP;
        if (isDown) dir = Dir.DOWN;
        if (isLeft) dir = Dir.LEFT;
        if (isRight) dir = Dir.RIGHT;
    }

    public void fire(IFireStrategy ifs) {
       ifs.fire( this);
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

    public void die() {
        this.isAlive = false;
        tf.getEnemyList().remove(this);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
