import structure5.*;

public class Interpreter {
    Stack<Token> myStack;
    Token next;
    
    public Interpreter(){
	myStack = new StackList<Token>();
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
	} else if (mySym.equals("add")) {
	    if (!myStack.isEmpty()) {
		Token t1 = myStack.pop();
	    
		if (myStack.isEmpty()) {
		    Token t2 = myStack.pop();
		    if (t1.isNumber() && t2.isNumber()) {
			myStack.push(new Token
				     (t1.getNumber() + t2.getNumber()));
		    }
		}
	    }
	}
    }
    
}

