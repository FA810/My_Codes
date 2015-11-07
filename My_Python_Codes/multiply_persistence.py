def MultiplicativePersistence(num): 
	if num < 10:
		return 0
	else:
		tmp = 1
		nus = str(num)
		for s in nus:
			tmp *= int(s)
		#print tmp	
		return 1+MultiplicativePersistence(tmp)

    
    
num = 25
# keep this function call here  
# to see how to enter arguments in Python scroll down
print MultiplicativePersistence(num)  
