package com.github.graycat27.atc.components.data.json.objects;

import com.github.graycat27.atc.components.data.MasterData;
import com.github.graycat27.atc.components.data.json.IJsonDataObject;

public class MasterDataObject implements IJsonDataObject {

    //フィールド


    //コンストラクタ


    //メソッド
    @Override
    public MasterData getOriginal() {
        return new MasterData();
    }
}