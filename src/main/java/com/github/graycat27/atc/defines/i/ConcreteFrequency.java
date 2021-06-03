package com.github.graycat27.atc.defines.i;

/** Frequencyの具象クラス
 * @deprecated AbstractFrequencyを継承して具体的な周波数クラスを作成してください */
@Deprecated
public class ConcreteFrequency extends AbstractFrequency {

    public ConcreteFrequency(String freq){
        super(freq);
    }
    public ConcreteFrequency(int n, int d) {
        super(n, d);
    }

    @Override
    public AbstractFrequency clone() {
        return new ConcreteFrequency(getFreq());
    }

    @Override
    public String toString(){
        return super.toString();
    }
}
