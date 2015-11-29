'''
Author: Fabio Rizzello
Simulate a whole championship with random outcome.
The teams are provided in input in a text file.
'''

import calendar
import matchplay

filename = 'seriea.txt'
show_last_matches 	= 28
points_per_victory 	= 3
points_per_draw		= 1
#points_per_loss	= 0
char_for_win		= "W"
char_for_draw		= "D"
char_for_loss		= " "
char_for_rest		= "_"
field_separator_in_file = ":"
wait_after_each_turn = False
max_team_name_length = 7
next_turn			= "Next turn "

teams_dictionary = {}
teams = []

class Team(object):	
	def __init__(self, name, value):
		self.name = name
		self.value = value
		self.ranking = 0
		self.serie = ""

def removekey(d, key):
    del d[key]		

def open_file_and_return_teams(filename):
	f = open(filename, 'r+')
	for line in f:
		line = line.strip("\n")		
		if(len(line)>0):
			(key, val) = line.split(field_separator_in_file)
			current_team = Team(key,val)
			teams_dictionary[current_team.name] = current_team
	f.close()		
	return teams_dictionary		
	
def list_teams(teams):
	for i in range(0, len(teams)):
		print teams[i]	
		
def show_ranking():
	for team in (sorted(teams_dictionary.values(), key=lambda team: team.ranking, reverse=True)):
		print team.name.rjust(max_team_name_length), str(team.ranking).rjust(4), team.serie.rjust(show_last_matches)[-show_last_matches:] 		

teams_dictionary = open_file_and_return_teams(filename)
teams = teams_dictionary.keys()	
max_team_name_length = max(max_team_name_length,len(max(teams, key=len))+1)

rounds = calendar.create_fixtures(teams_dictionary.keys())	

for single_round in rounds:
			for match in single_round:
				if ((match[0] != calendar.ghost_team) and (match[1] != calendar.ghost_team)):
					result = matchplay.play(match[0],int(teams_dictionary[match[0]].value), match[1],int(teams_dictionary[match[1]].value))
					if(result[0] > result[1]):
						teams_dictionary[match[0]].ranking += points_per_victory
						teams_dictionary[match[0]].serie += char_for_win	
						teams_dictionary[match[1]].serie += char_for_loss	
					elif(result[1] > result[0]):
						teams_dictionary[match[1]].ranking += points_per_victory
						teams_dictionary[match[0]].serie += char_for_loss	
						teams_dictionary[match[1]].serie += char_for_win
					else:	
						teams_dictionary[match[0]].ranking += points_per_draw						
						teams_dictionary[match[1]].ranking += points_per_draw
						teams_dictionary[match[0]].serie += char_for_draw
						teams_dictionary[match[1]].serie += char_for_draw
				else:
					if (match[0] != calendar.ghost_team):
						teams_dictionary[match[0]].serie += char_for_rest
					else:
						teams_dictionary[match[1]].serie += char_for_rest					
			print
			show_ranking()
			
			if(wait_after_each_turn):
				raw_input(next_turn)		
			print	

print "--- FINAL RANKING"
show_ranking()
#list_teams(teams)
