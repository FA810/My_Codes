package ranges;

import ranges.Range;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @author fabio
 *
 */

public class MultiRange {

	private SortedMap<Integer, Range> multi = new ConcurrentSkipListMap<Integer, Range>();

	/**
	 * Adds a new range to the MultiRange set.
	 * @param newRange range to be included
	 */
	public void addRange(Range newRange){
		System.out.println("inserting: "+newRange.getStart()+"-"+newRange.getEnd());

		ArrayList<Range> included = findInvolved(newRange);
		if(included.isEmpty()){
			// insertion doesn't involves other ranges
			multi.put(newRange.getStart(), newRange);
		} else {
			included.add(newRange);
			//need to know minimum and maximum value for a new range including some of the existing
			Iterator<Range> numIterator = included.iterator();
			TreeSet<Integer> minim = new TreeSet<Integer>();
			TreeSet<Integer> maxim = new TreeSet<Integer>();
			while (numIterator.hasNext()) {
				Range sample = numIterator.next();
				minim.add(sample.getStart());
				maxim.add(sample.getEnd());
				multi.remove(sample.getStart());
			}
			Integer min = minim.first();
			Integer max = maxim.last();
			multi.put(min, new Range(min,max));
		}

	}

	/**
	 * Finds all the ranges involved in the insertion or removal of a range in the MultiRange set.
	 * @param selected Range to be analyzed for changes.
	 * @return List of ranges included in the change.
	 */
	public ArrayList<Range> findInvolved(Range selected){
		ArrayList<Range> included = new ArrayList<Range>();
		Iterator<Integer> numIterator = multi.keySet().iterator();
		while (numIterator.hasNext()) {
			Integer key = numIterator.next();
			Range old = (Range) multi.get(key);
			// range completely included inside an existing range
			if((selected.getStart() >= old.getStart()) && (selected.getStart() <= old.getEnd())){included.add(old);}
			// range inside only in the bottom
			else if((selected.getEnd() >= old.getStart()) && (selected.getEnd() <= old.getEnd())){included.add(old);}
			// range completely including an existing range
			else if((selected.getStart() <= old.getStart()) && (selected.getEnd() >= old.getEnd())){included.add(old);}
			// range inside only in the top
			else if((selected.getStart() >= old.getStart()) && (selected.getEnd() <= old.getEnd())){included.add(old);};
		}

		Iterator<Range> rangeIterator = included.iterator();
		while (rangeIterator.hasNext()) {
			Range sample = rangeIterator.next();
			System.out.println("\tinvolved: "+sample.getStart()+"-"+sample.getEnd());
		}
		return included;
	}

	/**
	 * Removes a range from the MultiRange set.
	 * @param removedRange range to be excluded
	 */
	public void removeRange(Range removedRange){
		System.out.println("removing: "+removedRange.getStart()+"-"+removedRange.getEnd());
		if(!multi.isEmpty()){
			ArrayList<Range> included = findInvolved(removedRange);
			if(included.size()>0){
				Iterator<Range> itera = included.iterator();
				while (itera.hasNext()) {
					Range old = itera.next();
					multi.remove(old.getStart());
					if((removedRange.getStart()-1)>=old.getStart())
						addRange(new Range(old.getStart(),removedRange.getStart()-1));
					if((removedRange.getEnd()+1)<=old.getEnd())
						addRange(new Range(removedRange.getEnd()+1,old.getEnd()));
				}
			}
		} 
	}

	/**
	 * Prints out the ranges of a MultiRange set.
	 */
	public void showValues(){
		Iterator<Integer> numIterator = multi.keySet().iterator();
		while (numIterator.hasNext()) {
			Integer key = numIterator.next();
			Range x = (Range) multi.get(key);
			System.out.println("range : " + x.getStart() + " - " + x.getEnd());
		}
		System.out.println("--------");
	}

	public static void main(String args[]){
		MultiRange mr = new MultiRange();

		mr.addRange(new Range(10,100));mr.showValues();		
		mr.addRange(new Range(200,300));mr.showValues();
		mr.addRange(new Range(400,500));mr.showValues();

		mr.removeRange(new Range(95,205));mr.showValues();
		mr.removeRange(new Range(410,420));mr.showValues();
	}

}
