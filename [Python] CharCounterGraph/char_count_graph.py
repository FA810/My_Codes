'''
Given a text, count characters occurrencies and print a bar graph on screen
scaled within a value of characters width.
'''

import operator

# the dictionary for the letter's counter
counter = {}

# defining the character for a graph bar unit
bar_unit = "#"

# defining maximum value of graph bar units
max_units_on_screen = 60

text = """Linux (pronounced Listenin or, less frequently, 
 is a Unix like and mostly POSIX compliant[7]Linux (pronounced Listeni
 or, less frequently, is a Unix like and mostly POSIX compl
 iant[7] computer operating system (OS) assembled under the model of free and
 open source software development and distribution. The defining component o
 f Linux is the Linux kernel,[8] an operating system kernel first released o
 n 5 October 1991 by Linus Torvalds.[9][10] The Free Software Foundation use
 s the name GNU/Linux to describe the operating system, which has led to som
 e controversy.[11][12] Linux was originally developed as a free operating s
 ystem for personal computers based on the Intel x86 architecture, but has s
 ince been ported to more computer hardware platforms than any other operati
 ng system.[13] Thanks to its dominance on smartphones, Android, which is bu
 ilt on top of the Linux kernel, has the largest installed base of all gener
 al purpose operating systems.[14] Linux, in its original form, is also the 
 leading operating system on servers and other big iron systems such as main
 frame computers and virtually all fastest supercomputers,[15][16] but is us
 ed on only around 1.6% of desktop computers[17][18] with Linux based Chrome
 OS taking about 5% of the overall and nearly 20% of the sub $300 notebook 
 sales.[19] Linux also runs on embedded systems, which are devices whose op
 erating system is typically built into the firmware and is highly tailored
 to the system; this includes smartphones and tablet computers running And
 roid and other Linux derivatives,[20] TiVo and similar DVR devices, netwo
 rk routers, facility automation controls, televisions,[21][22] video game
 consoles, and smartwatches.[23] The development of Linux is one of the m
 ost prominent examples of free and open source software collaboration. T
 he underlying source code may be used, modified and distributed commer
 cially or non commercially by anyone under the terms of its respective
 licenses, such as the GNU General Public License. Typically, Linux is p
 ackaged in a form known as a Linux distribution, for both desktop and s
 erver use. Some of the popular mainstream Linux distributions are Debia
 n, Ubuntu, Linux Mint, Fedora, openSUSE, Arch Linux and Gentoo, togethe
 r with commercial Red Hat Enterprise Linux and SUSE Linux Enterprise Se
 rver distributions. Linux distributions include the Linux kernel, suppo
 rting utilities and libraries, and usually a large amount of applicatio
 n software to fulfill the distribution's intended use.computer operatin
 g system (OS) assembled under the model of free and open source softwar
 e development and distribution. The defining component of Linux is the 
 Linux kernel,[8] an operating system kernel first released on 5 October
 1991 by Linus Torvalds.[9][10] The Free Software Foundation uses the n
 ame GNU/Linux to describe the operating system, which has led to some 
 controversy.[11][12] Linux was originally developed as a free 
 operating system for personal computers based on the Intel x86 archite
 cture, but has since been ported to more computer hardware platforms 
 than any other operating system.[13] Thanks to its dominance on smartp
 hones, Android, which is built on top of the Linux kernel, has the lar
 gest installed base of all general purpose operating systems.[14] Linu
 x, in its original form, is also the leading operating system on serve
 rs and other big iron systems such as mainframe computers and virtuall
 y all fastest supercomputers,[15][16] but is used on only around 1.6% 
 of desktop computers[17][18] with Linux based Chrome OS taking about 5
 % of the overall and nearly 20% of the sub $300 notebook sales.[19] Li
 nux also runs on embedded systems, which are devices whose operating s
 ystem is typically built into the firmware and is highly tailored to t
 he system; this includes smartphones and tablet computers running Andr
 oid and other Linux derivatives,[20] TiVo and similar DVR devices, net
 work routers, facility automation controls, televisions,[21][22] video
 game consoles, and smartwatches.[23] The development of Linux is one 
 of the most prominent examples of free and open source software 
 collaboration. The underlying source code may be used, modified and d
 istributed commercially or non commercially by anyone under the t
erms of its respective licenses, such as the GNU General Public Licen
se. Typically, Linux is packaged in a form known as a Linux distribut
ion, for both desktop and server use. Some of the popular mainstream 
Linux distributions are Debian, Ubuntu, Linux Mint, Fedora, openSUSE, Arch Li
nux and Gentoo, together with commercial Red Hat Enterprise Linux and SUSE Li
nux Enterprise Serverdistributions. Linux distributions include the Linux ker
nel, supporting utilities and libraries, and usually a large amount of applic
ation to fulfill the distribution's intended use."""

'''
Given a list of tuples (key, value) and a maximum value, print the key 
and the value expressed as a scaled bar graph
'''
def printGraph(sorted_counter, max_counter_value):
	for i in sorted_counter:
		# calculate the scaled value
		total = (max_units_on_screen * i[1]) / max_counter_value
		print i[0]+":",
		row = ""
		for j in range(0,total):
			row += bar_unit
		print row


# scanning text and counting
for i in text:
	if i.isalpha():
		i = i.lower()
		if(i not in counter):
			counter[i] = 1
		else:
			counter[i] += 1

# maximum value of occurrencies
max_counter_value = max(counter.values())

# sorting ascending by key (alphabetic order)
print "Printing letters sorted by key:"
sorted_counter = sorted(counter.items(), key=operator.itemgetter(0))
printGraph(sorted_counter, max_counter_value)
	
# sorting descending by value
print "\nPrinting letters sorted descendingly by value:"
sorted_counter = sorted(counter.items(), key=operator.itemgetter(1), reverse=True)
printGraph(sorted_counter, max_counter_value)

