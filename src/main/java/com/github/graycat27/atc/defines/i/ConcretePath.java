package com.github.graycat27.atc.defines.i;

import java.util.ArrayList;
import java.util.List;

/** Pathの具象クラス
 * @deprecated AbstractPathを継承して具体的な線形クラスを作成してください */
public class ConcretePath extends AbstractPath {

    public ConcretePath(List<IPoint> way){
        super(way);
    }

    @Override
    public ConcretePath clone() {
        List<IPoint> way = new ArrayList<>();
        way.add(getStart());
        int cnt = getWayCount();
        for(int i=0; i<cnt; i++){
            way.add(getWayPoint(i));
        }
        way.add(getEnd());
        return new ConcretePath(way);
    }
}
