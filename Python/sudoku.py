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
# keep this function call here  
# to see how to enter arguments in Python scroll down
print SudokuQuadrantChecker(arr2) 	

#print find_error_and_quadrant("1,2,3,x,5,6,7,8,9")
