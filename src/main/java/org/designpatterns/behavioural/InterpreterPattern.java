package org.designpatterns.behavioural;

import java.util.HashMap;
import java.util.Map;

interface AbstractExpression {
    int interpret(Context context);
}

class NumberTerminalExpression implements AbstractExpression {

    String value;

    NumberTerminalExpression(String value) {
        this.value = value;
    }

    @Override
    public int interpret(Context context) {
        return context.get(value);
    }
}

class MultiplyNonTerminalExpression implements AbstractExpression {

    AbstractExpression leftExpression;
    AbstractExpression rightExpression;

    MultiplyNonTerminalExpression(AbstractExpression leftExpression, AbstractExpression rightExpression) {
        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
    }

    @Override
    public int interpret(Context context) {
        return leftExpression.interpret(context) * rightExpression.interpret(context);
    }
}

class Context {
    Map<String, Integer> contextMap = new HashMap<>();

    public void put(String str, int value) {
        contextMap.put(str, value);
    }

    public int get(String val) {
        return contextMap.get(val);
    }
}

public class InterpreterPattern {

    public static void main(String[] args) {
        Context context = new Context();
        context.put("a",2);
        context.put("b", 3);

        AbstractExpression abstractExpression = new MultiplyNonTerminalExpression(new NumberTerminalExpression("a"), new NumberTerminalExpression("b"));
        System.out.println(abstractExpression.interpret(context));
    }
}
