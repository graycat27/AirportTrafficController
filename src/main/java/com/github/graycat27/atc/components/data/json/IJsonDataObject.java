package com.github.graycat27.atc.components.data.json;

import com.github.graycat27.atc.components.data.defines.IDataObject;
import com.github.graycat27.atc.defines.i.IMaster;
import com.github.ucchyocean.lc.lib.com.google.gson.Gson;

public interface IJsonDataObject extends IDataObject {

    default String toJsonString(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    /** 保管用データ */
    IMaster getOriginal();
}
