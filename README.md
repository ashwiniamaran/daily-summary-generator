# daily-summary-generator

## Architecture
Spring boot project enabled with Lombok. Uses OpenCSV third party library to write into CSV files.

## Requirement
- Java JDK 8
- Maven

## How the application works?
- It reads a fixed width text file from the given 'input.source' property found in the application.properties file.
- It then parses the files contents.
- It then writes the parsed contents into a CSV file at the destination in the 'output.destination' property found in the application.properties file.

## Run
The project needs the source and destination folders for the input.txt and output.csv
- Edit the 'input.source' property to the location where the input.txt is located.
- Edit the 'output.destination' property to the location where you would like the CSV file to be created.
- To run with maven command: mvn spring-boot:run
