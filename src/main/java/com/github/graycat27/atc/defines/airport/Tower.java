package com.github.graycat27.atc.defines.airport;

import com.github.graycat27.atc.defines.i.AbstractPoint;

/** 管制塔 */
public class Tower extends AbstractPoint {

    /* フィールド */


    /* コンストラクタ */
    Tower(int posX, int posY, int posZ){
        super(posX, posY, posZ);
    }

    /* メソッド */
    @Override
    public Tower clone(){
        return new Tower(getX(), getY(), getZ());
    }

    @Override
    public String toString(){
        return "Tower:{Location:{"+ getX() +", "+ getY() +", "+ getZ() +"}}";
    }
}
