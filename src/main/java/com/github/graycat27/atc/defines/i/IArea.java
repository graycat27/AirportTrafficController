package com.github.graycat27.atc.defines.i;

import com.github.graycat27.atc.utils.ICloneable;

/** 空域、地表面域など、x-y-zの範囲を持つ */
public interface IArea extends ICloneable {

    @Override
    IArea clone();
}
