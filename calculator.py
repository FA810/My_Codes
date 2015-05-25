def Calculator(inStr): 
  for i in range(len(inStr)-1, 0, -1):
    if inStr[i] == '(' and inStr[i-1] in '0123456789)':
      inStr = inStr[:i] + '*' + inStr[i:]
    elif inStr[i-1] == ')' and inStr[i] in '0123456789(':
      inStr = inStr[:i-1] + '*' + inStr[i-1:]
  return eval(inStr)
    
# keep this function call here  
# to see how to enter arguments in Python scroll down
print Calculator(raw_input())    
