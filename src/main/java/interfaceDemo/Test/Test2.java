package interfaceDemo.Test;



import java.util.ArrayList;

import interfaceDemo.Interface.Operator;
import interfaceDemo.Interface.Value;
import interfaceDemo.imp.SubtractionOperator;
import interfaceDemo.imp.ValueNumber;

/**
 * User: QiZiCoder4
 * Date: 2017/3/9
 * Time: 10:18
 */
public class Test2 {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(new ValueNumber(4));
        list.add(new SubtractionOperator());
        list.add(new ValueNumber(4));
        Operator operator = null;
        int operatorIndex = -1;
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            if (obj instanceof Operator) {
                Operator o = (Operator) obj;
                if (operator == null) {
                    operator = o;
                    operatorIndex = i;
                } else if (operator.getPriority() < o.getPriority()) {
                    operator = o;
                    operatorIndex = i;
                }
            }
        }
        System.out.println(list);
        Value left = (Value) list.get(operatorIndex - 1);
        Value right = (Value) list.get(operatorIndex + 1);
        Value result = new ValueNumber(operator.operate(left, right));
        list.remove(operatorIndex - 1);
        list.remove(operatorIndex - 1);
        list.remove(operatorIndex - 1);
        list.add(operatorIndex - 1, result);
        System.out.println(list);
        System.out.println(result);
    }
}
