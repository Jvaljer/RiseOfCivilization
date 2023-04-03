package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CellIcon {
    public static Image Forest;
    public static Image Plain;
    public static Image Mountain;
    public static Image Iron_Deposit;

    public CellIcon(){
        try {
            Forest = ImageIO.read(new File("./icons/Forest.png"));
            Plain = ImageIO.read(new File("./icons/Plain.png"));
            Mountain = ImageIO.read(new File("./icons/Mountain.png"));
            Iron_Deposit = ImageIO.read(new File("./icons/Iron_Deposit.png"));


        } catch (IOException e) {
            throw new RuntimeException(e);
            }
    }


}

