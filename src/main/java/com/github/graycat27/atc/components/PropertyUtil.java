package com.github.graycat27.atc.components;

import com.github.graycat27.atc.consts.Property;
import com.github.graycat27.atc.setting.PropertySettings;
import com.github.graycat27.atc.utils.AtcDictionary;
import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/** プロパティファイルの値を利用した判定処理等の部品 */
public class PropertyUtil {

    /** 実行jarからプロパティファイルを展開する */
    public static void deployPropFile(){
        try {   /*  jarに同封されるresource配下のファイルを展開する
                    ref: https://hiroki-sawano.hatenablog.com/entry/copy-resource-dir-in-jar */
            final String srcDirName = "default_props";  // コピーしたいリソースを格納してあるresourceディレクトリ
            File destDir = new File(Property.FILE_PATH_DIR);       // コピー先のディレクトリ
            final String def = "default_";

            final File jarFile = new File(AtcDictionary.class.getProtectionDomain().getCodeSource().getLocation().getPath());
            final JarFile jar = new JarFile(jarFile);
            for (Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements(); ) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().startsWith(srcDirName + "/") && !entry.isDirectory()) {

                    String destFileName = entry.getName().substring(srcDirName.length() + 1);
                    if(destFileName.startsWith(def)){
                        destFileName = destFileName.substring(def.length());
                    }
                    File dest = new File(destDir, destFileName);

                    if(!dest.exists()) {
                        File parent = dest.getParentFile();
                        if (parent != null) {
                            parent.mkdirs();
                        }
                        try (FileOutputStream out = new FileOutputStream(dest);
                             InputStream in = jar.getInputStream(entry)) {
                            byte[] buffer = new byte[8 * 1024];
                            int s;
                            while ((s = in.read(buffer)) > 0) {
                                out.write(buffer, 0, s);
                            }
                        }
                    }
                }
            }
            jar.close();
        }catch(IOException ex){
            throw new RuntimeException(ex);
        }
    }


    /** World */
    public static World getWorld(){
        String worldName = PropertySettings.worldName();
        World w = Bukkit.getWorld(worldName);
        if(w == null){
            throw new IllegalArgumentException("there is no such world :"+ worldName);
        }
        return w;
    }

}
