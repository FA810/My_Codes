HOW TO LAUNCH

1. In Linux command line, type:

server.sh	- to launch a server instance (first and only one!)
client.sh	- can launch as many clients as desired

*notice: 	the server program will not start if you have the lock 
			on the database from other programs.

LOG FILE
Check the file: logfile.txt

STATISTICS
There is a view in the database called Statistics.
It takes the times collected in the other table, Times.


-----------------------------------------------------------------
TASK DESCRIPTION:
Implement client and server using Java (Java SE7):
Server offers service for player wallet and wallet is preserved in database (hsqldb).
Client itself is a server that offers gameplay logic.
Specific gameplay may not be implemented, client can just generate random balance updates.
Specific communication protocol between server is not specified (custom protocol can be invented).
Server must write proper log information, where at least IN/OUT per player must be grep’able.
Server must collect statistics about about processing of commands.
After every minute number of processed queries should be stored, also min, max and average query time should be stored.

The code itself should be as clear as possible; do not provide comments that do not add to the comprehensibility of the code. Awkward or complex code should have descriptive comments.

Commands between servers:
client->server: username, transaction id, balance change
server->client: transaction id, error code, balance version, balance change,
balance after change
Database structure
PLAYER(USERNAME, BALANCE_VERSION, BALANCE)
----------------------------------------------------------------------
