import java.util.Stack;

public class Postfix {
    // 연산자 정의
    String oper = "()+-*/%";

    // 입력받은 스텍의 연산자 우선순위
    int[] isp = {0, 19, 12, 12, 13, 13, 13};

    // 입력하는 연산자 우선순위
    int[] icp = {20, 19, 12, 12, 13, 13, 13};

    private Stack<String> stack = new Stack<>();

    public String infix_to_postfix(String input) {
        StringBuilder result = new StringBuilder();

        for(String s : input.split("")) {
            if(!oper.contains(s)) {
                result.append(s);
            }

            else if (s.equals(")")) {
                while (!stack.empty() && !stack.peek().equals("(")) {
                    result.append(stack.pop());
                }
                // 남은 "(" 제거
                stack.pop();
            }
            else {
                while(!stack.empty() && isp[oper.indexOf(stack.peek())] >= icp[oper.indexOf(s)]) {
                    result.append(stack.pop());
                }
                stack.push(s);
            }
        }

        while(!stack.empty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }

    public void calculate_postfix(String input) {
        int op1,op2;
        for(String s : input.split("")) {
            switch (s) {
                case "+" :
                    op2 = Integer.parseInt(stack.pop());
                    op1 = Integer.parseInt(stack.pop());
                    stack.push(Integer.toString(op1+op2));
                    break;
                case "-" :
                    op2 = Integer.parseInt(stack.pop());
                    op1 = Integer.parseInt(stack.pop());
                    stack.push(Integer.toString(op1-op2));
                    break;
                case "*" :
                    op2 = Integer.parseInt(stack.pop());
                    op1 = Integer.parseInt(stack.pop());
                    stack.push(Integer.toString(op1*op2));
                    break;
                case "/" :
                    op2 = Integer.parseInt(stack.pop());
                    op1 = Integer.parseInt(stack.pop());
                    stack.push(Integer.toString(op1/op2));
                    break;
                case "%" :
                    op2 = Integer.parseInt(stack.pop());
                    op1 = Integer.parseInt(stack.pop());
                    stack.push(Integer.toString(op1%op2));
                    break;
                default :
                    stack.push(s);
            }
        }

        System.out.print(stack.pop());
    }


    public static void main(String[] args) {
        Postfix p = new Postfix();

        String s = p.infix_to_postfix("6/2-3+4*2");
        System.out.println(s);

        p.calculate_postfix("62/3-42*+");
    }
}
