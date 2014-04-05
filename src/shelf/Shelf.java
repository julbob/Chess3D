package shelf;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Shelf {

	private Box shelf[][][] = new Box[6][10][7];
	private List<Piece> white = new ArrayList<Piece>();
	private List<Piece> black = new ArrayList<Piece>();
	/*the constructor initialize the shelf at the beginning*/
	public Shelf(){
		int i=0,j=0,k=0;
		boolean isExist,isLandmark=false; /*the landmark is the case where the movable shelf is related, this boolean declare the case is landmark or not*/
		Piece p;
		for(i=0;i<=5;i++){
			for(j=0;j<=9;j++){
				for(k=0;k<=6;k++){
					isExist=false;
					Color color=Color.LIGHT_GRAY;
					if(k==1&&(i>=1&&i<=4)&&(j>=1&&j<=4)){
						isExist=true;
					}
					else if(k==2&&j<=1&&(i<=1||i>=4)){
						isExist=true;
					}
					else if(k==3&&(j>=3&&j<=6)&&(i>=1&&i<=4)){
						isExist=true;
					}
					else if(k==5&&(j>=5&&j<=8)&&(i>=1&&i<=4)){
						isExist=true;
					}
					else if(k==6&&(j>=8)&&(i<=1||i>=4)){
						isExist=true;
					}
					
					if(isExist){
						if((k==1&&j==1&&(i==1||i==4))||(k==5&&j==8&&(i==1||i==4))){
							isLandmark=true;
						}
						else{
							isLandmark=false;
						}
						if((i+j)%2==0){
							color=Color.WHITE;
						}
						else{
							color=Color.DARK_GRAY;
						}
					}
					if((k==1&&j==2||k==2&&j==1)){
						p=new Pawn(false,i,j,k);
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, p, i, j, k);
						white.add(p);
					}
					else
					if(k==2&&j==0&&(i==0||i==5)){
						p=new Rook(false,i,j,k);
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, p, i, j, k);
						white.add(p);
					}
					else if(k==2&&j==0&&i==1){
						p=new Queen(false,i,j,k);
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, p, i, j, k);
						white.add(p);
					}
					else if(k==2&&j==0&&i==4){
						p=new King(false,i,j,k);
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, p, i, j, k);
						white.add(p);
					}
					else if(k==1&&j==1&&(i==1||i==4)){
						p=new Knight(false,i,j,k);
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, p, i, j, k);
						white.add(p);
					}
					else if(k==1&&j==1&&(i==2||i==3)){
						p=new Bishop(false,i,j,k);
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, p, i, j, k);
						white.add(p);
					}
					else if((k==5&&j==7||k==6&&j==8)){
						p=new Pawn(true,i,j,k);
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, p, i, j, k);
						black.add(p);
					}
					else if(k==6&&j==9&&(i==0||i==5)){
						p=new Rook(true,i,j,k);
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, p, i, j, k);
						black.add(p);
					}
					else if(k==6&&j==9&&i==1){
						p=new Queen(true,i,j,k);
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, p, i, j, k);
						black.add(p);
					}
					else if(k==6&&j==9&&i==4){
						p=new King(true,i,j,k);
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, p, i, j, k);
						black.add(p);
					}
					else if(k==5&&j==8&&(i==1||i==4)){
						p=new Knight(true,i,j,k);
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, p, i, j, k);
						black.add(p);
					}
					else if(k==5&&j==8&&(i==2||i==3)){
						p=new Bishop(true,i,j,k);
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, p, i, j, k);
						black.add(p);
					}
					
					else{
						this.shelf[i][j][k]=new Box(color, isExist,isLandmark, i, j, k);
					}
					
					
				}
			}
		}
		
		
	
	}
	
	public boolean getExistence(int i, int j, int k){
		return this.shelf[i][j][k].getIsExist();
	}
	
	public void setExistence(int i, int j, int k){
		if(this.shelf[i][j][k].getIsExist()){
			this.shelf[i][j][k].setIsExist(false);
		}
		else{
			this.shelf[i][j][k].setIsExist(true);
		}
	}
	
	public Piece getPiece(int i, int j, int k){
		return this.shelf[i][j][k].getPiece();
	}
	
	public void setPiece(int i, int j, int k, Piece p){
		this.shelf[i][j][k].setPiece(p);
	}
	
	public Box getBox(int i, int j, int k){
		return shelf[i][j][k];
	}

	public boolean getLandmark(int i, int j, int k){
		return this.shelf[i][j][k].isLandmark();
	}
	public void setLandmark(int i, int j, int k){
		if(this.shelf[i][j][k].getIsExist()){
			this.shelf[i][j][k].setLandmark(false);
		}
		else{
			this.shelf[i][j][k].setLandmark(true);
		}
	}
	
	public List<Piece> getTeam(boolean Color){
		if(Color==true){
			return white;
		}
		else{
			return black;
		}
	}
	
	public boolean isPiece(int i, int j, int k){
		if(getPiece(i, j, k)!=null){
			return true;
		}
		else{
			return false;
		}
	}
}
