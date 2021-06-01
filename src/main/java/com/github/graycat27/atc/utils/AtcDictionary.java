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
        Path dicFilePath = Path.of(Property.FILE_PATH_DIR, Property.FILE_NAME_DICTIONARY);
        dic = new Properties();
        try {
            dic.load(Files.newBufferedReader(dicFilePath, StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
