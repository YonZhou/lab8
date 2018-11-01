package postfix;

import java.util.Stack;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 * This class contains a method to evaluate an arithmetic expression
 * that is in Postfix notation (or Reverse Polish Notation).
 * See <a href="https://en.wikipedia.org/wiki/Reverse_Polish_notation">Wikipedia</a>
 * for details on the notation.
 *
 */
public class PostfixEvaluator {
	
	private String arithmeticExpr;
	
	/**
	 * This is the only constructor for this class.
	 * It takes a string that represents an arithmetic expression
	 * as input argument.
	 * 
	 * @param expr is a string that represents an arithmetic expression 
	 * <strong>in Postfix notation</strong>.
	 */
	public PostfixEvaluator( String expr ) {
		arithmeticExpr = expr;
	}
	
	/**
	 * This method evaluates the arithmetic expression that 
	 * was passed as a string to the constructor for this class.
	 * 
	 * @return the value of the arithmetic expression
	 * @throws MalformedExpressionException if the provided expression is not
	 * 	a valid expression in Postfix notation
	 */
	double eval( ) throws MalformedExpressionException {
		
		Scanner scanner = new Scanner(arithmeticExpr);


		Stack<Token> toEvaluate = new Stack<>();

		while(!scanner.isEmpty()){
            Token currToken = scanner.getToken();
            if(currToken.isDouble()){
               toEvaluate.add(currToken) ;
               scanner.eatToken() ;
            } else if (currToken.isVariable()) {
                scanner.useToken(currToken.getName());
                if(toEvaluate.isEmpty() || !toEvaluate.peek().isDouble()){
                    throw new MalformedExpressionException();
                }
               double val1 = toEvaluate.pop().getValue();
                if(toEvaluate.isEmpty() || !toEvaluate.peek().isDouble()){
                    throw new MalformedExpressionException();
                }
               double val2 = toEvaluate.pop().getValue();

               double result = 0;

                if(currToken.getName().equals("+")){
                   result = val2 + val1 ;
                } else if (currToken.getName().equals("-")){
                    result = val2 - val1 ;
                } else if(currToken.getName().equals("*")){
                    result = val2 * val1 ;
                } else if(currToken.getName().equals("/")){
                    result = val2 / val1 ;
                }
                toEvaluate.add(new Token(result));
            }
        }

		return toEvaluate.pop().getValue();
	}
	
}