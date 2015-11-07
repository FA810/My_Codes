def VertexCovering(arr): 
	#vertex = arr[0][1:-1].split(",")
	links  = arr[1][1:-1].split(",")
	path   = arr[2][1:-1].split(",")
	l = []
	#print vertex, links, path
	for i in path:	
		links = remove_links(i,links)
	#print links			
	if len(links) == 0:
		return "yes"
	else:
		return "("+",".join(links)+")"
	
def remove_links(car,links):
	remain = []
	for link in links:
		couple = link.split("-")		
		if car not in couple:
			remain.append(link)
	return remain		
   

arr = [ "(X,Y,Z,Q)","(X-Y,Y-Q,Y-Z)","(Z)"]    
arr2 = ["(A,B,C,D)","(A-B,A-D,B-D,A-C)","(D)"]
arr3 = ("(A,B,C,D,E,F)","(A-B,A-D,B-D,B-C,C-F,E-D)","(E,F,C,B,D,A)")
# keep this function call here  
# to see how to enter arguments in Python scroll down
print VertexCovering(arr2)  
