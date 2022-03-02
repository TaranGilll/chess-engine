import javax.swing.*;
import static java.lang.StrictMath.abs;

public class Knight extends Piece
{

    public Knight(String des) {
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

        int x_direction = abs(startX - endX);
        int y_direction = abs(startY - endY);

        return (x_direction * y_direction == 2);
    }
}