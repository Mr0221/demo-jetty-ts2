package interfaceDemo.Test;


import java.util.ArrayList;

import interfaceDemo.Interface.Operator;
import interfaceDemo.Interface.Value;
import interfaceDemo.imp.AddOperator;
import interfaceDemo.imp.SubtractionOperator;
import interfaceDemo.imp.ValueNumber;

/**
 * User: QiZiCoder4
 * Date: 2017/3/9
 * Time: 9:28
 */
public class Test {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<Object>();
        list.add(new ValueNumber(2));
        list.add(new AddOperator());
        list.add(new ValueNumber(4));
        list.add(new SubtractionOperator());
        list.add(new ValueNumber(5));
        System.out.println(list);
        while (list.size() > 1) {
            Operator operator = null;
            int operatorIndex = -1;
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i);
                if (obj instanceof Operator) {
                    Operator oper = (Operator) obj;
                    if (operator == null) {
                        operator = oper;
                        operatorIndex = i;
                    }else if (operator.getPriority()<oper.getPriority()){
                        operator = oper;
                        operatorIndex = i;
                    }
                }
            }
        Value left = (Value) list.get(operatorIndex - 1);
        Value right = (Value) list.get(operatorIndex + 1);
        System.out.println(left);
        System.out.println(right);
        Value result = new ValueNumber(operator.operate(left, right));
        list.remove(operatorIndex - 1);
        list.remove(operatorIndex - 1);
        list.remove(operatorIndex - 1);
        list.add(operatorIndex - 1, result);
        }
        System.out.println(list);
    }
}
