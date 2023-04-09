package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

import Model.GameModel;


/**
 * This class is the view of the dashboard. The dashboard is represented in
 * a seperate panel at the right of the map and contains from top to bottom :
 * the minimap, the inventory and information about the selected cell/worker.
 * Its default height is the height of the map and its default width is a
 * third of the width of the map.
 *
 * @author martin
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
    
    public int getWidth() {
    	return width;
    }
    
    public int getHeight() {
    	return height;
    }
    
    public CellInfoView getCellInfoView() {
    	return cell_info_view;
    }
    
    public CityInfoView getCityInfoView() {
    	return city_info_view;
    }
    
    public ActionView GetActionView() {
    	return Action_view;
    }
    
    @Override
    public void paint(Graphics G) {
    	super.paint(G);
    }
}
