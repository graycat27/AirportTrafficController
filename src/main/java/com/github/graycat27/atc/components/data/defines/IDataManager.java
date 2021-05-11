package com.github.graycat27.atc.components.data.defines;

import com.github.graycat27.atc.components.data.MasterData;
import com.github.graycat27.atc.components.data.defines.IDataObject;

/**
 * データ管理のAPI
 */
public interface IDataManager {

    void save(IDataObject obj);

    IDataObject read(IDataObject obj);

}
