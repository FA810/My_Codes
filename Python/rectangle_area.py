'''
Have the function RectangleArea(strArr) take the array of strings stored in 
arr, which will only contain 4 elements and be in the form (x y) where x and 
y are both integers, and return the area of the rectangle formed by the 4 
points on a Cartesian grid. The 4 elements will be in arbitrary order. 

For example: if strArr is ["(0 0)", "(3 0)", "(2 0)", "(3 2)"] then your 
program should return 6 because the width of the rectangle is 3 and the 
height is 2 and the area of a rectangle is equal to the width * height. 
'''

def RectangleArea(arr):
	tmp = arr[0][1:-1].split(" ")
	MaxX = int(tmp[0])
	MinX = MaxX
	MaxY = int(tmp[1])
	MinY = MaxY
	for i in range(1,len(arr)):		
		tmp = arr[i][1:-1].split(" ")
		x = int(tmp[0])
		y = int(tmp[1])
		MaxX = max(MaxX, x)
		MaxY = max(MaxY, y)
		MinX = min(MinX, x)
		MinY = min(MinY, y)
	return (MaxX-MinX)*(MaxY-MinY)
    
arr = ["(1 1)","(1 3)","(3 1)","(3 3)"]
print RectangleArea(arr)
