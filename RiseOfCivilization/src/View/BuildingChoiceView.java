package View;

import javax.swing.JFrame;
import Model.BuildingChoiceModel;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class BuildingChoiceView extends JFrame {
	private GameView game;
	private BuildingChoiceModel model;
	
	private JButton barrack;
	private JButton lumber_camp;
	private JButton miner_camp;
	private JButton quarryman_camp;
	
	public BuildingChoiceView(GameView GV, BuildingChoiceModel BCM) {
		super("Building Choice");
		
		game = GV;
		model = BCM;
		
		setPreferredSize(new Dimension(model.GetWidth(),model.GetHeight()));
		
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints cstr = new GridBagConstraints();
		
		cstr.gridx = 0;
		cstr.gridy = 0;
		cstr.anchor = GridBagConstraints.WEST;
		cstr.insets = new Insets(5,10,5,10);
		
		pane.add(new JLabel("Click on the Building you wanna create :"),cstr);
		
		cstr.gridy = 1;
		lumber_camp = new JButton("LumberCamp");
		pane.add(lumber_camp, cstr);
		
		cstr.gridx = 1;
		cstr.gridy = 1;
		miner_camp = new JButton("MinerCamp");
		pane.add(miner_camp, cstr);
		
		cstr.gridx = 0;
		cstr.gridy = 2;
		quarryman_camp = new JButton("QuarryMan Camp");
		pane.add(quarryman_camp, cstr);
		
		cstr.gridx = 1;
		cstr.gridy = 2;
		barrack = new JButton("Barrack");
		pane.add(barrack, cstr);
		
		setContentPane(pane);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
	}
	
	public BuildingChoiceModel GetModel() {
		return model;
	}
	
	public JButton GetBarrackButton() {
		return barrack;
	}
	
	public JButton GetLumberCampButton() {
		return lumber_camp;
	}
	
	public JButton GetMinerCampButton() {
		return miner_camp;
	}
	
	public JButton GetQuarrymanCampButton() {
		return quarryman_camp;
	}
}
