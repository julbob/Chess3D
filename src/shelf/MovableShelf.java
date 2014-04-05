package shelf;

import java.util.ArrayList;
import java.util.List;

import pieces.Pawn;

public class MovableShelf{
	private int high;
	private Box landmark;
	private List<Box> possiblemovement= new ArrayList<Box>(), boxinmovableshelf = new ArrayList<Box>();
	
	
	public MovableShelf(Box l, int k, Shelf s){
		this.setLandmark(l);
		this.setHigh(k);
		if(l.getY()==1||l.getY()==3||l.getY()==5){
			if(l.getX()==1){
				boxinmovableshelf.add(s.getBox(l.getX()-1, l.getY()-1, k ));
				boxinmovableshelf.add(s.getBox(l.getX()-1, l.getY(), k));
				boxinmovableshelf.add(s.getBox(l.getX(), l.getY()-1, k));
				boxinmovableshelf.add(s.getBox(l.getX(), l.getY(), k));
			}
			else{
				boxinmovableshelf.add(s.getBox(l.getX(), l.getY()-1, k ));
				boxinmovableshelf.add(s.getBox(l.getX(), l.getY(), k ));
				boxinmovableshelf.add(s.getBox(l.getX()+1, l.getY()-1, k ));
				boxinmovableshelf.add(s.getBox(l.getX()+1, l.getY(), k ));
			}
		}
		else{
			if(l.getX()==1){
				boxinmovableshelf.add(s.getBox(l.getX()-1, l.getY(), k ));
				boxinmovableshelf.add(s.getBox(l.getX()-1, l.getY()+1, k ));
				boxinmovableshelf.add(s.getBox(l.getX(), l.getY(), k ));
				boxinmovableshelf.add(s.getBox(l.getX(), l.getY()+1, k ));
			}
			else{
				boxinmovableshelf.add(s.getBox(l.getX(), l.getY(), k ));
				boxinmovableshelf.add(s.getBox(l.getX(), l.getY()+1, k ));
				boxinmovableshelf.add(s.getBox(l.getX()+1, l.getY(), k ));
				boxinmovableshelf.add(s.getBox(l.getX()+1, l.getY()+1, k ));
			}
		}
	}

	
	
	public void possibleMovement(Shelf s){
		int i = this.landmark.getX(),j = this.landmark.getY(), k = this.landmark.getZ(), m = this.boxinmovableshelf.get(0).getZ(), l;
		this.possiblemovement = new ArrayList<Box>();
		for(l=-3;l<=3;l=l+6){
			if(l==-3 && j>=3  ||  l==3 && j<=6){
				if(s.getExistence(i, j+l, k)&&!s.getLandmark(i, j+l, k)){
					this.possiblemovement.add(s.getBox(i, j+l, k));
				}
			}
			
			if(l==-3 && i>=3  ||  l==3 && i<=2){
				
				if(s.getExistence(i+l, j, k)&&!s.getLandmark(i+l, j, k)){
					this.possiblemovement.add(s.getBox(i+l, j, k));
				}
			}
		}
		
		if(j<=7 && k<=4){
			if(s.getExistence(i, j+2, k+2)&&!s.getLandmark(i, j+2, k+2)){
				this.possiblemovement.add(s.getBox(i, j+2, k+2));
			}
		}
		
		if(j>=2 && k>=2){
			if(s.getExistence(i, j-2, k-2)&&!s.getLandmark(i, j-2, k-2)){
				this.possiblemovement.add(s.getBox(i, j-2, k-2));
			}
		}
		
		/*if(m<this.landmark.getZ()){
			if(k<=4){
				this.possiblemovement.add(s.getBox(i,j,k+2));
			}
		}
		else{
			if(k>=2){
				this.possiblemovement.add(s.getBox(i, j, k-2));
			}
		}*/
		
	}
	
	
	
