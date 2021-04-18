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
        IPoint minP = new ConcretePoint(
                Math.min(x1, x2),
                Math.min(y1, y2),
                Math.min(z1, z2)
        );
        IPoint maxP = new ConcretePoint(
                Math.max(x1, x2),
                Math.max(y1, y2),
                Math.max(z1, z2)
        );
        this.minPoint = minP;
        this.maxPoint = maxP;
    }

    // メソッド
    @Override
    public boolean isIn(final IPoint point){
        int pointX = point.getX();
        int pointY = point.getY();
        int pointZ = point.getZ();

        return (
                minPoint.getX() <= pointX && pointX <= maxPoint.getX() &&
                minPoint.getY() <= pointY && pointY <= maxPoint.getY() &&
                minPoint.getZ() <= pointZ && pointZ <= maxPoint.getZ()
        );
    }

    @Override
    abstract public AbstractArea clone();

}
