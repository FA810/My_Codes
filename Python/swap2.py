'''
Using the Python language, have the function SwapII(str) take the str 
parameter and swap the case of each character. Then, if a letter is between 
two numbers (without separation), switch the places of the two numbers. 
For example: if str is 
"6Hello4 -8World, 7 yes3" the output should be 
"4hELLO6 -8wORLD, 7 YES3".

Input = "Hello -5LOL6"Output = "hELLO -6lol5"
Input = "2S 6 du5d4e"Output = "2s 6 DU4D5E" 
'''

letters = "qwertyuiopasdfghjklmnbvcxz"
capitals= "QWERTYUIOPLKJHGFDSAZXCVBNM"
allletters= "QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuiopasdfghjklmnbvcxz"
numbers = "0123456789"

def swap_digits(word):
	res = ''
	num = []
	for i in range(0,len(word)):
		if (word[i] in numbers):
			num.append(word[i])
	if len(num)<2:
		return word		
	for i in range(0,len(word)):
		if (word[i] == num[0]):
			res += num[1]
		elif (word[i] == num[1]):
			res += num[0]
		else:
			res+= word[i]	
	return res				
		
			

def SwapII(arr): 
	res=''
	lit = []
	for i in range(0,len(arr)):
		if arr[i] in capitals:
			res += arr[i].lower()
		elif arr[i] in letters:
			res += arr[i].upper()
		else:
			res += arr[i]
			
	lit = res.split()
	#print lit
	tmp = []	
	for i in lit:
		tmp.append(swap_digits(i))
	return " ".join(tmp)	

arr = "6Hello4 -8World, 7 yes3"   	#"4hELLO6 -8wORLD, 7 YES3"
arr2 = "2S 6 du5d4e"				#"2s 6 DU4D5E"
arr3 = "Hello -5LOL6"				#"hELLO -6lol5"
arr4 = "6coderbyte5"  
# keep this function call here  
# to see how to enter arguments in Python scroll down
print SwapII(arr4)  
#print swap_digits("-3ord4")


'''
 "6coderbyte5"  
 "L3OV2E"  
 "Hel4lo2 World"  
 "yeahHH"  
 "i love cAke33&"  
 "COMM2a2"  
 "abcdE 8numbers2"  
 "l2et5ter"  
 "yolO11"  
 "123gg))(("  
'''
