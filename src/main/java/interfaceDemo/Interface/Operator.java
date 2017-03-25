package interfaceDemo.Interface;

/**
 * User: QiZiCoder4
 * Date: 2017/3/9
 * Time: 9:19
 */
public interface Operator {
    double operate(Value left, Value right);

    int getPriority();

}
