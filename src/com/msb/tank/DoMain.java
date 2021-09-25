package com.msb.tank;

import com.msb.tank.enums.Dir;
import com.msb.tank.enums.Group;
import com.msb.tank.frame.TankFrame;
import com.msb.tank.tank.Tank;
import com.msb.tank.utils.PropertyMgr;

/**
 * @Auther: AaronBerg
 * @Date: 2021/7/5 - 07 - 05  11:22 下午
 * @Description:
 * @Version:1.0
 */
public class DoMain {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        int initTankCount = Integer.parseInt(PropertyMgr.get("initTankCount") == null?"0":PropertyMgr.get("initTankCount").toString());
        // 初始化敌方坦克
        for (int i = 0; i < initTankCount ; i++) {
            tf.getEnemyList().add(new Tank(100+ i*50, 30, Dir.DOWN, tf, Group.BAD));
        }

        while(true){
            Thread.sleep(50);
            tf.repaint();
        }
    }
}