	public boolean canMove(boolean color){
		int i, isMovable=0;
		boolean onepawn = false, canmove = false;
		
		for(i=0;i<4;i++){
			if(boxinmovableshelf.get(i).getPiece()==null){
				isMovable++;
			}
			if(boxinmovableshelf.get(i).getPiece() instanceof Pawn && onepawn==false){
				if(boxinmovableshelf.get(i).getPiece().getColor()==color){
					isMovable++;
				}
				onepawn = true;
			}
		}
		
		if(isMovable==4)
		{
			canmove = true;
		}
		
		return canmove;
	}
	
	
	
	
	public void movement(Shelf s,Box lbegin,Box lfinal,int high)
	{
		List<Box> list_tmp=new ArrayList<Box>();
		int i,x,y,z;
		
		if(this.possiblemovement.contains(lfinal)){
			this.landmark=lfinal;
			lfinal.setLandmark(true);
			lbegin.setLandmark(false);
			this.high=high;
			for(i=0;i<4;i++){
				//x=this.boxinmovableshelf.get(i).getX()+lfinal.getX()-lbegin.getX();
				x=lfinal.getX()-lbegin.getY();
				if(x==3){
					x--;
				}
				x=x+this.boxinmovableshelf.get(i).getX();
				y=lfinal.getY()-lbegin.getY();
				if(y==3){
					y++;
				}
				else if(y==-3){
					y--;
				}
				y=y+this.boxinmovableshelf.get(i).getY();
				z=this.boxinmovableshelf.get(i).getZ()+lfinal.getZ()-lbegin.getZ();
				list_tmp.add(s.getBox(x,y,z));
				list_tmp.get(i).setIsExist(true);
				list_tmp.get(i).setPiece(this.boxinmovableshelf.get(i).getPiece());
				this.boxinmovableshelf.get(i).setIsExist(false);
				this.boxinmovableshelf.get(i).setPiece(null);
			}
			this.boxinmovableshelf=list_tmp;
			for(i=0;i<4;i++){
				if(this.boxinmovableshelf.get(i).getPiece()!=null){
					this.boxinmovableshelf.get(i).getPiece().setX(this.boxinmovableshelf.get(i).getX());
					this.boxinmovableshelf.get(i).getPiece().setY(this.boxinmovableshelf.get(i).getY());
					this.boxinmovableshelf.get(i).getPiece().setZ(this.boxinmovableshelf.get(i).getZ());				
				}
			}
			
			/*if(l.getY()==1||l.getY()==3||l.getY()==5){
				if(l.getX()==1){
					tmplist.add(s.getBox(l.getX()-1, l.getY()-1, high ));
					tmplist.add(s.getBox(l.getX()-1, l.getY(), high ));
					tmplist.add(s.getBox(l.getX(), l.getY()-1, high ));
					tmplist.add(s.getBox(l.getX(), l.getY(), high ));
				}
				else{
					tmplist.add(s.getBox(l.getX(), l.getY()-1, high ));
					tmplist.add(s.getBox(l.getX(), l.getY(), high ));
					tmplist.add(s.getBox(l.getX()+1, l.getY()-1, high ));
					tmplist.add(s.getBox(l.getX()+1, l.getY(), high ));
				}
			}
			else{
				if(l.getY()==1){
					tmplist.add(s.getBox(l.getX()-1, l.getY(), high));
					tmplist.add(s.getBox(l.getX()-1, l.getY()+1, high ));
					tmplist.add(s.getBox(l.getX(), l.getY(), high ));
					tmplist.add(s.getBox(l.getX(), l.getY()+1, high ));
				}
				else{
					tmplist.add(s.getBox(l.getX(), l.getY(), high ));
					tmplist.add(s.getBox(l.getX(), l.getY()+1, high ));
					tmplist.add(s.getBox(l.getX()+1, l.getY(), high ));
					tmplist.add(s.getBox(l.getX()+1, l.getY()+1, high ));
				}
			}*/
			/*for(i=0;i<=3;i++){
				this.boxinmovableshelf.get(i).setIsExist(false);
				tmplist.get(i).setIsExist(true);
				tmplist.get(i).setPiece(this.boxinmovableshelf.get(i).getPiece());
				this.boxinmovableshelf.get(i).setPiece(null);
			}
			this.boxinmovableshelf = tmplist;*/
			
		}
		
	}
	
	public List<Box> getBoxInMovableShelf(){
		return boxinmovableshelf;
	}
	
	public List<Box> getMovableShelfPossibleMovement(){
		return possiblemovement;
	}
	
	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

	public Box getLandmark() {
		return landmark;
	}

	public void setLandmark(Box landmark) {
		this.landmark = landmark;
	}
}