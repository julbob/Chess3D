package pieces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import shelf.Box;
import shelf.Shelf;

public class Pawn extends Piece{
	
	public Pawn(boolean Color, int i, int j, int k){
		super(Color, i, j, k);
		
		if(this.getColor())
		{
			this.picture = null;
			try {
                this.picture = ImageIO.read(new File("Images/pawnwhite.png"));
                } catch (IOException e) {
                }
		}
		else
		{
			this.picture = null;
			try {
                this.picture = ImageIO.read(new File("Images/pawnblack.png"));
                } catch (IOException e) {
                }
		}
	}
	
	public void possibleMovement(int i, int j, int k, Shelf s, List<Direction> d){
		int sens,x,y;
		setPossibleMovement(new ArrayList<Box>());
		if(s.getPiece(i,j,k).getColor()){
			sens=-1;
		}
		else{
			sens = 1;
		}
		for(x=0;x<=6;x++){
			if(j+sens>=0&&j+sens<=9&&s.getExistence(i,j+sens,x)&&!s.isPiece(i,j+sens,x)){
				addPossibleMovement(s.getBox(i, j+sens, x));
				if(j+2*sens>=0&&j+2*sens<=9&&s.getExistence(i,j+2*sens,x)&&!s.isPiece(i,j+2*sens,x)&&!s.getPiece(i, j, k).isMove()){
					addPossibleMovement(s.getBox(i, j+2*sens, x));
				}
			}
			
			for(y=-1;y<=1;y=y+2){
				if(j+sens>=0&&j+sens<=8&&i+y>=0&&i+y<=5&&s.getExistence(i+y,j+sens,x)&&s.isPiece(i+y,j+sens,x)){
					if(s.getPiece(i+y, j+sens, x).getColor()!=s.getPiece(i, j, k).getColor()){
						addPossibleMovement(s.getBox(i+y,j+sens,x));
					}
					
				}
			}
		}

	
	}
}