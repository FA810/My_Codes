def OverlappingRectangles1(arr): 
	tmp = arr.replace("),(",");(")
	points = []
	#print tmp
	z = ""
	for i in tmp.split(";"):
		z = i[1:-1]
		points.append((int(z.split(',')[0]),int(z.split(',')[1])))
	#print points	
	vxmax1 = max(points[0][0],points[1][0],points[2][0])	
	vxmin1 = min(points[0][0],points[1][0],points[2][0])
	vymax1 = max(points[0][1],points[1][1],points[2][1])
	vymin1 = min(points[0][1],points[1][1],points[2][1])
	l1 = vxmax1 - vxmin1
	w1 = vymax1 - vymin1
	
	vxmax2 = max(points[4][0],points[5][0],points[6][0])
	vxmin2 = min(points[4][0],points[5][0],points[6][0])
	vymax2 = max(points[4][1],points[5][1],points[6][1])
	vymin2 = min(points[4][1],points[5][1],points[6][1])
	l2 = vxmax2 - vxmin2
	w2 = vymax2 - vymin2
	#print points
	#print l1,w1, 	vxmax1 , vxmin1,vymax1 , vymin1
	#print l2,w2, 	vxmax2 , vxmin2,vymax2 , vymin2
	area1 = l1 * w1
	area2 = l2 * w2
	#print area1,area2
	
	if(
	(vxmax2 in range(vxmin1,vxmax1) or vxmin2 in range(vxmin1,vxmax1)) and
	(vymax2 in range(vymin1,vymax1) or vymin2 in range(vymin1,vymax1))
	):
		xma = max(vxmin2,vxmin1) - min(vxmax1,vxmax2)
		yma = max(vymin2,vymin1) - min(vymax1,vymax2)
		#print xma,yma, xma*yma
		ama = xma*yma
		if(ama != 0):
			return area1/ ama
		else:
			return 0	
	else:
		return 0	
	
	

def OverlappingRectangles(strArr):
	strArr = eval(strArr[0])
	a = strArr[:4]
	b = strArr[4:]
	a_x = (a[0][0] , a[1][0] , a[2][0] , a[3][0])
	b_x = (b[0][0] , b[1][0] , b[2][0] , b[3][0])
	a_y = (a[0][1] , a[1][1] , a[2][1] , a[3][1])
	b_y = (b[0][1] , b[1][1] , b[2][1] , b[3][1])
	a_side_x = max(a_x) - min(a_x)
	a_side_y = max(a_y) - min(a_y)
	if max(min(a_x),min(b_x)) >= min(max(a_x),max(b_x)):
		return 0
	elif max(min(a_y),min(b_y)) >= min(max(a_y),max(b_y)):
		return 0
	elif min(b_x) < min(a_x) < max(a_x) < max(b_x) and min(b_y) < min(a_y) < max(a_y) < max(b_y):
		return 0
	else:
		overlap_x = abs(max(min(a_x),min(b_x)) - min(max(a_x),max(b_x)))
		overlap_y = abs(max(min(a_y),min(b_y)) - min(max(a_y),max(b_y)))
		return max((a_side_x/overlap_x) * (a_side_y/overlap_y),(a_side_y/overlap_x) * (a_side_x/overlap_y))
    





'''
1 "(0,0),(0,-2),(3,0),(3,-2),(2,-1),(3,-1),(2,3),(3,3)"  
2 "(0,0),(2,0),(0,4),(2,4),(0,1),(2,1),(0,4),(2,4)"
3 "(0,0),(0,-2),(3,0),(3,-2),(2,-2),(3,-2),(2,20),(3,20)"
4 "(0,0),(1,0),(0,1),(1,1),(0,0),(2,0),(0,-1),(2,-1)"
5 "(0,0),(5,0),(0,2),(5,2),(3,1),(5,1),(3,-1),(5,-1)"  
6 "(0,0),(5,0),(0,2),(5,2),(2,1),(5,1),(2,-1),(5,-1)"  
7 "(1,0),(1,1),(4,0),(4,1),(3,0),(4,0),(3,1),(4,1)" 
8 "(1,0),(1,1),(4,0),(4,1),(2,0),(4,0),(2,1),(4,1)"  
9 "(1,0),(1,1),(4,0),(4,1),(5,0),(27,0),(5,-25),(27,-25)"
10 "(5,0),(-2,0),(5,-1),(-2,-1),(3,-1),(5,-1),(3,56),(5,56)"

'''	
arr =  "(0,0),(5,0),(0,2),(5,2),(3,1),(5,1),(3,-1),(5,-1)"
# keep this function call here  
# to see how to enter arguments in Python scroll down
print OverlappingRectangles(arr)  









