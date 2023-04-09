package View;

import Model.BuildingModel;
import Model.CellModel;
import Model.InventoryModel;
import Model.MapModel;
import Types.CellId;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.HashMap;

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
	private GameView game_view;
	private MapView map_view;
	private DashboardView dashboard_view;
	private CellModel current_cell;
	private HashMap<JLabel, GridBagConstraints> labels_constraints;
	private GridBagLayout layout;
	private JLabel cell_type;
	private GridBagConstraints cell_type_c;
	private JLabel cell_res;
	private GridBagConstraints cell_res_c;
	private JLabel cell_inv;
	private GridBagConstraints cell_inv_c;
	private JLabel cell_max_res;
	private GridBagConstraints cell_max_res_c;
	private JLabel building_type;
	private GridBagConstraints building_type_c;
	private JLabel building_level;
	private GridBagConstraints building_level_c;
	private JLabel building_inv;
	private GridBagConstraints building_inv_c;
	private JLabel building_max_res;
	private GridBagConstraints building_max_res_c;
	private final static int offset = 10;
	
	public CellInfoView(GameView gv, DashboardView dv) {
		game_view = gv;
		map_view = gv.GetMapView();
		dashboard_view = dv;
		labels_constraints = new HashMap<JLabel, GridBagConstraints>();
		
		layout = new GridBagLayout();
		setLayout(layout);
		
		JLabel title = new JLabel("<HTML><U>Cell Information :</U></HTML>");
		title.setFont(title.getFont().deriveFont(18.0f));
		GridBagConstraints title_c = new GridBagConstraints();
		title_c.anchor = GridBagConstraints.NORTHWEST;
		title_c.fill = GridBagConstraints.HORIZONTAL;
		title_c.weightx = 1;
		title_c.weighty = 0;
		title_c.gridwidth = 2;
		title_c.insets = new Insets(0, (int) (offset/2), 0, 0);
		title_c.gridx = 0;
		title_c.gridy = 0;
		add(title, title_c);
		
		cell_type = new JLabel();
		cell_type_c = new GridBagConstraints();
		cell_type_c.anchor = GridBagConstraints.NORTHWEST;
		cell_type_c.fill = GridBagConstraints.HORIZONTAL;
		cell_type_c.weightx = 0.5;
		cell_type_c.weighty = 0;
		cell_type_c.insets = new Insets(0, offset, 0, 0);
		cell_type_c.gridx = 0;
		cell_type_c.gridy = 1;
		labels_constraints.put(cell_type, cell_type_c);
		add(cell_type, cell_type_c);
		
		cell_res = new JLabel();
		cell_res_c = new GridBagConstraints();
		cell_res_c.anchor = GridBagConstraints.NORTHWEST;
		cell_res_c.fill = GridBagConstraints.HORIZONTAL;
		cell_res_c.weightx = 0.5;
		cell_res_c.weighty = 0;
		cell_res_c.insets = new Insets(0, offset, 0, 0);
		cell_res_c.gridx = 0;
		cell_res_c.gridy = 2;
		labels_constraints.put(cell_res, cell_res_c);
		add(cell_res, cell_res_c);
		
		cell_inv = new JLabel();
		cell_inv_c = new GridBagConstraints();
		cell_inv_c.anchor = GridBagConstraints.NORTHWEST;
		cell_inv_c.fill = GridBagConstraints.HORIZONTAL;
		cell_inv_c.weightx = 0.5;
		cell_inv_c.weighty = 0;
		cell_inv_c.insets = new Insets(0, offset, 0, 0);
		cell_inv_c.gridx = 0;
		cell_inv_c.gridy = 3;
		labels_constraints.put(cell_inv, cell_inv_c);
		add(cell_inv, cell_inv_c);
		
		cell_max_res = new JLabel();
		cell_max_res_c = new GridBagConstraints();
		cell_max_res_c.anchor = GridBagConstraints.NORTHWEST;
		cell_max_res_c.fill = GridBagConstraints.HORIZONTAL;
		cell_max_res_c.weightx = 1;
		cell_max_res_c.weighty = 0;
		cell_max_res_c.insets = new Insets(0, offset, 0, 0);
		cell_max_res_c.gridx = 1;
		cell_max_res_c.gridy = 3;
		labels_constraints.put(cell_max_res, cell_max_res_c);
		add(cell_max_res, cell_max_res_c);
		
		building_type = new JLabel();
		building_type_c = new GridBagConstraints();
		building_type_c.anchor = GridBagConstraints.NORTHWEST;
		building_type_c.fill = GridBagConstraints.HORIZONTAL;
		building_type_c.weightx = 0.5;
		building_type_c.weighty = 0;
		building_type_c.insets = new Insets(0, offset, 0, 0);
		building_type_c.gridx = 0;
		building_type_c.gridy = 4;
		labels_constraints.put(building_type, building_type_c);
		add(building_type, building_type_c);
		
		building_level = new JLabel();
		building_level_c = new GridBagConstraints();
		building_level_c.anchor = GridBagConstraints.NORTHWEST;
		building_level_c.fill = GridBagConstraints.HORIZONTAL;
		building_level_c.weightx = 1;
		building_level_c.weighty = 0;
		building_level_c.insets = new Insets(0, offset, 0, 0);
		building_level_c.gridx = 1;
		building_level_c.gridy = 4;
		labels_constraints.put(building_level, building_level_c);
		add(building_level, building_level_c);
		
		building_inv = new JLabel();
		building_inv_c = new GridBagConstraints();
		building_inv_c.anchor = GridBagConstraints.NORTHWEST;
		building_inv_c.fill = GridBagConstraints.HORIZONTAL;
		building_inv_c.weightx = 0.5;
		building_inv_c.weighty = 0;
		building_inv_c.insets = new Insets(0, offset, 0, 0);
		building_inv_c.gridx = 0;
		building_inv_c.gridy = 5;
		labels_constraints.put(building_inv, building_inv_c);
		add(building_inv, building_inv_c);
		
		building_max_res = new JLabel();
		building_max_res_c = new GridBagConstraints();
		building_max_res_c.anchor = GridBagConstraints.NORTHWEST;
		building_max_res_c.fill = GridBagConstraints.HORIZONTAL;
		building_max_res_c.weightx = 1;
		building_max_res_c.weighty = 0;
		building_max_res_c.insets = new Insets(0, offset, 0, 0);
		building_max_res_c.gridx = 1;
		building_max_res_c.gridy = 5;
		labels_constraints.put(building_max_res, building_max_res_c);
		add(building_max_res, building_max_res_c);
		
		for (JLabel label : labels_constraints.keySet()) {
			label.setFont(label.getFont().deriveFont(13.0f));
		}
		
		setPreferredSize(new Dimension(dashboard_view.getWidth(), (int) (dashboard_view.getHeight()/3)));
		setBackground(Color.LIGHT_GRAY);
	}
	
	public void resetConstraints() {		
		for (JLabel label : labels_constraints.keySet()) {
			GridBagConstraints label_c = labels_constraints.get(label);
			label_c.weighty = 0;
			layout.setConstraints(label, label_c);
		}
		
		revalidate();
		repaint();
	}
	
	public void setLowest(JLabel label) {
		resetConstraints();
		
		GridBagConstraints label_c = labels_constraints.get(label);
		label_c.weighty = 1;
		layout.setConstraints(label, label_c);
		
		revalidate();
		repaint();
	}
	
	public void update(CellModel cell_model) {
		
		current_cell = cell_model;
		CellId cell_id = current_cell.GetId();
		
		cell_type.setText("Type : " + cell_id);
		
		if(cell_id == CellId.Plain) {
			cell_res.setText("Resource : None");
			cell_inv.setVisible(false);
			cell_max_res.setVisible(false);
			building_type.setVisible(false);
			building_level.setVisible(false);
			building_inv.setVisible(false);
			building_max_res.setVisible(false);
			
			setLowest(cell_res);
		}
		
		if(cell_id == CellId.City) {
			cell_res.setText("Resource : None");
			cell_inv.setVisible(false);
			cell_max_res.setVisible(false);
			building_type.setVisible(true);
			BuildingModel building = current_cell.getBuilding();
			if(building != null) {
				building_type.setText("Building : " + building.getId());
			} else {
				building_type.setText("Building : None");
			}
			building_level.setVisible(false);
			building_inv.setVisible(false);
			building_max_res.setVisible(false);
			
			setLowest(building_type);
		}

		if(cell_id != CellId.Plain && cell_id != CellId.City) {
			cell_res.setText("Resource : " + current_cell.getResource());
			BuildingModel building = current_cell.getBuilding();
			building_type.setVisible(true);
			if(building != null) {
				cell_max_res.setVisible(false);
				cell_inv.setVisible(false);
				building_type.setText("Building : " + building.getId());
				building_level.setVisible(true);
				building_level.setText("(Lv." + building.GetLevel() + ")");
				InventoryModel inventory = building.GetInventory();
				building_inv.setVisible(true);
				building_inv.setText("Stash : " + inventory.getAmount(building.GetProducedResource()));
				building_max_res.setVisible(true);
				building_max_res.setText("(max=" + inventory.GetMaxAmount() + ")");
				
				setLowest(building_inv);
			} else {
				cell_inv.setVisible(true);
				cell_inv.setText("Amount : " + current_cell.getResourceAmount());
				cell_max_res.setVisible(true);
				cell_max_res.setText("(max=" + current_cell.getInventory().GetMaxAmount() +")");
				building_type.setText("Building : None");
				building_level.setVisible(false);
				building_inv.setVisible(false);
				building_max_res.setVisible(false);
				
				setLowest(building_type);
			}
		}
		
		revalidate();
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if(current_cell!=null) {
			CellView cell_view = new CellView(map_view.GetGrid()[current_cell.GetX()][current_cell.GetY()]);
			cell_view.setAbs(0);
			cell_view.setOrd(0);
			cell_view.DrawCell(g, 60, 70, 185, true);
			
			if(current_cell.hasBuilding()) {
				for(BuildingView b_view : game_view.GetBuildingsView()) {
					if(b_view.getCoord().equals(current_cell.GetCoord()))
					b_view.DrawImageFromId(b_view.getModel().getId(), g, 70, 185, true);
				}
			}
		}
	}
}