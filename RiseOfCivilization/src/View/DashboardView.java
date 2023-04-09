package View;

import Model.GameModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


/**
 * This class is the view of the dashboard. The dashboard is represented in
 * a seperate panel at the right of the map and serves as a container for,
 * from top to bottom :
 * the CellInfoView, the CityInfoCiew and the ActionView.
 * Its default height is the height of the game frame and its default width
 * is a third of the height of the game frame.
 *
 * @author Martin
 */
@SuppressWarnings("serial")
public class DashboardView extends JPanel {
	private GameModel game_model;
	private GameView game_view;
    private int width;
    private int height;
    private CellInfoView cell_info_view;
    private CityInfoView city_info_view;
    private ActionView Action_view;

    /**
     * This is the constructor of the DashboardView. Its creates all of
     * its subviews before adding them to itself. It uses a simple
     * BorderLayout.
     *
     * @param gm the game model
     * @param gv the game view
     */
    public DashboardView(GameModel gm, GameView gv) {
    	game_model = gm;
    	game_view = gv;
    	
        width = game_model.GetPanelWidth();
        height = game_model.GetPanelheight();
        setPreferredSize(new Dimension(width, height));
        setLayout(new BorderLayout());
        
        cell_info_view = new CellInfoView(game_view, this);
        city_info_view = new CityInfoView(game_model, this);
        Action_view = new ActionView(this);

        add(cell_info_view, BorderLayout.NORTH);
        add(city_info_view, BorderLayout.CENTER);
        add(Action_view, BorderLayout.SOUTH);
        
        setBackground(Color.BLACK);
    }
    
    /**
    * Returns the width of the panel
    */
    public int getWidth() {
    	return width;
    }
    
    /**
    * Returns the height of the panel
    */
    public int getHeight() {
    	return height;
    }
    
    /**
    * Returns the cell_info_view of the dashboard
    */
    public CellInfoView getCellInfoView() {
    	return cell_info_view;
    }
    
    /**
    * Returns the city_info_view of the dashboard
    */
    public CityInfoView getCityInfoView() {
    	return city_info_view;
    }
    
    /**
    * Returns the action_view of the dashboard
    */
    public ActionView GetActionView() {
    	return Action_view;
    }
    
    @Override
    public void paint(Graphics G) {
    	super.paint(G);
    }
}
