package com.msb.tank;

import java.awt.*;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/**
 * @Auther: AaronBerg
 * @Date: 2021/7/5 - 07 - 05  11:35 下午
 * @Description:
 * @Version:1.0
 */
public class TankFrame extends Frame {
    static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;
    private List<Tank> tankList = new ArrayList();
    private List<Tank> enemyList = new ArrayList<>();
    private List<Bullet> bulletList = new ArrayList();
    private List<Explode> explodeList = new ArrayList<>();

    public TankFrame() {
        // 创建坦克
        Tank mainTank = new Tank(200, 400, Dir.UP,this,Group.GODD);
        tankList.add(mainTank);
        // 初始化敌方坦克 todo

        // 创建子弹
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setTitle("坦克大战");
        addKeyListener(new MyKeyListener());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setResizable(false);
        setVisible(true);
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹数量:"+bulletList.size(), 10, 60);
        g.drawString("敌人数量:"+enemyList.size(), 10, 80);
        g.drawString("爆炸数量:"+explodeList.size(), 10, 100);
        g.drawString("x:"+tankList.get(0).getX(), 10, 120);
        g.drawString("y:"+tankList.get(0).getY(), 10, 140);
        for (Tank tank : tankList) {
            tank.paintTank(g);
        }
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paintBullet(g);
        }
        for (int i = 0; i < enemyList.size(); i++) {
            enemyList.get(i).paintTank(g);
        }
        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(g);
        }

        // 判断是否子弹坦克相撞
        for (int i = 0; i < bulletList.size(); i++) {
            for (int j = 0; j < enemyList.size(); j++) {
                bulletList.get(i).collideWith(enemyList.get(j));
            }
        }
        g.setColor(color);
    }

    class MyKeyListener extends KeyAdapter {
        boolean isUp = false;
        boolean isDown = false;
        boolean isLeft = false;
        boolean isRight = false;

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    isUp = true;
                    break;
                case KeyEvent.VK_DOWN:
                    isDown = true;
                    break;
                case KeyEvent.VK_LEFT:
                    isLeft = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    isRight = true;
                    break;
                default:
                    break;
            }
            setMainTankDir(isUp, isDown, isLeft, isRight);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    isUp = false;
                    break;
                case KeyEvent.VK_DOWN:
                    isDown = false;
                    break;
                case KeyEvent.VK_LEFT:
                    isLeft = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    isRight = false;
                    break;
                case KeyEvent.VK_SPACE:
                    tankList.get(0).fire();
                default:
                    break;
            }
            setMainTankDir(isUp, isDown, isLeft, isRight);
        }

    }

    private void setMainTankDir(boolean isUp, boolean isDown, boolean isLeft, boolean isRight) {
        if (!isUp && !isDown && !isLeft && !isRight) {
            tankList.get(0).setMoving(false);
        } else {
            tankList.get(0).setMoving(true);
            tankList.get(0).setTankDir(isUp, isDown, isLeft, isRight);
        }
    }

    public List<Tank> getEnemyList() {
        return enemyList;
    }

    public void setEnemyList(List<Tank> enemyList) {
        this.enemyList = enemyList;
    }
    public static int getGameWidth() {
        return GAME_WIDTH;
    }

    public static int getGameHeight() {
        return GAME_HEIGHT;
    }
    public List<Bullet> getBulletList() {
        return bulletList;
    }

    public void setBulletList(List<Bullet> bulletList) {
        this.bulletList = bulletList;
    }
    public List<Explode> getExplodeList() {
        return explodeList;
    }

    public void setExplodeList(List<Explode> explodeList) {
        this.explodeList = explodeList;
    }
}
