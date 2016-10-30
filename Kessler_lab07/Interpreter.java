import structure5.*;

public class Interpreter {
    StackList<Token> myStack;
    Token next;
    SymbolTable table;
    
    public Interpreter(){
	myStack = new StackList<Token>();
	table = new SymbolTable();
    }

    public static void main(String []args) {
	Interpreter myInterpreter = new Interpreter();
	Reader mainReader = new Reader();
	myInterpreter.interpret(mainReader);
    }

    public void interpret(Reader r) {
	while (r.hasNext()) {
	    next = r.next();
	    if (next.isNumber() || next.isBoolean()) {
		myStack.push(next);
	    } else if (next.isSymbol()) {
		manageSymbol(next);
	    } 
	}
    }

    public void manageSymbol(Token s) {
	String mySym = s.getSymbol();
	if (mySym.equals("quit")) {
	    System.exit(0);
	} else if (mySym.equals("pstack")) {
	    System.out.println(myStack.toString());
	} else if (mySym.substring(0,1).equals("/")) {
	    myStack.push(s);
	} else if (table.contains(mySym)) {
	    myStack.push(table.get(mySym));
	} else if (mySym.equals("ptable")) {
	    System.out.println(table.toString());
	} else if (mySym.equals("pop")) {
	    Assert.condition(!myStack.isEmpty(), "There is at least one token on the stack.");
	    myStack.pop();
	} else if (mySym.equals("dup")) {
	    double num = myStack.peek().getNumber();
	    myStack.push(new Token(num));
	} else if (mySym.equals("def")) {
	    Token t1 = myStack.pop();
	    Assert.condition(myStack.peek().isSymbol(), "Definition token is a symbol.");
	    Assert.condition(myStack.peek().getSymbol().substring(0,1).equals("/"),
			     "Definition token is quoted symbol.");
	    String t2 = myStack.pop().getSymbol();
	    String key = t2.substring(1,t2.length());
	    if (table.contains(key)) {
		table.remove(key);
	    }
	    table.add(key, t1);
	} else {
	    arithmeticOperator(s);
	}
    }

    public void arithmeticOperator(Token s) {
	String mySym = s.getSymbol();
	Assert.condition(myStack.size() >= 2, "There are at least two tokens on the stack.");
	Assert.condition(myStack.peek().isNumber()
			 || table.get(myStack.peek().getSymbol()).isNumber(),
			 "Token is a number.");
	Token t1 = myStack.pop();
	if (myStack.peek().isNumber() || table.get(myStack.peek().getSymbol()).isNumber()) {
	    Token t2 = myStack.pop();
	    double t2num = t2.getNumber();
	    double t1num = t1.getNumber();
	 
	    if (mySym.equals("add")) {
		myStack.push(new Token(t2num + t1num));
	    } else if (mySym.equals("sub")) {
		myStack.push(new Token(t2num - t1num));
	    } else if (mySym.equals("mul")) {
		myStack.push(new Token(t2num * t1num));
	    } else if (mySym.equals("div")) {
		myStack.push(new Token(t2num / t1num));
	    } else if (mySym.equals("exch")) {
		myStack.push(t1);
		myStack.push(t2);
	    } else if (mySym.equals("eq")) {
		myStack.push(new Token(t2.equals(t1)));
	    } else if (mySym.equals("ne")) {
		myStack.push(new Token(!t2.equals(t1)));
	    }
	} else {
	    myStack.push(t1);
	    Assert.fail("Token is a number");
	}
    }
}

