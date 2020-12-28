package com.github.graycat27.atc.defines.i;

import org.bukkit.Location;

/**
 * 地点情報
 */
public interface IPoint {

    Location getLocation();
    int getX();
    int getY();
    int getZ();
    /** 三次元空間での直線距離を返します */
    double distance(final IPoint another);
    /** 水平面での直線距離を返します */
    double levelDistance(final IPoint another);
}
