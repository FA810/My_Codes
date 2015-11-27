package coding.question.three.collections.exceptions;

@SuppressWarnings("serial")
public class ElementCountException extends Exception {
	private String mistake;

	// default constructor - initializes instance variable to unknown
	public ElementCountException (){
		super();             // call superclass constructor
		mistake = "Element Occurrences Count is invalid";
	}

	// constructor receives some kind of message that is saved in an instance variable.
	public ElementCountException(String err){
		super(err);     // call super class constructor
		mistake = err;  // save message
	}

	// public method, called by exception catcher. It returns the error message.
	public String getError(){
		return mistake;
	}
}