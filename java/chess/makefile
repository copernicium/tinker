all: ChessBoard.class ChessPieces.java ChessPosition.class ChessPiece.class Pawn.class Game.class MySystem.class Bishop.class King.class Knight.class Queen.class Rook.class 

Queen.class: Queen.java
	javac Queen.java ChessPieces.java ChessBoard.java Pawn.java Rook.java King.java Knight.java Bishop.java MySystem.java ChessPiece.java ChessPosition.java

Rook.class: Rook.java
	javac Rook.java Queen.java ChessPieces.java ChessBoard.java Pawn.java King.java Knight.java Bishop.java MySystem.java ChessPiece.java ChessPosition.java

Knight.class: Knight.java
	javac Knight.java Queen.java ChessBoard.java ChessPieces.java  Pawn.java Rook.java King.java Bishop.java MySystem.java ChessPiece.java ChessPosition.java

King.class: King.java
	javac King.java Queen.java ChessBoard.java ChessPieces.java awn.java Rook.java Knight.java Bishop.java MySystem.java ChessPiece.java ChessPosition.java
	
Bishop.class: Bishop.java
	javac Bishop.java Queen.java ChessBoard.java ChessPieces.java Pawn.java Rook.java King.java Knight.java MySystem.java ChessPiece.java ChessPosition.java

MySystem.class: MySystem.java
	javac MySystem.java

ChessPiece.class: ChessPiece.java
	javac ChessPiece.java ChessPosition.java MySystem.java

Pawn.class: Pawn.java
	javac Pawn.java Queen.java ChessBoard.java ChessPieces.java  Rook.java King.java Knight.java Bishop.java MySystem.java ChessPiece.java ChessPosition.java
	
ChessPosition.class: ChessPosition.java
	javac ChessPosition.java

ChessBoard.class: ChessBoard.java
	javac ChessBoard.java Pawn.java Queen.java ChessPieces.java Rook.java King.java Knight.java Bishop.java MySystem.java ChessPiece.java ChessPosition.java

Game.class: Game.java
	javac -O Game.java ChessBoard.java Queen.java ChessPieces.java Pawn.java Rook.java King.java Knight.java Bishop.java MySystem.java ChessPiece.java ChessPosition.java
	
ChessPieces.class: ChessPieces.java 
	javac ChessPieces.java ChessBoard.java Queen.java Game.java Pawn.java Rook.java King.java Knight.java Bishop.java MySystem.java ChessPiece.java ChessPosition.java
.PHONY: clean
clean:
	find -name \*.class |xargs rm;
