package window;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import pieces.King;
import pieces.Piece;
import shelf.Box;
import shelf.MovableShelf;
import shelf.Shelf;

public class Board extends JPanel implements MouseInputListener{
	
	
	private int imouse=0, jmouse=0, nbclick=0, nbaction=0;
	private int sizeBox,margeX,margeY;
	
	private Shelf s=new Shelf();
	private MovableShelf[] movable = new MovableShelf[4];
	private Box beginbox, finalbox;
	private boolean firstclick = true,moveShelf=false;
	private List<Box> list = new ArrayList<Box>();
	private MovableShelf movableShelf_tmp;
	private JOptionPane d = new JOptionPane();
	
	public Board() {
		this.addMouseListener(this);

		this.movable[0] = new MovableShelf(s.getBox(1, 1, 1), 2, s);
		this.movable[1] = new MovableShelf(s.getBox(4, 1, 1), 2, s);
		this.movable[2] = new MovableShelf(s.getBox(1, 8, 5), 6, s);
		this.movable[3] = new MovableShelf(s.getBox(4, 8, 5), 6, s);
	}
	
	
	/*Built graphically the game board in a resizable window*/
	public void paintComponent(Graphics g){
				
		int i,j,x,y,z;
		Box tmp;
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.RED);
		if(getWidth()/10<getHeight()/18){
			this.sizeBox=getWidth()/10;
			this.margeY=(getHeight()-18*this.sizeBox)/2;
		}
		else{
			this.sizeBox=getHeight()/18;
			this.margeX=(getWidth()-10*this.sizeBox)/2;
		}
		
