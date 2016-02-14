'''
Have the function ChangingSequence(arr) take the array of numbers 
stored in arr and return the index at which the numbers stop increasing 
and begin decreasing or stop decreasing and begin increasing. 
For example: if arr is [1, 2, 4, 6, 4, 3, 1] then your program should 
return 3 because 6 is the last point in the array where the numbers 
were increasing and the next number begins a decreasing sequence. 
The array will contain at least 3 numbers and it may contains only a 
single sequence, increasing or decreasing. If there is only a single 
sequence in the array, then your program should return -1. Indexing 
should begin with 0. 
'''
def ChangingSequence(arr):
	for i in range(2,len(arr)+1):		
		temp = sorted(arr[0:i])
		#print i, arr[0:i], temp, temp[::-1]
		if (arr[0:i] == temp) or (arr[0:i] == temp[::-1]):
			continue      
		else:
			return i-2
	return -1
       

   
arr1 = [5, 4, 3, 2, 10, 11]
arr2 =  [1, 2, 4, 6, 4, 3, 1]
arr3 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 1]
arr4 = [1,2,1]

print ChangingSequence(arr1)	# return 4
print ChangingSequence(arr2)	# return 3
print ChangingSequence(arr3)	# return 8
print ChangingSequence(arr4)	# return 1
