package evaluatorexpresii;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.Deque;

public class FormaPostfixata {
	static int precedence(char c){
        switch (c){
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }
	
	public static boolean isOperand(char c) {
		if ((c >= '0')&& (c<='9') || (c >= 'a') && (c<='z') || (c >= 'A') && (c<='Z')) {
			return true;
		}
		return false;
	}
	
    static String infixToPostFix(String exp){
        String result = "";
        Deque<Character> stack = new ArrayDeque<>();
        if(exp.equals(null)){
		return "This  string is null";
	}else {
		for (int i = 0; i <exp.length() ; i++) {
			char c = exp.charAt(i);
			if(isOperand(c)){
				result+= c;
				System.out.println(result);
			}
			//checks if this is an Operator;
			else if(precedence(c)>0) {
            			while(!stack.isEmpty() && ((precedence(stack.peek())>precedence(c)) || 
            				(precedence(stack.peek())==precedence(c) && (precedence(stack.peek())<3)))){
            				result += stack.peek();
            				stack.pop();
                    			System.out.println(result);
            			}
            			stack.push(c);
			} 
			else if(c=='('){
                    		stack.push(c);
                    		System.out.println(result);
			}
			else if(c==')'){
				char x = stack.pop();
				while(x!='('){
                   	 		result += x;
                    			x = stack.pop();
                    			System.out.println(result);
				}
				System.out.println(result);
				if(stack.isEmpty()) {
					return "Error";
				}
				if( stack.peek() == '(') {
					stack.pop();
				}
			}
		}
		while(!stack.isEmpty()) {
			char x = stack.pop();
			result += x;
			if(x == '(') {
				return "Error";
	        	}
			System.out.println(result);
		}
		
	}
        System.out.println(result);
	return result;
    }

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
        System.out.println("Infix Expression: ");
        String exp = sc.nextLine();
        System.out.println("Postfix Expression: " + infixToPostFix(exp));
    }

}
