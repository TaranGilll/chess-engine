import javax.swing.*;
import static java.lang.StrictMath.abs;

public class Queen extends Piece
{

    public Queen(String des) {
        super(des);
    }

    @Override
    public boolean canMove(JButton button1, JButton button2, JButton[][] squares) {

        //if piece on the end Square is the same color as the current
        if (!super.canMove(button1, button2, squares))
            return false;

        //determine button coordinates on the board
        int startX = button1.getX() / 64;
        int startY = button1.getY() / 64;
        int endX = button2.getX() / 64;
        int endY = button2.getY() / 64;

        //check if Queen can move legally (diagonal and in a straight line)
        int x_distance = abs(startX - endX);
        int y_distance = abs(startY - endY);

        if ((x_distance != y_distance) && (startX != endX && startY != endY))
            return false;

        //CHANGE LATER - more more concise if possible (copy of rook and bishop RN)
        String direction = "";

        //moving diagonally (bishop)
        if (x_distance == y_distance)
        {
            //determine direction of movement
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
                else if (direction.equals("se"))
                {
                    if (squares[startY + i][startX + i].getIcon() != null)
                        return false;
                }
            }
        }

        //moving straight (rook)
        else if (startX == endX || startY == endY)
        {
            //determine direction
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
        }

        return true;
    }
}