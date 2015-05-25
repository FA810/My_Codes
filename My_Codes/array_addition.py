def ArrayAdditionI(arr):
	arr = sorted(arr)
	largest = arr.pop()
	return str(recursion(largest,arr)).lower()

def recursion(target,array):
	#print "target,array: "+str(target),str(array)
	if(len(array) == 0):
		return target == 0
	else:
		n = array[0]
    	array = array[1:]    	
    	#print n,array,target
    	res = recursion(target,array) or recursion(target - n, array)
    	return res


arr = [4,8,1,17,19]
print ArrayAdditionI(arr)
