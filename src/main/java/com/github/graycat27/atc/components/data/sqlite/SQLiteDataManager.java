package com.github.graycat27.atc.components.data.sqlite;

import com.github.graycat27.atc.components.data.DataManager;
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
    public void save() {
        throw new UnsupportedOperationException();
    }

    @Override
    public IDataObject read() {
        throw new UnsupportedOperationException();
    }
}
