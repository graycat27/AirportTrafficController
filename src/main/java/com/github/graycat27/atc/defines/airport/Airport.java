package com.github.graycat27.atc.defines.airport;

import com.github.graycat27.atc.defines.sky.ATCArea;

import java.util.List;

/**
 * 空港が持つ情報を定義します
 */
public class Airport {

    //定義情報
    /** 空港名 */
    private String name = "";

    //施設系
    /** 滑走路 */
    private List<Runway> runways;

    /** 誘導路 */

    /** 駐機位置（スポット） */
    private List<Spot> spots;

    /** 管制塔 */
    private Tower tower;

    //管制系
    /** 管制空域 */
    private List<ATCArea> areas;



    //コンストラクタ


    //メソッド
}
