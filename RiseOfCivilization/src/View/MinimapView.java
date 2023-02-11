package View;

import Model.MapModel;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

/**
 * This class is for the view of the minimap. The minimap is represented in
 * a seperate panel at the top of the infobar panel.
 * Its default height is a third of the height of the infobar.
 *
 * @author martin
 */
@SuppressWarnings("serial")
public class MinimapView extends JPanel {
	private MapModel map_model;
	
	public MinimapView(MapModel mm) {
		map_model = mm;
		setPreferredSize(new Dimension(map_model.GetWidth()/3, map_model.GetHeight()/3));
		setBackground(Color.BLUE);
		
	}
}
