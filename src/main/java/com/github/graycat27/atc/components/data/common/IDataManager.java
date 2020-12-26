package com.github.graycat27.atc.components.data.common;

import com.github.graycat27.atc.components.data.defines.DataCondition;
import com.github.graycat27.atc.components.data.defines.IDataObject;

/**
 * データ管理のAPI
 */
public interface IDataManager {

    void save(IDataObject obj, DataCondition con);

    IDataObject read(DataCondition con);
}
