package game;



import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;

import pieces.Piece;
import shelf.MovableShelf;
import shelf.Shelf;
import window.Window2;

public class Game{
	
	private Window2 window = new Window2();
	private Shelf s = new Shelf();
	private Piece piece;
	private MovableShelf movable1=new MovableShelf(s.getBox(1, 1, 1), 2, s);
	private MovableShelf movable2=new MovableShelf(s.getBox(1, 4, 1), 2, s);
	private MovableShelf movable3=new MovableShelf(s.getBox(8, 1, 1), 6, s);
	private MovableShelf movable4=new MovableShelf(s.getBox(8, 4, 1), 6, s);
	public void play(){
		piece = s.getPiece(0,1,2);
		piece.possibleMovement(0, 1, 2, s, piece.getDirections());	}
}