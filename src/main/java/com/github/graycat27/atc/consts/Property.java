package com.github.graycat27.atc.consts;

import com.github.graycat27.atc.AirportTrafficController;

public class Property {
    private Property(){ /* インスタンス化防止 */ }

    /** プロパティファイルのディレクトリパス */
    public static final String FILE_PATH_DIR =
            AirportTrafficController.getPlugin(AirportTrafficController.class).getDataFolder().getPath();

    /** 辞書ファイルのファイル名 */
    public static final String FILE_NAME_DICTIONARY = "atc_dic.prop";

    /** プロパティファイルのファイル名 */
    public static final String FILE_NAME_PROPERTY = "atc_property.property";

    //プロパティから読みだすキー値
    /** 管制設定のできるワールド名（サーバ内で単一指定） */
    public static final String WORLD_NAME = "world_name";

    /** レーダーの照射秒 */
    public static final String RADAR_SECONDS = "radar_seconds";

}
