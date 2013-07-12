echo off
clear
color 0a
title PinkWiz compilacion
echo Inicia compilacion....
javac src\Principal.java src\basic\*.java src\conversion\*.java src\convulsion\*.java src\geometric\*.java src\image\*.java src\morphological\*.java c\*.java -d bin\
echo Fin compilacion :)
echo Creando ejecutable....
cd bin\
echo Main-Class: Principal >manifest.txt
jar cvfm PinkWiz.jar  manifest.txt  basic\*.class conversion\*.class convulsion\*.class geometric\*.class image\*.class morphological\*.class *.class
del  manifest.txt  .\*.class basic\ conversion\  convulsion\ geometric\ image\ morphological\
xcopy PinkWiz.jar ..\
cd ..\

echo Fin ejecutable :)
echo Jefferson Amado Peña Torres <jeffersonamado@gmail.com>

set /p choice="Genero documentacion? si(1) o no(0)"
if "%choice%"=="1" goto shutdown
echo Documentacion....
javadoc src\Principal.java src\basic\*.java src\conversion\*.java src\convulsion\*.java src\geometric\*.java src\image\*.java src\morphological\*.java src\*.java -d doc\
echo Fin Documentacion ...
echo Para ver <doc\index.html>

