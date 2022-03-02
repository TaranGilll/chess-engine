import javax.swing.*;

public abstract class Piece {
    //instance variables
    private boolean white;
    private boolean alive;

    //constructor - initializes the color of the piece and sets it to alive when the game begins
    public Piece(String des) {
        if (des.contains("white"))
            white = true;
        else
            white = false;
        alive = true;
    }

    //determine the status of the piece: alive or dead
    public boolean isAlive() {
        return alive;
    }

    //determine the color of the piece: white or black
    public boolean isWhite() { return white;}

    //change the status of the piece depending on how the game progresses
    public void setStatus(boolean status) {
        alive = status;
    }

    //determine if the piece can make a legal move
    //pieces cannot move if they compromise a check!! - ADD ALL
    //static or not
    public boolean canMove(JButton button1, JButton button2, JButton[][] squares)
    {
        String des1 = "";
        String des2 = "";
        if (button1.getIcon() != null)
            des1 = ((ImageIcon)button1.getIcon()).getDescription();
        if (button2.getIcon() != null) {
            des2 = ((ImageIcon) button2.getIcon()).getDescription();
        }

        if ( (des1.contains("white") && des2.contains("white")) || (des1.contains("black") && des2.contains("black")) )
            return false;

        return true;
    }

}
