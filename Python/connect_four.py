'''
potrebbe essere ripulito di piu'.
esempio: verticali e orizzontale funzionano ancora 
con lista stringhe e non matrice...
'''

def ConnectFourWinner(arr):
	mine = arr[0]
	oppo = ["Y","R"]["Y" == mine]
	#print mine,oppo
	arr = arr[1:]
	#print make_lists(arr)
	oriz = check_rows(arr,mine,oppo)
	if oriz != (-1,-1):
		return "("+str(oriz[0])+"x"+str(oriz[1])+")"
	vert = check_columns(arr,mine,oppo)
	if vert != (-1,-1):
		return "("+str(vert[0])+"x"+str(vert[1])+")"
	diag = check_diagonals(make_lists(arr),mine,oppo)
	if diag != (-1,-1):
		return "("+str(diag[0])+"x"+str(diag[1])+")"
	return "none"
    
def print_board(arr):
	arr = arr[1:]
	#for i in xrange(len(arr)-1,-1,-1):
	for i in xrange(0,len(arr)):	
		print arr[i]

def check_rows(arr,mine,oppo):
	for i in xrange(len(arr)-1,-1,-1):	#da 6 a 0
		row = arr[i][1:-1].split(",")
		#print row
		if row[3] == oppo:
			continue
		for j in range(0,4):			#da 0 a 3 centro
			piece = [p for p in row[j:j+4]]
			#print piece
			if piece.count(mine) == 3 and piece.count("x") == 1:
				foundY = piece.index("x")+j				
				if(i<5):
					tmp = arr[i+1][1:-1].split(",")
					if tmp[foundY] != "x":
						return (i+1,foundY+1)
				else:
					return (i+1,foundY+1)				
	return (-1,-1)
	
def check_columns(arr,mine,oppo):	
	mid		= arr[3][1:-1].split(",")
	columns = len(mid)
	rows 	= len(arr)
	#print mid,columns,rows	
	for i in range(0,columns):
		if mid[i] == oppo:
			#print "FUCK"
			continue
		else:
			for j in range(0,4):
				piece = []
				for t in range(5,1,-1):
					r1 = (arr[t-j][1:-1].split(","))[i]
					piece.append(r1)			
					#print piece
				if piece.count(mine) == 3 and piece.count("x") == 1:
					return(t-j+1,i+1)			
	return (-1,-1)
	
def check_diagonals(arr,mine,oppo):
	
	for sx in range(0,4):	#da 0 a 3 base sinistra
		for i in range(5,2,-1): # da 5 a 2
			#print arr[i]
			line = []
			for j in range(0,4):
				line.append(arr[i-j][sx+j])
			if line.count(mine) == 3 and line.count("x") == 1:
				foundY = line.index("x")
				#print i-foundY,sx+foundY
				if i==5 and foundY==0:
					return (i-foundY+1,sx+foundY+1)
				elif arr[i-foundY+1][sx+foundY] != "x":	
					#print arr[i-foundY+1][dx-foundY]
					return (i-foundY+1,sx+foundY+1)	
		
	for dx in range(3,7):	#da 3 a 7 base destra
		for i in range(5,2,-1): # da 5 a 2
			line = []
			for j in range(0,4):
				line.append(arr[i-j][dx-j])
			#print line
			if line.count(mine) == 3 and line.count("x") == 1:
				foundY = line.index("x")
				#print i-foundY,dx-foundY
				if i==5 and foundY==0:
					return (i-foundY+1,dx-foundY+1)
				elif arr[i-foundY+1][dx-foundY] != "x":	
					#print arr[i-foundY+1][dx-foundY]
					return (i-foundY+1,dx-foundY+1)	
	return (-1,-1)
		
def make_lists(arr):
	total = []
	for i in arr:
		mid	= i[1:-1].split(",")
		total.append(mid)
	return total		
	

arr = ["Y",
"(x,x,x,x,x,x,x)",
"(x,x,x,x,x,x,x)",
"(x,x,x,x,x,x,x)",
"(x,x,Y,Y,x,x,x)",
"(x,R,R,Y,Y,x,R)",
"(x,Y,R,R,R,Y,R)"] 
# keep this function call here  
# to see how to enter arguments in Python scroll down
print ConnectFourWinner(arr)  

#print_board(arr)
