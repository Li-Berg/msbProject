package com.msb.tank;

/**
 * @Auther: AaronBerg
 * @Date: 2021/7/25 - 07 - 25  9:14 下午
 * @Description:
 * @Version:1.0
 */
public class TankUtil {
    public static int[] changeLocation(Dir dir, int x, int y, int speed,boolean canOut) {
        int[] res = new int[2];
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
        }
        if(canOut == false) {
            if (x <= 0) x = 0;
            if (y <= 30) y = 30;
            if (x >= TankFrame.getGameWidth()-Tank.WIDTH) x = TankFrame.getGameWidth() - Tank.WIDTH;
            if (y >= TankFrame.getGameHeight()-Tank.HEIGHT) y = TankFrame.getGameHeight() - Tank.HEIGHT;
        }
        res[0] = x;
        res[1] = y;
        return res;
    }
}
