package com.github.graycat27.atc.defines;

/**
 * 方角 1～360°の方位角を持つ<br>
 * 真北 = 0 = 360, 時計回りの角度。（東=90,南=180,西=270,北=360)
 */
public class Direction {

    /* フィールド */
    /** 1周を示す定数値 */
    private static final int ROUND = 360;

    /** 方位値 */
    private final int direction;

    /* コンストラクタ */
    public Direction(final int direction){
        int dir = direction % ROUND;
        while(dir <= 0){
            dir += ROUND;
        }
        this.direction = dir;
    }

    /* メソッド */
    public int getValue(){
        return direction;
    }

    /** 現在の方角から時計回りに指定角回転させた時の方角を返します */
    public Direction rotate(int rotation){
        int newVal = this.direction + rotation;
        return new Direction(newVal);
    }

}
