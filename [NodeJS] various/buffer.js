// write a buffer
buf = new Buffer(256);
len = buf.write("Simply Easy Learning");

console.log("Octets written : "+  len);

// read a buffer
buf = new Buffer(26);
for (var i = 0 ; i < 26 ; i++) {
  buf[i] = i + 97;
}

console.log( buf.toString('ascii'));       // outputs: abcdefghijklmnopqrstuvwxyz
console.log( buf.toString('ascii',0,5));   // outputs: abcde
console.log( buf.toString('utf8',0,5));    // outputs: abcde
console.log( buf.toString(undefined,0,5)); // encoding defaults to 'utf8', outputs abcde

// convert a buffer in json
var buf = new Buffer('Simply Easy Learning');
var json = buf.toJSON(buf);

console.log(json);

// concatenate buffers
var buffer1 = new Buffer('TutorialsPoint ');
var buffer2 = new Buffer('Simply Easy Learning');
var buffer3 = Buffer.concat([buffer1,buffer2]);
console.log("buffer3 content: " + buffer3.toString());

// compare buffers
var buffer11 = new Buffer('ABC');
var buffer12 = new Buffer('ABCD');
// compare available only in node > 0.12
//var result = buffer11.compare(buffer12);
var result = Buffer.compare(buffer11,buffer12);
//var result = -1

if(result < 0) {
   console.log(buffer11 +" comes before " + buffer12);
}else if(result == 0){
   console.log(buffer11 +" is same as " + buffer12);
}else {
   console.log(buffer11 +" comes after " + buffer12);
}

// copy buffers
var buffer1 = new Buffer('ABCDE');
var buffer2 = new Buffer(3);
buffer1.copy(buffer2);
console.log("buffer2 content: " + buffer2.toString());

// slice buffers
var buffer1 = new Buffer('TutorialsPoint');
var buffer2 = buffer1.slice(0,9);
console.log("buffer2 content: " + buffer2.toString());
