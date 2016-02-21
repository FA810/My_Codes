'''
Have the function ChessboardTraveling(str) read str which will be a string 
consisting of the location of a space on a standard 8x8 chess board with no 
pieces on the board along with another space on the chess board. The structure 
of str will be the following: "(x y)(a b)" where (x y) represents the position 
you are currently on with x and y ranging from 1 to 8 and (a b) represents some 
other space on the chess board with a and b also ranging from 1 to 8 where 
a > x and b > y. Your program should determine how many ways there are of 
traveling from (x y) on the board to (a b) moving only up and to the right. 
For example: if str is (1 1)(2 2) then your program should output 2 because 
there are only two possible ways to travel from space (1 1) on a chessboard to 
space (2 2) while making only moves up and to the right. 
'''

side = 8
moves2 = []	

def generateMoves(): 
	for i in range(0,side):
		tmp = []
		for j in range(0,side):
			if i == 0:
				tmp.append(1)
			elif j == 0 and i > 0:
				tmp.append(1)
			elif i > 0 and j > 0:
				tmp.append(tmp[j-1]+moves2[i-1][j])
		moves2.append(tmp)	
	#print_moves(moves)
	return moves2

moves = [
	[  1,  1,  1,  1,  1,  1,   1,   1],
	[  1,  2,  3,  4,  5,  6,   7,   8],
	[  1,  3,  6, 10, 15, 21,  28,  36],
	[  1,  4, 10, 20, 35, 56,  84, 120],
	[  1,  5, 15, 35, 70,126, 210, 330],
	[  1,  6, 21, 56,126,252, 462, 792],
	[  1,  7, 28, 84,210,462, 924,1716],
	[  1,  8, 36,120,330,792,1716,3432],
]

def ChessboardTraveling(str):
	moves2 = generateMoves() 
	arr = str[1:-1].replace(")("," ").split(" ")
	numx = int(arr[2]) - int(arr[0])
	numy = int(arr[3]) - int(arr[1])
	return moves2[numx][numy]
	
    
str =  "(1 1)(3 3)" 
print ChessboardTraveling(str)  

		
def print_moves(moves):			
	for i in range(0, side):
		print moves2[i],"\n"				
