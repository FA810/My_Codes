#!/usr/bin/python

from Tkinter import *
import subprocess

root = Tk()

artifactDefault = ""
groupDefault = "com.fabio"
typeDefault = "quickstart" # or "webapp"

paddingx = 5
paddingy = 5

root.wm_title("Create Maven Project")
root.resizable(width=FALSE, height=FALSE)
#root.geometry('{}x{}'.format(500, 170))

quote = """HAMLET: To be, or not to be--that is the question:
Whether 'tis nobler in the mind to suffer
The slings and arrows of outrageous fortune
Or to take arms against a sea of troubles
And by opposing end them. To die, to sleep--
No more--and by a sleep to say we end
The heartache, and the thousand natural shocks
That flesh is heir to. 'Tis a consummation
Devoutly to be wished."""

def add_scrollable_text():
	textScro = Frame (root, padx=paddingx, pady=paddingy)
	T = Text(textScro, height=6, width=50)
	S = Scrollbar(textScro)
	T.pack(side=LEFT, expand=1)
	S.pack(side=RIGHT, fill=BOTH)

	S.config(command=T.yview)
	T.config(yscrollcommand=S.set)
	
	T.insert(END, quote)

	textScro.grid(row=5,column=1)
	
def add_label(text, row, column, columnspan = 1):
	labelText = StringVar()
	labelText.set(text)
	label = Label( root, textvariable=labelText)
	label.grid(row=row,column=column, sticky = NE, columnspan = columnspan, padx=paddingx, pady=paddingy)
	return label


def add_button(textToShow, row, column, columnspan = 1, sticky = NSEW, width = 20):
	button = Button(root, text=textToShow, width = width)
	button.grid(row=row,column=column, columnspan = columnspan, sticky = sticky, padx=paddingx, pady=paddingy)
	return button
	
def add_text(row, column, columnspan = 1, defaultText = "", width = 40):
	text = Text(root, height=1, width = width)
	text.grid(row=row,column=column, columnspan = columnspan, sticky = NSEW, padx=paddingx, pady=paddingy)
	if defaultText != "":
		text.insert( END, defaultText)
	return text	
	
def add_radios(fields, row, column, columnspan = 1, sticky = W):
	global v
	projectType.set(typeDefault)
	for text, mode in fields:
		b = Radiobutton(root, text=text, variable=projectType, value=mode, indicatoron=1)
		b.grid(row=row,column=column, columnspan = columnspan, sticky = sticky, padx=paddingx, pady=paddingy)
		row += 1
	return

def reset_fields():
	global projectType, artifactText, groupText
	projectType.set(typeDefault)
	artifactText.delete(1.0, END)
	groupText.delete(1.0, END)
	artifactText.insert(END, artifactDefault)
	groupText.insert(END, groupDefault)
	return

def create_project():
	global artifactText, groupText, projectType	
	artifact = artifactText.get("1.0",END)[0:-1]
	group =  groupText.get("1.0",END)[0:-1]
	project = projectType.get()
	print artifact,group
	if artifact != "" and group != "":		
		command = "mvn archetype:generate -DgroupId=" \
			+group+" -DartifactId="+artifact+ \
			" -DarchetypeArtifactId=maven-archetype-"+project + \
			" -DinteractiveMode=false ; cd "+artifact + \
			" ; mvn eclipse:eclipse"
		print command	
		p1 = subprocess.Popen(command, stdout=subprocess.PIPE, shell=True)	
		
#add_scrollable_text()
#add_button("button that takes the whole space", 2, 0, 3)
reset = add_button("Reset", 5, 1, width = 10)
reset.config(command=reset_fields)
create = add_button("Create", 5, 2)
create.config(command=create_project)

add_label("Maven Artifact", 0, 0)
add_label("Maven Group", 1, 0)


artifactText = add_text(0, 1, 2, defaultText = artifactDefault)
groupText = add_text(1, 1, 2, defaultText = groupDefault)


MODES = [
		("Simple Project", "quickstart"),
        ("Web Application", "webapp")]

projectType = StringVar()
add_label("Project Type", 3, 0)
add_radios(MODES, 3, 1)

mainloop()
