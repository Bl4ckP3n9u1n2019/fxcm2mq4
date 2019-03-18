## FXCM2MQ¤

Takes CSV export file from FXCM Simple Export Tool. Returns MQ4 compatible
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

- Clone/download the project to your computer
- Get the input file from FXCM
- Copy it to the project root folder
- Change the name in the inputFile variable
- Execute this Java program
- Enjoy the output file in the root folder
