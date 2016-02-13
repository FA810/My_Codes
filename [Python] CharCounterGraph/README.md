This code is available also in Java:
https://github.com/FA810/My_Codes/tree/master/%5BJava%5D%20CharCounterGraph

## Summary
Given a text, count characters occurrencies and print a bar graph on screen
scaled within a value of characters width.

For example, given the text:
```
Linux (pronounced Listenin or, less frequently, is a Unix like and mostly 
POSIX compliant[7]Linux (pronounced Listenior, less frequently, is a Unix 
like and mostly POSIX compliant[7] computer operating system (OS) assembled 
under the model of free andopen source software development and distribution. 
The defining component of Linux is the Linux kernel,[8] an operating system 
kernel first released on 5 October 1991 by Linus Torvalds.[9][10] The Free 
Software Foundation uses the name GNU/Linux to describe the operating system, 
which has led to some controversy.[11][12] Linux was originally developed as 
a free operating system for personal computers based on the Intel x86 
architecture, but has since been ported to more computer hardware platforms 
than any other operating system.[13] Thanks to its dominance on smartphones, 
Android, which is built on top of the Linux kernel, has the largest installed 
base of all general purpose operating systems.[14] Linux, in its original form, 
is also the leading operating system on servers and other big iron systems 
such as mainframe computers and virtually all fastest supercomputers,[15][16] 
but is used on only around 1.6% of desktop computers[17][18] with Linux based 
ChromeOS taking about 5% of the overall and nearly 20% of the sub $300 notebook 
sales.[19] Linux also runs on embedded systems, which are devices whose 
operating system is typically built into the firmware and is highly tailored 
to the system; this includes smartphones and tablet computers running Android 
and other Linux derivatives,[20] TiVo and similar DVR devices, network routers, 
facility automation controls, televisions,[21][22] video gameconsoles, and 
smartwatches.[23] The development of Linux is one of the most prominent 
examples of free and open source software collaboration. The underlying source 
code may be used, modified and distributed commercially or non commercially 
by anyone under the terms of its respectivelicenses, such as the GNU General 
Public License. Typically, Linux is packaged in a form known as a Linux 
distribution, for both desktop and server use. Some of the popular mainstream 
Linux distributions are Debian, Ubuntu, Linux Mint, Fedora, openSUSE, Arch 
Linux and Gentoo, together with commercial Red Hat Enterprise Linux and SUSE 
Linux Enterprise Server distributions. Linux distributions include the Linux 
kernel, supporting utilities and libraries, and usually a large amount of 
application software to fulfill the distribution's intended use.computer 
operating system (OS) assembled under the model of free and open source 
software development and distribution. The defining component of Linux is the 
Linux kernel,[8] an operating system kernel first released on 5 October1991 
by Linus Torvalds.[9][10] The Free Software Foundation uses the name GNU/Linux 
to describe the operating system, which has led to some controversy.[11][12] 
Linux was originally developed as a free operating system for personal 
computers based on the Intel x86 architecture, but has since been ported to 
more computer hardware platforms than any other operating system.[13] Thanks 
to its dominance on smartphones, Android, which is built on top of the Linux 
kernel, has the largest installed base of all general purpose operating 
systems.[14] Linux, in its original form, is also the leading operating system 
on servers and other big iron systems such as mainframe computers and virtually 
all fastest supercomputers,[15][16] but is used on only around 1.6% of desktop 
computers[17][18] with Linux based Chrome OS taking about 5% of the overall and 
nearly 20% of the sub $300 notebook sales.[19] Linux also runs on embedded 
systems, which are devices whose operating system is typically built into the 
firmware and is highly tailored to the system; this includes smartphones and 
tablet computers running Android and other Linux derivatives,[20] TiVo and 
similar DVR devices, network routers, facility automation controls, 
televisions,[21][22] videogame consoles, and smartwatches.[23] The development 
of Linux is one of the most prominent examples of free and open source 
software collaboration. The underlying source code may be used, modified and 
distributed commercially or non commercially by anyone under the terms of its 
respective licenses, such as the GNU General Public License. Typically, Linux 
is packaged in a form known as a Linux distribution, for both desktop and 
server use. Some of the popular mainstream Linux distributions are Debian, 
Ubuntu, Linux Mint, Fedora, openSUSE, Arch Linux and Gentoo, together with 
commercial Red Hat Enterprise Linux and SUSE Linux Enterprise Server 
distributions. Linux distributions include the Linux kernel, supporting 
utilities and libraries, and usually a large amount of application to fulfill 
the distribution's intended use.
```
The output will be:
```
Printing letters sorted by key:
a: #################################
b: ########
c: #############
d: #####################
e: ############################################################
f: ##########
g: #######
h: ##############
i: ########################################
k: ###
l: ###########################
m: ################
n: #########################################
o: ########################################
p: #############
q: 
r: #################################
s: #########################################
t: #########################################
u: ######################
v: #####
w: ####
x: ######
y: ########

Printing letters sorted descendingly by value:
e: ############################################################
n: #########################################
s: #########################################
t: #########################################
o: ########################################
i: ########################################
r: #################################
a: #################################
l: ###########################
u: ######################
d: #####################
m: ################
h: ##############
c: #############
p: #############
f: ##########
b: ########
y: ########
g: #######
x: ######
v: #####
w: ####
k: ###
q: 

```
