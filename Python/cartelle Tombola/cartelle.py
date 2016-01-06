import math, random

numero_di_cartelle = 40
filename = "cartelle.csv"
print_cartella_on_screen = True
I_want_3_rows_of_9 = True

def generaNum(min,max):
	return (random.randint(min,max))

def creaCartella():
	a = []
	a.append(generaNum(1,9))
	for i in xrange(10,80,10):
		a.append(generaNum(i,i+9))
	a.append(generaNum(80,90))
	while len(a)<15:
		if(len(a) < 11):
			n = generaNum(1,29)
		elif(len(a) < 13):
			n = generaNum(30,59)	
		else:
			n = generaNum(60,90)	
		if n not in a:
			a.append(n)			
	a.sort()
	return a
	
def dispose(cartella):
	disp = [[],[],[]]
	start = generaNum(0,2)
	for i in range(start,len(cartella)+start):
		disp[i%3].append(cartella[i-start])		
	if(I_want_3_rows_of_9 == True):
		disp[0] = makeSpace(disp[0])
		disp[1] = makeSpace(disp[1])
		disp[2] = makeSpace(disp[2])	
	(disp[0],disp[1],disp[2]) = swapColumns(disp[0],disp[1],disp[2])	
	return disp
	
def print_on_screen(disp):	
	for i in range(0,len(disp)):
		print disp[i]
	
def swapColumns(n1,n2,n3):
	for i in range(0,len(n1)):
		if n1[i] > n2[i] and n2[i] > 0:
			tmp = n1[i]
			n1[i] = n2[i]
			n2[i] = tmp
		if n1[i] > n3[i] and n3[i] > 0:
			tmp = n1[i]
			n1[i] = n3[i]
			n3[i] = tmp	
		if n2[i] > n3[i] and n3[i] > 0:
			tmp = n2[i]
			n2[i] = n3[i]
			n3[i] = tmp	
	return (n1,n2,n3)			
	
def makeSpace(row):
	result = []
	index = 0
	for i in xrange(10,91,10):
		if index >= 5:
			break
		if i == 90:
			i = 91
		if(row[index] < i): 
			result.append(row[index])
			index+=1
		else:
			result.append(0)
	while len(result) < 9:
		result.append(0)	
	return result

f = open(filename, "w+")

header = "seri,01,02,03,04,05,06,07,08,09,10,11,12,13,14,15"
if(I_want_3_rows_of_9 == True):
	header += (",16,17,18,19,20,21,22,23,24,25,26,27")
header += ("\n")
f.writelines(header)

for j in range(1,numero_di_cartelle+1):
	cartella = dispose(creaCartella())
	result = cartella[0] + cartella[1] + cartella[2]
	if(print_cartella_on_screen == True):
		print "---- cartella ",j
		print_on_screen(cartella)		
	row = ""
	for i in range(0, len(result)):		
		if result[i] != 0:
			row += str(result[i]).zfill(2)
		else:
			row += "  "	
		if (i == len(result)-1):
			row += "\n"
		else:
			row += 	","
	#print ('{0:04d}'.format(j)  + "," + str(row))
	f.writelines('{0:04d}'.format(j)  + "," + str(row))

f.close

