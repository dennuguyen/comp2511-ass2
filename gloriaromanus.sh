# Dependencies
jfx="javafx.base,javafx.controls,javafx.fxml,javafx.graphics,javafx.media,javafx.swing,javafx.web"
modules="--module-path ./lib --add-modules=$jfx"

# Directories
test="src/test/"
gloria="src/unsw/gloriaromanus/"
component="src/unsw/gloriaromanus/component/"
util="src/unsw/gloriaromanus/util/"

# Compile java files
javac $modules $test*.java $gloria*.java $component*.java $util*.java -cp "./lib/*"

# Run java main
if [ "$1" == "--test" ]; then
    echo ""
    echo "======== TEST MODE ========"
    main="test.GloriaRomanusTest"
else
    main="unsw.gloriaromanus.GloriaRomanusLauncher"
fi
java $modules -cp ./lib/*:src/ $main "$@"

# Remove class files
rm $test*.class $gloria*.class $component*.class $util*.class
