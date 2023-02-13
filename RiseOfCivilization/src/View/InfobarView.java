package View;

import Model.MapModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;


/**
 * This class is for the view of the infobar. The infobar is represented in
 * a seperate panel at the right of the map and contains from top to bottom :
 * the minimap, the inventory and information about the selected cell/worker.
 * Its default height is the height of the map and its default width is a
 * third of the width of the map.
 *
 * @author martin
 */
@SuppressWarnings("serial")
public class InfobarView extends JPanel {
	private MapModel map_model;
	private MinimapView minimap_view;
	private InventoryView inventory_view;
	
	public InfobarView(MapModel mm, MinimapView mmv, InventoryView iv) {
		map_model = mm;
		minimap_view = mmv;
		inventory_view = iv;
		
		setPreferredSize(new Dimension(map_model.GetWidth()/3, map_model.GetHeight()));
		setLayout(new BorderLayout());
		
		add(minimap_view, BorderLayout.NORTH);
		add(inventory_view, BorderLayout.CENTER);
		
	}
}