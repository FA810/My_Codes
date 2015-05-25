letters = "qwertyuioplkjhgfdsazxcvbnm"

def NumberEncoding(str): 
	res = ""
	for i in str:
		if i.lower() in letters:
			res	= res + convert_to_ascii(i)
		else:
			res = res + i
	return res

def convert_to_ascii(text):
    return "".join(str(ord(char)-96) for char in text)
      
    
arr = "hello 45"
arr2 = "jaj$a"
arr3 =  "f%% %" 
arr4 =   "km$$e" 
arr5 =    "f() ()"
# keep this function call here  
# to see how to enter arguments in Python scroll down
print NumberEncoding(arr4)  
