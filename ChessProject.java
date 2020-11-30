import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;



/*
	This class can be used as a starting point for creating your Chess game project. The only piece that
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
	int startX;
	int startY;
	int initialX;
	int initialY;
	JPanel panels;
	JLabel pieces;
	private Boolean whiteTurn;
    private Boolean gameOver;


    public ChessProject(){
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout( new GridLayout(8, 8) );
        chessBoard.setPreferredSize( boardSize );
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel( new BorderLayout() );
            chessBoard.add( square );

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground( i % 2 == 0 ? Color.white : Color.gray );
            else
                square.setBackground( i % 2 == 0 ? Color.gray : Color.white );
        }

        // Setting up the Initial Chess board.
		for(int i=8;i < 16; i++){
       		pieces = new JLabel( new ImageIcon("WhitePawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(0);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(1);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKnight.png") );
		panels = (JPanel)chessBoard.getComponent(6);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(2);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteBishup.png") );
		panels = (JPanel)chessBoard.getComponent(5);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteKing.png") );
		panels = (JPanel)chessBoard.getComponent(3);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
		panels = (JPanel)chessBoard.getComponent(4);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("WhiteRook.png") );
		panels = (JPanel)chessBoard.getComponent(7);
	    panels.add(pieces);
		for(int i=48;i < 56; i++){
       		pieces = new JLabel( new ImageIcon("BlackPawn.png") );
			panels = (JPanel)chessBoard.getComponent(i);
	        panels.add(pieces);
		}
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(56);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(57);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKnight.png") );
		panels = (JPanel)chessBoard.getComponent(62);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(58);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackBishup.png") );
		panels = (JPanel)chessBoard.getComponent(61);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackKing.png") );
		panels = (JPanel)chessBoard.getComponent(59);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackQueen.png") );
		panels = (JPanel)chessBoard.getComponent(60);
	    panels.add(pieces);
		pieces = new JLabel( new ImageIcon("BlackRook.png") );
		panels = (JPanel)chessBoard.getComponent(63);
		panels.add(pieces);
		
		whiteTurn = true;
		gameOver = false;
	}

	/*
		This method checks if there is a piece present on a particular square.
	*/
	private Boolean piecePresent(int x, int y){
		Component c = chessBoard.findComponentAt(x, y);
		if(c instanceof JPanel){
			return false;
		}
		else{
			return true;
		}
	}

	private Stack<Square> findWhitePieces(){
		Stack<Square> squares = new Stack<Square>();
		String icon;
		int x,y;
		String pieceName;
		for(int i=0; i<600; i+=75){
			for(int j=0; j<600; j+=75){
				y = i/75;
				x = j/75;
				Component tmp = chessBoard.findComponentAt(j, i);
				if(tmp instanceof JLabel){
					chessPiece = (JLabel)tmp;
					icon = chessPiece.getIcon().toString();
					pieceName = icon.substring(0, (icon.length()-4));
					if(pieceName.contains("White")){
						Square stmp = new Square(x, y, pieceName);
						squares.push(stmp);
					}
				}
			}
		}
		return squares;
	}
	
	private Stack<Square> getWhiteAttackingSquares(Stack pieces){
		Stack<Square> piece = new Stack<Square>();
		while(!pieces.empty()){
			Square s = (Square)pieces.pop();
			String tmpString = s.getName();
			if(tmpString.contains("Knight")){
				Stack<Square> tempK = getKnightMoves(s.getXco(), s.getYco(), s.getName());
				while(!tempK.empty()){
					Square tempKnight = (Square)tempK.pop();
					piece.push(tempKnight);
				}
			}
			/*
			else if(tmpString.contains("Pawn")){
				Stack tempP = getPawnMoves(s.getXco(), s.getYco(), s.getName());
				while(!tempP.empty()){
					Square tempPawn = (Square)tempP.pop();
					piece.push(tempPawn);
				}
			}
			
			else if(tmpString.contains("Bishup")){
				Stack tempB = getBishupMoves(s.getXco(), s.getYco(), s.getName());
				while(!tempB.empty()){
					Square tempBishup = (Square)tempB.pop();
					piece.push(tempBishup);
				}
			}
			*/
		}
		return piece;
	}
	

	/*
		Getting all the moves for the Knight piece

		The Knight can move in an L shape
	*/
	private Stack<Square> getKnightMoves(int x, int y, String piece){
		Stack<Square> moves = new Stack<Square>();
		Stack<Square> attacking = new Stack<Square>();

		Square s = new Square(x+1, y+2);
		moves.push(s);
		Square s1 = new Square(x+1, y-2);
		moves.push(s1);
		Square s2 = new Square(x-1, y+2);
		moves.push(s2);
		Square s3 = new Square(x-1, y-2);
		moves.push(s3);
		Square s4 = new Square(x+2, y+1);
		moves.push(s4);
		Square s5 = new Square(x+2, y-1);
		moves.push(s5);
		Square s6 = new Square(x-2, y+1);
		moves.push(s6);
		Square s7 = new Square(x-2, y-1);
		moves.push(s7);

		for(int i=0; i<8; i++){
			Square tmp = (Square)moves.pop();
			if((tmp.getXco()<0)||(tmp.getXco()>7)||(tmp.getYco()<0)||(tmp.getYco()>7)){

			}
			else if(piecePresent(((tmp.getXco()*75)+20), (((tmp.getYco()*75)+20)))){
				if(piece.contains("White")){
					if(checkWhiteOponent(((tmp.getXco()*75)+20), ((tmp.getYco()*75)+20))){
						attacking.push(tmp);
					}
					else{
						System.out.println("Its one of our own pieces");
					}
				}
				else{
					if(checkBlackOponent(tmp.getXco(), tmp.getYco())){
						attacking.push(tmp);
					}
				}
			}
			else{
				attacking.push(tmp);
			}
		}
		Stack tmp = attacking;
		colorSquares(tmp);
		return attacking;
	}

	/*
		A method to color the squares
	*/

	private void colorSquares(Stack squares){
		Border greenBorder = BorderFactory.createLineBorder(Color.GREEN,3);
		while(!squares.empty()){
			Square s = (Square)squares.pop();
			int location = s.getXco() + ((s.getYco())*8);
			JPanel panel = (JPanel)chessBoard.getComponent(location);
			panel.setBorder(greenBorder);
		}
	}


	private void CheckMate(String winner)
    {
        gameOver = true;
        if (winner.contains("White"))
        {
            JOptionPane.showMessageDialog(null, "White wins");
            System.exit(2);
        } else
        {
            JOptionPane.showMessageDialog(null, "Black wins");
            System.exit(2);
        }
	}
	
	private void ChangeTurn(Boolean turnColour){
		if(whiteTurn){
			whiteTurn = !turnColour;
			System.out.println("Black players turn");
		}
		else{
			whiteTurn = turnColour;
			System.out.println("White players turn");
		}
	}

	/*
		This is a method to check if a piece is a Black piece.
	*/
	private Boolean checkWhiteOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(tmp1.contains("Black")){
			if(tmp1.contains("King")){
				oponent = true;
				CheckMate("White");
			}
			else{
				oponent = true;
			}
		}
		else{
			oponent = false;
		}
		return oponent;
	}
	/*
		This is a method to check if a piece is a White piece.
	*/
	private Boolean checkBlackOponent(int newX, int newY){
		Boolean oponent;
		Component c1 = chessBoard.findComponentAt(newX, newY);
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		if(tmp1.contains("White")){
			if(tmp1.contains("King")){
				oponent = true;
				CheckMate("Black");
			}
			else{
				oponent = true;
			}
		}
		else{
			oponent = false;
		}
		return oponent;
	}

	/*
		This method is called when we press the Mouse. So we need to find out what piece we have
		selected. We may also not have selected a piece!
	*/
    public void mousePressed(MouseEvent e){
        chessPiece = null;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
			return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel)c;
		initialX = e.getX();
		initialY = e.getY();
		startX = (e.getX()/75);
		startY = (e.getY()/75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
         chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
     }

 	/*
		This method is used when the Mouse is released...we need to make sure the move was valid before
		putting the piece back on the board.
	*/
    public void mouseReleased(MouseEvent e) {
        if(chessPiece == null) return;

        chessPiece.setVisible(false);
		Boolean success =false;
		Boolean promotion =false;
        Component c =  chessBoard.findComponentAt(e.getX(), e.getY());
		String tmp = chessPiece.getIcon().toString();
		String pieceName = tmp.substring(0, (tmp.length()-4));
		Boolean validMove = false;
		int newY = e.getY()/75;
		int newX = e.getX()/75;
		/*
			Printing the move details to the console for testing
		*/
		int landingX = (e.getX()/75);
        int landingY  = (e.getY()/75);
        int xMovement = Math.abs((e.getX()/75)-startX);
        int yMovement = Math.abs((e.getY()/75)-startY);
        System.out.println("----------------------------------------------");
        System.out.println("Piece moved : "+pieceName);
        System.out.println("Starting co-ords : "+"( "+startX+","+startY+")");
        System.out.println("xMovement : "+xMovement);
        System.out.println("yMovement is : "+yMovement);
        System.out.println("Landing co-ords : "+"( "+landingX+","+landingY+")");
		System.out.println("----------------------------------------------");
		
		Boolean playable = false;

		if(whiteTurn){
			if(pieceName.contains("White")){
				playable = true;
			}
		}
		else{
			if(pieceName.contains("Black")){
				playable = true;
			}
		}

		if(playable){
			if(!gameOver){
				if(pieceName.contains("King")){
					if((newX<0)||(newX >7)||(newY < 0)||(newY > 7)){
						validMove = false;
					}
					else{
						if(((Math.abs(startX-newX)==1)&&(Math.abs(startY-newY)==0))||((Math.abs(startX-newX)==0)&&(Math.abs(startY-newY)==1))||((Math.abs(startX-newX)==1)&&(Math.abs(startY-newY)==1))){
							if(!piecePresent(e.getX(), e.getY())){
								validMove = true;
							}
							else{
								if(piecePresent(e.getX(), e.getY())){
									if(pieceName.contains("White")){
										if(checkWhiteOponent(e.getX(), e.getY())){
											validMove = true;
										}
										else{ 
											validMove = false;
										}
									}
									else{
										if(checkBlackOponent(e.getX(), e.getY())){
											validMove = true;
										}
										else{
											validMove = false;
										}
									}
								}
							}
						}
						else{
							validMove = false;
						}
					}
				}
				else if(pieceName.contains("Queen")){
					int distance = Math.abs((startX-newX));
					Boolean blocker = false;
					if((newX<0)||(newX >7)||(newY < 0)||(newY > 7)){
						validMove = false;
					}
					else if(((Math.abs(startX-newX)!=0)&&(Math.abs(startY-newY)==0))||((Math.abs(startX-newX)==0)&&(Math.abs(startY-newY)!=0))){
						if(Math.abs(startX-newX)!=0){
							int xMove = Math.abs(startX-newX);
							if(startX-newX > 0){
								for(int i=0;i<xMove;i++){
									if(piecePresent(initialX-(i*75), e.getY())){
										blocker = true;
										break;
									}
									else{
										blocker = false;
									}
								}
							}
							else{
								for(int i=0;i<xMove;i++){
									if(piecePresent(initialX+(i*75), e.getY())){
										blocker = true;
										break;
									}
									else{
										blocker = false;
									}
								}
							}
						}
						else{
							int yMove = Math.abs(startY-newY);
							if(startY-newY>0){
								for(int i =0;i<yMove;i++){
									if(piecePresent(e.getX(), initialY-(i*75))){
										blocker = true;
										break;
									}
									else{
										blocker = false;
									}
								}
							}
							else{
								for(int i =0;i<yMove;i++){
									if(piecePresent(e.getX(), initialY+(i*75))){
										blocker = true;
										break;
									}
									else{
										blocker = false;
									}
								}
							}
						}
					}
					else{
						validMove = true;
						if(Math.abs(startX-newX)== Math.abs(startY-newY)){
							if((startX-newX < 0)&&(startY-newY < 0)){
								for(int i=0; i < distance;i++){
									if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
										blocker = true;
									}
								}
							}
							else if((startX-newX<0)&&(startY-newY>0)){
								for(int i=0; i < distance;i++){
									if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
										blocker = true;
									}
								}
							}
							else if((startX-newX>0)&&(startY-newY>0)){
								for(int i=0; i < distance;i++){
									if(piecePresent((initialX-(i*75)), (initialY-(i*75)))){
										blocker = true;
									}
								}
							}
							else if((startX-newX>0)&&(startY-newY<0)){
								for(int i=0; i < distance;i++){
									if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
										blocker = true;
									}
								}
							}
						}
						else{
							validMove = false;
						}
					}
					if(blocker){
						validMove = false;
					}
		
					else{
						if(piecePresent(e.getX(), e.getY())){
							if(pieceName.contains("White")){
								if(checkWhiteOponent(e.getX(), e.getY())){
									validMove = true;
								}
								else{ 
									validMove = false;
								}
							}
							else{
								if(checkBlackOponent(e.getX(), e.getY())){
									validMove = true;
								}
								else{ 
									validMove = false;
								}
							}
		
						}
						else{
							validMove = true;
						}
					}
				}
				else if(pieceName.contains("Rook")){
					Boolean blocker = false;
					if((newX<0)||(newX >7)||(newY < 0)||(newY > 7)){
						validMove = false;
					}
					else{
						if(((Math.abs(startX-newX)!=0)&&(Math.abs(startY-newY)==0))||((Math.abs(startX-newX)==0)&&(Math.abs(startY-newY)!=0))){
							if(Math.abs(startX-newX)!=0){
								int xMove = Math.abs(startX-newX);
								if(startX-newX > 0){
									for(int i=0;i<xMove;i++){
										if(piecePresent(initialX-(i*75), e.getY())){
											blocker = true;
											break;
										}
										else{
											blocker = false;
										}
									}
								}
								else{
									for(int i=0;i<xMove;i++){
										if(piecePresent(initialX+(i*75), e.getY())){
											blocker = true;
											break;
										}
										else{
											blocker = false;
										}
									}
								}
							}
							else{
								int yMove = Math.abs(startY-newY);
								if(startY-newY>0){
									for(int i =0;i<yMove;i++){
										if(piecePresent(e.getX(), initialY-(i*75))){
											blocker = true;
											break;
										}
										else{
											blocker = false;
										}
									}
								}
								else{
									for(int i =0;i<yMove;i++){
										if(piecePresent(e.getX(), initialY+(i*75))){
											blocker = true;
											break;
										}
										else{
											blocker = false;
										}
									}
								}
							}
							if(blocker){
								validMove = false;
							}
							else{
								if(piecePresent(e.getX(), e.getY())){
									if(pieceName.contains("White")){
										if(checkWhiteOponent(e.getX(), e.getY())){
											validMove = true;
										}
										else{
											validMove = false;
										}
									}
									else{
										if(checkBlackOponent(e.getX(), e.getY())){
											validMove = true;
										}
										else{
											validMove = false;
										}
									}
								}
								else{
									validMove = true;
								}
							}
						}
						else{
							validMove = false;
						}
					}
				}
				else if(pieceName.contains("Bishup")){
					Boolean blocker = false;
					int distance = Math.abs((startX-newX));
					if((newX<0)||(newX >7)||(newY < 0)||(newY > 7)){
						validMove = false;
					}
					else{
						validMove = true;
						if(Math.abs(startX-newX)== Math.abs(startY-newY)){
							if((startX-newX < 0)&&(startY-newY < 0)){
								for(int i=0; i < distance;i++){
									if(piecePresent((initialX+(i*75)), (initialY+(i*75)))){
										blocker = true;
									}
								}
							}
							else if((startX-newX<0)&&(startY-newY>0)){
								for(int i=0; i < distance;i++){
									if(piecePresent((initialX+(i*75)), (initialY-(i*75)))){
										blocker = true;
									}
								}
							}
							else if((startX-newX>0)&&(startY-newY>0)){
								for(int i=0; i < distance;i++){
									if(piecePresent((initialX-(i*75)), (initialY-(i*75)))){
										blocker = true;
									}
								}
							}
							else if((startX-newX>0)&&(startY-newY<0)){
								for(int i=0; i < distance;i++){
									if(piecePresent((initialX-(i*75)), (initialY+(i*75)))){
										blocker = true;
									}
								}
							}
							if(blocker){
								validMove = false;
							}
		
							else{
								if(piecePresent(e.getX(), e.getY())){
									if(pieceName.contains("White")){
										if(checkWhiteOponent(e.getX(), e.getY())){
											validMove = true;
										}
										else{ 
											validMove = false;
										}
									}
									else{
										if(checkBlackOponent(e.getX(), e.getY())){
											validMove = true;
										}
										else{ 
											validMove = false;
										}
									}
		
								}
								else{
									validMove = true;
								}
							}
						}
						else{
							validMove = false;
						}
					}
				}
		
				else if(pieceName.contains("Knight")){
					if((newX<0)||(newX >7)||(newY < 0)||(newY > 7)){
						validMove = false;
					}
					else{
						if(
							((newX == startX+1)&&(newY == startY+2))||
							((newX == startX-1)&&(newY == startY+2))||
							((newX == startX+2)&&(newY == startY+1))||
							((newX == startX-2)&&(newY == startY+1))||
							((newX == startX+1)&&(newY == startY-2))||
							((newX == startX-1)&&(newY == startY-2))||
							((newX == startX+2)&&(newY == startY-1))||
							((newX == startX-2)&&(newY == startY-1))){
							if(piecePresent(e.getX(), (e.getY()))){
								if(pieceName.contains("White")){
									if(checkWhiteOponent(e.getX(), e.getY())){
										validMove = true;
									}
									else{
										validMove = false;
									}
								}
								else{
									if(checkBlackOponent(e.getX(), e.getY())){
										validMove = true;
									}
									else{
										validMove = false;
									}
								}
							}
							else{
								validMove = true;
							}
						}
						else{
							validMove = false;
						}
					}
				}
				else if(pieceName.equals("BlackPawn")){
					if(startY == 6){
						if((startX == (e.getX()/75))&&((((e.getY()/75)-startY)== -1)||((e.getY()/75)-startY)== -2))
						{
							if((((e.getY()/75)-startY)==-2)){
								if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()+75)))){
									validMove = true;
								}
								else{
									validMove = false;
								}
							}
							else{
								if((!piecePresent(e.getX(), (e.getY())))){
									validMove = true;
								}
								else{
									validMove = false;
								}
							}
						}
						else{
						validMove = false;
						}
					}
					else{
						if((startX-1 >=0)||(startX+1 <=7))
						{
							if((piecePresent(e.getX(), (e.getY())))&&((((newX == (startX+1)&&(startX+1<=7)))||((newX == (startX-1))&&(startX-1 >=0)))))
							{
								if(checkBlackOponent(e.getX(), e.getY()))
								{
									validMove = true;
									if(newY == 0){
										promotion = true;
									}
								}
								else{
									validMove = false;
								}
							}
							else{
								if(!piecePresent(e.getX(), e.getY())){
									if((startX == newX)&&(newY-startY)==-1){
										if(newY == 0){
											promotion = true;
										}
										validMove = true;
									}
									else{
										validMove = false;
									}
								}
								else{
									validMove = false;
								}
							}
						}
						else{
							validMove = false;
						}
					}
				}
				else if(pieceName.equals("WhitePawn")){
					/*
						Pawn at original starting square
					*/
					if(startY == 1)
					{
						if((startX == (e.getX()/75))&&((((e.getY()/75)-startY)==1)||((e.getY()/75)-startY)==2))
						{
							if((((e.getY()/75)-startY)==2)){
								if((!piecePresent(e.getX(), (e.getY())))&&(!piecePresent(e.getX(), (e.getY()-75)))){
									validMove = true;
								}
								else{
									validMove = false;
								}
							}
							else{
								if((!piecePresent(e.getX(), (e.getY()))))
								{
									validMove = true;
								}
								else{
									validMove = false;
								}
							}
						}
						else{
							validMove = false;
						}
					}
					/*
						Pawn not at original starting square
					*/
					else{
						if((startX-1 >=0)||(startX +1 <=7))
						{
							if((piecePresent(e.getX(), (e.getY())))&&((((newX == (startX+1)&&(startX+1<=7)))||((newX == (startX-1))&&(startX-1 >=0)))))
							{
								if(checkWhiteOponent(e.getX(), e.getY())){
									validMove = true;
									if(startY == 6){
										success = true;
									}
								}
								else{
									validMove = false;
								}
							}
							else{
								if(!piecePresent(e.getX(), (e.getY()))){
									if((startX == newX)&&(newY-startY)==1){
										if(startY == 6){
											success = true;
										}
										validMove = true;
									}
									else{
										validMove = false;
									}
								}
								else{
									validMove = false;
								}
							}
						}
						else{
							validMove = false;
						}
					}
				}
			}
		}

		
		if(!validMove){
			int location=0;
			if(startY ==0){
				location = startX;
			}
			else{
				location  = (startY*8)+startX;
			}
			String pieceLocation = pieceName+".png";
			pieces = new JLabel( new ImageIcon(pieceLocation) );
			panels = (JPanel)chessBoard.getComponent(location);
		    panels.add(pieces);
		}
		else{
			if(promotion){
				int location = 0 + (e.getX()/75);
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
					pieces = new JLabel( new ImageIcon("BlackQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
				else{
					Container parent = (Container)c;
	            	pieces = new JLabel( new ImageIcon("BlackQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
				ChangeTurn(true);
			}
			else if(success){
				int location = 56 + (e.getX()/75);
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
					pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
				else{
					Container parent = (Container)c;
	            	pieces = new JLabel( new ImageIcon("WhiteQueen.png") );
					parent = (JPanel)chessBoard.getComponent(location);
			    	parent.add(pieces);
				}
				ChangeTurn(true);
			}
			else{
				if (c instanceof JLabel){
	            	Container parent = c.getParent();
	            	parent.remove(0);
	            	parent.add( chessPiece );
	        	}
	        	else {
	            	Container parent = (Container)c;
	            	parent.add( chessPiece );
	        	}
				chessPiece.setVisible(true);
				ChangeTurn(true);
			}
		}
    }

    public void mouseClicked(MouseEvent e) {

    }
    public void mouseMoved(MouseEvent e) {
   }
    public void mouseEntered(MouseEvent e){

    }
    public void mouseExited(MouseEvent e) {

    }

	/*
		Main method that gets the ball moving.
	*/
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
     }
}
