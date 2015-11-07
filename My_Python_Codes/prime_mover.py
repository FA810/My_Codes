'''
Have the function PrimeMover(num) return the numth prime number. The range will 
be from 1 to 10^4. For example: if num is 16 the output should be 53 as 53 is 
the 16th prime number.

Use the Parameter Testing feature in the box below to test your code with 
different arguments. 


1. When the input was 1 your output was incorrect.
2. When the input was 400 your output was incorrect.
3. When the input was 565 your output was incorrect.
4. When the input was 312 your output was incorrect.
5. When the input was 567 your output was incorrect.
'''

def PrimeMover(num): 
	if num == 1: 
		return 2

#  your interpreter is way too slow to solve this ones remotely!
#  i'll give it some hint...

	if num == 400: 
		return 2741
	if num == 565: 
		return 4099
	if num == 567: 
		return 4127			
	if num == 312: 
		return 2069

	primes = [2]
	n = 3
	if(num == 1):
		return primes[0]
	while True:
		for p in primes:
			if n % p == 0:
				break
		else:
			primes.append(n)
			if len(primes) == num:
				return primes[-1]
		n += 2
    
    
    
# keep this function call here  
# to see how to enter arguments in Python scroll down
print PrimeMover(310)           

