import javax.swing.*;
import static java.lang.StrictMath.abs;

public class Rook extends Piece
{

    public Rook(String des) {
        super(des);
    }

    @Override
    public boolean canMove(JButton button1, JButton button2, JButton[][] squares)
    {

        //if piece on the end Square is the same color as the current
        if (!super.canMove(button1, button2, squares))
            return false;

        //determine button coordinates on the board
        int startX = button1.getX() / 64;
        int startY = button1.getY() / 64;
        int endX = button2.getX() / 64;
        int endY = button2.getY() / 64;

        //ensure rook moves legally (only in a straight line)
        if (startX != endX && startY != endY)
            return false;

        //determine the direction of movement
        String direction = "";
        if (startX < endX)
            direction = "east";
        else if (startX > endX)
            direction = "west";
        else if (startY < endY)
            direction = "south";
        else if (startY > endY)
            direction = "north";

        //make sure rook doesn't jump over any pieces in its path
        int spaces = 0;
        if (direction.endsWith("st"))
            spaces = abs(startX - endX);
        else if (direction.endsWith("th"))
            spaces = abs(startY - endY);

        for (int i = 1; i < spaces; i++)
        {
            if (direction.equals("east"))
            {
                if (squares[startY][startX + i].getIcon() != null)
                    return false;
            }
            else if (direction.equals("west"))
            {
                if (squares[startY][startX - i].getIcon() != null)
                    return false;
            }
            else if (direction.equals("south"))
            {
                if (squares[startY + i][startX].getIcon() != null)
                    return false;
            }
            else if (direction.equals("north"))
            {
                if (squares[startY - i][startX].getIcon() != null)
                    return false;
            }
        }

        return true;
    }
}