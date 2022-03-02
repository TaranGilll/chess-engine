import javax.swing.*;
import static java.lang.StrictMath.abs;

public class King extends Piece
{
    boolean castled = false;

    public King(String des) {
        super(des);
    }

    //when the King castles
    public void setCastled() { castled = false; }

    //determine if King has castled or not
    public boolean isCastled() { return castled; }

    //determine if it is castling move
    public boolean isCastlingMove(int startX, int endX, int startY, int endY)
    {
        if (isCastled())
            return false;
        else if (startX != 3)
            return false;
        else if (startY != endY)
            return false;
        else if (endX != 1 || endX != 5)
            return false;

        return true;
    }

    //determine if moving to a certain position puts the King at check
    //static so that it can be accessed
    public static boolean inCheckPosition(JButton button2)
    {
        return false;
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

        //ensure it can move legally (diagonal or straight one square)
        int x_distance = abs(startX - endX);
        int y_distance = abs(startY - endY);

        //ensure legal moves
        if (!isCastlingMove(startX, endX, startY, endY)) {
            if (!(x_distance != y_distance) && !(startX != endX && startY != endY))
                return false;
            else if (x_distance > 1 || y_distance > 1)
                return false;
        } else {
            System.out.println("Ho");
            if (squares[startY][endX].getIcon() != null)
                return false;
            else if (endX == 1) {
                //ensure bishop spot is empty
                if (squares[startY][startX - 1] != null)
                    return false;
                //ensure rook is at its spot
                if (!Board.getDescription(squares[startY][0]).contains("Rook"))
                    return false;
            } else if (endX == 5) {
                //ensure king and bishop spot empty
                for (int i = startX + 1; i < 6; i++) {
                    if (squares[startY][i].getIcon() != null)
                        return false;
                }
            }
            //ensure rook it at its point
            if (!Board.getDescription(squares[startY][0]).contains("Rook"))
                return false;
        }

        //check if proposed move will result in a check
        if (inCheckPosition(button2)) return false;

        return true;
    }
}
