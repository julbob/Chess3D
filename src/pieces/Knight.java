package pieces;



import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import shelf.Box;
import shelf.Shelf;

public class Knight extends Piece{
		
	public Knight(boolean Color, int i, int j, int k){
		super(Color,i,j,k);
		if(this.getColor())
		{
			this.picture = null;
			try {
                this.picture = ImageIO.read(new File("Images/knightwhite.png"));
                } catch (IOException e) {
                }
		}
		else
		{
			this.picture = null;
			try {
                this.picture = ImageIO.read(new File("Images/knightblack.png"));
                } catch (IOException e) {
                }
		}
	}
	
	public void possibleMovement(int i, int j, int k, Shelf s, List<Direction> d){
		int x=0,y=0,z=0;
		setPossibleMovement(new ArrayList<Box>());
		for(x=0;x<=6;x++){
			for(y=-2;y<=2;y++){
				if(y==-2||y==2){
					for(z=-1;z<=1;z=z+2){
						if(i+y>=0 && i+y<=5 && j+z>=0 && j+z<=9 && s.getExistence(i+y,j+z,x)){
							if(!s.isPiece(i+y,j+z,x) || (s.isPiece(i+y,j+z,x)&& s.getPiece(i,j,k).getColor()!=s.getPiece(i+y,j+z,x).getColor())) {
								addPossibleMovement(s.getBox(i+y,j+z,x));
							}
						}
					}
				}
				else if(y==-1||y==1){
					for(z=-2;z<=2;z=z+4){
						if(i+y>=0 && i+y<=5 && j+z>=0 && j+z<=9 && s.getExistence(i+y,j+z,x)){
							if(!s.isPiece(i+y,j+z,x)|| (s.isPiece(i+y,j+z,x)&& s.getPiece(i,j,k).getColor()!=s.getPiece(i+y,j+z,x).getColor())) {
								addPossibleMovement(s.getBox(i+y,j+z,x));							}
						}
					}
				}
			}
		}

	}
	
}