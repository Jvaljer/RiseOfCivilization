package View;

import Model.MapModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import java.awt.*;

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
    private MapModel map_model;
    private MinimapView minimap_view;
    private CityInfoView city_info_view;
    private ActionView Action_view;

    public DashboardView(MapModel mm, MinimapView mmv, CityInfoView civ, ActionView ibv) {
        map_model = mm;
        minimap_view = mmv;
        city_info_view = civ;
        Action_view = ibv;

        setPreferredSize(new Dimension(map_model.GetWidth()/3, map_model.GetHeight()));
        setLayout(new BorderLayout());

        add(minimap_view, BorderLayout.NORTH);
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
