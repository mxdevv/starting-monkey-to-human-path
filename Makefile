all:
	@echo "make: This project isn't lib or app, therefore 'make clean only'"
	@echo "      read README.MD for more"
clean:
	find ./ -type f -name '*.class' -delete
