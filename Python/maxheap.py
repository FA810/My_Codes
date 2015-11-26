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
# keep this function call here  
# to see how to enter arguments in Python scroll down
print MaxHeapChecker(arr)  
