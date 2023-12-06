# British Standard Time Application

Modelled this application after the 24 - hour format.

## Requirements
Application requirements include:
* JDK 1.11
* Maven 3.8.3+

To install Java 11 on Mac, you can use either of the below links:
- https://medium.com/@kirebyte/using-homebrew-to-install-java-jdk11-on-macos-2021-4a90aa276f1c
- https://medium.com/macoclock/using-homebrew-to-install-java-jdk11-on-macos-44b30f497b38
 
To install Java 11 on Windows, you can use the link below
- https://stackoverflow.com/questions/52511778/how-to-install-openjdk-11-on-windows

To install Maven 3.8.3+ on Mac or Windows, you can use the link below
- https://www.baeldung.com/install-maven-on-windows-linux-mac

## Running Project
1. Go into project directory
2. Run **mvn clean package** to compile jar
3. Run the generated jar **british-standard-time-jar-with-dependencies.jar** with below command
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
