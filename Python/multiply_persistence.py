'''
Have the function MultiplicativePersistence(num) take the num parameter being 
passed which will always be a positive integer and return its multiplicative 
persistence which is the number of times you must multiply the digits in num 
until you reach a single digit. For example: if num is 39 then your program 
should return 3 because 3 * 9 = 27 then 2 * 7 = 14 and finally 1 * 4 = 4 and 
you stop at 4.
'''

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
print MultiplicativePersistence(num)  
