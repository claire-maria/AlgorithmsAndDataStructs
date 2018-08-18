package bonusAssignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// -------------------------------------------------------------------------
/**
 * Utility class containing validation/evaluation/conversion operations for
 * prefix and postfix arithmetic expressions.
 *
 * @author Claire Gribbin
 * @version 1/12/15 13:03:48
 */

public class arith {

	// ~ Validation methods
	// ..........................................................

	/**
	 * Validation method for prefix notation.
	 *
	 * @param prefixLiterals
	 *            : an array containing the string literals hopefully in prefix
	 *            order. The method assumes that each of these literals can be one
	 *            of: - "+", "-", "*", or "/" - or a valid string representation of
	 *            an integer.
	 *
	 * @return true if the parameter is indeed in prefix notation, and false
	 *         otherwise.
	 **/
	public static boolean validatePrefixOrder(String prefixLiterals[]) {
		int stackCount = 0;
		for (int i = prefixLiterals.length - 1; i >= 0; i--) {
			// if char is a delimiter, move on
			if (isEmpty(prefixLiterals[i])) {
				continue;
			}
			// is char is bracket, move on
			else if (isBracket(prefixLiterals[i])) {
				continue;
			}
			// if number/not operator move add to stack counter
			else if (isNumber(prefixLiterals[i]) && !isOperator(prefixLiterals[i])) {
				++stackCount;
			} else {
				// reduce stack
				stackCount -= 2;
				// if less than 0, stop
				if (stackCount < 0) {
					return false;
				}
				++stackCount;

			}

		}
		return (stackCount == 1);
	}

	/**
	 * Validation method for postfix notation.
	 *
	 * @param postfixLiterals
	 *            : an array containing the string literals hopefully in postfix
	 *            order. The method assumes that each of these literals can be one
	 *            of: - "+", "-", "*", or "/" - or a valid string representation of
	 *            an integer.
	 *
	 * @return true if the parameter is indeed in postfix notation, and false
	 *         otherwise.
	 **/
	public static boolean validatePostfixOrder(String postfixLiterals[]) {
		int stackCount = 0;
		for (int i = 0; i < postfixLiterals.length; i++) {
			if (isEmpty(postfixLiterals[i])) {
				continue;
			} else if (isBracket(postfixLiterals[i])) {
				continue;
			} else if (isNumber(postfixLiterals[i]) && !isOperator(postfixLiterals[i])) {
				++stackCount;
			} else {
				stackCount -= 2;
				if (stackCount < 0) {
					return false;
				}
				++stackCount;
			}
		}
		return (stackCount == 1);
	}

	// ~ Evaluation methods
	// ..........................................................

	/**
	 * Evaluation method for prefix notation.
	 *
	 * @param prefixLiterals
	 *            : an array containing the string literals in prefix order. The
	 *            method assumes that each of these literals can be one of: - "+",
	 *            "-", "*", or "/" - or a valid string representation of an integer.
	 *
	 * @return the integer result of evaluating the expression
	 **/
	public static int evaluatePrefixOrder(String prefixLiterals[]) {
		return evaluatePostfixOrder(convertPrefixToPostfix(prefixLiterals));
	}

	/**
	 * Evaluation method for postfix notation.
	 *
	 * @param postfixLiterals
	 *            : an array containing the string literals in postfix order. The
	 *            method assumes that each of these literals can be one of: - "+",
	 *            "-", "*", or "/" - or a valid string representation of an integer.
	 *
	 * @return the integer result of evaluating the expression
	 **/
	public static int evaluatePostfixOrder(String postfixLiterals[]) {
		ArrayList<String> stack = new ArrayList<String>();
		for (int i = 0; i < postfixLiterals.length; i++) {
			if (isEmpty(postfixLiterals[i])) {
				continue;
			} else if (isBracket(postfixLiterals[i])) {
				continue;
			}
			// If character is operator, then pop two elements from stack, perform operation
			// and push the result back
			else if (isOperator(postfixLiterals[i])) {
				int x;
				int y;
				switch (postfixLiterals[i].charAt(0)) {
				case '+': {
					x = Integer.parseInt(stack.remove(stack.size() - 1));
					y = Integer.parseInt(stack.remove(stack.size() - 1));
					stack.add(String.valueOf(x + y));
					break;
				}
				case '-': {
					x = Integer.parseInt(stack.remove(stack.size() - 1));
					y = Integer.parseInt(stack.remove(stack.size() - 1));
					stack.add(String.valueOf(x - y));
					break;
				}
				case '*': {
					x = Integer.parseInt(stack.remove(stack.size() - 1));
					y = Integer.parseInt(stack.remove(stack.size() - 1));
					stack.add(String.valueOf(x * y));
					break;
				}
				case '/': {
					x = Integer.parseInt(stack.remove(stack.size() - 1));
					y = Integer.parseInt(stack.remove(stack.size() - 1));
					stack.add(String.valueOf(x / y));
					break;
				}
				default: {
					break;
				}
				}
			} else {
				stack.add(postfixLiterals[i]); // Theta(1) Theta(N)
			}

		}
		return Integer.parseInt(stack.remove(stack.size() - 1));

	}

	// ~ Conversion methods
	// ..........................................................

