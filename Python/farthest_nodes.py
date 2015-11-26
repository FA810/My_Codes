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
# keep this function call here  
# to see how to enter arguments in Python scroll down
print FarthestNodes(arr)  
