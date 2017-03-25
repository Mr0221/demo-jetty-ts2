package interfaceDemo.imp;

import interfaceDemo.Interface.Operator;
import interfaceDemo.Interface.Value;

/**
 * User: QiZiCoder4
 * Date: 2017/3/9
 * Time: 9:27
 */
public class SubtractionOperator implements Operator {

    public double operate(Value left, Value right) {
        return left.getNumber() - right.getNumber();
    }

    public int getPriority() {
        return 1;
    }

    @Override
    public String toString() {
        return "-";
    }
}
