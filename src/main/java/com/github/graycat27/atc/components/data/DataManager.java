package com.github.graycat27.atc.components.data;

import com.github.graycat27.atc.components.data.MasterData;
import com.github.graycat27.atc.consts.DataSourceType;

public abstract class DataManager implements IDataManager {

    // フィールド
    private DataSourceType dataSourceType;

    private MasterData data;

    // コンストラクタ
    protected DataManager(DataSourceType type){
        dataSourceType = type;
    }

    // メソッド
    public MasterData getData(){
        return data;
    }
}
