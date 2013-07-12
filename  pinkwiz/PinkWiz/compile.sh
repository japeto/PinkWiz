#!/bin/bash
# Script de compilacion de la aplicacion
# Jefferson Amado Peña Torres
echo "Inicia compilacion...."
javac src/Principal.java src/basic/*.java src/conversion/*.java src/convulsion/*.java src/geometric/*.java src/image/*.java src/morphological/*.java src/*.java -d bin/
echo "Fin compilacion :)"
echo "Creando ejecutable...."
cd bin/
echo Main-Class: Principal >manifest.txt
jar cvfm PinkWiz.jar  manifest.txt  basic/*.class conversion/*.class convulsion/*.class geometric/*.class image/*.class morphological/*.class *.class
rm -rfv  manifest.txt  ./*.class basic/ conversion/  convulsion/ geometric/ image/ morphological/
mv PinkWiz.jar ../
cd ../

echo "Fin ejecutable :)"
echo "Jefferson Amado Peña Torres <jeffersonamado@gmail.com>"



echo 'Genero documentacion? si o no'
read DOC
if [ $DOC = 'Si' ];
then
echo "Documentacion...."
javadoc src/Principal.java src/basic/*.java src/conversion/*.java src/convulsion/*.java src/geometric/*.java src/image/*.java src/morphological/*.java src/*.java -d doc/
echo "Fin Documentacion ..."
echo "Para ver <doc/index.html>"
exit 0
fi

