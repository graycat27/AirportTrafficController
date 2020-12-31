package com.github.graycat27.atc.defines.i;

import com.github.graycat27.atc.components.PropertyUtil;
import org.bukkit.Location;
import org.bukkit.World;

/** 特定のx-y-z座標 */
public abstract class Point implements IPoint {

    /* フィールド */
    //地点情報
    private final int posX;
    private final int posY;
    private final int posZ;

    /* コンストラクタ */
    public Point(int posX, int posZ){
        this.posX = posX;
        this.posY = 64; //水面高度を既定値とする
        this.posZ = posZ;
    }

    public Point(int posX, int posY, int posZ){
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
    }

    /* メソッド */
    /**
     * プロパティで指定のworldでLocationを返します（座標値は整数のままで補正なし）
     * @return このインスタンスが持つ座標に基づくLocation
     */
    @Override
    public Location getLocation() {
        return new Location(PropertyUtil.getWorld() , posX, posY, posZ);
    }
    @Override
    public int getX(){
        return this.posX;
    }
    @Override
    public int getY(){
        return this.posY;
    }
    @Override
    public int getZ(){
        return this.posZ;
    }

    @Override
    public double distance(final IPoint another) {
        if(another == null){
            throw new IllegalArgumentException("another point param was null");
        }
        return Math.hypot(levelDistance(another), (this.posY - another.getY()));
    }

    @Override
    public double levelDistance(final IPoint another) {
        if(another == null){
            throw new IllegalArgumentException("another point param was null");
        }
        return Math.hypot((this.posX - another.getX()), (this.posZ - another.getZ()));
    }

    /* override from Object */
    @Override
    public boolean equals(Object another){
        if(!(another instanceof IPoint)){
            return false;
        }
        final IPoint anotherPoint = (IPoint) another;
        return (anotherPoint.getX() == this.posX &&
                anotherPoint.getY() == this.posY &&
                anotherPoint.getZ() == this.posZ);
    }

    @Override
    public String toString(){
        return "Point:{x="+ posX +", y="+ posY +", z="+ posZ +"}";
    }
}
