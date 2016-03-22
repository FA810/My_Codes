var fs = require("fs");

// now Synchronous read
var data = fs.readFileSync('my.py');

console.log(data.toString());

console.log("Program Ended");

// now Asynchronous read
fs.readFile('my.py', function (err, data) {
    if (err) return console.error(err);
    console.log(data.toString());
});

console.log("Program Ended");
