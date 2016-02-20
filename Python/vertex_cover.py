'''
Have the function VertexCovering(strArr) take strArr which will be an array of 
length three. The first part of the array will be a list of vertices in a graph 
in the form (A,B,C,...), the second part of the array will be the edges 
connecting the vertices in the form (A-B,C-D,...) where each edge is 
bidirectional. The last part of the array will be a set of vertices in the form 
(X,Y,Z,...) and your program will have to determine whether or not the set of 
vertices given covers every edge in the graph such that every edge is incident 
to at least one vertex in the set. For example: if strArr is 
["(A,B,C,D)","(A-B,A-D,B-D,A-C)","(A,B)"] then the vertices (A,B) are in fact 
one of the endpoints of every edge in the graph, so every edge has been 
accounted for. Therefore your program should return the string yes. But, if for 
example the last part of the array was (C,B) then these vertices don't cover 
all the edges because the edge connecting A-D is left out. If this is the case 
your program should return the edges given in the second part of the array that 
are left out in the same order they were listed, so for this example your 
program should return (A-D).

The graph will have at least 2 vertices and all the vertices in the graph will 
be connected. The third part of the array listing the vertices may be empty, 
where it would only contain the parenthesis with no values within it: "()" 
'''

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
print VertexCovering(arr2)  
