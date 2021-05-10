package com.github.graycat27.atc.defines.i;

import com.github.graycat27.atc.utils.ICloneable;

public interface IPath extends IMaster {

    IPoint getStart();
    int getWayCount();
    IPoint getWayPoint(int index);
    IPoint getEnd();

    /** 経由点をすべて通る長さを返します（三次元） */
    double length();
    /** 経由点をすべて通る水平面の長さを返します */
    double levelLength();

    /** 始終点の直線距離を返します（三次元） */
    double distance();
    /** 始終点の水平面直線距離を返します */
    double levelDistance();

    /** 始終点が同じ座標の「閉じた」図形であるかを返します
     * @return 閉じた図形の時、<code>true</code> */
    boolean isClosed();

    /** このPathが示す線形上に存在するか否かを返します */
    boolean isOnPath(IPoint point);
    /** このPathを水平面に投射した線形上に存在するか否かを返します */
    boolean isOnLevelPath(IPoint point);

    /** このPathを水平面に投射したときに同じであるか否かを返します */
    boolean levelEquals(Object another);
    /** 始終点と経由点が合致した同一の線形を表わしているかを返します。<br>
     * 始終点が逆の線形は<code>true</code>と評価します<br>
     * 経由地点の指定が異なる場合は<code>false</code>と評価します */
    @Override
    boolean equals(Object another);

    @Override
    IPath clone();

}
