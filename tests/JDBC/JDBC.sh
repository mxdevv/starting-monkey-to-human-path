CLASSPATH=".:../../src/:/usr/share/jdbc-mysql/lib/jdbc-mysql.jar"
NAME="JDBC"

echo -e "\e[32mrun $NAME\e[0m"
java -cp $CLASSPATH $NAME
echo ""
