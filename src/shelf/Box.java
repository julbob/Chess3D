package shelf;

import java.awt.Color;

import javax.swing.JPanel;

import pieces.Piece;

public class Box extends JPanel{

	private Color color;
	private boolean isExist, isLandmark;
	private Piece piece;
	private int x,y,z;

	public Box(Color color, boolean isExist, boolean isLandmark, int i, int j, int k){
		this.color = color;
		this.isLandmark=isLandmark;
		this.isExist = isExist;
		this.piece = null;
		this.x=i;
		this.y=j;
		this.z=k;
	}
	public Box(Color color, boolean isExist,boolean isLandmark, Piece p, int i, int j, int k){
		this.color = color;
		this.isExist = isExist;
		this.isLandmark=isLandmark;
		this.piece = p;
		this.x=i;
		this.y=j;
		this.z=k;
	}
	
	public Color getColor(){
		return this.color;
	}
	
	public void setColor(Color color){
		this.color = color;
	}
	
	public boolean getIsExist(){
		return this.isExist;
	}
	
	public void setIsExist(boolean isExist){
		this.isExist = isExist;
		if(this.isExist){
			if((this.x+this.y)%2==0){
				this.color=Color.WHITE;
			}
			else{
				this.color=Color.DARK_GRAY;
			}
		}
		else{
			this.color=Color.LIGHT_GRAY;
		}
	}
	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getZ() {
		return z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public boolean isLandmark() {
		return isLandmark;
	}
	public void setLandmark(boolean isLandmark) {
		this.isLandmark = isLandmark;
	}



}
