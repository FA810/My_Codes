'''
Have the function ArrayAdditionI(arr) take the array of numbers stored 
in arr and return the string true if any combination of numbers in the 
array can be added up to equal the largest number in the array, 
otherwise return the string false. 

For example: if arr contains [4, 6, 23, 10, 1, 3] the output should 
return true because 4 + 6 + 10 + 3 = 23. 
The array will not be empty, will not contain all the same elements, 
and may contain negative numbers. 
'''

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
