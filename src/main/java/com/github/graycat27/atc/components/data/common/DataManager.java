package com.github.graycat27.atc.components.data.common;

import com.github.graycat27.atc.consts.DataSourceType;

public abstract class DataManager implements IDataManager {

    private DataSourceType dataSourceType;

    protected DataManager(DataSourceType type){
        dataSourceType = type;
    }

}
