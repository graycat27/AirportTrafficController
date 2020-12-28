package com.github.graycat27.atc.components;

import com.github.graycat27.atc.consts.Property;
import com.github.graycat27.atc.setting.PropertySettings;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.util.List;
import java.util.Objects;

/** プロパティファイルの値を利用した判定処理等の部品 */
public class PropertyUtil {


    /* WorldName */
    public static World getWorld(){
        String worldName = PropertySettings.worldName();
        World w = Bukkit.getWorld(worldName);
        if(w == null){
            throw new IllegalArgumentException("there is no such world :"+ worldName);
        }
        return w;
    }

}
