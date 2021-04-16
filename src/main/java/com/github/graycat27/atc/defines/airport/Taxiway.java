package com.github.graycat27.atc.defines.airport;

import com.github.graycat27.atc.defines.i.IPoint;
import com.github.graycat27.atc.defines.i.Path;

import java.util.ArrayList;
import java.util.List;

/**
 * 誘導路<br>
 * 屈曲している可能性がある
 */
public class Taxiway extends Path {

    // フィールド
    private String name;

    // コンストラクタ
    public Taxiway(String name, List<IPoint> way){
        super(way);
        this.name = name;
    }

    // メソッド
    @Override
    public Taxiway clone(){
        List<IPoint> way = new ArrayList<>();
        way.add(this.getStart());
        int cnt = this.getWayCount();
        for(int i = 0; i<cnt; i++) {
            way.add(this.getWayPoint(i));
        }
        way.add(this.getEnd());
        return new Taxiway(this.name, way);
    }
}
