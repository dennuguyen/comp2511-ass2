javac unsw/gloriaromanus/*.java -cp "../lib/*"
java -cp ../lib/*: unsw.gloriaromanus.GloriaRomanusApplication "$@"
rm unsw/gloriaromanus/*.class
