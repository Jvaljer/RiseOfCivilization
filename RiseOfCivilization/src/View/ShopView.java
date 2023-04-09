package View;
import javax.swing.ButtonGroup;
import Types.Resource;
import Model.ShopModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JButton;
import java.awt.*;

public class ShopView extends JFrame implements ChangeListener {
	private GameView game;
	private ShopModel model;
	
    private final JSlider woodSlider;
    private final JSlider stoneSlider;
    private final JSlider ironSlider;
    private final JLabel buy_label;
    private final JLabel sell_label;

    private JButton buy_button;
    private JButton sell_button;
    
    private int buy_price;
    private int sell_price;
    private int player_gold;
    
    private int wood_amount;
    private int stone_amount;
    private int iron_amount;
    
    public ShopView(GameView GV, ShopModel SM) {
        super("Shop Interface");
        game = GV;
        model = SM;
        player_gold = game.GetGameModel().getInventoryModel().getAmount(Resource.Gold);
        
        setPreferredSize(new Dimension(model.GetWidth(),model.GetHeight()));

        // Initialization of sliders
        woodSlider = new JSlider(0, 500, 0);
        woodSlider.setMajorTickSpacing(100);
        woodSlider.setMinorTickSpacing(50);
        woodSlider.setPaintTicks(true);
        woodSlider.setPaintLabels(true);
        woodSlider.addChangeListener(this);

        stoneSlider = new JSlider(0, 500, 0);
        stoneSlider.setMajorTickSpacing(100);
        stoneSlider.setMinorTickSpacing(50);
        stoneSlider.setPaintTicks(true);
        stoneSlider.setPaintLabels(true);
        stoneSlider.addChangeListener(this);

        ironSlider = new JSlider(0, 500, 0);
        ironSlider.setMajorTickSpacing(100);
        ironSlider.setMinorTickSpacing(50);
        ironSlider.setPaintTicks(true);
        ironSlider.setPaintLabels(true);
        ironSlider.addChangeListener(this);

        // Initialization of price labels
        buy_label = new JLabel("Buy Price : 0 gold");
        sell_label = new JLabel("Sell Price : 0 gold");

        // Initialization of radio button to buy or sell
        buy_button = new JButton("Buy");
        sell_button = new JButton("Sell");

        // Initialization of main panel
        JPanel mainPanel = new JPanel(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        mainPanel.add(new JLabel("Wood -"), c);

        c.gridx = 1;
        mainPanel.add(woodSlider, c);

        c.gridx = 0;
        c.gridy = 1;
        mainPanel.add(new JLabel("Stone -"), c);

        c.gridx = 1;
        mainPanel.add(stoneSlider, c);

        c.gridx = 0;
        c.gridy = 2;
        mainPanel.add(new JLabel("Iron -"), c);

        c.gridx = 1;
        mainPanel.add(ironSlider, c);

        c.insets = new Insets(5,10,5,10);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        mainPanel.add(buy_label, c);

        c.gridx = 0;
        c.gridy = 4;
        mainPanel.add(sell_label, c);
        
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        mainPanel.add(buy_button, c);
        
        c.gridx = 1;
        mainPanel.add(sell_button, c);

        // Configuration of the window
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    @Override
    public void stateChanged(ChangeEvent e) {
        // Get slider values
        wood_amount = RoundValue(woodSlider.getValue());
        stone_amount = RoundValue(stoneSlider.getValue());
        iron_amount = RoundValue(ironSlider.getValue());

        // Calculate buy/sell prices
        int wood_buy_price = wood_amount * 2;
        int stone_buy_price = stone_amount * 3;
        int iron_buy_price = iron_amount * 5;
        
        int wood_sell_price = wood_amount * 1;
        int stone_sell_price = stone_amount * 2;
        int iron_sell_price = iron_amount * 4;

        // Display buy/sell prices depending on user selection
        buy_price = wood_buy_price + stone_buy_price + iron_buy_price;
        buy_label.setText("Buying Price : " + buy_price + " gold");
        buy_button.setEnabled(player_gold - buy_price >=0);
        
        sell_price = wood_sell_price + stone_sell_price + iron_sell_price;
        sell_label.setText("Selling Price : " + sell_price + " gold");
        }

    private int RoundValue(int value) {
        return Math.round(value/10f) * 10;
    }
    
    public JButton GetBuyButton() {
    	return buy_button;
    }
    
    public JButton GetSellButton() {
    	return sell_button;
    }
    
    public int GetBuyPrice() {
    	return buy_price;
    }
    
    public int GetSellPrice() {
    	return sell_price;
    }
    
    public int GetPlayerGold() {
    	return player_gold;
    }
    
    public int GetWoodAmount() {
    	return wood_amount;
    }
    
    public int GetStoneAmount() {
    	return stone_amount;
    }
    
    public int GetIronAmount() {
    	return iron_amount;
    }
}
