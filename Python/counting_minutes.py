def min_bef(hh,mm,pm):
	if (pm==0) and hh==12:
		return 1440 #h=0
	elif (pm==1):
		if(hh<12):
			hh += 12		
	mm = 60-mm
	#print hh,mm,pm			
	return ((24-(hh+1))*60)+mm	

def min_aft(hh,mm,pm):
	if (pm==0) and hh==12:
		return 0 #h=0
	elif (pm==1):
		if(hh<12):
			hh += 12
	#print hh,mm,pm			
	return (hh*60)+mm


def CountingMinutesI(sen):
	sen = sen.replace('m','')
	sen = sen.replace(':',' ')
	sen = sen.replace('p',' 1')
	sen = sen.replace('a',' 0')
	lit = sen.split("-")
	lit = lit[0].split(" ")	+lit[1].split(" ")
	lita=[]
	for i in lit:
		lita.append(int(i))	
	print lita
	
	#due am o pm
	if(lita[2] == lita[5]):
		#print "pm == pm"	
		#if(lita[0] == lita[3]): #chebbordello!!!
		res = min_bef(lita[0],lita[1],lita[2])-min_bef(lita[3],lita[4],lita[5])
		if res <0 :
			return 1440+res
		else:
			return res
	#regolare a meno che il +12 lo fa diventare alto 	
	elif(lita[2] < lita[5]):
		return min_bef(lita[0],lita[1],lita[2])-min_bef(lita[3],lita[4],lita[5])
	# devi fare le differenze col giorno successivo	
	elif(lita[2] > lita[5]):
		#print "pm vs am"
		return min_bef(lita[0],lita[1],lita[2])+min_aft(lita[3],lita[4],lita[5])



    
    

sen= "1:23pm-1:08pm"  		#1425
#  0   1   2   3   4   5
#[13, 23, 01, 13, 08, 01]
sen3 = "12:30pm-12:00am"	#690
#  0   1   2   3   4   5
#[12, 30, 01, 00, 00, 00]

sen4 = "1:00pm-11:00am"  	#1320
sen5 = "2:08pm-2:00am"  	#712
sen6 = "11:00am-2:10pm"  	#190
sen7 = "12:31pm-12:34pm"  	#3		#tosto
sen8 = "3:00pm-4:45am"  	#825
sen9 = "5:00pm-5:11pm"   	#11
'''
print min_bef(12,30,1)
print min_aft(12,30,1)
'''    
# keep this function call here  
# to see how to enter arguments in Python scroll down

print sen, CountingMinutesI(sen )
print sen3,CountingMinutesI(sen3 ) 
print sen4,CountingMinutesI(sen4 ) 
print sen5,CountingMinutesI(sen5 ) 
print sen6,CountingMinutesI(sen6 ) 
print sen7,CountingMinutesI(sen7 ) 
print sen8,CountingMinutesI(sen8 ) 
print sen8,CountingMinutesI(sen9 )  


'''
1 "12:30pm-12:00am" 
2 "1:00pm-11:00am" 
3 "2:03pm-1:39pm" 
4 "1:23am-1:08am" 
5 "2:08pm-2:00am" 
6 "2:00pm-3:00pm" 
7 "11:00am-2:10pm" 
8 "12:31pm-12:34pm" 
9 "3:00pm-4:45am" 
10 "5:00pm-5:11pm" 
'''

	
    


