package pieces;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import shelf.Box;
import shelf.Shelf;

public class King extends Piece{
	
	List<Piece> dangerous;
	public King(boolean Color, int i, int j, int k){
		super(Color,i,j,k);
		
		if(this.getColor())
		{
			this.picture = null;
			try {
                this.picture = ImageIO.read(new File("Images/kingwhite.png"));
                } catch (IOException e) {
                }
		}
		else
		{
			this.picture = null;
			try {
                this.picture = ImageIO.read(new File("Images/kingblack.png"));
                } catch (IOException e) {
                }
		}
	}
	
	public void possibleMovement(int i, int j, int k, Shelf s, List<Direction> d){
		int x=0,y=0,z=0;
		setPossibleMovement(new ArrayList<Box>());
		for(x=0;x<=6;x++){
			for(y=-1;y<=1;y++){
				for(z=-1;z<=1;z++){
					if(i+y>=0 && i+y<=5 && j+z>=0 && j+z<=8 && s.getExistence(i+y, j+z, x) && (y!=0||z!=0)){
						if(!s.isPiece(i+y,j+z,x) || (s.isPiece(i+y,j+z,x) && s.getPiece(i,j,k).getColor()!=s.getPiece(i+y,j+z,x).getColor())) {
							addPossibleMovement(s.getBox(i+y, j+z, x));
						}
					}
				}
			}
		}
	}
	
	/*public void possibleMovementWithoutCheck(Shelf s, List<Box> list){
		List<Piece> team= s.getTeam(!getColor());
		Piece tmp=null;
		Box bbegin=s.getBox(getX(), getY(), getZ());
		int i,j,k;
		for(k=0;k<list.size();k++){
			System.out.println(getX()+""+ getY()+""+ getZ());
			
			PieceMovement(bbegin, list.get(k), s);
			System.out.println(getX()+""+ getY()+""+ getZ());
			for(j=0;j<team.size();j++){
				tmp=team.get(j);
				tmp.possibleMovement(tmp.getX(), tmp.getY(), tmp.getZ(), s, tmp.getDirections());
				for(i=0;i<list.size();i++){
					if(tmp.getPossibleMovement().contains(list.get(i))){
						getPossibleMovement().remove(list.get(i));
					}
				}			
			}
			PieceMovement(list.get(k), bbegin, s);
		}
	}*/
	
	/*public boolean checkMate(Shelf s){
		boolean isCatch=false;
		if(getPossibleMovement().size()==0&&check(getX(),getY(),getZ(),s)){
			if(dangerous.size()==1){
				int i=0;
				List<Piece> team = s.getTeam(getColor());
				while(i<team.size()&&!isCatch){
					isCatch=team.get(i).getPossibleMovement().contains(s.getBox(dangerous.get(0).getX(), dangerous.get(0).getY(), dangerous.get(0).getZ()));
					i++;
				}
				if(!isCatch){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}

		}
		else{
			return false;
		}
	}*/
}