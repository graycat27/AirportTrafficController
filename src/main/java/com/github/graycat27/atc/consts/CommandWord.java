package com.github.graycat27.atc.consts;

/** コマンドの入力語 */
public final class CommandWord {

    /**  */
    public static final String ATC = "atc";

    /** 周波数管理コマンド群 */
    public static class Freq {
        public static final String FREQ = "freq";
        /** チューニング（参加・切替） */
        public static final String TUNING = "tuning";
        /** 切断 */
        public static final String CUT = "cut";
    }
}
