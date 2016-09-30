package com.example.acer.littleprolog;

/**
 * Created by Acer on 29/9/2016.
 */

public class Expression {
    private Integer leftSide;
    private Integer rightSide;
    private String operator;
    private Expression exp;
    private Boolean result;

    public Expression(Integer left, String operator, Integer right){
        this.leftSide = left;
        this.rightSide = right;
        this.operator = operator;
        this.result = compute(left,operator,right);
    }

    public Expression(Integer left, String operator, Expression exp){
        this.leftSide = left;
        this.operator = operator;
        this.exp = exp;
        Integer result1 = compute2(exp.getLeftSide(),exp.getOperator(),exp.getRightSide());
        this.result = compute(this.leftSide,this.operator,result1);
    }

    public Integer getLeftSide(){
        return this.leftSide;
    }

    public String getOperator(){
        return this.operator;
    }

    public Integer getRightSide(){
        return this.rightSide;
    }

    public Boolean getResult(){
        return this.result;
    }

    public Boolean compute(Integer left, String operator, Integer right){
        Boolean result = null;
        if (operator.equals("==")){
            result = left.equals(right);
        }
        else if (operator.equals(">")){
            result = left > right;
        }
        else if (operator.equals("<")){
            result = left < right;
        }
        else if (operator.equals(">=")){
            result = left >= right;
        }
        else if (operator.equals("<=")){
            result = left <= right;
        }
        return result;
    }

    public Integer compute2(Integer left, String operator, Integer right){
        Integer result = null;
        if (operator.equals("+")){
            result = left + right;
        }
        else if (operator.equals("-")){
            result = left - right;
        }
        else if (operator.equals("*")){
            result = left * right;
        }
        else if (operator.equals("/")){
            result = left / right;
        }
        return result;
    }
}
