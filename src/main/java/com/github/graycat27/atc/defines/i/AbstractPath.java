package com.github.graycat27.atc.defines.i;

import java.util.ArrayList;
import java.util.List;

/** 線形の範囲情報 */
@SuppressWarnings("ForLoopReplaceableByForEach")
public abstract class AbstractPath implements IPath {

    /* フィールド */
    /** 始点 */
    private final IPoint start;
    /** 始終点を除く経由点 設定が無い場合は空のList */
    private final List<IPoint> way;
    /** 終点 */
    private final IPoint end;

    /* コンストラクタ */
    /**
     * Listで渡された経由点を結ぶPathを生成します
     * @param wayPoints 経由点(始終点含む) null不可
     */
    public AbstractPath(List<IPoint> wayPoints){
        if(wayPoints == null || wayPoints.size() == 0){
            throw new IllegalArgumentException("way of Path can not be null or empty");
        }
        IPoint p0 = wayPoints.get(0);
        if(p0 == null){
            throw new IllegalArgumentException("Point of way can not be null");
        }
        start = p0;
        final int wayLimit = wayPoints.size() - 1;
        List<IPoint> wayList = new ArrayList<>();
        for(int i=1; i < wayLimit; i++){
            IPoint p = wayPoints.get(i);
            if(p == null){
                throw new IllegalArgumentException("Point of way can not be null");
            }
            wayList.add(p);
        }
        way = wayList;
        IPoint pEnd = wayPoints.get(wayLimit);
        if(pEnd == null){
            throw new IllegalArgumentException("Point of way can not be null");
        }
        end = pEnd;
    }

    /* メソッド */
    @Override
    public IPoint getStart(){
        return start;
    }
    @Override
    public int getWayCount(){
        return way.size();
    }
    @Override
    public IPoint getWayPoint(int index){
        return way.get(index);
    }
    @Override
    public IPoint getEnd(){
        return end;
    }

    @Override
    public double length() {
        double len = 0;
        if(way.size() == 0){
            //経由点が無い場合は始終点距離と同じ
            return distance();
        }
        IPoint cur = start;
        IPoint next;
        for(int i = 0; i < way.size(); i++){
            next = way.get(i);
            len += cur.distance(next);

            cur = next;
        }
        return len;
    }

    @Override
    public double levelLength() {
        double len = 0;
        if(way.size() == 0){
            //経由点が無い場合は始終点距離と同じ
            return levelDistance();
        }
        IPoint cur = start;
        IPoint next;
        for(int i = 0; i < way.size(); i++){
            next = way.get(i);
            len += cur.levelDistance(next);

            cur = next;
        }
        return len;
    }

    @Override
    public double distance() {
        return start.distance(end);
    }

    @Override
    public double levelDistance() {
        return start.levelDistance(end);
    }

    @Override
    public boolean isClosed() {
        return (start.equals(end));
    }

    /** このPathが示す線形上に存在するか否かを返します */
    public boolean isOnPath(IPoint point){
        //TODO implement this
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public boolean isOnLevelPath(IPoint point){
        //TODO implement this
        throw new UnsupportedOperationException("not implemented");
    }

    @Override
    public boolean levelEquals(Object another){
        if(!(another instanceof IPath)){
            return false;
        }
        final IPath anotherPath = (IPath) another;
        boolean isEqual;
        //順方向検査
        {
            isEqual = (
                    this.start.levelEqual(anotherPath.getStart()) &&
                            this.end.levelEqual(anotherPath.getEnd()) &&
                            this.getWayCount() == anotherPath.getWayCount()
            );
            if(isEqual){
                for(int thisIdx = 0, otherIdx = 0; thisIdx < this.way.size();/* nil */){
                    //way整合
                    if(this.way.get(thisIdx).levelEqual(anotherPath.getWayPoint(otherIdx))){
                        thisIdx++;
                        otherIdx++;
                        continue;
                    }
                    isEqual = false;
                    break;
                }
            }
            if(isEqual){
                return true;
            }
        }
        //逆方向検査
        {
            isEqual = (
                    this.start.levelEqual(anotherPath.getEnd()) &&
                            this.end.levelEqual(anotherPath.getStart()) &&
                            this.getWayCount() == anotherPath.getWayCount()
            );
            if(isEqual){
                for(int thisIdx = 0, otherIdx = anotherPath.getWayCount()-1; thisIdx < this.way.size();/* nil */){
                    //way整合
                    if(this.way.get(thisIdx).levelEqual(anotherPath.getWayPoint(otherIdx))){
                        thisIdx++;
                        otherIdx--;
                        continue;
                    }
                    isEqual = false;
                    break;
                }
            }
        }
        return isEqual;
    }

    @Override
    abstract public AbstractPath clone();

    /* override from Object */
    @Override
    public boolean equals(Object another) {
        if(!(another instanceof IPath)){
            return false;
        }
        final IPath anotherPath = (IPath) another;
        boolean isEqual;
        //順方向検査
        {
            isEqual = (
                    this.start.equals(anotherPath.getStart()) &&
                    this.end.equals(anotherPath.getEnd()) &&
                    this.getWayCount() == anotherPath.getWayCount()
                    );
            if(isEqual){
                for(int thisIdx = 0, otherIdx = 0; thisIdx < this.way.size();/* nil */){
                    //way整合
                    IPoint thisWayPoint = this.way.get(thisIdx);
                    IPoint anotherWayPoint = anotherPath.getWayPoint(otherIdx);
                    if(thisWayPoint.equals(anotherWayPoint)) {
                        thisIdx++;
                        otherIdx++;
                        continue;
                    }
                    isEqual = false;
                    break;
                }
            }
            if(isEqual){
                return true;
            }
        }
        //逆方向検査
        {
            isEqual = (
                    this.start.equals(anotherPath.getEnd()) &&
                    this.end.equals(anotherPath.getStart()) &&
                    this.getWayCount() == anotherPath.getWayCount()
            );
            if(isEqual){
                for(int thisIdx = 0, otherIdx = anotherPath.getWayCount()-1; thisIdx < this.way.size();/* nil */){
                    //way整合
                    if(this.way.get(thisIdx).equals(anotherPath.getWayPoint(otherIdx))){
                        thisIdx++;
                        otherIdx--;
                        continue;
                    }
                    isEqual = false;
                    break;
                }
            }
        }
        return isEqual;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Path:{");
        sb.append("start:{").append(start.toString()).append("},");
        sb.append("way:[");
        int waySize = way.size();
        for(int i=0; i<waySize; i++){
            sb.append(way.get(i).toString());
            if(i < waySize-1){
                sb.append(",");
            }
        }
        sb.append("],");
        sb.append("end:{").append(end.toString()).append("}");
        sb.append("}");
        return sb.toString();
    }

}
