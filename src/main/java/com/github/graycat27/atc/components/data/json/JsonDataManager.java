package com.github.graycat27.atc.components.data.json;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.components.data.DataManager;
import com.github.graycat27.atc.components.data.defines.IDataObject;
import com.github.graycat27.atc.components.data.json.objects.MasterDataObject;
import com.github.graycat27.atc.consts.DataSourceType;

import com.github.graycat27.atc.defines.events.AtcDataUpdateEvent;
import com.github.graycat27.atc.defines.i.IFrequency;
import com.github.graycat27.atc.defines.i.IPoint;
import com.github.ucchyocean.lc.lib.com.google.gson.GsonBuilder;
import com.github.ucchyocean.lc.lib.com.google.gson.Gson;
import com.github.ucchyocean.lc.lib.com.google.gson.JsonElement;
import com.github.ucchyocean.lc.lib.com.google.gson.stream.JsonWriter;
import org.bukkit.Bukkit;

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
        File file = new File(saveFilePath);
        if(!file.exists()){
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    /* メソッド */
    @Override
    public void save() {
        IJsonDataObject jsonData = MasterDataObject.convertFromOriginal(this.data);
        saveJson(jsonData);
        Bukkit.getServer().getPluginManager().callEvent(new AtcDataUpdateEvent());
    }

    private void saveJson(IJsonDataObject data){
        try(JsonWriter writer = new JsonWriter(new FileWriter(saveFilePath))){
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(IFrequency.class, new InterfaceAdapter());
            builder.registerTypeAdapter(IPoint.class, new InterfaceAdapter());
            Gson gson = builder.create();
            writer.setIndent("   ");    // 3spaces
            JsonElement jsonEl = gson.toJsonTree(data);
            gson.toJson(jsonEl, writer);
        }catch(IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public IDataObject read() {
        MasterDataObject dataObject = (MasterDataObject) readJson();
        this.data = dataObject.getOriginal();
        Bukkit.getServer().getPluginManager().callEvent(new AtcDataUpdateEvent());
        return dataObject;
    }

    private IJsonDataObject readJson(){
        File file = new File(saveFilePath);
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(IFrequency.class, new InterfaceAdapter());
            builder.registerTypeAdapter(IPoint.class, new InterfaceAdapter());
            Gson gson = builder.create();
            MasterDataObject result = gson.fromJson(reader, MasterDataObject.class);
            return result == null ? new MasterDataObject() : result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
