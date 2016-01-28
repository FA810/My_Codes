from gi.repository import Gtk
import os
import subprocess

default_artifact = ""
default_group = "com.fabio"
type = "quickstart"
file_name = "window.glade"

def reset_fields(self):
	global type
	builder.get_object("entry1").set_text(default_artifact)
	builder.get_object("entry2").set_text(default_group)
	radio2.set_active(True)
	type = "quickstart"

def on_button_toggled(self, button, name):
	global type	
	if button.get_active() and name == "1":
		type = "webapp"
	else:
		type = "quickstart"	

def create_project(self):
	if builder.get_object("entry1").get_text() != "" and \
	   builder.get_object("entry2").get_text() != "":
		command = "mvn archetype:generate -DgroupId=" \
			+entry2.get_text()+" -DartifactId="+entry1.get_text()+ \
			" -DarchetypeArtifactId=maven-archetype-"+type+ \
			" -DinteractiveMode=false ; cd "+entry1.get_text()+ \
			" ; mvn eclipse:eclipse"
		p1 = subprocess.Popen(command, stdout=subprocess.PIPE, shell=True)

builder = Gtk.Builder()
builder.add_from_file(file_name)

radio1 = builder.get_object("radiobutton1")
radio2 = builder.get_object("radiobutton2")
reset  = builder.get_object("button1")
create = builder.get_object("button2")
entry1 = builder.get_object("entry1")
entry2 = builder.get_object("entry2")

window = builder.get_object("window1")

window.connect("delete-event", Gtk.main_quit) # no parenthesis; you pass functions to connect

reset.connect("pressed", reset_fields)
create.connect("pressed", create_project)

radio1.connect("toggled", on_button_toggled, radio1, "1")
radio2.connect("toggled", on_button_toggled, radio2, "2")
window.show_all()

reset_fields(builder)

Gtk.main()

# just a class for changing directory
'''
class cd:
    """Context manager for changing the current working directory"""
    def __init__(self, newPath):
        self.newPath = os.path.expanduser(newPath)

    def __enter__(self):
        self.savedPath = os.getcwd()
        os.chdir(self.newPath)

    def __exit__(self, etype, value, traceback):
        os.chdir(self.savedPath)
'''


