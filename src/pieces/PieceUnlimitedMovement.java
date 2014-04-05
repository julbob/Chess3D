package pieces;

import java.util.ArrayList;
import java.util.List;

import shelf.Box;
import shelf.Shelf;

public abstract class PieceUnlimitedMovement extends Piece{
	
	public PieceUnlimitedMovement(boolean Color, int i, int j, int k){
		super(Color,i,j,k);
	}
	
	public void possibleMovement(int i, int j, int k, Shelf s, List<Direction> d){
		int x=0,y=0,l=0,m=0,dx,dy;
		boolean stop=false;
		setPossibleMovement(new ArrayList<Box>());
		for(x=0;x<d.size();x++){
			stop=false;
			dx=d.get(x).getX();
			dy=d.get(x).getY();
			l=i+dx;
			m=j+dy;
			while(l>=0&&l<=5&&m>=0&&m<=9&&!stop){
				stop=true;
				for(y=0;y<=6;y++){
					if(s.getExistence(l, m, y)){
						if(s.isPiece(l, m, y)){
							if(s.getPiece(l, m, y).getColor()!=s.getPiece(i, j, k).getColor()){
								addPossibleMovement(s.getBox(l, m, y));
								stop=true;
							}
						}
						else{
							addPossibleMovement(s.getBox(l,m,y));
							stop=false;
						}
					}
				}
				l=l+dx;
				m=m+dy;
			}
		}
	}
}