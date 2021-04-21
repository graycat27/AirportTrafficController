package com.github.graycat27.atc.components.data.sqlite;

import com.github.graycat27.atc.components.data.common.DataManager;
import com.github.graycat27.atc.components.data.defines.DataCondition;
import com.github.graycat27.atc.components.data.defines.IDataObject;
import com.github.graycat27.atc.consts.DataSourceType;

public class SQLiteDataManager extends DataManager {

    /* フィールド */



    /* コンストラクタ */
    SQLiteDataManager(){
        super(DataSourceType.SQLite);
    }

    /* メソッド */
    @Override
    public void save(IDataObject obj, DataCondition con) {
        throw new UnsupportedOperationException();
    }

    @Override
    public IDataObject read(IDataObject obj, DataCondition con) {
        throw new UnsupportedOperationException();
    }
}
