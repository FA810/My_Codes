#!/bin/bash
echo "Comparing..."
echo "-  In the old but NOT in the new:"
awk 'FNR==NR {a[$0]++; next} !a[$0]' ./friendList.txt ./old/friendList.txt
echo "-  In the new but NOT in the old:"
awk 'FNR==NR {a[$0]++; next} !a[$0]' ./old/friendList.txt ./friendList.txt
