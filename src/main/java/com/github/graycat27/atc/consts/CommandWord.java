package com.github.graycat27.atc.consts;

/** コマンドの入力語 */
public final class CommandWord {

    /**  */
    public static final String ATC_full = "AirTrafficController";
    public static final String ATC = "atc";

    /** ヘルプ */
    public static final String HELP = "help";

    /** 周波数管理コマンド群 */
    public static class Freq {
        public static final String FREQ = "freq";
        /** チューニング（参加・切替） */
        public static final String MONITOR = "monitor";
        /** 切断 */
        public static final String CUT = "cut";
    }

    /* 管理コマンド群 */
    /** データ管理系 */
    public static class Manage {
        public static final String MANAGE = "manage";
        /** 追加 */
        public static final String ADD = "add";
        /** 編集 */
        public static final String MODIFY = "mod";
        /** 照会 */
        public static final String INFO = "info";
        /*
        削除 DELETE
         */
    }

    /** 対象データ */
    public static class Target {
        /** 空港 */
        public static final String AIRPORT = "airport";
        /** 管制圏 */
        public static final String AREA = "area";
    }

    /** 空港の詳細情報キー */
    public static class AirportMeta {
        /** 名称 日本語可 */
        public static final String NAME = "name";
        /** atc name 英数字のみ */
        public static final String ATC_Name = "ATC_name";
        /** TWR管制の周波数 */
        public static final String TWR_FREQ = "TWR_freq";
        /** CTL管制の周波数 */
        public static final String CTL_FREQ = "CTL_freq";
    }

}
