'''
Using the Python language, have the function TexasHoldEm(hand) determine which 
is the winning player after reading the hand 
parameter being passed which will be written in the following format:

["a1,a2","b1,b2","c1,c2","d1,d2","Flop1,Flop2,Flop3"]

where the elements "a1,a2","b1,b2","c1,c2","d1,d2" ,etc. are the couple of cards 
for each player in a format that may be like this example:

"A,J","K,K","10,4","6,8","Q,9" 

"Flop1,Flop2,Flop3" are the three cards of the flop, for example:

"9,A,Q"

Hence 
Player 1 will have: A,J,9,A,Q
Player 2 will have: K,K,9,A,Q
Player 3 will have: 10,4,9,A,Q
Player 4 will have: 6,8,9,A,Q
Player 5 will have: Q,9,9,A,Q
Player 6 will have: 9,9,9,A,Q

In this version of Texas Hold Em there are no card colours, players make points only 
by having (in order of increasing importance):
highest card, pair, two pair, three of a kind, straight, full house, four of a kind and royal flush.

Therefore the output must be 6, because player 6 has the best hand, with three of a kind.

Input Assumptions:
- players' number range from 2 to unlimited;
- there will be NO ties, the first listed player with best hand wins.
'''

cardval = {"10":10, "2":2, "3":3, "4":4, "5":5, "6":6, "7":7, "8":8, "9":9, "0":10, "J":11, "Q":12, "K":13, "A":14,}

pr			    = 60 
two_pair		= 120
three_of_a_kind	= 360
straight		= 500
full_house		= 1000
four_of_a_kind	= 2000
royal_flush		= 4000

def sort_hand(hand):
	tmp = []
	for i in hand:
		tmp.append(cardval[i])
	sortedCards = sorted(tmp, reverse=True)	
	return sortedCards
	
def find_highest(hand):
	tmp = []
	for i in hand:
		tmp.append(cardval[i])
	sortedCards = sorted(tmp, reverse=True)	
	return sortedCards	
	

def char_count(word,let):
	tot = 0
	for i in word:
		if i == let:
			tot +=1
	return tot

def is_straight(hand):
	if find_straight(hand) != []:
		return True
	else:
		return False	

def find_straight(hand):
	tmp = set()
	for i in hand:
		tmp.add(cardval[i])
	if len(tmp)<5:
		return ''
	sortedCards = sorted(tmp, reverse=True)	
	count = 0
	stra = []
	stra.append(sortedCards[0])
	for i in range(1,len(sortedCards)):
		if(sortedCards[i] == sortedCards[i-1]-1 ):
			stra.append(sortedCards[i])
		else:
			stra = []	
			stra.append(sortedCards[i])
		if(len(stra) == 5): 
			return stra	
	return stra		
			
def find_pair(hand):
	pairs = set()
	for i in hand:
		if(char_count(hand,i) == 2):
			pairs.add(i)
	return list(pairs)

def find_double_pair(hand):
	tmp = find_pair(hand) 
	if len(tmp)>1:
		return tmp
	else:
		return []	

def find_three_of_a_kind(hand):
	three = []
	maxi = 0
	for i in hand:
		if(char_count(hand,i) == 3) and cardval[i] > maxi:
			maxi = cardval[i]
			three = [i]			
	return three
		
def find_poker(hand):
	for i in hand[:4]:
		if(char_count(hand,i) == 4):
			return [i]
	return []

def is_royal(hand):
	flu = ["A","K","Q","J","0"]
	for i in hand:
		if (i in flu):
			flu.remove(i)
	if(len(flu) == 0):
		return True
	else:
		return False	

def find_full_house(hand):
	three = find_three_of_a_kind(hand)
	pair = find_pair(hand)
	if(len(three)>0 and len(pair)>0):
		return [three,pair]						
	else:
		return []	
	


def show_hand(hand):
	poker  = find_poker(hand)
	pair   = find_pair(hand)
	dpair  = find_double_pair(hand)
	toak   = find_three_of_a_kind(hand)
	fhouse = find_full_house(hand)
	royal  = is_royal(hand)
	strai  = find_straight(hand)
	high   = sort_hand(hand)[0]
	
	if (len(poker) > 0): 
		print "poker: \t\t",poker
		return four_of_a_kind + 4 * int(cardval[poker[0]])
	if (royal):
		print "royal: \t\t",royal	
		return royal_flush
	if (len(pair) > 0) and (len(toak) == 1): 
		print "full: \t\t",fhouse	
		return full_house + 15*int(cardval[fhouse[0][0]])+int(cardval[fhouse[1][0]])	
	if (len(strai) == 5):
		print "straight: \t\t",strai
		return straight + strai[0]
	if (len(pair) == 0) and (len(toak) == 1): 
		print "toak: \t\t",toak	
		return three_of_a_kind + int(cardval[toak[0]])
	if (len(dpair) > 1): 
		print "d_pair: \t",dpair	
		return two_pair + 15 * int(cardval[dpair[0]]) + int(cardval[dpair[1]])
	if (len(pair) == 1) and (len(toak) == 0): 
		print "pair: \t\t",pair
		return pr+ int(cardval[pair[0]])
	
	print "highest: \t\t",high	
	return sort_hand(hand)[0]
	
	
	

def convert(hand):
	return hand.replace('10','0').replace(',','')
	
def find_winner(scores):
	maxi = max(scores)
	for i in range(0,len(scores)):
		if scores[i] == maxi:
			return i
		

def read_poker(arr):
	flop = arr[-1]
	print flop
	'''
	turn = arr[-2]
	print turn
	river= arr[-1]
	print river
	'''
	plays = arr[0:-1]
	print plays
	scores = []
	for i in plays:
		tmp = convert(i)+convert(flop)
		print tmp
		scores.append(show_hand(tmp))
	print scores	
	print find_winner(scores)+1
		
arr = ["K,A","Q,J","8,6","J,7","8,K","5,6","2,3,4"] # player: 6
arr2= ["K,A","Q,J","8,10","J,7","8,K","5,6","K,A,4"]
arr3 = ["A,J","K,K","10,4","6,8","Q,9","9,9","9,A,Q"]

read_poker(arr3)


#print find_straight("8Q09JA")		
