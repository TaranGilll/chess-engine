import javax.swing.*;
import static java.lang.StrictMath.abs;

public class Pawn extends Piece
{

    public Pawn(String des) {
        super(des);
    }

    @Override
    public boolean canMove(JButton button1, JButton button2, JButton[][] squares)
    {
        //if piece on the end Square is the same color as the current
        if(!super.canMove(button1, button2, squares))
            return false;

        //determine button coordinates on the board
        int startX = button1.getX() / 64;
        int startY = button1.getY() / 64;
        int endX = button2.getX() / 64;
        int endY = button2.getY() / 64;

        int x_direction = abs(startX - endX);
        int y_direction = abs(startY - endY);

        //taking another piece diagonally
        if(x_direction == 1 && y_direction == 1)
        {
            if (this.isWhite() && squares[startY + 1][startX + 1].getIcon() == null)
                return false;
            else if (this.isWhite() && squares[startY + 1][startX - 1].getIcon() == null)
                return false;
            else if (!this.isWhite() && squares[startY - 1][startX + 1].getIcon() == null)
                return false;
            else if (!this.isWhite() && squares[startY + 1][startX + 1].getIcon() == null)
                return false;
        }

        //can only move up one square if no piece in that spot
        else if (y_direction == 1 && x_direction == 0)
        {
            if (this.isWhite() && squares[startY + 1][startX].getIcon() != null)
                return false;
            else if (!this.isWhite() && squares[startY - 1][startX].getIcon() != null)
                return false;
        }

        //can only move up two pieces from "base" spot
        else if (y_direction == 2)
        {
            if (this.isWhite() && startY == 1)
            {
                if(squares[startY + 1][startX].getIcon() != null)
                    return false;
            }
            else if (!this.isWhite() && startY == 6)
            {
                if(squares[startY - 1][startX].getIcon() != null)
                    return false;
            }
            else
                return false;
        }

        //other illegal moves
        else if (x_direction != 0 || y_direction > 2)
            return false;

        //enpasant - ADD
        //if it reaches other side 

        return true;
    }
}