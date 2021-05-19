package com.github.graycat27.atc.defines.airport;

import com.github.graycat27.atc.defines.i.IPoint;
import com.github.graycat27.atc.defines.i.AbstractPath;

import java.util.ArrayList;
import java.util.List;

/**
 * 誘導路<br>
 * 屈曲している可能性がある
 */
public class Taxiway extends AbstractPath {

    // フィールド
    private final String name;
    public String getName(){
        return name;
    }

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

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder("Taxiway:{");
        sb.append("Name:").append(getName()).append(",");
        sb.append("Start:{").append(getStart().toString()).append("},");
        sb.append("way:[");
        int waySize = getWayCount();
        for(int i=0; i<waySize; i++){
            sb.append(getWayPoint(i).toString());
            if(i < waySize-1){
                sb.append(",");
            }
        }
        sb.append("],");
        sb.append("End:{").append(getEnd().toString()).append("}");
        sb.append("}");
        return sb.toString();
    }
}