		for(i=0;i<=9;i++){
			for(j=0;j<=17;j++){
				
				tmp=convert(i,j);
				
				if(tmp!=null){
					x=tmp.getX();
					y=tmp.getY();
					z=tmp.getZ();
					g.setColor(s.getBox(x, y, z).getColor());
					g.fillRect(i*sizeBox+margeX, j*sizeBox+margeY, this.sizeBox, this.sizeBox);
					
					if(s.getPiece(x,y,z) != null){
						g.drawImage(s.getPiece(x, y, z).getPicture(), i*sizeBox+margeX, j*sizeBox+margeY, this.sizeBox, this.sizeBox, this);
					}

				}
				else{
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(i*sizeBox+margeX, j*sizeBox+margeY, this.sizeBox, this.sizeBox);
				}
				
			}
		}
		
	}

	
	/*Convert the 2D coordinates, obtained on the self, in 3D coordinates to use them in the game engine*/
	public Box convert(int i, int j){
		Box tmp=null;
		if(((j<=1&&j>=0) || (j>=4&&j<=5)) && (i<=1||i>=8)){
			if(i<=1 && i>=0){
				tmp=s.getBox(i, j, 2);
			}
			else if(i>=8 && i<=9){
				tmp=s.getBox(i-4, j, 2);
			}
		}
		else if(j>=1&&j<=4 && i>=3&&i<=6){
			tmp=s.getBox(i-2, j, 1);
		}
		else if(j>=7&&j<=10 && i>=3&&i<=6){
			tmp=s.getBox(i-2,j-4,3);
		}
		else if(((j>=6&&j<=7) || (j<=11&&j>=10) && (i<=1||i>=8))){
			if(i<=1 && i>=0){
				tmp=s.getBox(i, j-4, 4);
			}
			else if(i>=8 && i<=9){
				tmp=s.getBox(i-4, j-4, 4);
			}
		}
		else if(j>=13&&j<=16 && i<=6&&i>=3){
			tmp=s.getBox(i-2, j-8, 5);
		}
		else if(((j>=16&&j<=17)||(j>=12&&j<=13))&&(i<=1||i>=8)){
			if(i<=1&&i>=0){
				tmp=s.getBox(i, j-8, 6);
			}
			else if(i>=8&&i<=9){
				tmp=s.getBox(i-4, j-8, 6);
			}
		}
		return tmp;
	}
	
	
	/*Complete the list of the possible boxes where the piece can move*/
	public void completeListMovement(Box tmp){
		
		tmp.getPiece().possibleMovement(tmp.getPiece().getX(), tmp.getPiece().getY(), tmp.getPiece().getZ(), s, tmp.getPiece().getDirections());
		this.list = tmp.getPiece().getPossibleMovement();	
	}
	
	
	/*Change box color of the boxes where the piece can move*/
	public void changeBoxColor(){
		Graphics g = this.getGraphics();
		int i,x,y,z;
			
		for(i=0;i<this.list.size();i++){
			x = this.list.get(i).getX();
			y = this.list.get(i).getY();
			z = this.list.get(i).getZ();
						
			if(this.list.get(i).getPiece() == null){
				this.s.getBox(x,y,z).setColor(Color.BLUE);
			}
			else
			{
				this.s.getBox(x,y,z).setColor(Color.RED);
			}
		}
		paintComponent(g);
	}
	
	
	
	
	/*Change box color of the 4 boxes where the shelf can move*/
	public void changeShelfBoxColor(List<Box> list, Color color){
		Graphics g = this.getGraphics();
		int i,x,y,z;
			
		for(i=0;i<list.size();i++)
		{
			x = list.get(i).getX();
			y = list.get(i).getY();
			z = list.get(i).getZ();
			
			s.getBox(x,y,z).setColor(color);
		}
		paintComponent(g);
	}
	
	
	
	/*Gets a box with coordinates in 3D from 2D box clicked on the self*/
	public Box getBox3D(MouseEvent e){
		Box tmp;
		
		setImouse((e.getX()-margeX)/(this.sizeBox));
		setJmouse((e.getY()-margeY)/(this.sizeBox));

		tmp = convert(getImouse(),getJmouse());
			
		return tmp;
	}
	
	/*Resets the color of box on the board game*/
	public void restartColor(List<Box> list){
		int i;
		for(i=0;i<this.list.size();i++){
			if((this.list.get(i).getX()+this.list.get(i).getY())%2 == 0){
				this.list.get(i).setColor(Color.white);
			}
			else
			{
				this.list.get(i).setColor(Color.DARK_GRAY);

			}
		}
	}
	
	

	
	/*Allows a piece to move from one box to another and eat a piece belonging to the opposite team*/
	public void pieceMovement(Box box, boolean playercolor){
		int i;
		Piece p;
		Graphics g = this.getGraphics();	
		boolean stop=false,color=false;
		
		
		if(this.list == null){
			this.firstclick = true;
		}
			
		if(this.firstclick){
			if(box.getPiece() != null)
			{
				if(box.getPiece().getColor()==playercolor)
				{		
					completeListMovement(box);
					
					/*if(box.getPiece() instanceof King){
						((King) box.getPiece()).possibleMovementWithoutCheck(s,this.list);
					}*/
				
					this.beginbox = box;
					changeBoxColor();
					this.firstclick = false;
				}
			}
			else {
				for(i=0;i<4;i++)
				{
					if(movable[i].getBoxInMovableShelf().contains(box) && movable[i].canMove(colorToPlay(this.nbaction)))
					{
						movable[i].possibleMovement(s);
						this.movableShelf_tmp = movable[i];
						list = this.movableShelf_tmp.getMovableShelfPossibleMovement();
						this.beginbox = box;
						changeShelfBoxColor(list, Color.BLUE);
						this.firstclick = false;
						this.moveShelf = true;
					}
				}
			}
			
		}
		else
		{
			if(!this.moveShelf){
				this.finalbox = box;
				p = beginbox.getPiece();
				this.list = p.getPossibleMovement();
				
				if(this.list.contains(this.finalbox)){
					if(this.finalbox.getPiece() instanceof King){
						stop = true;
						if(this.finalbox.getPiece().getColor()){
							color=true;
						}
					}
					p.PieceMovement(this.beginbox, this.finalbox, s);
					setNbaction(this.nbaction+1);
				}
			}
			else
			{
				this.finalbox=box;
				if(list.contains(finalbox)){
					this.movableShelf_tmp.movement(s,movableShelf_tmp.getLandmark(), finalbox, finalbox.getZ()+1);
					setNbaction(this.nbaction+1);
				}
				
				this.moveShelf=false;
			}
			
			restartColor(this.list);
			this.list = null;
			paintComponent(g);
			repaint();
			if(stop){
				if(!color){
					d.showMessageDialog(this, "WHITE WINS");
				}
				else{
					d.showMessageDialog(this, "BLACK WINS");
				}
				System.exit(0);
			}
		}
		
	}
	
	/*Allows players to take turn*/
	public boolean colorToPlay(int nbaction){
		boolean teamcolor = true;
		
		if(nbaction%2 == 0){
			teamcolor = true;
		}
		else
		{
			teamcolor = false;
		}
		
		return teamcolor;
	}
	
	

	/*Manages the consequences of a mouse click*/
	public void mouseClicked(MouseEvent e) {
		Box box;
		boolean playercolor;
		
		setNbclick(this.nbclick+1);
		
		box = getBox3D(e);

		if(box != null)
		{
			playercolor = colorToPlay(this.nbaction);
			pieceMovement(box, playercolor);
		}
	

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	public int getImouse() {
		return imouse;
	}
	public void setImouse(int imouse) {
		this.imouse = imouse;
	}
	public int getJmouse() {
		return jmouse;
	}
	public void setJmouse(int jmouse) {
		this.jmouse = jmouse;
	}

	public boolean isFirstclick() {
		return firstclick;
	}

	public void setFirstclick(boolean firstclick) {
		this.firstclick = firstclick;
	}


	public int getNbclick() {
		return nbclick;
	}


	public void setNbclick(int nbclick) {
		this.nbclick = nbclick;
	}


	public int getNbaction() {
		return nbaction;
	}


	public void setNbaction(int nbaction) {
		this.nbaction = nbaction;
	}
}