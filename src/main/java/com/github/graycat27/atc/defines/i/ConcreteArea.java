package com.github.graycat27.atc.defines.i;

/** Areaの具象クラス<br>
 * NOTE: {@link AbstractArea}を継承して具体的な領域クラスを作成してください */
public class ConcreteArea extends AbstractArea {

    public ConcreteArea(IPoint pos1, IPoint pos2){
        super(pos1, pos2);
    }

    @Override
    public ConcreteArea clone() {
        return new ConcreteArea(getMinPoint(), getMaxPoint());
    }
}
