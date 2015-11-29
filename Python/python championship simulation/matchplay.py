'''
Author: Fabio Rizzello
Simulate a single match with random outcome between two teams.
The teams are provided in input in a text file.
'''

import random

def play(team1,score1,team2,score2):
	team1_goals = 0 
	team2_goals = 0
	total=score1+score2
	attempts = random.randint(5,20)
	#print attempts
	#print total
	while(attempts > 0):
		turn = random.randint(0,total)
		#print turn
		if (turn > (score1)):
			#print team2+" has a chance...",
			team2_goals += shot()
		else:
			#print team1+" has a chance...",
			team1_goals += shot()
		attempts -= 1		
	#print "final score:"
	print team1+"-"+team2+" "+str(team1_goals)+"-"+str(team2_goals)
	return [team1_goals,team2_goals]

def shot():
	result = 0
	last = 4
	chance = random.randint(0,last)
	if(chance == last):
		#print "\t\t goal!"
		result = 1	
	else:	
		#print "\t\t miss"
		pass
	return result

#partita("team1",200,"team2",210)
