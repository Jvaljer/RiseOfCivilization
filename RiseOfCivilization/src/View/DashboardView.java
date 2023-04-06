package View;

import Model.MapModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


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
	private GameView game;
    private CellInfoView cell_info_view;
    private CityInfoView city_info_view;
    private ActionView Action_view;

    public DashboardView(GameView GV, CellInfoView celliv, CityInfoView cityiv, ActionView ibv) {
    	game = GV;
        cell_info_view = celliv;
        city_info_view = cityiv;
        Action_view = ibv;

        setPreferredSize(new Dimension(game.GetGameModel().GetPanelWidth(),game.GetGameModel().GetPanelheight()));
        setLayout(new BorderLayout());

        add(cell_info_view, BorderLayout.NORTH);
        add(city_info_view, BorderLayout.CENTER);
        add(Action_view, BorderLayout.SOUTH);

    }

    public ActionView GetActionView() {
        return Action_view;
    }
    
    @Override
    public void paint(Graphics G) {
    	super.paint(G);
    }
}
