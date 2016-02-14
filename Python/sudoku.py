'''
Have the function SudokuQuadrantChecker(strArr) read the strArr parameter being 
passed which will represent a 9x9 Sudoku board of integers ranging from 1 to 9. 
The rules of Sudoku are to place each of the 9 integers integer in every row 
and column and not have any integers repeat in the respective row, column, 
or 3x3 sub-grid. The input strArr will represent a Sudoku board and it will be 
structured in the following format: 
["(N,N,N,N,N,x,x,x,x)","(...)","(...)",...)] where N stands for an integer 
between 1 and 9 and x will stand for an empty cell. Your program will determine 
if the board is legal; the board also does not necessarily have to be finished. 
If the board is legal, your program should return the string legal but if it 
isn't legal, it should return the 3x3 quadrants (separated by commas) where the 
errors exist. The 3x3 quadrants are numbered from 1 to 9 starting from top-left 
going to bottom-right.

For example, if strArr is: 
["(1,2,3,4,5,6,7,8,1)","(x,x,x,x,x,x,x,x,x)","(x,x,x,x,x,x,x,x,x)",
"(1,x,x,x,x,x,x,x,x)","(x,x,x,x,x,x,x,x,x)","(x,x,x,x,x,x,x,x,x)",
"(x,x,x,x,x,x,x,x,x)","(x,x,x,x,x,x,x,x,x)","(x,x,x,x,x,x,x,x,x)"] 
then your program should return 1,3,4 since the errors are in quadrants 
1, 3 and 4 because of the repeating integer 1.

Another example, if strArr is: 
["(1,2,3,4,5,6,7,8,9)","(x,x,x,x,x,x,x,x,x)","(6,x,5,x,3,x,x,4,x)",
"(2,x,1,1,x,x,x,x,x)","(x,x,x,x,x,x,x,x,x)","(x,x,x,x,x,x,x,x,x)",
"(x,x,x,x,x,x,x,x,x)","(x,x,x,x,x,x,x,x,x)","(x,x,x,x,x,x,x,x,9)"] 
then your program should return 3,4,5,9. 
'''

empty = "x"
    
def find_error_and_quadrant(arr):
	wrong = []
	for i in range(0,len(arr)):
		#print arr[i]			
		if(arr[i] != empty and count_occurrencies(arr, arr[i])>1):
			wrong.append(i/3)
			#print "wrong ", count_occurrencies(arr, arr[i])
	return set(wrong)
	
def count_occurrencies(arr, chr):
	count = 0
	for i in arr:
		if i == chr:
			count += 1
	return count
	
def matrixify(arr):
	result = []
	for i in arr:
		result.append(i[1:-1].split(","))
	return result	

def check_rows(arr):
	wrong = []
	#print "rows"
	for row in range(0,len(arr)):
		wrong2 = find_error_and_quadrant(arr[row])
		for quad in wrong2:
			num = ((row/3)*3)+(quad%3)
			#num += +1
			if num not in wrong:
				wrong.append(num)
	#print "wrong ",wrong
	return wrong

def check_columns(arr):
	wrong = []
	#print "columns"
	tmpcol = []
	for col in range(0,len(arr)):
		for row in range(0,len(arr)):
			tmpcol.append(arr[row][col])		
		#print tmpcol
		wrong2 = find_error_and_quadrant(tmpcol)	
		for quad in wrong2:
			num = (col/3)+((quad%3)*3)
			if num not in wrong:
				wrong.append(num)
		tmpcol = []	
	#print "wrong ",wrong
	return wrong

def check_grids(arr):
	wrong = []
	#print "grids"
	tmpgrid= []
	for x in xrange(0,len(arr),3):
		for y in xrange(0,len(arr),3):
			for indx in range(0,3):
				for indy in range(0,3):
					#print x+indx,y+indy
					tmpgrid.append(arr[x+indx][y+indy])
			#print "--------",x+(y/3)	
			#print tmpgrid		
			wrong2 = find_error_and_quadrant(tmpgrid)
			if(len(wrong2) > 0 ):
				num = x+(y/3)
				if num not in wrong:
					wrong.append(num)		
			#print wrong2
			tmpgrid = []	
	#print "wrong ",wrong
	return wrong

def SudokuQuadrantChecker(arr):
	wrong = []
	result = []
	arr = matrixify(arr)
	#print arr	
	#print check_rows(arr)
	#print check_columns(arr)
	#print check_grids(arr)
	wrong += check_rows(arr)
	wrong += check_columns(arr)
	wrong += check_grids(arr)
	if(len(wrong) == 0):
		return "legal"
	wrong = list(set(wrong))
	for i in wrong:
		result.append(i+1)
	return str(sorted(result))[1:-1].replace(" ","")

arr = [
"(1,2,x,4,5,6,7,8,9)",
"(2,x,x,x,x,x,x,x,x)",
"(3,x,x,x,x,x,x,x,x)",
"(4,x,x,x,x,x,x,x,x)",
"(5,x,x,x,x,x,x,x,x)",
"(6,x,x,x,x,x,x,x,x)",
"(7,x,x,x,x,x,9,x,x)",
"(8,x,x,x,x,x,x,x,x)",
"(9,x,x,x,x,x,x,x,x)"
]

arr2 =   [
"(1,2,3,4,5,6,7,8,9)","(x,x,x,x,x,x,x,x,x)","(6,x,5,x,3,x,x,4,x)",
"(2,x,1,1,x,x,x,x,x)","(x,x,x,x,x,x,x,x,x)","(x,x,x,x,x,x,x,x,x)",
"(x,x,x,x,x,x,x,x,x)","(x,x,x,x,x,x,x,x,x)","(x,x,x,x,x,x,x,x,9)"]	

print SudokuQuadrantChecker(arr2) 	

#print find_error_and_quadrant("1,2,3,x,5,6,7,8,9")
