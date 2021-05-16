package com.github.graycat27.atc.components.data;

import com.github.graycat27.atc.components.data.defines.IDataManager;
import com.github.graycat27.atc.consts.DataSourceType;

public abstract class DataManager implements IDataManager {

    // フィールド
    private DataSourceType dataSourceType;

    protected MasterData data;

    // コンストラクタ
    protected DataManager(DataSourceType type){
        dataSourceType = type;
    }

    // メソッド
    MasterData getData(){
        return data;
    }
}
