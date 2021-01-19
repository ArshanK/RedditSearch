package project;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * Represents an undirected graph with vertices of type SubReddit.
 * 
 * @author Arshan Khan
 */
public class GUI {

	private JFrame frame;
	private JTextField textbox;
	
	private static JLabel option1;
	private static JLabel option2;
	private static JLabel option3;
	private static JLabel option4;
	private static JLabel option5;
	
	private static JLabel occurence1;
	private static JLabel occurence2;
	private static JLabel occurence3;
	private static JLabel occurence4;
	private static JLabel occurence5;
	
	private static HashMap<String, HashMap<String, Integer>> hashmap;

	/**
	 * Create the application.
	 */
	public GUI() {
		// This needs to be run ONLY once for the whole program. Every other time it should just be sorting based off the words given
		hashmap = DataParser.parseData();
		
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    		initialize();
    		frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 358);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 0, 434, 319);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel title = new JLabel("Welcome to AdGauge");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.WHITE);
		title.setFont(new Font("SansSerif", Font.BOLD, 20));
		title.setBounds(103, 11, 229, 23);
		panel.add(title);
		
		JLabel prompt = new JLabel("What are you advertising?");
		prompt.setHorizontalAlignment(SwingConstants.CENTER);
		prompt.setForeground(Color.WHITE);
		prompt.setFont(new Font("SansSerif", Font.BOLD, 14));
		prompt.setBounds(132, 68, 189, 22);
		panel.add(prompt);
		
		textbox = new JTextField();
		textbox.setToolTipText("");
		textbox.setBounds(83, 101, 147, 20);
		panel.add(textbox);
		textbox.setColumns(10);
		
		JButton button = new JButton("SEARCH");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// If it's not empty, run the search
				if (!textbox.getText().equals("")) {
					System.out.println("button pressed");
					RedditSearch.getTop5(hashmap, textbox.getText());
				}
			}
		});
		button.setBounds(254, 100, 89, 23);
		panel.add(button);
		
		JLabel lblNewLabel = new JLabel("Subreddits to Advertise on");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(22, 144, 196, 14);
		panel.add(lblNewLabel);
		
		JLabel lblOfOccurences = new JLabel("# of Occurences");
		lblOfOccurences.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOfOccurences.setForeground(Color.WHITE);
		lblOfOccurences.setBounds(268, 144, 126, 14);
		panel.add(lblOfOccurences);
		
		option1 = new JLabel("Option 1");
		option1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		option1.setForeground(Color.WHITE);
		option1.setBounds(33, 180, 140, 14);
		panel.add(option1);
		
		option2 = new JLabel("Option 2");
		option2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		option2.setForeground(Color.WHITE);
		option2.setBounds(33, 205, 140, 14);
		panel.add(option2);
		
		option3 = new JLabel("Option 3");
		option3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		option3.setForeground(Color.WHITE);
		option3.setBounds(33, 230, 140, 14);
		panel.add(option3);
		
		option4 = new JLabel("Option 4");
		option4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		option4.setForeground(Color.WHITE);
		option4.setBounds(33, 255, 140, 14);
		panel.add(option4);
		
		option5 = new JLabel("Option 5");
		option5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		option5.setForeground(Color.WHITE);
		option5.setBounds(33, 280, 140, 14);
		panel.add(option5);
		
		occurence1 = new JLabel("0");
		occurence1.setForeground(Color.WHITE);
		occurence1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		occurence1.setBounds(315, 180, 68, 14);
		panel.add(occurence1);
		
		occurence2 = new JLabel("0");
		occurence2.setForeground(Color.WHITE);
		occurence2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		occurence2.setBounds(315, 205, 68, 14);
		panel.add(occurence2);
		
		occurence3 = new JLabel("0");
		occurence3.setForeground(Color.WHITE);
		occurence3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		occurence3.setBounds(315, 230, 68, 14);
		panel.add(occurence3);
		
		occurence4 = new JLabel("0");
		occurence4.setForeground(Color.WHITE);
		occurence4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		occurence4.setBounds(315, 255, 68, 14);
		panel.add(occurence4);
		
		occurence5 = new JLabel("0");
		occurence5.setForeground(Color.WHITE);
		occurence5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		occurence5.setBounds(315, 280, 68, 14);
		panel.add(occurence5);
	}
	
	public static void showResults(String[] options, int[] occurences) {
		option1.setText(options[4]);
		option2.setText(options[3]);
		option3.setText(options[2]);
		option4.setText(options[1]);
		option5.setText(options[0]);
		
		occurence1.setText("" + occurences[4]);
		occurence2.setText("" + occurences[3]);
		occurence3.setText("" + occurences[2]);
		occurence4.setText("" + occurences[1]);
		occurence5.setText("" + occurences[0]);
	}
}
