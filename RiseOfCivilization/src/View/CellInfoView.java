package View;

import Model.BuildingModel;
import Model.CellModel;
import Model.InventoryModel;
import Types.CellId;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * This class is the view of the minimap. The minimap is represented in
 * a seperate panel at the top of the infobar panel.
 * Its default height is a third of the height of the infobar.
 *
 * @author martin
 */
@SuppressWarnings("serial")
public class CellInfoView extends JPanel {
	private DashboardView dashboard_view;
	private HashMap<JLabel, GridBagConstraints> labels_constraints;
	private GridBagLayout layout;
	private JLabel cell_type;
	private GridBagConstraints cell_type_constr;
	private JLabel cell_resource;
	private GridBagConstraints cell_resource_constr;
	private JLabel cell_inventory;
	private GridBagConstraints cell_inventory_constr;
	private JLabel cell_max_resource;
	private GridBagConstraints cell_max_resource_constr;
	private JLabel building_type;
	private GridBagConstraints building_type_constr;
	private JLabel building_level;
	private GridBagConstraints building_level_constr;
	private JLabel building_inventory;
	private GridBagConstraints building_inventory_constr;
	private JLabel building_max_resource;
	private GridBagConstraints building_max_resource_constr;
	
	public CellInfoView(DashboardView dv) {
		dashboard_view = dv;
		labels_constraints = new HashMap<JLabel, GridBagConstraints>();
		
		layout = new GridBagLayout();
		setLayout(layout);		
		
		cell_type = new JLabel();
		cell_type_constr = new GridBagConstraints();
		cell_type_constr.anchor = GridBagConstraints.NORTHWEST;
		cell_type_constr.fill = GridBagConstraints.HORIZONTAL;
		cell_type_constr.weightx = 0.5;
		cell_type_constr.weighty = 0;
		cell_type_constr.gridx = 0;
		cell_type_constr.gridy = 0;
		labels_constraints.put(cell_type, cell_type_constr);
		add(cell_type, cell_type_constr);
		
		cell_resource = new JLabel();
		cell_resource_constr = new GridBagConstraints();
		cell_resource_constr.anchor = GridBagConstraints.NORTHWEST;
		cell_resource_constr.fill = GridBagConstraints.HORIZONTAL;
		cell_resource_constr.weightx = 0.5;
		cell_resource_constr.weighty = 0;
		cell_resource_constr.gridx = 0;
		cell_resource_constr.gridy = 1;
		labels_constraints.put(cell_resource, cell_resource_constr);
		add(cell_resource, cell_resource_constr);
		
		cell_inventory = new JLabel();
		cell_inventory_constr = new GridBagConstraints();
		cell_inventory_constr.anchor = GridBagConstraints.NORTHWEST;
		cell_inventory_constr.fill = GridBagConstraints.HORIZONTAL;
		cell_inventory_constr.weightx = 0.5;
		cell_inventory_constr.weighty = 0;
		cell_inventory_constr.gridx = 0;
		cell_inventory_constr.gridy = 2;
		labels_constraints.put(cell_inventory, cell_inventory_constr);
		add(cell_inventory, cell_inventory_constr);
		
		cell_max_resource = new JLabel();
		cell_max_resource_constr = new GridBagConstraints();
		cell_max_resource_constr.anchor = GridBagConstraints.NORTHWEST;
		cell_max_resource_constr.fill = GridBagConstraints.HORIZONTAL;
		cell_max_resource_constr.weightx = 1;
		cell_max_resource_constr.weighty = 0;
		cell_max_resource_constr.gridx = 1;
		cell_max_resource_constr.gridy = 2;
		labels_constraints.put(cell_max_resource, cell_max_resource_constr);
		add(cell_max_resource, cell_max_resource_constr);
		
		building_type = new JLabel();
		building_type_constr = new GridBagConstraints();
		building_type_constr.anchor = GridBagConstraints.NORTHWEST;
		building_type_constr.fill = GridBagConstraints.HORIZONTAL;
		building_type_constr.weightx = 0.5;
		building_type_constr.weighty = 0;
		building_type_constr.gridx = 0;
		building_type_constr.gridy = 3;
		labels_constraints.put(building_type, building_type_constr);
		add(building_type, building_type_constr);
		
		building_level = new JLabel();
		building_level_constr = new GridBagConstraints();
		building_level_constr.anchor = GridBagConstraints.NORTHWEST;
		building_level_constr.fill = GridBagConstraints.HORIZONTAL;
		building_level_constr.weightx = 1;
		building_level_constr.weighty = 0;
		building_level_constr.gridx = 1;
		building_level_constr.gridy = 3;
		labels_constraints.put(building_level, building_level_constr);
		add(building_level, building_level_constr);
		
		building_inventory = new JLabel();
		building_inventory_constr = new GridBagConstraints();
		building_inventory_constr.anchor = GridBagConstraints.NORTHWEST;
		building_inventory_constr.fill = GridBagConstraints.HORIZONTAL;
		building_inventory_constr.weightx = 0.5;
		building_inventory_constr.weighty = 0;
		building_inventory_constr.gridx = 0;
		building_inventory_constr.gridy = 4;
		labels_constraints.put(building_inventory, building_inventory_constr);
		add(building_inventory, building_inventory_constr);
		
		building_max_resource = new JLabel();
		building_max_resource_constr = new GridBagConstraints();
		building_max_resource_constr.anchor = GridBagConstraints.NORTHWEST;
		building_max_resource_constr.fill = GridBagConstraints.HORIZONTAL;
		building_max_resource_constr.weightx = 1;
		building_max_resource_constr.weighty = 0;
		building_max_resource_constr.gridx = 1;
		building_max_resource_constr.gridy = 4;
		labels_constraints.put(building_max_resource, building_max_resource_constr);
		add(building_max_resource, building_max_resource_constr);
		
		for (JLabel label : labels_constraints.keySet()) {
			label.setFont(label.getFont().deriveFont(15.0f));
		}
		
		int width = dashboard_view.getWidth();
		setPreferredSize(new Dimension(width, width));
		setBackground(Color.LIGHT_GRAY);
	}
	
