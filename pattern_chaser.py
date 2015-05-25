my_dict = {}

def count_pat(sen,adv):
	for i in range(0,len(sen)-adv+1):
		if i>0 and sen[i:i+adv] == sen[i-1:i-1+adv]:
			continue
		if( my_dict.has_key(sen[i:i+adv]) ):
			my_dict[sen[i:i+adv]] += 1
		else:
			my_dict[sen[i:i+adv]] = 1
	sort = sorted([(value,key) for (key,value) in my_dict.items()])		
	return sort[::-1]	

def finder(sen):
	i=2
	maxi_pat = ''
	while i < len(sen)-1:
		sort = count_pat(sen,i)
		maxi = sort[0][0]
		if maxi < 2:
			return maxi_pat
		else:
			maxi_pat = sort[0][1]
		my_dict.clear()
		i += 1
	return maxi_pat	

def PatternChaser(sen): 
	result = finder(sen) 
	if result == '' :
		return "no null"
	else:
		return "yes "+str(result)	
	
	
sen = "abcdef12kkk12"   
# keep this function call here  
# to see how to enter arguments in Python scroll down
print PatternChaser(sen)  
