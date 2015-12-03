package ranges;

public class Range {

	private Integer start, end;

	public Range(int n1, int n2){
		if(n1 <= n2){
			this.start = n1;
			this.end = n2;}
		else{
			this.start = n2;
			this.end = n1;
		};
	}
	
	public Integer getStart(){
		return start;
	}
	
	public Integer getEnd(){
		return end;
	}
	
	public void setStart(Integer newStart){
		 this.start = newStart;
	}
	
	public void setEnd(Integer newEnd){
		 this.end = newEnd;
	}

}
