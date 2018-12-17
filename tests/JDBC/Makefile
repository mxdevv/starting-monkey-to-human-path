NAME=JDBC
CLASSPATH=.:../../src/
SOURCEPATH=$(CLASSPATH)
LIBS=:/usr/share/jdbc-mysql/lib/jdbc-mysql.jar

all:
	javac -classpath $(CLASSPATH)$(LIBS) \
		-sourcepath $(SOURCEPATH) $(NAME).java
clean:
	rm $(NAME).class
