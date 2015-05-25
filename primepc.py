'''
Coderbyte

Using the Python language, have the function PrimeChecker(num) take num and 
return 1 if any arrangement of num comes out to be a prime number, otherwise 
return 0. For example: if num is 910, the output should be 1 because 910 can 
be arranged into 109 or 019, both of which are primes.

'''
import itertools

def defi_perm(num):
	list2Perm = str(num)
	listPerm = [[a, b, c]
            for a in list2Perm
            for b in list2Perm
            for c in list2Perm
            if ( a != b and b != c and a != c )
            ]
	return listPerm

def addperm(x,l):
	return [ l[0:i] + [x] + l[i:]  for i in range(len(l)+1) ]

def defi_perm2(l):
	l = str(l)
	if len(l) == 0:
		return [[]]
	return [x for y in defi_perm2(l[1:]) for x in addperm(l[0],y) ]

def is_prime(num):
  for i in range(2,num):
    if i * i > num:
      return True
    
    if num % i == 0:
      return False
      
def PrimeChecker(num):
	#perm = list(itertools.permutations(str(num)))
	perm = defi_perm2(num)
	for i in perm:
		#print int(''.join(i))
		if is_prime( int(''.join(i))):
			return 1
	return 0
	
num = 104
print PrimeChecker(num)	
	
