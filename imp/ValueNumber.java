package imp;

import Interface.Value;

/**
 * User: QiZiCoder4
 * Date: 2017/3/9
 * Time: 9:30
 */
public class ValueNumber implements Value{

    private double number;

    public ValueNumber(double number) {
        this.number = number;
    }

    @Override
    public double getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}
