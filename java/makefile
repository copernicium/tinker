all: hello Simple_maze PiEstimator


PiEstimator: PiEstimator.java
	javac PiEstimator.java

hello: hello.java
	javac hello.java

Simple_maze: Simple_maze.java
	javac Simple_maze.java

.PHONY: clean
clean:
	rm -rf hello.class; rm -rf Simple_maze.class; rm -rf PiEstimator.class;
