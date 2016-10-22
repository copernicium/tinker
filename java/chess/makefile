all: ChessBoard ChessPosition ChessPiece Pawn Game MyAssert

MyAssert: MyAssert.java
	javac MyAssert.java

ChessPiece: ChessPiece.java
	javac ChessPiece.java

Pawn: Pawn.java
	javac Pawn.java
	
ChessPosition: ChessPosition.java
	javac ChessPosition.java

ChessBoard: ChessBoard.java
	javac ChessBoard.java

Game: Game.java
	javac Game.java

.PHONY: clean
clean:
	rm -rf ChessBoard.class; rm -rf ChessPosition.class; rm -rf ChessPiece.class; rm -rf Pawn.class; rm -rf Game.class; rm -rf MyAssert.class; 