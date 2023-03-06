package View;

import Model.MapModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;


/**
 * This class is for the view of the dashboard. The dashboard is represented in
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
	private InventoryView inventory_view;
	private InfobarView infobar_view;
	
	public DashboardView(MapModel mm, MinimapView mmv, InventoryView iv, InfobarView ibv) {
		map_model = mm;
		minimap_view = mmv;
		inventory_view = iv;
		infobar_view = ibv;
		
		setPreferredSize(new Dimension(map_model.GetWidth()/3, map_model.GetHeight()));
		setLayout(new BorderLayout());
		
		add(minimap_view, BorderLayout.NORTH);
		add(infobar_view, BorderLayout.CENTER);
		add(inventory_view, BorderLayout.SOUTH);
		
	}
}
