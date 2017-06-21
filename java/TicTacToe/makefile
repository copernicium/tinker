all: Game.class

Game.class: Game.java
	javac Game.java Board.java Mark.java ../Util/Util.java ../Util/Point.java

.PHONY: clean
clean:
	find -name \*.class |xargs rm -f;
