'''
Given a string sequence with parenthesis, brackets and curly brackets,
return True or False if the sequence is legitimate.
'''

def validBraces(string):
	pall = []
	oppos = {')':'(',']':'[','}':'{'}
	for i in string:
		if i in oppos.values():
			pall.append(i)
		elif i in oppos.keys():
			if pall != [] and pall[-1] == oppos[i]:
				pall.pop()
			else:
				return False	
	if pall == []:
		return True
	return False		
	
# valid ones	
print validBraces("([{}])")
print validBraces("([{}]){}[{}]")
print validBraces("{}[]()[()]")	
# invalid ones
print validBraces("([{}])}")	
print validBraces("([{}[{[})])}")

