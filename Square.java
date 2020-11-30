public class Square {
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