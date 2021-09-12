package com.msb.tank;

import java.awt.*;

/**
 * @Auther: AaronBerg
 * @Date: 2021/8/30 - 08 - 30  9:54 上午
 * @Description:
 * @Version:1.0
 */
public class Explode {
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x,y;
    private int step = 0;
    TankFrame tf = null;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

    public void paint(Graphics g){
        int eX = this.x + (Tank.WIDTH - this.WIDTH)/2 + 1;
        int eY = this.y + (Tank.HEIGHT - this.HEIGHT)/2 - 1;
        g.drawImage(ResourceMgr.explodes[step++],eX,eY,null);
        if(step >= ResourceMgr.explodes.length) {
            step = 0;
            tf.getExplodeList().remove(this);
        }
    }
}
