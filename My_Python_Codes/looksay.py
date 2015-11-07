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
# keep this function call here  
# to see how to enter arguments in Python scroll down
print LookSaySequence(num)  


