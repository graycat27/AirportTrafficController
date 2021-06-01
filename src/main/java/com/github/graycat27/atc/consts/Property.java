package com.github.graycat27.atc.consts;

import com.github.graycat27.atc.AirportTrafficController;

public class Property {
    private Property(){ /* インスタンス化防止 */ }

    //プロパティファイルのディレクトリパス
    public static final String FILE_PATH_DIR =
            AirportTrafficController.getPlugin(AirportTrafficController.class).getDataFolder().getPath();

    //プロパティファイルのファイル名
    public static final String FILE_PATH_DICTIONARY = "atc_dic.prop";


    //プロパティから読みだすキー値

    /** 管制設定のできるワールド名（サーバ内で単一指定） */
    public static final String WORLD_NAME = "world_name";

}
