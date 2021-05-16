package com.github.graycat27.atc.components.data.defines;

import com.github.graycat27.atc.defines.i.IMaster;

/**
 * 永続データとして保存する形式のデータオブジェクトとしてマークするインタフェース
 */
public interface IDataObject {

    IDataObject convertFromOriginal(IMaster original);
}
