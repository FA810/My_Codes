def OffLineMinimum(arr): 
	tmp = []
	res = []
	for i in arr:
		if i != "E":
			tmp.append(int(i))
		else:
			if len(tmp) > 0:
				m = min(tmp)
				res.append(m)
				tmp.remove(m)
	return (str(res)[1:-1]).replace(" ","")
    
# keep this function call here  
# to see how to enter arguments in Python scroll down
arr = [  "6","5","1","2","3","4","E","E","E"    ]
print OffLineMinimum(arr)  

'''
 "5","4","6","E","1","7","E","E","3","2"  
 "1","2","E","E","3"  
 "4","E","1","E","2","E","3","E"  
 "1","2","3","E","E"  
 "1","2","E","3","E","5","4","6"  
 "3","5","E","6","E","1","4","2","E"  
 "4","1","5","E","E","E"  
 "6","5","1","2","3","4","E","E","E"  
 "6","5","1","E","3","4","E","2","E"  

'''
