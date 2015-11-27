package coding.question.three.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set; 

import coding.question.three.collections.exceptions.ElementCountException;

/**
 * Generic "bag" container that follows guidelines from the Java Collections library.<br>
 * 
 * <b>Note that this implementation is not synchronized.</b>
 * 
 * @author Fabio Rizzello
 * @version 1.0   
 */

public class Bag<T> implements Collection<T>, Iterable<T> {

	/**
	 * Element of the bag (object + number of occurrences).
	 * @author Fabio Rizzello
	 */
	private class Element implements Comparable<Element> {
		private T element;
		private int count;

		private Element(T element) {
			this.element = element;			
		}
		private T getElement() {
			return element;
		}
		private int getCount() {
			return count;
		}
		private void setCount(int count) throws ElementCountException{
			if(count<=0) throw new ElementCountException();
			this.count = count;
		}
		@Override
		public int compareTo(Element arg0) {
			if(arg0.getCount() < this.getCount()) return 1;
			else if(arg0.getCount() == this.getCount()) return 0;
			else return -1;
		}
	}

	private ArrayList<Element> elements;
	private Set<T> uniques;

	/**
	 * Constructs an empty bag with an initial capacity of zero.
	 */
	public Bag() {
		elements = new ArrayList<Element>();
		uniques = new HashSet<T>();		
	}

	/**
	 * Adds an element to the bag.
	 * @param arg0 element whose going to be added in the bag
	 * @return <code>true</code> if the bag changed as a result of the call
	 * @throws ClassCastException
	 * @throws NullPointerException 
	 * @throws IllegalArgumentException
	 * @see java.util.Collection#add(java.lang.Object)
	 */
	@Override
	public boolean add(T arg0) throws ClassCastException,NullPointerException,IllegalArgumentException{
		if(uniques.contains(arg0)){
			//element already included, with some occurrences
			for(Element e : elements){				
				if(e.getElement().equals(arg0))
					//element found, adding occurrences
					addOccurrences(e, 1);				
			};
			return true;
		}
		else{
			//new element 
			uniques.add(arg0);
			Element e = new Element(arg0);
			//adding occurrences
			addOccurrences(e, 1);
			elements.add(e);
			return true;
		}
	}

	/**
	 * Adds occurrences to an element
	 * @param e
	 * @param nCopies
	 */
	private void addOccurrences(Element e, int nCopies){
		try {
			e.setCount(e.getCount()+nCopies);
		} 
		catch (ElementCountException e1) {e1.printStackTrace();}
		catch (NullPointerException e1) {e1.printStackTrace();}
	}

	/** 
	 * Adds a collection of elements to the bag.
	 * @param arg0 collection of elements which going to be added in the bag
	 * @return <code>true</code> if the bag changed as a result of the call
	 * @throws ClassCastException
	 * @throws NullPointerException 
	 * @throws IllegalArgumentException
	 * @see java.util.Collection#addAll(java.util.Collection)
	 */
	@Override
	public boolean addAll(Collection<? extends T> arg0) throws ClassCastException,NullPointerException,IllegalArgumentException{
		for(T item : arg0){
			add(item);
		}
		return true;
	}

	/**
	 * Removes all of the elements from the bag. 
	 * The bag will be empty after this method returns. 
	 * @see java.util.Collection#clear()
	 */
	@Override
	public void clear() {
		uniques.clear();
		elements.clear();
	}

	/**
	 * Returns <code>true</code> if the bag contains the specified element.
	 * @param arg0 element to be searched
	 * @return <code>true</code> if the bag contains the specified element
	 * @throws NullPointerException
	 * @see java.util.Collection#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object arg0) throws NullPointerException{
		if(uniques.contains(arg0))return true;
		else return false;
	}

	/**
	 * Returns <code>true</code> if the bag contains all of the elements 
	 * in the specified collection. 
	 * @see java.util.Collection#containsAll(java.util.Collection)
	 */
	@Override
	public boolean containsAll(Collection<?>  arg0) {
		for(Object item : arg0){
			contains(item);
		}
		return false;
	}

	/**
	 * Returns <code>true</code> if the bag contains no elements.
	 * @return <code>true</code> if the bag contains no elements
	 * @see java.util.Collection#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		if(elements.size()>0) return false;
		else return true;
	}

	/**
	 * Returns an iterator over the elements in this collection. 
	 * There are no guarantees concerning the order in which 
	 * the elements are returned.
	 * @return an <code>Iterator</code> over the elements in the bag
	 * @see java.util.Collection#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		ArrayList<T> listOfCopies = new ArrayList<T>();
		for(Element e: elements){
			for(int i=0;i<e.getCount();i++){
				listOfCopies.add(e.getElement());
			}
		};
		return listOfCopies.iterator();
	}

	/**
	 * Removes the specified element from the bag, if it is present. 
	 * @param arg0 element to be removed
	 * @return <code>true</code> if the bag has been modified
	 * @see java.util.Collection#remove(java.lang.Object)
	 */
	@Override
	public boolean remove(Object arg0) throws ConcurrentModificationException{
		if(uniques.contains(arg0)){ //element included
			uniques.remove(arg0);
			Object removable=null;
			for(Element e : elements){
				if(e.getElement().equals(arg0)){ 
					removable=e; //element found and to be removed
				};
			}
			elements.remove(removable);
			return true;
		}
		else{return false;}
	}

