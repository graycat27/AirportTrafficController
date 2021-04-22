package com.github.graycat27.atc.components.data.json;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.data.common.DataManager;
import com.github.graycat27.atc.components.data.defines.DataCondition;
import com.github.graycat27.atc.components.data.defines.IDataObject;
import com.github.graycat27.atc.consts.DataSourceType;

import com.github.ucchyocean.lc.lib.com.google.gson.GsonBuilder;
import com.github.ucchyocean.lc.lib.com.google.gson.Gson;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * JSON形式でのデータの保存と読み出しを実装します
 */
public class JsonDataManager extends DataManager {

    /* フィールド */
    private final String saveFilePath;
    private final String saveFileName = "atcConfig.json";

    /* コンストラクタ */
    public JsonDataManager(){
        super(DataSourceType.JSON);
        saveFilePath = AirportTrafficController.getPlugin(AirportTrafficController.class).getDataFolder().getPath()
                + File.separator + saveFileName;
    }

    /* メソッド */
    @Override
    public void save(IDataObject data, DataCondition con) {
        File file = new File(saveFilePath);
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().create();
            gson.toJson(data, writer);
            //FIXME たぶん上書きとか変な挙動する
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public IDataObject read(IDataObject dataObject, DataCondition con) {
        File file = new File(saveFilePath);
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, dataObject.getClass());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
