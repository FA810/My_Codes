'''
Author: Fabio Rizzello
Functions to create fixtures for a provided list of teams.
'''
ghost_team = "___"

def create_fixtures(teams_list):		
	if(len(teams_list) == 0):
		return []		
	home = 0
	away = 0
	rounds = []	
	teams_number = len(teams_list)

	# check even or odd number of teams, use ghost team in latter case
	ghost = False
	if ( teams_number % 2 == 1 ):
		teams_list.append(ghost_team)
		teams_number = len(teams_list)
		ghost = True

	# finding how many rounds and matches per round are involved
	total_rounds = (teams_number - 1) * 2
	matches_per_round = teams_number / 2	

	# completing fixtures
	for single_round in range(0,total_rounds):	
		day = []
		for mat in range(0,matches_per_round):
			home = (single_round + mat) % (teams_number - 1)
			away = (teams_number - 1 - mat + single_round) % (teams_number - 1)
			if (mat == 0):
				away = teams_number - 1
			if(single_round % 2 == 0):						
				day.append([teams_list[home] , teams_list[away]])
			else:	
				day.append([teams_list[away] , teams_list[home]])
		rounds.append(day)
	return rounds

def print_matches(rounds):
		for single_round in rounds:
			for match in single_round:
				for team in match:
					print team,
				print
			print	
	
def try_module():	
	rounds = create_fixtures(['Napoli', 'Parma', 'Juventus', 'Roma'])	
	print_matches(rounds)	
#print_matches(create_fixtures(['Napoli', 'Parma', 'Juventus', 'Roma', "cagat"]))
#try_module()
	
