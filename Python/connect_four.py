'''
 Have the function ConnectFourWinner(strArr) read the strArr parameter being 
passed which will represent a 6x7 Connect Four board. The rules of the game 
are: two players alternate turns and place a colored disc down into the grid 
from the top and the disc falls down the column until it hits the bottom or 
until it hits a piece beneath it. The object of the game is to connect four of 
one's own discs of the same color next to each other vertically, horizontally, 
or diagonally before your opponent. The input strArr will represent a Connect 
Four board and it will be structured in the following format: 
["R/Y","(R,Y,x,x,x,x,x)","(...)","(...)",...)] where R represents the player 
using red discs, Y represents the player using yellow discs and x represents 
an empty space on the board. The first element of strArr will be either R or Y 
and it represents whose turn it is. Your program should determine, based on 
whose turn it is, whether a space exists that can give that player a win. If 
a space does exist your program should return the space in the following 
format: (RxC) where R=row and C=column. If no space exists, return the string 
none. The board will always be in a legal setup.

For example, if strArr is: 
["R","(x,x,x,x,x,x,x)","(x,x,x,x,x,x,x)","(x,x,x,x,x,x,x)","(x,x,x,R,x,x,x)",
"(x,x,x,R,x,x,x)","(x,x,x,R,Y,Y,Y)"] then your program should return (3x4).

Another example, if strArr is: ["Y","(x,x,x,x,x,x,x)","(x,x,x,x,x,x,x)",
"(x,x,x,x,x,x,x)","(x,x,Y,Y,x,x,x)","(x,R,R,Y,Y,x,R)","(x,Y,R,R,R,Y,R)"] 
then your program should return (3x3). 
'''

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
# return (3x3)

print ConnectFourWinner(arr)  

#print_board(arr)
