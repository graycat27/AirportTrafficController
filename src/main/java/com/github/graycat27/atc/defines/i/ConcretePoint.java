package com.github.graycat27.atc.defines.i;

/** Pointの具象クラス<br>
 * NOTE: {@link AbstractPoint}を継承して具体的な地点クラスを作成してください */
public class ConcretePoint extends AbstractPoint {

    public ConcretePoint(int posX, int posZ){
        super(posX, posZ);
    }
    public ConcretePoint(int posX, int posY, int posZ) {
        super(posX, posY, posZ);
    }

    @Override
    public ConcretePoint clone() {
        return new ConcretePoint(getX(), getY(), getZ());
    }
}
