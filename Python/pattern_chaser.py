'''
Have the function PatternChaser(sen) take str which will be a string and return 
the longest pattern within the string. A pattern for this challenge will be 
defined as: if at least 2 or more adjacent characters within the string repeat 
at least twice. So for example "aabecaa" contains the pattern aa, on the other 
hand "abbbaac" doesn't contain any pattern. Your program should return yes/no 
pattern/null. So if str were "aabejiabkfabed" the output should be yes abe. 
If str were "123224" the output should return no null. The string may either 
contain all characters (a through z only), integers, or both. 
But the parameter will always be a string type. The maximum length for the 
string being passed in will be 20 characters. If a string for example is 
"aa2bbbaacbbb" the pattern is "bbb" and not "aa". You must always return the 
longest pattern possible. 
'''

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
print PatternChaser(sen)  
