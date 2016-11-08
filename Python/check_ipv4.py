#check IPv4 address

def ipv4_address(address):
	return address.count('.') == 3 and all([x.isdigit() and x==str(int(x)) and 0<=int(x)<=255 for x in address.split('.')])

# Or with regex:

from re import compile, match

REGEX = compile(r'((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.){4}$')
def ipv4_address_regex(address):
	return bool(match(REGEX, address + '.'))

valid = ["255.255.255.255", "10.20.30.40", "0.0.0.0", "127.0.0.1"]
invalid = ["127.0.0.0.1", "..255.255", "\n127.0.0.1", " 127.0.0.1 ", "10.256.30.40", "127.0.1"]
all_addresses = valid + invalid

for address in all_addresses:
	print address, ipv4_address_regex(address)
	print address, ipv4_address(address)

