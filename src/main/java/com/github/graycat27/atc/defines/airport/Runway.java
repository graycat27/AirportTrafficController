package com.github.graycat27.atc.defines.airport;

import com.github.graycat27.atc.defines.i.IPoint;
import com.github.graycat27.atc.defines.i.Path;

import java.util.ArrayList;
import java.util.List;

public class Runway extends Path {

    /* フィールド */
    /** 滑走路番号
     * <code>18-36</code>, <code>09R-27L</code> など */
    private final String rwyNumber;


    /* コンストラクタ */
    Runway(String rwyNum, List<IPoint> startEndPoint){
        super(startEndPoint);
        if(startEndPoint.size() != 2 || startEndPoint.get(0).equals(startEndPoint.get(1))){
            throw new IllegalArgumentException("Runway must be a single line");
        }

        if(rwyNum == null || rwyNum.length() == 0){
            throw new IllegalArgumentException("Runway Number can not be null or empty");
        }
        rwyNumber = rwyNum;
    }

    /* メソッド */
    @Override
    public Runway clone(){
        List<IPoint> startEndPoint = new ArrayList<>();
        startEndPoint.add(this.getStart());
        startEndPoint.add(this.getEnd());
        return new Runway(this.rwyNumber, startEndPoint);
    }


}
