package pieces;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Rook extends PieceUnlimitedMovement{
	
	public Rook(boolean Color, int i, int j, int k){
		super(Color,i,j,k);
		Direction d1 = new Direction(1,0);
		getDirections().add(d1);
		Direction d2 = new Direction(0,1);
		getDirections().add(d2);
		Direction d3 = new Direction(-1,0);
		getDirections().add(d3);
		Direction d4= new Direction(0,-1);
		getDirections().add(d4);
		
		if(this.getColor())
		{
			this.picture = null;
			try {
                this.picture = ImageIO.read(new File("Images/rookwhite.png"));
                } catch (IOException e) {
                }
		}
		else
		{
			this.picture = null;
			try {
                this.picture = ImageIO.read(new File("Images/rookblack.png"));
                } catch (IOException e) {
                }
		}
	}
}