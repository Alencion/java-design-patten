package Visitor;
//Double Dispatch
public class ClassicVisitor {
}

interface  ExpressionVisitor{
    void visit(DoubleExpression2 E);
    void visit(AdditionExpression E);
}

abstract class Expression2{
    public abstract void accept(ExpressionVisitor visitor);
}

class DoubleExpression2 extends Expression2{
    public double value;

    public DoubleExpression2(double value) {
        this.value = value;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}