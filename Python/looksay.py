'''
Have the function LookSaySequence(num) take the num parameter being passed and 
return the next number in the sequence according to the following rule: to 
generate the next number in a sequence read off the digits of the given number, 
counting the number of digits in groups of the same digit. For example, the 
sequence beginning with 1 would be: 1, 11, 21, 1211, ... 
The 11 comes from there being "one 1" before it and the 21 comes from there 
being "two 1's" before it. So your program should return the next number in the 
sequence given num. 
'''

def find_sequence(sen):
	j = 0
	while(j+1 < len(sen) and sen[j] == sen[j+1]):
		j +=1
	return j+1

def LookSaySequence(num): 
	sen = str(num)
	res = ""
	tmp = find_sequence(sen)
	res = res + str(tmp) + sen[0]
	for i in range(1,len(sen)):
		if(sen[i] != sen[i-1]):
			tmp = find_sequence(sen[i:])
			res = res + str(tmp) + sen[i]
			i = i + tmp
	return res
    

num = 2466   
print LookSaySequence(num)  


