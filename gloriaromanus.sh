# Dependencies
jfx="javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web"
lib="--module-path ./lib --add-modules=$jfx"

# Directories
gloria="src/unsw/gloriaromanus/"
modules="src/unsw/gloriaromanus/**/"

# Compile java files
javac $lib $gloria*.java $modules*.java -cp "./lib/*"

# Run java main
main="unsw.gloriaromanus.GloriaRomanusLauncher"
java $lib -cp ./lib/*:src/ $main "$@"

# Remove class files
rm $test*.class $gloria*.class $modules*.class