	public void resetConstraints() {		
		for (JLabel label : labels_constraints.keySet()) {
			GridBagConstraints label_constr = labels_constraints.get(label);
			label_constr.weighty = 0;
			layout.setConstraints(label, label_constr);
		}
		
		revalidate();
		repaint();
	}
	
	public void setLowest(JLabel label) {
		resetConstraints();
		
		GridBagConstraints label_constr = labels_constraints.get(label);
		label_constr.weighty = 1;
		layout.setConstraints(label, label_constr);
		
		revalidate();
		repaint();
	}
	
	public void update(CellModel cell_model) {
		CellId cell_id = cell_model.GetId();
		
		cell_type.setText("Type : " + cell_id);
		
		if(cell_id == CellId.Plain) {
			cell_resource.setText("Resources : None");
			cell_inventory.setVisible(false);
			cell_max_resource.setVisible(false);
			building_type.setVisible(false);
			building_level.setVisible(false);
			building_inventory.setVisible(false);
			building_max_resource.setVisible(false);
			
			setLowest(cell_resource);
		}
		
		if(cell_id == CellId.City) {
			cell_resource.setText("Resources : None");
			cell_inventory.setVisible(false);
			cell_max_resource.setVisible(false);
			building_type.setVisible(true);
			BuildingModel building = cell_model.getBuilding();
			if(building != null) {
				building_type.setText("Building : " + building.getId());
			} else {
				building_type.setText("Building : None");
			}
			building_level.setVisible(false);
			building_inventory.setVisible(false);
			building_max_resource.setVisible(false);
			
			setLowest(building_type);
		}

		if(cell_id != CellId.Plain && cell_id != CellId.City) {
			cell_resource.setText("Resources : " + cell_model.getResource());
			BuildingModel building = cell_model.getBuilding();
			building_type.setVisible(true);
			if(building != null) {
				cell_max_resource.setVisible(false);
				cell_inventory.setVisible(false);
				building_type.setText("Building : " + building.getId());
				building_level.setVisible(true);
				building_level.setText("(Lv." + building.GetLevel() + ")");
				InventoryModel inventory = building.GetInventory();
				building_inventory.setVisible(true);
				building_inventory.setText("Stash : " + inventory.getAmount(building.GetProducedResource()));
				building_max_resource.setVisible(true);
				building_max_resource.setText("(max = " + inventory.GetMaxAmount() + ")");
				
				setLowest(building_inventory);
			} else {
				cell_inventory.setVisible(true);
				cell_inventory.setText("Amount : " + cell_model.getResourceAmount());
				cell_max_resource.setVisible(true);
				cell_max_resource.setText("(max = " + cell_model.getInventory().GetMaxAmount() +")");
				building_type.setText("Building : None");
				building_level.setVisible(false);
				building_inventory.setVisible(false);
				building_max_resource.setVisible(false);
				
				setLowest(building_type);
			}
		}
	}
}