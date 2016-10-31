import structure5.*;

/*
 * Name: Caroline Kessler
 * Lab: noon-2 lab
 * Class Interpreter: This class uses a Reader to read in Tokens and put the tokens on a StackList.
 * If the tokens are numbers or booleans they are pushed onto the stack. If the token is a
 * symbol it is processed and executed according to the symbol. The class creates a SymbolTable
 * which allows the user to create definitions for symbols. The definition is of any Token type, 
 * which allows the user to create procedures, which are groups of Tokens to be read in and
 * performed in the specified order. Commands allow the user to access the StackList, access
 * the SymbolTable, quit the program, and pop Tokens off the Stack.
 *
 */

public class Interpreter {
    StackList<Token> myStack;
    Token next;
    SymbolTable table;
    // Constants for different operators
    static final int ADD = 0, SUB = 1, MUL = 2, DIV = 3, EXCH = 4, EQ = 5, NE = 6;
    
    // Initialize the StackList and SymbolTable
    public Interpreter(){
	myStack = new StackList<Token>();
	table = new SymbolTable();
    }

    /*
     * pre:
     * post: creates a new Interpreter object with a Reader to parse incoming Tokens
     */
    public static void main(String[] args) {
	Interpreter myInterpreter = new Interpreter();
	Reader mainReader = new Reader();
	myInterpreter.interpret(mainReader);
    }

    /*
     * pre: There is a valid Reader
     * post: Reads in next token as long as there is a next one, operates according to token type
     */
    public void interpret(Reader r) {
	while (r.hasNext()) {
	    next = r.next();
	    if (next.isNumber() || next.isBoolean()) {
		myStack.push(next);
	    } else if (next.isSymbol()) {
		manageSymbol(next);
	    } else if (next.isProcedure()) {
		myStack.push(next);
	    }
	}
    }

    /*
     * pre: token is a symbol
     * post: perform operation given the symbol
     */
    public void manageSymbol(Token s) {
	String mySym = s.getSymbol();
	if (mySym.equals("quit")) {
	    System.exit(0);
	} else if (mySym.equals("pstack")) {
	    System.out.println(myStack.toString());
	} else if (mySym.substring(0,1).equals("/")) {
	    myStack.push(s);
	} else if (table.contains(mySym)) {
	    Token t = table.get(mySym);
	    if (t.isProcedure()) {
		// executes prodedure if value in table is procedure
		Reader r = new Reader(t.getProcedure());
		interpret(r);
	    } else {
		// adds token to stack if value in table is anything but a procedure
		myStack.push(t);
	    }
	} else if (mySym.equals("ptable")) {
	    System.out.println(table.toString());
	} else if (mySym.equals("pop")) {
	    Assert.condition(!myStack.isEmpty(), "There is at least one token on the stack.");
	    myStack.pop();
	} else if (mySym.equals("add")) {
	    arithmeticOperator(ADD);
	} else if (mySym.equals("sub")) {
	    arithmeticOperator(SUB);
	} else if (mySym.equals("mul")) {
	    arithmeticOperator(MUL);
	} else if (mySym.equals("div")) {
	    arithmeticOperator(DIV);
	} else if (mySym.equals("dup")) {
	    myStack.push(myStack.peek());
	} else if (mySym.equals("exch")) {
	    twoTokenOperator(EXCH);
	} else if (mySym.equals("eq")) {
	    twoTokenOperator(EQ);
	} else if (mySym.equals("ne")) {
	    twoTokenOperator(NE);
	} else if (mySym.equals("def")) {
	    definition();
	} else if (mySym.equals("if")) {
	    ifStatement();
	}
    }

    /*
     * pre: Stack size is at least two, and the two tokens to be used are numbers
     * post: Pushes the result of the given mathematical operator onto the stack
     */
    public void arithmeticOperator(int operator) {
	Assert.condition(myStack.size() >= 2, "There are at least two tokens on the stack.");
	Assert.condition(myStack.peek().isNumber()
			 || table.get(myStack.peek().getSymbol()).isNumber(),
			 "Token is a number."); // Checks for numbers that are stored as definition
	Token t1 = myStack.pop();
	if (myStack.peek().isNumber() || table.get(myStack.peek().getSymbol()).isNumber()) {
	    Token t2 = myStack.pop();
	    double t2num = t2.getNumber();
	    double t1num = t1.getNumber();
	    switch (operator) {
	    case ADD:
		myStack.push(new Token(t2num + t1num));
		break;
	    case SUB:
		myStack.push(new Token(t2num - t1num));
		break;
	    case MUL:
		myStack.push(new Token(t2num * t1num));
		break;
	    case DIV:
		myStack.push(new Token(t2num / t1num));
		break;
	    default:
		Assert.fail("Use valid operator");
	    }
	} else {
	    myStack.push(t1); // pushes the second value back on if it isn't a number
	    Assert.fail("Token is a number");
	}
    }

    /*
     * pre: Stack size is at least two
     * post: performs either exch, eq, or ne, and pushes the result onto the stack
     */
    public void twoTokenOperator(int operator) {
	Assert.condition(myStack.size() >= 2, "There are at least two tokens on the stack.");
	Token t1 = myStack.pop();
	Token t2 = myStack.pop();
	switch (operator) {
	case EXCH:
	    // switches the order that two tokens appear on the stack
	    myStack.push(t1);
	    myStack.push(t2);
	    break;
	case EQ:
	    // checks if two tokens are equal
	    myStack.push(new Token(t2.equals(t1)));
	    break;
	case NE:
	    // checks if two tokens are not equal
	    myStack.push(new Token(!t2.equals(t1)));
	    break;
	default:
	    Assert.fail("Use valid Operator");
	}
    }
    
    /*
     * pre: the second Token is a "quoted" symbol (starts with "/")
     * post: adds the definition to the table if new, replaces old definition with new one 
     *       if already stored in the table
     */
    public void definition() {
	Token t1 = myStack.pop();
	Assert.condition(myStack.peek().isSymbol(), "Definition token is a symbol.");
	Assert.condition(myStack.peek().getSymbol().startsWith("/"),
			 "Definition token is quoted symbol.");
	String t2 = myStack.pop().getSymbol();
	String key = t2.substring(1,t2.length());
	if (table.contains(key)) {
	    table.remove(key);
	}
	table.add(key, t1);	
    }
    
    /*
     * pre: Stack size is at least 2, and the second token is a boolean
     * post: iff boolean is true execute the top token
     */
    public void ifStatement() {
	Assert.condition(myStack.size() >= 2, "There are at least two tokens on the stack.");
	Token toRun = myStack.pop();
	Assert.condition(myStack.peek().isBoolean(), "Token is a boolean");
	Token condition = myStack.pop();
	if (condition.getBoolean()) {
	    if (toRun.isProcedure()) {
		Reader r = new Reader(toRun.getProcedure());
		interpret(r);
	    } else if (toRun.isSymbol()) {
		manageSymbol(toRun);
	    } else if (toRun.isNumber() || toRun.isBoolean()) {
		myStack.push(toRun);
	    }
	}
    }
}

