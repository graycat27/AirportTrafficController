package com.github.graycat27.atc.defines.i;

import com.github.graycat27.atc.utils.ICloneable;

/** 空域、地表面域など、x-y-zの範囲を持つ */
public interface IArea extends IMaster {

    IPoint getMinPoint();
    IPoint getMaxPoint();

    /** 指定地点がこのエリア内であるか判定する
     * @return エリア内に含まれる場合<code>true</code> 含まれない場合<code>false</code> */
    boolean isIn(IPoint point);

    @Override
    IArea clone();
}