	/**
	 * Converts prefix to postfix.
	 *
	 * @param prefixLiterals
	 *            : an array containing the string literals in prefix order. The
	 *            method assumes that each of these literals can be one of: - "+",
	 *            "-", "*", or "/" - or a valid string representation of an integer.
	 *
	 * @return the expression in postfix order.
	 **/
	public static String[] convertPrefixToPostfix(String prefixLiterals[]) {
		ArrayList<String> stack = new ArrayList<String>();
		String x;
		String y;
		for (int i = prefixLiterals.length - 1; i >= 0; i--) {
			if (isEmpty(prefixLiterals[i])) {
				continue;
			} else if (!isOperator(prefixLiterals[i])) {
				// Push back the result of operation on the stack
				stack.add("" + prefixLiterals[i] + ",");
			} else {

				x = stack.remove(stack.size() - 1);
				y = stack.remove(stack.size() - 1);
				stack.add(x + ", " + y + ", " + prefixLiterals[i] + ", ");
			}
		}
		return ArrayGenerator(stack.remove(stack.size() - 1));
	}

	/**
	 * Converts postfix to prefix.
	 *
	 * @param prefixLiterals
	 *            : an array containing the string literals in postfix order. The
	 *            method assumes that each of these literals can be one of: - "+",
	 *            "-", "*", or "/" - or a valid string representation of an integer.
	 *
	 * @return the expression in prefix order.
	 **/
	public static String[] convertPostfixToPrefix(String postfixLiterals[]) {
		ArrayList<String> stack = new ArrayList<String>();
		String x;
		String y;
		for (int i = 0; i < postfixLiterals.length; i++) {
			if (isEmpty(postfixLiterals[i])) {
				continue;
			} else if (!isOperator(postfixLiterals[i])) {
				// Push back the result of operation on the stack
				stack.add("" + postfixLiterals[i] + ",");
			} else {

				x = stack.remove(stack.size() - 1);
				y = stack.remove(stack.size() - 1);
				stack.add(x + ", " + y + ", " + postfixLiterals[i] + ", ");
			}
		}
		return ArrayGenerator(stack.remove(stack.size() - 1));
	}

	/**
	 * Converts prefix to infix.
	 *
	 * @param infixLiterals
	 *            : an array containing the string literals in prefix order. The
	 *            method assumes that each of these literals can be one of: - "+",
	 *            "-", "*", or "/" - or a valid string representation of an integer.
	 *
	 * @return the expression in infix order.
	 **/
	public static String[] convertPrefixToInfix(String prefixLiterals[]) {
		ArrayList<String> stack = new ArrayList<String>();
		String x;
		String y;
		for (int i = prefixLiterals.length - 1; i >= 0; i--) {
			if (isEmpty(prefixLiterals[i])) {
				continue;
			} else if (!isOperator(prefixLiterals[i])) {
				// Push back the result of operation on the stack
				stack.add("" + prefixLiterals[i] + ",");
			} else {

				x = stack.remove(stack.size() - 1);
				y = stack.remove(stack.size() - 1);
				stack.add(("(" + "," + x + "," + prefixLiterals[i] + "," + y + "," + ")" + ","));
			}
		}
		return ArrayGenerator(stack.remove(stack.size() - 1));
	}

	/**
	 * Converts postfix to infix.
	 *
	 * @param infixLiterals
	 *            : an array containing the string literals in postfix order. The
	 *            method assumes that each of these literals can be one of: - "+",
	 *            "-", "*", or "/" - or a valid string representation of an integer.
	 *
	 * @return the expression in infix order.
	 **/
	public static String[] convertPostfixToInfix(String postfixLiterals[]) {
		ArrayList<String> stack = new ArrayList<String>();
		String x;
		String y;
		for (int i = postfixLiterals.length - 1; i >= 0; i--) {
			if (isEmpty(postfixLiterals[i])) {
				continue;
			} else if (!isOperator(postfixLiterals[i])) {
				// Push back the result of operation on the stack
				stack.add("" + postfixLiterals[i] + ",");
			} else {

				x = stack.remove(stack.size() - 1);
				y = stack.remove(stack.size() - 1);
				stack.add("(" + "," + y + "," + postfixLiterals[i] + "," + x + "," + ")" + ",");
			}
		}
		return ArrayGenerator(stack.remove(stack.size() - 1));
	}

	public static boolean isBracket(String input) {
		if (input.charAt(0) == '(' || input.charAt(0) == ')') {
			return true;
		}
		return false;
	}

	public static boolean isNumber(String input) {
		if (input.matches("-?\\d+(\\.\\d+)?")) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String input) {
		if (input.charAt(0) == ' ' || input.charAt(0) == ',' || (!isNumber(input) && !isOperator(input))) {
			return true;
		}

		return false;
	}

	public static boolean isOperator(String input) {
		if (input.charAt(0) == '+' || input.charAt(0) == '-' || input.charAt(0) == '*' || input.charAt(0) == '/') {
			return true;
		}
		return false;
	}

	public static String[] ArrayGenerator(String input) {

		List<String> list = new ArrayList<String>(Arrays.asList(input.split(",")));
		list.removeAll(Collections.singleton(""));
		return list.toArray(new String[list.size()]);
	}

}