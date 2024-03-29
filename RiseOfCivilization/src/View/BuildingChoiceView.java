package View;

import javax.swing.JFrame;
import Model.BuildingChoiceModel;
import javax.swing.JButton;
import java.awt.*;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * This class is the apears when your try to build onto à city cell.
 * It gives you the choice between mutiple buildings
 *
 * @author Abel
 */
public class BuildingChoiceView extends JFrame {
	private GameView game;
	private BuildingChoiceModel model;

	/**barrack button*/private JButton barrack;
	/**lumber_camp button*/private JButton lumber_camp;
	/**miner_camp button*/private JButton miner_camp;
	/**QuarryWorker_camp button*/ JButton QuarryWorker_camp;

	/**
	 *Constructor of BuildingChoiceView creates all buttons set the grid bag layout and adds it to the JPanel
	 */
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
		QuarryWorker_camp = new JButton("QuarryWorker Camp");
		pane.add(QuarryWorker_camp, cstr);
		
		cstr.gridx = 1;
		cstr.gridy = 2;
		barrack = new JButton("Barrack");
		pane.add(barrack, cstr);
		
		setContentPane(pane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
	
	public JButton GetQuarryWorkerCampButton() {
		return QuarryWorker_camp;
	}
}
