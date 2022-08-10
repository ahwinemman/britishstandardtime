# British Standard Time Application

Modelled this application after the 24 - hour format.

## Requirements
Application requirements include:
* JDK 1.11
* Maven 3.8.3

## Running Project
1. Go into project directory
2. Run **mvn clean package** to compile jar
3. Run the generated jar **british-standard-time-jar-with-dependencies.jar** with below command
   ```` cmd script ````
   `java -jar target/british-standard-time-jar-with-dependencies.jar`
4. Then you will get this prompt: `Enter the input time you want to convert to british spoken form:`
5. You can then type in the time you want to convert, for example: `1:00`
6. The program keeps running expecting new inputs until you end it.

### Sample Input
`14:45`

### Sample Output
`INFO: The time is: quarter to three`

## End the program
1. Ctrl-c will end the program
