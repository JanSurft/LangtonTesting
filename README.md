# LangtonTesting
Test framework for Langton program

With this testing framework you can test your langton program an example test case file is also included which is fully working.

What can this framework do? It tests your Langton program in a similar manner as the Praktomat tests do.

The Test programm feed input commands to your Langton program and asserts the given expected output against your output.

Here a short example testfile content that simulates the "Fundamental test with ordinary ant (default movement rules):

//theese commentaries are allowed
/* as are theese */
// the start test board
board={
0000
0*00
0A00
0000}

// the arguemnts to start the program, there are none so default
start=<NONE>,<NONE>

input="print"
output={
0000
0*00
0a00
0000}

input="position A"
output="2,1"

input="position a"
output="2,1"

input="field 2,1"
output="a"

input="direction a"
output="N"

input="ant"
output="a"

// here an example if no output is expected
input="create e,0,0"
output=<NONE>

input="ant"
output="a,e"

input="direction e"
output="S"

input="move 1"
output=<NONE>

input="direction e"
ouput="O"

input="quit"
ouput=<NONE>
