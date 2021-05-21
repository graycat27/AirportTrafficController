package com.github.graycat27.atc.defines.i;

import com.github.graycat27.atc.utils.ICloneable;
import org.bukkit.Location;

/**
 * 地点情報
 */
public interface IPoint extends IMaster {

    Location getLocation();
    int getX();
    int getY();
    int getZ();
    /** 三次元空間での直線距離を返します */
    double distance(final IPoint another);
    /** 水平面での直線距離を返します */
    double levelDistance(final IPoint another);

    /** このPointを水平面に投射したときに同じであるか否かを返します */
    boolean levelEqual(Object another);

    @Override
    IPoint clone();

    static IPoint getByLocation(Location location){
        return new ConcretePoint(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }
}
