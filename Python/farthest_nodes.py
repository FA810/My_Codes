'''
Have the function FarthestNodes(strArr) read strArr which will be an array of 
hyphenated letters representing paths between those two nodes. For example: 
["a-b","b-c","b-d"] means that there is a path from node a to b (and b to a), 
b to c, and b to d. Your program should determine the longest path that exists 
in the graph and return the length of that path. So for the example above, 
your program should return 2 because of the paths a-b-c and d-b-c. The path 
a-b-c also means that there is a path c-b-a. No cycles will exist in the graph 
and every node will be connected to some other node in the graph. 
'''

def FarthestNodes(arr):
	connectionsDict = {}
	for link in arr:
		if link.split("-")[0] not in connectionsDict:
			connectionsDict[link.split("-")[0]] = [link.split("-")[1]]
		else:
			connectionsDict[link.split("-")[0]].append(link.split("-")[1])
		if link.split("-")[1] not in connectionsDict:
			connectionsDict[link.split("-")[1]] = [link.split("-")[0]]
		else:
			connectionsDict[link.split("-")[1]].append(link.split("-")[0])
	#print connectionsDict
	#print len(connectionsDict)
	#print connectionsDict.keys()
	maxi = 1
	for i in connectionsDict.keys():
		maxi = max(maxi,find_neighbours([i],connectionsDict))
	#print find_neighbours(['d','b'],connectionsDict)
	return maxi
	
def find_neighbours(cars,connectionsDict):
	neig = []
	#print connectionsDict[cars[0]]
	for c in cars:
		if c not in neig:
			neig.append(c)
		for s in connectionsDict[c]:
			if s not in neig:
				neig.append(s)
	if len(neig) < len(connectionsDict):
		return 1+find_neighbours(neig,connectionsDict)
	return 1

arr  = ["a-b","b-c","b-d"]
arr4 = [ "(X,Y,Z,Q)","(X-Y,Y-Q,Y-Z)","(Z,Y,Q,X)"]    
arr2 = ["(A,B,C,D)","(A-B,A-D,B-D,A-C)","(C,A,B,D)"]
arr3 = ("(A,B,C,D,E,F)","(A-B,A-D,B-D,B-C,C-F,E-D)","(E,F,C,B,D,A)")  

print FarthestNodes(arr)  
