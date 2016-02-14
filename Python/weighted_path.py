'''
Have the function WeightedPath(arr) take strArr which will be an array of 
strings which models a non-looping weighted Graph. The structure of the array 
will be as follows: The first element in the array will be the number of 
nodes N (points) in the array as a string. The next N elements will be the 
nodes which can be anything (A, B, C .. Brick Street, Main Street .. etc.). 
Then after the Nth element, the rest of the elements in the array will be the 
connections between all of the nodes along with their weights (integers) 
separated by the pipe symbol (|). They will look like this: (A|B|3, B|C|12 .. 
Brick Street|Main Street|14 .. etc.). Although, there may exist no connections 
at all.

An example of strArr may be: 
["4","A","B","C","D","A|B|1","B|D|9","B|C|3","C|D|4"]. 
It may help to visualize the Graph by drawing out the nodes and their 
connections. Your program should return the shortest path when the weights are 
added up from node to node from the first Node to the last Node in the array 
separated by dashes. So in the example above the output should be A-B-C-D. 
Here is another example with strArr being 
["7","A","B","C","D","E","F","G","A|B|1","A|E|9","B|C|2","C|D|1","D|F|2",
"E|D|6","F|G|2"]. The output for this array should be A-B-C-D-F-G. 
There will only ever be one shortest path for the array. 
If no path between the first and last node exists, return -1. 
The array will at minimum have two nodes. Also, the connection A-B for example, 
means that A can get to B and B can get to A. A path may not go through any 
Node more than once.
'''
import operator

def WeightedPath(arr):
	infinity = 500
	lim = int(arr[0])
	nodes = arr[1:1+lim]
	start = nodes[0]
	dest = nodes[-1]
	#print lim, nodes
	neighbours = find_neighbours(nodes,arr)
	#print neighbours
	dist = {x:infinity for x in nodes}
	dist[start] = 0
	prev = {}
	unvisited = {x:infinity for x in nodes}
	unvisited[start] = 0
	marked = set()
	while unvisited:
		current = first_order_dict(unvisited)
		#print current
		if current == dest:
			break
		del unvisited[current]
				
		available = set(neighbours[current].keys())-marked
		#print available
		if len(available) == 0:
			continue
		for neighbour in available:			
			vwLength = dist[current] + neighbours[current][neighbour]
			if (neighbour not in unvisited)  or vwLength < unvisited[neighbour]:
				unvisited[neighbour] = vwLength
				dist[neighbour] = vwLength
				prev[neighbour] = current
				#print "|- "+neighbour, vwLength	
			marked.add(current)		
				
	#print prev, dist			
	if dest not in prev:
		return -1
	path = dest
	curr = dest	
	
	while curr!= start:
		path = path +"-"+ prev[curr]
		curr = prev[curr]		
	#print path[::-1]
	return path[::-1]
	
def find_neighbours(nodes,arr):
	neighbours = {node:{} for node in nodes}    
	for edge in arr[int(arr[0])+1:]:
		neighbours[edge.split("|")[0]][edge.split("|")[1]]=int(edge.split("|")[2])
		neighbours[edge.split("|")[1]][edge.split("|")[0]]=int(edge.split("|")[2])
	return neighbours
	
def order_dict(mydict):
	return sorted(mydict.items(), key=operator.itemgetter(1))
	
def first_order_dict(mydict):
	return order_dict(mydict)[0][0]
	
arr9 = ["8","C","B","A","D","E","F","G","H","C|D|1","D|F|2","G|F|2","G|E|1","E|B|1","H|B|1","C|A|13","B|A|2","C|E|1"]	
arr8 = ["6","D","B","C","A","E","F","B|A|2","A|F|3","A|C|4","B|D|1","D|E|12","C|D|4","F|E|1"]
arr7 = ["6","A","B","C","D","E","F","B|A|2","A|F|12","A|C|4","B|D|1","D|E|1","C|D|4","F|E|1"]
arr5 = ["3","AA","BB","CC","CC|BB|102"]
arr6 = ["7","D","A","N","I","E","L","B","D|A|1","A|N|2","L|B|22"]
arr = ["4","A","B","C","D", "A|B|2", "C|B|11", "C|D|3", "B|D|2"]
arr3 = ["7","A","B","C","D","E","F","G","A|B|1","A|E|9","B|C|2","C|D|1","D|F|2","E|D|6","F|G|2"]
arr4 = ["4","A","B","C","D","A|B|1","B|D|9","B|C|3","C|D|4"]
 
print WeightedPath(arr)  		
