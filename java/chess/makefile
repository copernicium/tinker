all: ChessBoard.class ChessPosition.class ChessPiece.class Pawn.class Game.class MySystem.class

MySystem.class: MySystem.java
	javac MySystem.java

ChessPiece.class: ChessPiece.java
	javac ChessPiece.java

Pawn.class: Pawn.java
	javac Pawn.java
	
ChessPosition.class: ChessPosition.java
	javac ChessPosition.java

ChessBoard.class: ChessBoard.java
	javac ChessBoard.java

Game.class: Game.java
	javac Game.java

.PHONY: clean
clean:
	find -name \*.class |xargs rm;