package View;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.GridLayout;
import javax.swing.JButton;

public class ShopView extends JFrame implements ChangeListener {
    private final JSlider woodSlider;
    private final JSlider stoneSlider;
    private final JSlider ironSlider;
    private final JLabel buyPriceLabel;
    private final JLabel sellPriceLabel;
    private boolean isBuying = true; // variable to store if user wants to buy or sell

    public ShopView() {
        super("Material Trader");

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
        buyPriceLabel = new JLabel("Buy Price : 0");
        sellPriceLabel = new JLabel("Sell Price : 0");

        // Initialization of radio button to buy or sell
        JButton buy = new JButton("Buy");
        JButton sell = new JButton("Sell");

        // Initialization of main panel
        JPanel mainPanel = new JPanel(new GridLayout(4, 2));
        mainPanel.add(new JLabel("Wood -"));
        mainPanel.add(woodSlider);
        mainPanel.add(new JLabel("Stone -"));
        mainPanel.add(stoneSlider);
        mainPanel.add(new JLabel("Iron -"));
        mainPanel.add(ironSlider);
        mainPanel.add(buyPriceLabel);
        mainPanel.add(sellPriceLabel);

        // Adding button to main panel
        mainPanel.add(buy);
        mainPanel.add(sell);
        
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
        int wood = RoundToNearestHundred(woodSlider.getValue());
        int stone = RoundToNearestHundred(stoneSlider.getValue());
        int iron = RoundToNearestHundred(ironSlider.getValue());

        // Calculate buy/sell prices
        int wood_buy_price = wood * 2;
        int stone_buy_price = stone * 3;
        int iron_buy_price = iron * 5;
        
        int wood_sell_price = wood * 1;
        int stone_sell_price = stone * 2;
        int iron_sell_price = iron * 4;

        // Display buy/sell prices depending on user selection
        int buy_price = wood_buy_price + stone_buy_price + iron_buy_price;
        buyPriceLabel.setText("Buying Price : " + buy_price);
        
        int sell_price = wood_sell_price + stone_sell_price + iron_sell_price;
        sellPriceLabel.setText("Selling Price : " + sell_price);
        
    }

    private int RoundToNearestHundred(int value) {
        return Math.round(value / 100f) * 100;
    }
}
