@echo off

REM Kicsomagolás
tar -xf logarlec.zip

REM Java fájlok fordítása
javac logarlec/*.java

REM Jar fájl készítése az osztályfájlokból
jar cfm logar.jar MANIFEST.MF logarlec/*.class

REM Teszt mappában lévő Java fájlok fordítása
javac teszt/*.java

REM Teszt jar fájl készítése az osztályfájlokból
jar cfm teszt.jar MANIFEST_TEST.MF teszt/*.class

REM Teszt jar fájl futtatása
java -jar teszt.jar

pause
