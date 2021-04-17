package com.github.graycat27.atc.defines.i;

/** 四角形の立体空間を定義する */
public abstract class AbstractArea implements IArea {

    // フィールド
    /* 範囲の情報 */
    /** x,y,zが最小値の角 */
    private IPoint minPoint;
    /** x,y,zが最大値の角 */
    private IPoint maxPoint;

    public IPoint getMinPoint(){
        return minPoint.clone();
    }
    public IPoint getMaxPoint(){
        return maxPoint.clone();
    }

    // コンストラクタ
    public AbstractArea(final IPoint pos1, final IPoint pos2){
        int x1 = pos1.getX();
        int y1 = pos1.getY();
        int z1 = pos1.getZ();
        int x2 = pos2.getX();
        int y2 = pos2.getY();
        int z2 = pos2.getZ();


    }

    // メソッド
    @Override
    public boolean isIn(final IPoint point){
        //TODO make this


        return false;
    }

    @Override
    abstract public AbstractArea clone();

}
