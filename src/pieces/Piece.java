package pieces;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import shelf.Box;
import shelf.Shelf;

public abstract class Piece{
	private boolean color;
	private List<Box> possibleMovement;
	private boolean isMove = false;
	private List<Direction> directions=new ArrayList<Direction>();
	private int i=0,j=0,k=0;
	protected Image picture;

	public Piece(boolean color,int i, int j, int k){
		this.color = color;
		this.setX(i);
		this.setY(j);
		this.setZ(k);
		this.setPossibleMovement(new ArrayList<Box>());
	}
	
	/*this function moves the piece*/
	public void PieceMovement(Box cbegin, Box cfinal, Shelf s) {
		
		Piece p=cbegin.getPiece();
		if(cfinal.getColor()==Color.BLUE || cfinal.getColor()==Color.RED) {
			if(cfinal.getPiece() == null){
				cbegin.setPiece(null);
				cfinal.setPiece(p);
				p.setX(cfinal.getX());
				p.setY(cfinal.getY());
				p.setZ(cfinal.getZ());
			}
			
			else{
				s.getTeam(cfinal.getPiece().getColor()).remove(cfinal.getPiece());
				cfinal.setPiece(p);
				cbegin.setPiece(null);
				p.setX(cfinal.getX());
				p.setY(cfinal.getY());
				p.setZ(cfinal.getZ());
			}
			this.isMove=true;
		}
	}
	
	
	
	public boolean getColor(){
		return this.color;
	}
	public void setColor(boolean color){
		this.color = color;
	}
	

	public List<Box> getPossibleMovement() {
		return possibleMovement;
	}
	public void setPossibleMovement(List<Box> possibleMovement) {
		this.possibleMovement = possibleMovement;
	}
	public void addPossibleMovement(Box b){
		this.possibleMovement.add(b);
	}
	public void removePossibleMovement(Box b){
		this.possibleMovement.remove(b);
	}
	/*this function create a list of possible movement*/
	public abstract void possibleMovement(int i, int j, int k, Shelf s, List<Direction> d);
	public boolean isMove() {
		return isMove;
	}
	public List<Direction> getDirections(){
		return this.directions;
	}
	public void setDirections(List<Direction> directions){
		this.directions=directions;
	}


	public int getX() {
		return i;
	}


	public void setX(int i) {
		this.i = i;
	}


	public int getY() {
		return j;
	}


	public void setY(int j) {
		this.j = j;
	}


	public int getZ() {
		return k;
	}


	public void setZ(int k) {
		this.k = k;
	}


	public Image getPicture() {
		return picture;
	}


	public void setPicture(Image picture) {
		this.picture = picture;
	}
}