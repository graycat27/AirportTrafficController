package com.github.graycat27.atc.components;

import com.github.graycat27.atc.defines.Direction;
import com.github.graycat27.atc.defines.i.IPoint;
import org.bukkit.Location;

/** 方角処理の共通処理を提供します */
public class DirectionUtil {

    /** LocationからDirectionを返します */
    public static Direction getDirFromLocation(final Location loc){
        if(loc == null || loc.getYaw() == 0){
            return null;
        }
        return new Direction(Math.round(loc.getYaw())%360);
    }

    /**
     * 基準地点から目標へ向かう方角を返します
     * @param base 基準とする地点
     * @param target 目標とする地点
     * @return baseからtargetを見るときの方角。同地の場合は北(360)。
     */
    public static Direction getDirectionBetweenPoints(final IPoint base, final IPoint target){
        int deltaX = target.getX() - base.getX();
        int deltaZ = target.getZ() - base.getZ();
        //軸方向と合致
        if(deltaX == 0 || deltaZ == 0){
            if(deltaZ != 0){
                /* x軸のみ合致 = 南北のどちらか */
                return new Direction((deltaZ > 0)?180:360);
            }
            if(deltaX != 0){
                /* z軸のみ合致 = 東西のどちらか */
                return new Direction((deltaX > 0)?90:270);
            }
            //x-z座標が合致する2点。北を返す
            return new Direction(0);
        }

        //角度算出
        double div = (double)deltaZ / deltaX;
        double dir = Math.toDegrees(Math.atan(div));
        {
            //x-z軸の正方向と、北の方角合わせ。tanは2方向で同値をとるため補正
            if(deltaX > 0){
                //+x 東半円
                dir += 90;
            }
            if(deltaX < 0){
                //-x 西半円
                dir -= 90;
            }
        }
        return new Direction((int)Math.floor(dir));
    }
}
