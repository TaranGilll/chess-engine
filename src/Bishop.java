import javax.swing.*;
import static java.lang.StrictMath.abs;

public class Bishop extends Piece
{
    public Bishop(String des) {
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

        //ensure bishop moves legally
        if (abs(startX - endX) != abs(startY - endY))
            return false;

        //determine direction of movement
        String direction = "";
        if (startX > endX && startY > endY)
            direction = "nw";
        else if (startX > endX && startY < endY)
            direction = "sw";
        else if (startX < endX && startY > endY)
            direction = "ne";
        else
            direction = "se";

        //make sure no pieces are being "jumped" in the direction of movement
        int spaces = abs(startX - endX);
        for (int i = 1; i < spaces; i++)
        {
            if (direction.equals("nw"))
            {
                if (squares[startY - i][startX - i].getIcon() != null)
                    return false;
            }
            else if (direction.equals("sw"))
            {
                if (squares[startY + i][startX - i].getIcon() != null)
                    return false;
            }
            else if (direction.equals("ne"))
            {
                if (squares[startY - i][startX + i].getIcon() != null)
                    return false;
            }
            else if (direction.equals("se")) //change
            {
                if (squares[startY + i][startX + i].getIcon() != null)
                    return false;
            }
        }

        return true;
    }
}

