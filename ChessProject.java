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
	Defining the class for the square object 
	*/
	class Square {
		public int xCoord;
		public int yCoord;
		public String pieceName;
	
		public Square(int x, int y, String name){
			xCoord = x;
			yCoord = y;
			pieceName = name;
		}
	
		public Square(int x, int y){
			xCoord = x;
			yCoord = y;
			pieceName = "";
		}
	
		public int getXco(){
			return xCoord;
		}
	
		public int getYco(){
			return yCoord;
		}
	
		public String getName(){
			return pieceName;
		}
	}

	/*
	Defining the class for the move object 
	*/
	class Move{
		Square start;
		Square landing;
	  
		public Move(Square x, Square y){
		  start = x;
		  landing = y;
		}
	  
		public Move(){
		  
		}
	  
		public Square getStart(){
		  return start;
		}
	  
		public Square getLanding(){
		  return landing;
		}
	  }

	/*
	Defining the class for the AI Agent object 
	*/
	public class AIAgent{
		Random rand;
	  
		public AIAgent(){
		  rand = new Random();
		}
	  
	  /*
		The method randomMove takes as input a stack of potential moves that the AI agent
		can make. The agent uses a rondom number generator to randomly select a move from
		the inputted Stack and returns this to the calling agent.
	  */
	  
		public Move randomMove(Stack<Move> possibilities){
		
			int moveID = rand.nextInt(possibilities.size());
			System.out.println("Agent randomly selected move : "+moveID);
			for(int i=1;i < (possibilities.size()-(moveID));i++){
			possibilities.pop();
			}
			Move selectedMove = (Move)possibilities.pop();
			return selectedMove;
		}
		
		public Move nextBestMove(Stack<Move> possibilities){
			Move selectedMove = new Move();
			return selectedMove;
		}
		
		public Move twoLevelsDeep(Stack<Move> possibilities){
			Move selectedMove = new Move();
			return selectedMove;
		}
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


	/*
	Method to check if there is a BlackKing in the surrounding squares of a given Square.
	The method should return true if there is no King in any of the squares surrounding
	the square that was submitted to the method. The method checks the grid below:


											_|_____________|_________|_____________|_
											|             |         |             |
											|(x-75, y-75) |(x, y-75)|(x+75, y-75) |
											_|_____________|_________|_____________|_
											|             |         |             |
											|(x-75, y)    | (x, y)  |(x+75, y)    |
											_|_____________|_________|_____________|_
											|             |         |             |
											|(x-75, y+75) |(x, y+75)|(x+75, y+75) |
											_|_____________|_________|_____________|_
											|             |         |             |


	*/
	private Boolean checkSurroundingSquares(Square s){
		Boolean possible = false;
		int x = s.getXco()*75;
		int y = s.getYco()*75;
		Square s1 = new Square(x+75, y);
		Square s2 = new Square(x-75, y);
		Square s3 = new Square(x, y+75);
		Square s4 = new Square(x, y-75);
		Square s5 = new Square(x+75, y+75);
		Square s6 = new Square(x-75, y+75);
		Square s7 = new Square(x+75, y-75);
		Square s8 = new Square(x-75, y-75);
		if(!((s1.getName().contains("BlackKing"))||
			((s2.getName().contains("BlackKing"))||
			((s3.getName()).contains("BlackKing"))||
			((s4.getName()).contains("BlackKing"))||
			((s5.getName()).contains("BlackKing"))||
			((s6.getName()).contains("BlackKing"))||
			((s7.getName()).contains("BlackKing"))||
			((s8.getName()).contains("BlackKing"))))){
			possible = true;
		}
		return possible;
	}

	
	
	private Stack<Square> getWhiteAttackingSquares(Stack<Square> pieces){
		Stack<Square> piece = new Stack<Square>();
		while(!pieces.empty()){
			Square s = (Square)pieces.pop();
			String tmpString = s.getName();
			if(tmpString.contains("King")){
				Stack<Move> tempKg = getKingMoves(s.getXco(), s.getYco(), s.getName());
				while(!tempKg.empty()){
					Square tempKing = (Square)tempKg.pop().getLanding();
					piece.push(tempKing);
				}
			}
			else if(tmpString.contains("Queen")){
				Stack<Move> tempQ = getQueenMoves(s.getXco(), s.getYco(), s.getName());
				while(!tempQ.empty()){
					Square tempQueen = (Square)tempQ.pop().getLanding();
					piece.push(tempQueen);
				}
			}
			else if(tmpString.contains("Knight")){
				Stack<Move> tempK = getKnightMoves(s.getXco(), s.getYco(), s.getName());
				while(!tempK.empty()){
					Square tempKnight = (Square)tempK.pop().getLanding();
					piece.push(tempKnight);
				}
			}
			else if(tmpString.contains("Rook")){
				Stack<Move> tempR = getRookMoves(s.getXco(), s.getYco(), s.getName());
				while(!tempR.empty()){
					Square tempRook = (Square)tempR.pop().getLanding();
					piece.push(tempRook);
				}
			}
			else if(tmpString.contains("Bishup")){
				Stack<Move> tempB = getBishupMoves(s.getXco(), s.getYco(), s.getName());
				while(!tempB.empty()){
					Square tempBishup = (Square)tempB.pop().getLanding();
					piece.push(tempBishup);
				}
			}
			else{
				Stack<Move> tempP = getWhitePawnMoves(s.getXco(), s.getYco(), s.getName());
				while(!tempP.empty()){
					Square tempPawn = (Square)tempP.pop().getLanding();
					piece.push(tempPawn);
				}
			}
		}
		return piece;
	}
	
	private Stack<Move> getWhitePawnMoves(int x, int y, String piece){
		Square startingSquare = new Square(x, y, piece);
		Stack<Move> moves = new Stack<Move>();
		Move validM, validM2, validM3, validM4;

		if(startingSquare.getYco() == 1){
			Square tmpy = new Square(x, y+1, piece);
			Square tmpy1 = new Square(x, y+2, piece);
			validM = new Move(startingSquare, tmpy);
			validM2 = new Move(startingSquare, tmpy1);
			if(!piecePresent(((tmpy.getXco()*75)+20), (((tmpy.getYco()*75)+20)))){
				moves.push(validM);
			}
			else if(!piecePresent(((tmpy1.getXco()*75)+20), (((tmpy1.getYco()*75)+20)))){
				moves.push(validM2);
			}
		}
		else{
			Square tmpy = new Square(x, y+1, piece);
			Square tmpy2 = new Square(x-1, y+1, piece);
			Square tmpy3 = new Square(x+1, y+1, piece);
			validM = new Move(startingSquare, tmpy);
			validM3 = new Move(startingSquare, tmpy2);
			validM4 = new Move(startingSquare, tmpy3);
			if(!piecePresent(((tmpy.getXco()*75)+20), (((tmpy.getYco()*75)+20)))){
				moves.push(validM);
			}
			else{
				if(checkWhiteOponent(((tmpy2.getXco()*75)+20), (((tmpy2.getYco()*75)+20)))){
					moves.push(validM3);
				}
				else if(checkWhiteOponent(((tmpy3.getXco()*75)+20), (((tmpy3.getYco()*75)+20)))){
					moves.push(validM4);
				}
			}
			
		}
		getLandingSquares(moves);
		return moves;
	}

	/*
	Method to return all the squares that a King can move to. The King can move either one square in an x direction or
	one square in a y direction. it can take its opponents piece but not its own piece. As seen in the below grid the Rook can either move in a horizontal direction (x changing value)
	or in a vertical movement (y changing direction)

								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           |          |           |           |
								-|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           |          |           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           |(x, y-1) |           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |(x-1, y)   | (x, y)  |(x+1, y)   |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           | (x, y+1)|           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           |         |           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           |         |           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
	*/

	private Stack<Move> getKingMoves(int x, int y, String piece){
		Square startingSquare = new Square(x, y, piece);
		Stack<Move> moves = new Stack<Move>();
		Move validM, validM2, validM3;
		int tmpx1 = x+1;
		int tmpx2 = x-1;
		int tmpy1 = y+1;
		int tmpy2 = y-1;
		
		if(!((tmpx1 > 7))){
			Square tmp = new Square(tmpx1, y, piece);
			Square tmp1 = new Square(tmpx2, tmpy1, piece);
			Square tmp2 = new Square(tmpx2, tmpy2, piece);
			if(checkSurroundingSquares(tmp)){
				validM = new Move(startingSquare, tmp);
				if(!piecePresent(((tmp.getXco()*75)+20), (((tmp.getYco()*75)+20)))){
					moves.push(validM);
				}
				else{
					System.out.println("The values that we are going to be looking at are : "+((tmp.getXco()*75)+20)+" and the y value is : "+((tmp.getYco()*75)+20));
					if(checkWhiteOponent(((tmp.getXco()*75)+20), (((tmp.getYco()*75)+20)))){
						moves.push(validM);
					}
				}
			}
			if(!(tmpy1 > 7)){
				if(checkSurroundingSquares(tmp1)){
					validM2 = new Move(startingSquare, tmp1);
					if(!piecePresent(((tmp1.getXco()*75)+20), (((tmp1.getYco()*75)+20)))){
						moves.push(validM2);
					}
					else{
						System.out.println("The values that we are going to be looking at are : "+((tmp1.getXco()*75)+20)+" and the y value is : "+((tmp1.getYco()*75)+20));
						if(checkWhiteOponent(((tmp1.getXco()*75)+20), (((tmp1.getYco()*75)+20)))){
						moves.push(validM2);
						}
					}
				}
			}
			if(!(tmpy2 < 0)){
				if(checkSurroundingSquares(tmp2)){
					validM3 = new Move(startingSquare, tmp2);
					if(!piecePresent(((tmp2.getXco()*75)+20), (((tmp2.getYco()*75)+20)))){
						moves.push(validM3);
					}
					else{
						System.out.println("The values that we are going to be looking at are : "+((tmp2.getXco()*75)+20)+" and the y value is : "+((tmp2.getYco()*75)+20));
						if(checkWhiteOponent(((tmp2.getXco()*75)+20), (((tmp2.getYco()*75)+20)))){
						moves.push(validM3);
						}
					}
				}
			}
		}
		if(!((tmpx2 < 0))){
			Square tmp3 = new Square(tmpx2, y, piece);
			Square tmp4 = new Square(tmpx2, tmpy1, piece);
			Square tmp5 = new Square(tmpx2, tmpy2, piece);
			if(checkSurroundingSquares(tmp3)){
				validM = new Move(startingSquare, tmp3);
				if(!piecePresent(((tmp3.getXco()*75)+20), (((tmp3.getYco()*75)+20)))){
					moves.push(validM);
				}
				else{
					if(checkWhiteOponent(((tmp3.getXco()*75)+20), (((tmp3.getYco()*75)+20)))){
						moves.push(validM);
					}
				}
			}
			if(!(tmpy1 > 7)){
				if(checkSurroundingSquares(tmp4)){
					validM2 = new Move(startingSquare, tmp4);
					if(!piecePresent(((tmp4.getXco()*75)+20), (((tmp4.getYco()*75)+20)))){
						moves.push(validM2);
					}
					else{
						if(checkWhiteOponent(((tmp4.getXco()*75)+20), (((tmp4.getYco()*75)+20)))){
							moves.push(validM2);
						}
					}
				}
			}
			if(!(tmpy2 < 0)){
				if(checkSurroundingSquares(tmp5)){
					validM3 = new Move(startingSquare, tmp5);
					if(!piecePresent(((tmp5.getXco()*75)+20), (((tmp5.getYco()*75)+20)))){
						moves.push(validM3);
					}
					else{
						if(checkWhiteOponent(((tmp5.getXco()*75)+20), (((tmp5.getYco()*75)+20)))){
							moves.push(validM3);
						}
					}
				}
			}
		}

		Square tmp7 = new Square(x, tmpy1, piece);
		Square tmp8 = new Square(x, tmpy2, piece);
		if(!(tmpy1 > 7)){
			if(checkSurroundingSquares(tmp7)){
				validM2 = new Move(startingSquare, tmp7);
				if(!piecePresent(((tmp7.getXco()*75)+20), (((tmp7.getYco()*75)+20)))){
					moves.push(validM2);
				}
				else{
					if(checkWhiteOponent(((tmp7.getXco()*75)+20), (((tmp7.getYco()*75)+20)))){
						moves.push(validM2);
					}
				}
			}
		}
		if(!(tmpy2 < 0)){
			if(checkSurroundingSquares(tmp8)){
				validM3 = new Move(startingSquare, tmp8);
				if(!piecePresent(((tmp8.getXco()*75)+20), (((tmp8.getYco()*75)+20)))){
					moves.push(validM3);
				}
				else{
					if(checkWhiteOponent(((tmp8.getXco()*75)+20), (((tmp8.getYco()*75)+20)))){
						moves.push(validM3);
					}
				}
			}
		}
		getLandingSquares(moves);
		return moves;
	}

	/*
	Method to return all the squares that a Queen can move to. As seen in the below grid, the Queen can move as either the BIshop OR the Rook


								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           | (x, y-3)|           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								| (x-N, y-N)  |           | (x, y-2)|           | (x+N, y-N)|
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             | (x-1, y-1)| (x, y-1)| (x+1, y-1)|           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|  (x-2, y)   | (x-1, y)  | (x, y)  | (x+1, y)  | (x+2, y)  |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |(x-1, y+1) | (x, y+1)| (x+1, y+1)|           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								| (x-N, y+N)  |           | (x, y+2)|           | (x+N, y+N)|
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           | (x, y+3)|           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |

	*/
	private Stack<Move> getQueenMoves(int x, int y, String piece){
		Stack<Move> completeMoves = new Stack<Move>();
  		Stack<Move> tmpMoves = new Stack<Move>();
		Move tmp;
		
		tmpMoves = getRookMoves(x, y, piece);
		while(!tmpMoves.empty()){
			tmp = (Move)tmpMoves.pop();
			completeMoves.push(tmp);
		}
		tmpMoves = getBishupMoves(x, y, piece);
		while(!tmpMoves.empty()){
			tmp = (Move)tmpMoves.pop();
			completeMoves.push(tmp);
		}
		getLandingSquares(completeMoves);
		return completeMoves;
	}

	/*
		Getting all the moves for the Knight piece

		The Knight can move in an L shape
	*/
	private Stack<Move> getKnightMoves(int x, int y, String piece){
		Square startingSquare = new Square(x, y, piece);
		Stack<Square> moves = new Stack<Square>();
		Stack<Move> attacking = new Stack<Move>();

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
			Move tmpMove = new Move(startingSquare, tmp);
			if((tmp.getXco()<0)||(tmp.getXco()>7)||(tmp.getYco()<0)||(tmp.getYco()>7)){

			}
			else if(piecePresent(((tmp.getXco()*75)+20), (((tmp.getYco()*75)+20)))){
				if(piece.contains("White")){
					if(checkWhiteOponent(((tmp.getXco()*75)+20), ((tmp.getYco()*75)+20))){
						attacking.push(tmpMove);
					}
					else{
						System.out.println("Its one of our own pieces");
					}
				}
				else{
					if(checkBlackOponent(tmp.getXco(), tmp.getYco())){
						attacking.push(tmpMove);
					}
				}
			}
			else{
				attacking.push(tmpMove);
			}
		}
		getLandingSquares(attacking);
		return attacking;
	}
	/*
	Method to return all the squares that a Rook can move to. The Rook can either move in an x direction or
	in a y direction as long as there is nothing in the way and it can take its opponents piece but not its
	own piece. As seen in the below grid the Rook can either move in a horizontal direction (x changing value)
	or in a vertical movement (y changing direction)

								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           |(x, y-N) |           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           |(x, y-2) |           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           |(x, y-1) |           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								| (x-N, y)    |(x-1, y)   | (x, y)  |(x+1, y)   |(x+N, y)   |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           | (x, y+1)|           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           |(x, y+2) |           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           |(x, y+N) |           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
	*/
	
	private Stack<Move> getRookMoves(int x, int y, String piece){
		Square startingSquare = new Square(x, y, piece);
		Stack<Move> moves = new Stack<Move>();
		Move validM, validM2, validM3, validM4;
		/*
			There are four possible directions that the Rook can move to:
			- the x value is increasing
			- the x value is decreasing
			- the y value is increasing
			- the y value is decreasing

			Each of these movements should be catered for. The loop guard is set to incriment up to the maximun number of squares.
			On each iteration of the first loop we are adding the value of i to the current x coordinate.
			We make sure that the new potential square is going to be on the board and if it is we create a new square and a new potential
			move (originating square, new square).If there are no pieces present on the potential square we simply add it to the Stack
			of potential moves.
			If there is a piece on the square we need to check if its an opponent piece. If it is an opponent piece its a valid move, but we
			must break out of the loop using the Java break keyword as we can't jump over the piece and search for squares. If its not
			an opponent piece we simply break out of the loop.

			This cycle needs to happen four times for each of the possible directions of the Rook.
		*/
		for(int i=1;i < 8;i++){
			int tmpx = x+i;
			int tmpy = y;
			if(!(tmpx > 7 || tmpx < 0)){
				Square tmp = new Square(tmpx, tmpy, piece);
				validM = new Move(startingSquare, tmp);
				if(!piecePresent(((tmp.getXco()*75)+20), (((tmp.getYco()*75)+20)))){
					moves.push(validM);
				}
				else{
					if(checkWhiteOponent(((tmp.getXco()*75)+20), ((tmp.getYco()*75)+20))){
						moves.push(validM);
						break;
					}
					else{
						break;
					}
				}
			}
		} //end of the loop with x increasing and Y doing nothing...
		for(int j=1;j < 8;j++){
			int tmpx1 = x-j;
			int tmpy1 = y;
			if(!(tmpx1 > 7 || tmpx1 < 0)){
				Square tmp2 = new Square(tmpx1, tmpy1, piece);
				validM2 = new Move(startingSquare, tmp2);
				if(!piecePresent(((tmp2.getXco()*75)+20), (((tmp2.getYco()*75)+20)))){
					moves.push(validM2);
				}
				else{
					if(checkWhiteOponent(((tmp2.getXco()*75)+20), ((tmp2.getYco()*75)+20))){
						moves.push(validM2);
						break;
					}
					else{
						break;
					}
				}
			}
		}//end of the loop with x increasing and Y doing nothing...
		for(int k=1;k < 8;k++){
			int tmpx3 = x;
			int tmpy3 = y+k;
			if(!(tmpy3 > 7 || tmpy3 < 0)){
				Square tmp3 = new Square(tmpx3, tmpy3, piece);
				validM3 = new Move(startingSquare, tmp3);
				if(!piecePresent(((tmp3.getXco()*75)+20), (((tmp3.getYco()*75)+20)))){
					moves.push(validM3);
				}
				else{
					if(checkWhiteOponent(((tmp3.getXco()*75)+20), ((tmp3.getYco()*75)+20))){
						moves.push(validM3);
						break;
					}
					else{
						break;
					}
				}
			}
		}//end of the loop with x increasing and Y doing nothing...
		for(int l=1;l < 8;l++){
			int tmpx4 = x;
			int tmpy4 = y-l;
			if(!(tmpy4 > 7 || tmpy4 < 0)){
				Square tmp4 = new Square(tmpx4, tmpy4, piece);
				validM4 = new Move(startingSquare, tmp4);
				if(!piecePresent(((tmp4.getXco()*75)+20), (((tmp4.getYco()*75)+20)))){
					moves.push(validM4);
				}
				else{
					if(checkWhiteOponent(((tmp4.getXco()*75)+20), ((tmp4.getYco()*75)+20))){
						moves.push(validM4);
						break;
					}
					else{
						break;
					}
				}
			}
		}//end of the loop with x increasing and Y doing nothing...
		getLandingSquares(moves);
		return moves;
	}// end of get Rook Moves.

	/*
	Method to return all the squares that a Bishop can move to. As seen in the below grid, the Bishop
	can move in a diagonal moement. There are essentially four different directions from a single
	square that the Bishop can move along. The Bishop can move any distance along this diagonal
	as long as there is nothing in the way. The Bishop can also take an opponent piece but cannot take its
	own piece.


								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           |         |           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								| (x-N, y-N)  |           |         |           |(x+N, y-N) |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             | (x-1, y-1)|         | (x+1, y-1)|           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           | (x, y)  |           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |(x-1, y+1) |         | (x+1, y+1)|           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|(x-N, y+N)   |           |         |           |(x+N, y+N) |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |
								|             |           |         |           |           |
								_|_____________|___________|_________|___________|___________|_
								|             |           |         |           |           |

	*/

	private Stack<Move> getBishupMoves(int x, int y, String piece){
		Square startingSquare = new Square(x, y, piece);
		Stack<Move> moves = new Stack<Move>();
		Move validM, validM2, validM3, validM4;
		for(int i=1;i < 8;i++){
			int tmpx = x+i;
			int tmpy = y+i;
			if(!(tmpx > 7 || tmpx < 0 || tmpy > 7 || tmpy < 0)){
				Square tmp = new Square(tmpx, tmpy, piece);
				validM = new Move(startingSquare, tmp);
				if(!piecePresent(((tmp.getXco()*75)+20), (((tmp.getYco()*75)+20)))){
					moves.push(validM);
				}
				else{
					if(checkWhiteOponent(((tmp.getXco()*75)+20), ((tmp.getYco()*75)+20))){
						moves.push(validM);
						break;
					}
					else{
						break;
					}
				}
			}
		} // end of the first for Loop
		for(int k=1; k<8; k++){
			int tmpk = x+k;
			int tmpy2 = y-k;
			if(!(tmpk > 7 || tmpk < 0 || tmpy2 > 7 || tmpy2 < 0)){
				Square tmpK1 = new Square(tmpk, tmpy2, piece);
				validM2 = new Move(startingSquare, tmpK1);
				if(!piecePresent(((tmpK1.getXco()*75)+20), (((tmpK1.getYco()*75)+20)))){
					moves.push(validM2);
				}
				else{
					if(checkWhiteOponent(((tmpK1.getXco()*75)+20), ((tmpK1.getYco()*75)+20))){
						moves.push(validM2);
						break;
					}
					else{
						break;
					}
				}
			}
		}// end of the second for loop
		for(int l=1;l < 8;l++){
			int tmpL2 = x-l;
			int tmpy3 = y+l;
			if(!(tmpL2 > 7 || tmpL2 < 0 || tmpy3 > 7 || tmpy3 < 0)){
				Square tmpLMov2 = new Square(tmpL2, tmpy3, piece);
				validM3 = new Move(startingSquare, tmpLMov2);
				if(!piecePresent(((tmpLMov2.getXco()*75)+20), (((tmpLMov2.getYco()*75)+20)))){
					moves.push(validM3);
				}
				else{
					if(checkWhiteOponent(((tmpLMov2.getXco()*75)+20), ((tmpLMov2.getYco()*75)+20))){
						moves.push(validM3);
						break;
					}
					else{
						break;
					}
				}
			}
		}// end of the third for loop 
		for(int n=1;n < 8;n++){
			int tmpN2 = x-n;
			int tmpy4 = y-n;
			if(!(tmpN2 > 7 || tmpN2 < 0 || tmpy4 > 7 || tmpy4 < 0)){
				Square tmpNmov2 = new Square(tmpN2, tmpy4, piece);
				validM4 = new Move(startingSquare, tmpNmov2);
				if(!piecePresent(((tmpNmov2.getXco()*75)+20), (((tmpNmov2.getYco()*75)+20)))){
					moves.push(validM4);
				}
				else{
					if(checkWhiteOponent(((tmpNmov2.getXco()*75)+20), ((tmpNmov2.getYco()*75)+20))){
						moves.push(validM4);
						break;
					}
					else{
						break;
					}
				}
			}
		}// end of the forth loop
		getLandingSquares(moves);
		return moves;
	}

	/*
		A method to color the squares
	*/

	private void colorSquares(Stack<Square> squares){
		Border greenBorder = BorderFactory.createLineBorder(Color.GREEN,3);
		while(!squares.empty()){
			Square s = (Square)squares.pop();
			int location = s.getXco() + ((s.getYco())*8);
			JPanel panel = (JPanel)chessBoard.getComponent(location);
			panel.setBorder(greenBorder);
		}
	}

	/*
    	Method to get the landing square of a bunch of moves...
	*/
	private void getLandingSquares(Stack<Move> found){
		Move tmp;
		Square landing;
		Stack<Square> squares = new Stack<Square>();
		while(!found.empty()){
			tmp = (Move)found.pop();
			landing = (Square)tmp.getLanding();
			squares.push(landing);
		}
		colorSquares(squares);
	}

	private void resetBorders(){
		Border empty = BorderFactory.createEmptyBorder();
		for(int i=0;i < 64;i++){
			JPanel tmppanel = (JPanel)chessBoard.getComponent(i);
			tmppanel.setBorder(empty);
		}
	}

	/*
		The method printStack takes in a Stack of Moves and prints out all possible moves.
	*/
	private void printStack(Stack<Move> input){
	Move m;
	Square s, l;
	while(!input.empty()){
		m = (Move)input.pop();
		s = (Square)m.getStart();
		l = (Square)m.getLanding();
		System.out.println("The possible move that was found is : ("+s.getXco()+" , "+s.getYco()+"), landing at ("+l.getXco()+" , "+l.getYco()+")");
		}
	}


	private void CheckMate(String winner){
        gameOver = true;
        if (winner.contains("White")){
            JOptionPane.showMessageDialog(null, "White wins");
            System.exit(5);
		}
		else{
            JOptionPane.showMessageDialog(null, "Black wins");
            System.exit(5);
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
							// LOOK OVER TAKING PAWNS TO THE LEFT & RIGHT
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
