package View;

import Model.InventoryModel;
import Model.MapModel;
import Types.Resource;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This class is for the view of the inventory. The inventory is represented in
 * a seperate panel in the middle of the infobar panel.
 *
 * @author martin
 */
@SuppressWarnings("serial")
public class InventoryView extends JPanel {
	private MapModel map_model;
	private InventoryModel inventory_model;
	
	private ConcurrentHashMap<Resource, JLabel> labels;
	
	public InventoryView(MapModel mm, InventoryModel im) {
		map_model = mm;
		inventory_model = im;
		
		labels = new ConcurrentHashMap<Resource, JLabel>();
		Resource[] resource_values = Resource.values();
		for(int i=0; i < resource_values.length; i++) {
			JLabel label = new JLabel(Resource.values()[i] + " : " + inventory_model.getAmount(Resource.values()[i]));
			add(label);
		}
		
		setPreferredSize(new Dimension(map_model.GetWidth()/3, map_model.GetHeight()/3));
		setBackground(Color.lightGray);
		
	}
	
	public void drawInventory (Graphics g) {
		
	}
}
