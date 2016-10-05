package com.example.acer.littleprolog;

/**
 * Created by Ivan on 5/10/2016.
 * Purpose: Expression is in charge of storing the parameters of expression and calculating and returns the result of the operation.
 */

public class Expression {
    //An integer to store the value of the left side from expression
    private Integer leftSide;
    //An integer to store the value of the right side from expression
    private Integer rightSide;
    //A string to store the value of the operator from expression
    private String operator;
    //A string to store the value of the expression
    private Expression exp;
    //A boolean to store the result of the expression
    private Boolean result;

    public Expression(Integer left, String operator, Integer right){
        /*
        @desc: To initialise the values of the expression
        @param: None
        @pre: left - integer that stores the leftside of the expression, operator - string that stores the operator of the expression, right - integer that stores the rightside of the expression
        @post: the values are initialised
        * */
        this.leftSide = left;
        this.rightSide = right;
        this.operator = operator;
        this.result = compute(left,operator,right);
    }

    public Expression(Integer left, String operator, Expression exp){
        /*
        @desc: To initialise the 2nd type of expression
        @param: None
        @pre: left - integer that stores the leftside of the expression, operator - string that stores the operator of the expression, exp - the expression on the right side
        @post: the values are initialised
        * */
        this.leftSide = left;
        this.operator = operator;
        this.exp = exp;
        Integer result1 = compute2(exp.getLeftSide(),exp.getOperator(),exp.getRightSide());
        this.result = compute(this.leftSide,this.operator,result1);
    }

    public Integer getLeftSide(){
        /*
        @desc: To retrieve the value of the leftside of expression
        @param: None
        @pre: None
        @post: the value are returned
        */
        return this.leftSide;
    }

    public String getOperator(){
        /*
        @desc: To retrieve the value of the operator of expression
        @param: None
        @pre: None
        @post: the value are returned
        */
        return this.operator;
    }

    public Integer getRightSide(){
        /*
        @desc: To retrieve the value of the rightside of expression
        @param: None
        @pre: None
        @post: the value are returned
        */
        return this.rightSide;
    }

    public Boolean getResult(){
        /*
        @desc: To retrieve the boolean of the result of expression
        @param: None
        @pre: None
        @post: the value are returned
        */
        return this.result;
    }

    public Boolean compute(Integer left, String operator, Integer right){
        /*
        @desc: To retrieve the value of the expression
        @param: None
        @pre: None
        @post: the value are returned
        */
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
        /*
        @desc: To retrieve the value of the expression
        @param: None
        @pre: None
        @post: the value are returned
        */
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
