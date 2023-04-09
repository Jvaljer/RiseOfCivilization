package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Contains all Icons shown on the map
 *
 * @author Amaury
 */

public class CellIcon {
    public static Image Forest;
    public static Image Plain;
    public static Image Mountain;
    public static Image Iron_Deposit;

    public static Image CityHall;
    public static Image SawMill;
    public static Image Mine;
    public static Image Quarry;
    public static Image LumberCamp;
    public static Image MinerCamp;
    public static Image QuarryWorkerCamp;
    public static Image Barrack;
    public static Image Shop;

    public CellIcon(){
        try {
            Forest = ImageIO.read(new File("./icons/Forest.png"));
            Plain = ImageIO.read(new File("./icons/Plain.png"));
            Mountain = ImageIO.read(new File("./icons/Mountain.png"));
            Iron_Deposit = ImageIO.read(new File("./icons/Iron_Deposit.png"));


            CityHall = ImageIO.read(new File("./icons/CityHall.png"));
            SawMill=ImageIO.read(new File("./icons/SawMill.png"));
            Mine = ImageIO.read(new File("./icons/Mine.png"));
            Quarry = ImageIO.read(new File("./icons/Quarry.png"));
            LumberCamp = ImageIO.read(new File("./icons/LumberCamp.png"));
            MinerCamp = ImageIO.read(new File("./icons/MinerCamp.png"));
            QuarryWorkerCamp = ImageIO.read(new File("./icons/QuarryWorkerCamp.png"));
            Barrack = ImageIO.read(new File("./icons/Barrack.png"));
            Shop = ImageIO.read(new File("./icons/Shop_Bulding.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

