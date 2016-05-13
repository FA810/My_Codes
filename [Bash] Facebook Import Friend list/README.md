## Purpose ##
- Import your (or someone else's visible) list of Facebook friends in a text file
- Compare with previous backups

##Requirements##
- Firefox, any version that can save web pages in "Text files" only

##Save the page##

1. go to the friends page on Facebook;
2. scroll all the way down to the bottom until the page loads all friends;
3. save the page with ".html" extension, for example "myfile.html", just remember to save it as "text files".


##IMPORT##

1. from the project folder give the command "import.sh" followed by your filename (with full path if it's in different location), following the former example:

 ./import.sh myfile.html

2. you will now have the files:

	- myfile.html_friendList.txt		: your friends list
	- myfile.html_friendList_sorted.txt	: a sorted version of your friends list
	- friendList.txt					: just a standard name of the first
	
	
##COMPARISON##

1. copy a "friendList.txt" into "old" folder;
2. whenever you like, save and import your friends list again;
3. call the command "compare.sh" to see the differences between current and old version of "friendList.txt":

 ./compare.sh

 now you'll get the list of people who has been added to your friends list, or removed or changed name.


##CLEAN##

1. use the command "clean.sh":

 ./clean.sh
