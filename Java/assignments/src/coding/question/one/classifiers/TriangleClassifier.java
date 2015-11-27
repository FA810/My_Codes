/**
 * 
 */
package coding.question.one.classifiers;

import java.util.*;

/**
 * @author fabio
 *
 */
public class TriangleClassifier {
	public static final int SCALENE			= 1;
	public static final int ISOSCELES		= 2;
	public static final int EQUILATERAL		= 3;
	public static final int ERROR_TRIANGLE	= 4;
	private int[] sides;

	public TriangleClassifier(int side1, int side2, int side3){
		this.sides = new int[]{side1,side2,side3};
	}
	

	/**
	 * Classifies the triangle according to its sides.
	 * @return a value expressing the type of triangle
	 */
	public int classifyTriangle(){
		
		if((sides[0]<0) || (sides[1]<0) || (sides[2]<0)) return ERROR_TRIANGLE;
		order(sides);
		if(sides[2]>sides[1]+sides[0]) return ERROR_TRIANGLE; //error, we could throw an exception
		if((sides[2]==sides[1]) && (sides[1]==sides[0])){return EQUILATERAL;}
		if((sides[1]==sides[0]) || (sides[1]==sides[2])) return ISOSCELES;
		else return SCALENE; //scalene
	}

	/**
	 * Sorts the values in an array in descending order.
	 * @param values array to be sorted
	 * @return sorted array 
	 */
	private int[] order(int[] values){
		Arrays.sort(values);		
		return values;
	}

	private void printVal(){
		for(int i=0;i<sides.length;i++){System.out.println("side "+i+": "+sides[i]);}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] sides = {66,66,66};
		TriangleClassifier myClassifier= new TriangleClassifier(sides[0], sides[1], sides[2]);
		System.out.println(":::: ");
		myClassifier.printVal();
		int result = myClassifier.classifyTriangle();
		System.out.println(":::: the triangle is classified as: "+result);
	}

}
