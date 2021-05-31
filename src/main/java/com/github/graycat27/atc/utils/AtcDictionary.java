package com.github.graycat27.atc.utils;

import com.github.graycat27.atc.AirportTrafficController;
import com.github.graycat27.atc.consts.Property;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class AtcDictionary {

    private AtcDictionary(){ /* インスタンス化防止 */ }

    private static Properties dic;

    public static String get(final String key){
        return dic.getProperty(key, key);
    }

    public static void updateDictionary(){
        Path path = Paths.get(
                AirportTrafficController.getPlugin(AirportTrafficController.class).getDataFolder().getPath(),
                Property.FILE_PATH_DICTIONARY);

        dic = new Properties();
        try {
            dic.load(Files.newBufferedReader(path, StandardCharsets.UTF_8));
        } catch (NoSuchFileException e){
            File file = new File(path.toString());
            if(!file.exists()){
                try {
                    file.getParentFile().mkdirs();
                    file.createNewFile();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
