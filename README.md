## FXCM2MQ4

Takes CSV export file from _FXCM Simple Export Tool_. Converts it to the _Meta Trader 4_ compatible
CSV import file.
 
Example input format: 

```
"Date","Time","Open","High","Low","Close","Total Ticks"
12/02/2018,23.00.00,25580,1,25924,6,25580,1,25914,6,122
```
 
output format:

```
2018.02.12,23:00,25580.10,25924.60,25580.10,25914.60,122
```

NOTE: input file must contain header, too. Place the import file to the
project root folder. Output file will be in the footer too.
 
### How to run

- Clone/download this project to your computer
- Get the input file from FXCM
- Copy it to the project root folder
- Change the name in the _inputFile_ variable
- Execute this Java program at the project root:

```
javac  src/fi/tr/f2m/Converter.java
java -cp src fi.tr.f2m.Converter
```

Enjoy the output file in the root folder!
