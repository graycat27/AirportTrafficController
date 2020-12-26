package com.github.graycat27.atc.components.data.json;

import com.github.graycat27.atc.components.data.common.DataManager;
import com.github.graycat27.atc.components.data.defines.DataCondition;
import com.github.graycat27.atc.components.data.defines.IDataObject;
import com.github.graycat27.atc.consts.DataSourceType;
import com.google.gson.Gson;

/**
 * JSON形式でのデータの保存と読み出しを実装します
 */
public class JsonDataManager extends DataManager {

    /* フィールド */
    //FIXME 保存先パス。設定ファイルから取得？plugins\atc\とか固定パス？
    private final String saveRootPath = null;



    /* コンストラクタ */
    JsonDataManager(){
        super(DataSourceType.JSON);
    }

    /* メソッド */
    @Override
    public void save(IDataObject data, DataCondition con) {
        Gson gson = new Gson();
        String jsonSrt = gson.toJson(data);

        throw new UnsupportedOperationException();
    }

    @Override
    public IDataObject read(DataCondition con) {
        throw new UnsupportedOperationException();
    }

}
