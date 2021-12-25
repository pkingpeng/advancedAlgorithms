package com.pqr.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @file: PolandNotation.java
 * @time: 2021/4/20 11:16 PM
 * @Author by Pking
 * <p>
 * 逆波兰表达式
 */
public class PolandNotation {
    public static void main(String[] args) {

        //完成一个中缀表达式 --> 后缀表达式
        //1. 1+((2+3)*4)-5 --> 1 2 3 + 4 * + 5 -
        //2. str操作不方便 --> 先把string的中缀表达式 传唤为 ArrayList = [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]
        //3. nifixExpressionList --> sufixExpressionList
        // 即 [1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] --> [1, 2, 3, +, 4, *, +, 5, -]
        String nifixExpression = "1+((2+3)*4)-5";
        List<String> nifixExpressionList = toNifixExpressionList(nifixExpression);
        System.out.println("中缀表达式list" + nifixExpressionList);

        List<String> suffixExpressionList = parseToSuffixExpressionList(nifixExpressionList);
        System.out.println("后缀表达式list:" + suffixExpressionList);


//        //定义一个逆波兰表达式
//        String suffixExpression = "3 4 + 5 * 6 -";
//        //思路
//        //1. 先将后缀表达式 放到 --> arrayList
//        //2. 结合arrayList 传给方法，配合栈完成计算
//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println("rpnList=" + rpnList);

        //测试
        int res = calculate(suffixExpressionList);
        System.out.printf("计算结果为：%d\n", res);
    }


    //将中缀表达式的string --> list
    public static List<String> toNifixExpressionList(String s) {
        List<String> nifixExpressionList = new ArrayList<String>();
        int i = 0; //默认为0，用于遍历中缀表达式字符串
        String str;
        char c;
        do {
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) { //c 不是一个数字
                nifixExpressionList.add("" + c);
                i++;
            } else {//数字，需要考虑多位数，进行拼接
                str = "";
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {//c是数字 '0' --> 48, '9' --> 57
                    str += c;
                    i++;
                }
                nifixExpressionList.add(str);
            }
        } while (i < s.length());
        return nifixExpressionList;
    }

    //中缀表达式list 转换为后缀表达式list

    /**
     * 1) 初始化两个栈:运算符栈 s1 和储存中间结果的栈 s2;
     * 2) 从左至右扫描中缀表达式;
     * 3) 遇到操作数时，将其压 s2;
     * 4) 遇到运算符时，比较其与 s1 栈顶运算符的优先级:
     * 1.如果 s1 为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈;
     * 2.否则，若优先级比栈顶运算符的高，也将运算符压入 s1;
     * 3.否则，将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到(4-1)与 s1 中新的栈顶运算符相比较;
     * 5) 遇到括号时:
     * 1. 如果是左括号“(”，则直接压入 s1
     * 2. 如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
     * 6) 重复步骤 2) 至 5)，直到表达式的最右边
     * 7) 将 s1 中剩余的运算符依次弹出并压入 s2
     * 8) 依次弹出 s2 中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式
     **/
    public static List<String> parseToSuffixExpressionList(List<String> nifixExpressionList) {
        Stack<String> s1 = new Stack<String>();
        List<String> s2 = new ArrayList<String>();

        //遍历中缀表达式list
        for (String item : nifixExpressionList) {
            //数字 --> s2
            if (item.matches("\\d+")) {//数字
                s2.add(item);
            } else if (item.equals("(") || s1.size() == 0) {
                s1.push(item);
            } else if (item.equals(")")) {
                // 如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //弹出(, 从而消除一对小括号
            } else {
                //item优先级 <= s1栈顶的优先级
                //将 s1 栈顶的运算符弹出并压入到 s2 中，再次转到(4-1)与 s1 中新的栈顶运算符相比较
                while (s1.size() != 0 && Operation.getPriorityValue(s1.peek()) >= Operation.getPriorityValue(item)) {
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }

//    //将一个后缀表达式，依次将数据和运算符 放入到一个ArrayList中
//    public static List<String> getListString(String suffixExpression) {
//        //分割
//        String[] split = suffixExpression.split(" ");
//        List<String> list = new ArrayList<String>();
//        for (String ele : split) {
//            list.add(ele); // ele --> 3, 4, + ...
//        }
//        return list;
//    }


    //完成对于逆波兰表达式的运算

    /**
     * eg: (3+4)×5-6 对应的后缀表达式就是 3 4 + 5 × 6 - , 针对后缀表达式求值步骤如下:
     * 1.从左至右扫描，将 3 和 4 压入堆栈;
     * 2.遇到+运算符，因此弹出 4 和 3(4 为栈顶元素，3 为次顶元素)，计算出 3+4 的值，得 7，再将 7 入栈; 3.将 5 入栈;
     * 4.接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈;
     * 5.将 6 入栈;
     * 6.最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
     **/
    public static int calculate(List<String> ls) {
        //创建栈，一个即可
        Stack<String> stack = new Stack<String>();
        //遍历list
        for (String item : ls) {
            //使用正则表达式取出数
            if (item.matches("\\d+")) { //代表匹配的是多位数字
                stack.push(item); //数字 直接入栈
            } else {
                //字母--> pop两个数并运算，结果入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                //判断是什么符号
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //结果入栈
                stack.push(res + "");  //整数转成字符串
            }
        }
        //最后留在栈里面的就是最终结果
        return Integer.parseInt(stack.pop());
    }
}

/**
 * 返回运算符对应的优先级
 **/
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //返回对应的优先级
    public static int getPriorityValue(String Operion) {
        int res = 0;
        switch (Operion) {
            case "+":
                res = ADD;
                break;
            case "-":
                res = SUB;
                break;
            case "*":
                res = MUL;
                break;
            case "/":
                res = DIV;
                break;
            default:
                break;
        }
        return res;
    }
}