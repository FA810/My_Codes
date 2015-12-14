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
       

   
arr = [5, 4, 3, 2, 10, 11]
arr2 =  [1, 2, 4, 6, 4, 3, 1]
arr3 = [1, 2, 3, 4, 5, 6, 7, 8, 9, 1]
arr4 = [1,2,1]
# keep this function call here
# to see how to enter arguments in Python scroll down
print ChangingSequence(arr3)

'''
1. When the input was (1, 2, 4, 6, 4, 3, 1) your output was incorrect.
2. When the input was (1, 2, 1) your output was incorrect.
3. When the input was (1, 2, 3, 4, 5, 6, 7, 8, 9, 1) your output was incorrect.
4. When the input was (4, 3, 2, 1) your output was incorrect.
5. When the input was (4, 5, 6, 5, 4, 2, 1) your output was incorrect.
6. When the input was (5, 4, 3, 2, 10, 11) your output was incorrect.
7. When the input was (100, 101, 102, 101) your output was incorrect.
8. When the input was (3, 2, 1, 0, -1, -2, 10) your output was incorrect.

'''
