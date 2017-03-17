all: ZorkRunner.class

ZorkRunner.class: ZorkRunner.java
	javac -O ZorkRunner.java Game.java Treasure.java Room.java ../../../Util/Util.java

.PHONY: clean
clean:
	find -name \*.class |xargs rm -f;
	rm -f ../../../Util/Util.class;
