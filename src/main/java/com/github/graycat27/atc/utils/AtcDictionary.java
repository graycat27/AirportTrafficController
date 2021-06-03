package com.github.graycat27.atc.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import com.github.graycat27.atc.consts.Property;

public class AtcDictionary {

    private AtcDictionary(){ /* インスタンス化防止 */ }

    private static Properties dic;

    public static String get(final String key){
        return dic.getProperty(key.toUpperCase(), key);
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
