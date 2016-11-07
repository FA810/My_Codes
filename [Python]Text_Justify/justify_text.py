'''
The task here is to emulate text justification in monospace font. 
A single-lined text and the expected justification width are provided as input. 
The longest word will never be greater than this width.

Here are the rules:
- Use spaces to fill in the gaps between words.
- Each line should contain as many words as possible.
- Use '\n' to separate lines.
- Gap between words can't differ by more than one space.
- Lines should end with a word not a space.
- '\n' is not included in the length of a line.
- Large gaps go first, then smaller ones: 'Lorem---ipsum---dolor--sit--amet' (3, 3, 2, 2 spaces).
- Last line should not be justified, use only one space between words.
- Last line should not contain '\n'
- Strings with one word do not need gaps ('somelongword\n').

'''

'''
Example with width=30:

Lorem  ipsum  dolor  sit amet,
consectetur  adipiscing  elit.
Vestibulum    sagittis   dolor
mauris,  at  elementum  ligula
tempor  eget.  In quis rhoncus
nunc,  at  aliquet orci. Fusce
at   dolor   sit   amet  felis
suscipit   tristique.   Nam  a
imperdiet   tellus.  Nulla  eu
vestibulum    urna.    Vivamus
tincidunt  suscipit  enim, nec
ultrices   nisi  volutpat  ac.
Maecenas   sit   amet  lacinia
arcu,  non dictum justo. Donec
sed  quam  vel  risus faucibus
euismod.  Suspendisse  rhoncus
rhoncus  felis  at  fermentum.
Donec lorem magna, ultricies a
nunc    sit    amet,   blandit
fringilla  nunc. In vestibulum
velit    ac    felis   rhoncus
pellentesque. Mauris at tellus
enim.  Aliquam eleifend tempus
dapibus. Pellentesque commodo,
nisi    sit   amet   hendrerit
fringilla,   ante  odio  porta
lacus,   ut   elementum  justo
nulla et dolor.
'''

text = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum sagittis dolor mauris, at elementum ligula tempor eget. In quis rhoncus nunc, at aliquet orci. Fusce at dolor sit amet felis suscipit tristique. Nam a imperdiet tellus. Nulla eu vestibulum urna. Vivamus tincidunt suscipit enim, nec ultrices nisi volutpat ac. Maecenas sit amet lacinia arcu, non dictum justo. Donec sed quam vel risus faucibus euismod. Suspendisse rhoncus rhoncus felis at fermentum. Donec lorem magna, ultricies a nunc sit amet, blandit fringilla nunc. In vestibulum velit ac felis rhoncus pellentesque. Mauris at tellus enim. Aliquam eleifend tempus dapibus. Pellentesque commodo, nisi sit amet hendrerit fringilla, ante odio porta lacus, ut elementum justo nulla et dolor.'

# this function gives us the correct spacing in a list, with any character.
def distributor(n,t,car):
	return [(int(x/t)+1)*str(car) for x in range(n-t,n)][::-1]

# this functon merges two lists, we'll ned it for merging words and spaces.	
def merge_lists(list1,list2):
	num = min(len(list1), len(list2))
	result = [None]*(num*2)
	result[::2] = list1[:num]
	result[1::2] = list2[:num]
	result.extend(list1[num:])
	result.extend(list2[num:])
	return result
	
# this is the main function, provided text and max width of the line.
def justify(text, width):
	words = text.split()
	final = []	
	while len(words) > 0:
		line = [words.pop(0)]
		while len(words) > 0 and len(' '.join(line)+' '+words[0]) <= width :
			line.append(words.pop(0))
		if len(words) > 0:	
			final.append(merge_lists(line, distributor(width-len(''.join(line)),len(line)-1,' ')))
		else:
			final.append(' '.join(line))
	return '\n'.join([''.join(i) for i in final[:-1]])+'\n'+(final[-1])
	
# let's try with widths 50, 70, 90	
for width in range(50,91,20):
	print justify(text, width)
