'''
Have the function MaxHeapChecker(arr) take arr which represents a heap data 
structure and determine whether or not it is a max heap. A max heap has the 
property that all nodes in the heap are either greater than or equal to each of 
its children. For example: if arr is [100,19,36,17] then this is a max heap and 
your program should return the string max. If the input is not a max heap then 
your program should return a list of nodes in string format, in the order that 
they appear in the tree, that currently do not satisfy the max heap property 
because the child nodes are larger than their parent. For example: if arr is 
[10,19,52,13,16] then your program should return 19,52.

Another example: if arr is [10,19,52,104,14] then your program should return 
19,52,104 
'''

def MaxHeapChecker(arr): 
	res = []
	for i in range(1,len(arr)):
		#print arr[i], arr[(i-1)/2]
		if(arr[i] > int(arr[(i-1)/2])):
			res.append(arr[i])
	if len(res)	== 0:
		return "max"	
	return str(res)[1:-1].replace(" ","")
    
arr = [10,19,52,104,14]
print MaxHeapChecker(arr)  
