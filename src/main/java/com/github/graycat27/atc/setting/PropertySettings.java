package com.github.graycat27.atc.setting;

import com.github.graycat27.atc.consts.Property;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/** プロパティから読み出した値を持つ */
public class PropertySettings {

    private static Properties props = new Properties();

    private PropertySettings() { /* インスタンス化防止 */ }

    public static void readProps(){
        Path propFilePath = Path.of(Property.FILE_PATH_DIR, Property.FILE_NAME_PROPERTY);
        try {
            props.load(Files.newBufferedReader(propFilePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String worldName(){
        String worldName = (String) props.get(Property.WORLD_NAME);
        return worldName != null ? worldName : "world";
    }

    public static int radarSeconds(){
        return Integer.parseInt((String) props.get(Property.RADAR_SECONDS));
    }

}
