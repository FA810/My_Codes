#!/bin/bash
echo $1
sed -n '/Search.*riends/,$p' $1 > tmp3.html
sed -n '/More About/q;p' tmp3.html > $1_friendList.txt
rm -f tmp3.html
# this is for showing the idle ones
#sed -i.bak '/<#>/d' $1_friendList.txt
sed -i.bak '/Add Friend/d' $1_friendList.txt
sed -i.bak '/Close Friend/d' $1_friendList.txt
# substitute inserting a new line
sed -i.bak  's/<https:/\n<https:/' $1_friendList.txt
sed -i.bak '/Works /d' $1_friendList.txt
sed -i.bak '/ at /d' $1_friendList.txt
sed -i.bak '/<https:/d' $1_friendList.txt
sed -i.bak '/ friends/d' $1_friendList.txt
sed -i.bak '/ o /d' $1_friendList.txt
sed -i.bak '/Search.*riends/d' $1_friendList.txt
sed -i.bak '/_\/Friend/d' $1_friendList.txt
sed -i.bak '/_Acquaintance/d' $1_friendList.txt
sed -i.bak '/_Friend/d' $1_friendList.txt
# remove lines with spaces and a star
sed -i.bak '/  \*/d' $1_friendList.txt
# remove empty lines
sed -i.bak '/^\s*$/d' $1_friendList.txt
sed -i.bak 's/^    //' $1_friendList.txt
sed -i.bak '/^    /d' $1_friendList.txt
sed -i.bak '/^Search Friends/d' $1_friendList.txt
rm -f $1_friendList.txt.bak
# get a sorted version
sort $1_friendList.txt > $1_friendList_sorted.txt
cp $1_friendList.txt friendList.txt
mv -f $1_friendList.txt .
mv -f $1_friendList_sorted.txt .

