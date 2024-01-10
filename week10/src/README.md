 # README for 10.3 - Working with Object I/O Streams and Serializing of Objects
 
### Step 1:
Run the `PasswordWrite.java` to write password
 object into  1234.ser file
  
 `$ javac PasswordWrite.java`
 
 `$ java PasswordWrite 1234.ser`

### Step 2:
Run the `PasswordRead.java` to read the password object 
from 1234.ser file

`$ javac PasswordRead.java    `

`$ java PasswordRead 1234.ser `

2 possible output:

Output 1:
**The password has not been tempered with!
Password: password**

Output 2:
**The password has been tempered with!**

### Step 3:
Run the `Modify.java` to modify 1234.ser file:

`$ javac Modify.java`

`$ java Modify 1234.ser`

`$ java PasswordRead 1234.ser`

### Step 4:
Run the `PasswordRead.java` to read the password object 
from 1234.ser file

`$ javac PasswordRead.java    `

`$ java PasswordRead 1234.ser `

2 possible output:

Output 1:
**The password has not been tempered with! Password: password**

Output 2:
**The password has been tempered with!**


