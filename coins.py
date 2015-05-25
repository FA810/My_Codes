coins = [1,5,7,9,11]

def CoinDeterminer(num):
	if(num%coins[-1] == 0):
		return num/coins[-1]		
	elif(num in coins[:-1]) :
		return 1
	elif(num in [2,6,8,10,12,14,16,18,20]):
		return 2
	elif(num in [3,13,15,17,19,21,23,25,27,29,31]):
		return 3
	elif(num in [4,24,26,28,30,32,34,36,38,40,42]):
		return 4
	else:
		return 1+min([CoinDeterminer(num-i) for i in coins])
		#return 1+min(map(lambda i: [CoinDeterminer(num-i)], coins))

num = 57
# 34 37 100
# keep this function call here  
# to see how to enter arguments in Python scroll down
print CoinDeterminer(num)  