	/**
	 * Removes the <code>nCopies</code> occurrences 
	 * of the specified element from the bag, if they are present.
	 * @param arg0 element to be removed
	 * @param nCopies number of occurrences of the element to remove
	 * @return <code>true</code> if the bag has been modified 
	 * @throws ClassCastException
	 * @throws NullPointerException 
	 * @throws IllegalArgumentException
	 */
	public boolean remove(Object arg0, int nCopies) throws ClassCastException,NullPointerException,IllegalArgumentException{
		if(uniques.contains(arg0)){ //element included
			for(Element e : elements){
				if(e.getElement() == arg0){ //element found
					addOccurrences(e,-(nCopies));
					if(e.getCount()==0)
						remove(e);
					return true;
				}
			};
		}
		return false;
	}

	/** 
	 * Removes all of this collection's elements that are also 
	 * contained in the specified collection (optional operation). 
	 * @param arg0 collection of elements to be removed from the bag
	 * @return <code>true</code> if the bag has been modified 	 * 
	 * @see java.util.Collection#removeAll(java.util.Collection)
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {
		boolean removal = false;
		for(Object obj:arg0){
			removal = remove(obj,1) || removal;
		}
		return removal;
	}

	/**
	 * Retains only the elements in this collection that are contained 
	 * in the specified collection (optional operation). 
	 * In other words, removes from this collection all of its 
	 * elements that are not contained in the specified collection.	 * 
	 * @param arg0 collection of elements to be retained in the bag.
	 * @return <code>true</code> if the bag has been modified.
	 * @see java.util.Collection#retainAll(java.util.Collection)
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {
		boolean change = false;
		ArrayList<T> toRemove = new ArrayList<T>();
		for(T t : uniques){ // seek for ones to exclude
			if(!arg0.contains(t)){
				toRemove.add(t);
			};
		};		
		if(toRemove.size()>0){ 
			change = true;
			for(T t : toRemove){ // actual removal
				remove(t);
			};
		}
		return change;
	}

	/**
	 * Returns the number of elements in the bag.  
	 * @return number of elements in the bag
	 * @see java.util.Collection#size()
	 */
	@Override
	public int size() {
		int total = 0;
		for(Element e: elements){
			total = total + e.getCount();
		};
		return total;
	}

	/**
	 * Returns an array containing all of the elements in the bag. 
	 * @return array containing all of the elements in the bag
	 * @see java.util.Collection#toArray()
	 */
	@Override
	public Object[] toArray() {
		return elements.toArray();
	}

	/** (Unimplemented) 
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray(Object[] arg0) {
		return null;
	}

	/**
	 * Sorts the element in the bag in ascending order by number of occurrences.
	 */
	public void sort() {
		Collections.sort(elements);
		Collections.reverse(elements);
	}

	/**
	 * Print all the elements of the bag together with their number of 
	 * occurrences.
	 */
	public void printElements() {
		printElements(elements.size());
	}

	/**
	 * Print the firsr <code>n</code> elements of the bag together with 
	 * their number of occurrences.
	 * @param n number of elements to print
	 */
	public void printElements(int n) {		
		try {
			if(n>elements.size()) 
				throw new IndexOutOfBoundsException();
			System.out.println("Bag contains:");
			for(int i=0;i<n;i++){
				Element e = elements.get(i);
				System.out.format(" - item:  %s  - n.: %s\n",e.getElement(),e.getCount());
			};
		} catch (IndexOutOfBoundsException e) {e.printStackTrace();}
	}

	public static void main(String[] args) {
		Bag<String> bsw = new Bag<String>();
		String myWords[] = {"home","web","folder","home","desktop","folder",
				"folder","home","home","ubuntu","mouse","keyboard","mouse",
				"printer","linux","ubuntu","system","cpu","driver","disk",
		"driver"};
		bsw.addAll(Arrays.asList(myWords));
		bsw.sort();
		System.out.println("\n\nOrder of elements after sorting");
		bsw.printElements();		

		Bag<Integer> bsi = new Bag<Integer>();
		Integer myNumbers[] = {1,99,1,56,45,99,1,1,2,15,45,65,56,48,
				99,99,99,99,31,32,33,34,35,36,3,33,33};
		bsi.addAll(Arrays.asList(myNumbers));
		bsi.sort();
		System.out.println("\n\nOrder of elements after sorting");
		bsi.printElements();

		System.out.println("\nTop elements");
		bsi.printElements(5);		
	}

}

