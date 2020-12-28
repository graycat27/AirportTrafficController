package com.github.graycat27.atc.defines.airport;

import com.github.graycat27.atc.defines.i.Point;
import org.apache.commons.lang3.StringUtils;

public class Spot extends Point {

    /* フィールド */
    String spotNumber;

    /* コンストラクタ */
    Spot(String spotNo, int posX, int posY, int posZ){
        super(posX, posY, posZ);
        if(StringUtils.isEmpty(spotNo)){
            throw new IllegalArgumentException("spot number must not be empty");
        }
        this.spotNumber = spotNo;
    }

    /* メソッド */
    /** スポット番号(文字あり) */
    public String getSpotNumber(){
        return this.spotNumber;
    }

}
