package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultCaret;

import org.apache.commons.lang3.StringUtils;

import controller.Fortification;
import controller.DataInitialization;
import controller.Reinforcement;
import model.SelectionOfArmies;
import model.Continent;
import model.RiskPlayers;
import model.Territories;

/**
 * 
 * User Interface for Game Play
 */
public class Panels implements ActionListener, ListSelectionListener {
	/**
	 * @param frame     Frame object
	 * @param players   Player model object
	 * @param territory Territory model object
	 * @param continent Continent model object
	 */

	JFrame frame;
	RiskPlayers players;
	Territories territory;
	Continent continent;
	int playerTurn = 0;
	String newButnName = "New Game";
	String exitButnName = "Quit";
	String twoPlayersButnName = "twoPlayersButn";
	String threePlayersButnName = "threePlayersButn";
	String fourPlayersButnName = "fourPlayersButn";
	String fivePlayersButnName = "fivePlayersButn";
	String createNewMapButnName = "Create New Map";
	String editExistingMapButnName = "Edit Existing Map";
	String saveButnName = "Save";
	String backButnName = "backBtn";
	String existingMapFilePath;

	private String editMapButnName = "Edit Butn";
	private String mapFilePath;
	int playersPlaying;
	private GridLayout mainLayout;
	private JButton reinforceButn;
	private JButton attackButn;
	private JButton fortifyButn;
	private JButton endTurnButn;
	private JButton menuButn;
	private JButton turnInButn;
	private JButton createNewMapButn;
	private JButton editExistingMapButn;
	private JButton newButn;
	private JButton exitButn;
	private JButton twoPlayersButn;
	private JButton threePlayersButn;
	private JButton fourPlayersButn;
	private JButton fivePlayersButn;
	private JButton backButn;
	private JButton editButn;
	private JButton startGameButn;

	private JTextArea territoryDetails;
	private JTextArea logArea;
	private JList<String> cardsList;
	private JList<String> territoryAList;
	private JList<String> territoryBList;
	private JList<String> continentInfoList;
	private JList<String> territoryInfoList;
	private GridBagConstraints con;
	private DefaultCaret caret;

	private JRadioButton mapOptionA;
	private JRadioButton mapOptionB;

	private boolean randomMap = true;
	private DefaultListModel<String> territoryAModel;
	private DefaultListModel<String> territoryBModel;
	private DefaultListModel<String> continentInfoModel;
	private DefaultListModel<String> territoryInfoModel;

	private JComboBox<String> territoryADropDown;
	private JComboBox<String> territoryBDropDown;
	private SpinnerNumberModel selectArmyModel;
	private JLabel fortErrorMsg;
	public static JTextArea log = new JTextArea(25, 20);

	/**
	 * Setting up the number of Players.
	 * 
	 * @return playerPanel
	 */
	public JPanel playersMenu() {
		// Creates the panel
		JPanel playerPanel = new JPanel();
		// Sets Layout
		LayoutManager playerLayout = new GridLayout(6, 1, 5, 5);
		playerPanel.setLayout(playerLayout);

		JLabel playerCountLabel = new JLabel("Number of Players : ");
		twoPlayersButn = new JButton("Two");
		threePlayersButn = new JButton("Three");
		fourPlayersButn = new JButton("Four");
		fivePlayersButn = new JButton("Five");
		backButn = new JButton("Back");

		playerPanel.add(playerCountLabel);
		playerPanel.add(twoPlayersButn);
		playerPanel.add(threePlayersButn);
		playerPanel.add(fourPlayersButn);
		playerPanel.add(fivePlayersButn);
		playerPanel.add(backButn);

		twoPlayersButn.addActionListener(this);
		threePlayersButn.addActionListener(this);
		fourPlayersButn.addActionListener(this);
		fivePlayersButn.addActionListener(this);
		backButn.addActionListener(this);

		twoPlayersButn.setActionCommand(twoPlayersButnName);
		threePlayersButn.setActionCommand(threePlayersButnName);
		fourPlayersButn.setActionCommand(fourPlayersButnName);
		fivePlayersButn.setActionCommand(fivePlayersButnName);
		backButn.setActionCommand(backButnName);
		return playerPanel;
	}

	/**
	 * Create New File or Update Existing one Navigation Panel
	 * 
	 * @return editMapPanel
	 */
	protected JPanel editMapPanel() {

		// Creates the panel
		JPanel editMapPanel = new JPanel();
		// Sets Layout
		GridLayout editMapLayout = new GridLayout(2, 1, 5, 5);
		editMapPanel.setLayout(editMapLayout);
		// Creates buttons
		createNewMapButn = new JButton("Create new map");
		editExistingMapButn = new JButton("Edit Existing map");
		editMapPanel.add(createNewMapButn);
		editMapPanel.add(editExistingMapButn);
		createNewMapButn.addActionListener(this);
		editExistingMapButn.addActionListener(this);
		createNewMapButn.setActionCommand(createNewMapButnName);
		editExistingMapButn.setActionCommand(editExistingMapButnName);
		return editMapPanel;

	}

	/**
	 * method used for Creating new Map from scratch
	 * 
	 * @return createMapPanel
	 */
	protected JPanel createMapPanel() {

		frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setResizable(true);
		frame.setVisible(true);
		NewEMPanel newEditMapPanel = new NewEMPanel();
		return newEditMapPanel.createMapPanel(frame, false);
	}

	/**
	 * method used for Editing Existing Map
	 * 
	 * @return existingMapPanel
	 */
	protected JPanel editExistingMapPanel() {
		frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setResizable(true);
		frame.setVisible(true);
		NewEMPanel newEditMapPanel = new NewEMPanel();
		return newEditMapPanel.createMapPanel(frame, true);
	}

	/**
	 * 
	 * @return returns Jpanel of logs
	 */
	protected JPanel displayLog() {
		JPanel logPanel = new JPanel();
		logPanel.setSize(new Dimension(300, 600));
		GridBagLayout eventLayout = new GridBagLayout();
		logPanel.setLayout(eventLayout);

		menuButn = new JButton("Menu");
		menuButn.setActionCommand(backButnName);
		menuButn.addActionListener(this);

		logArea = new JTextArea(4, 20);
		logArea.setFocusable(false);
		logArea.setLineWrap(true);
		logArea.setWrapStyleWord(true);
		caret = (DefaultCaret) logArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane logScrollPane = new JScrollPane(logArea);
		logScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		updateLogArea();

		JScrollPane jScrollPane = new JScrollPane(log);
		jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		logPanel.add(menuButn,
				setGridBagConstraints(new Insets(5, 0, 20, 5), GridBagConstraints.HORIZONTAL, 0.5, 0.5, 0, 0));
		logPanel.add(logScrollPane,
				setGridBagConstraints(new Insets(0, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 5, 0, 1));
		logPanel.add(jScrollPane, setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 5, 0, 2));

		return logPanel;
	}

	public static void riskLogger(String logString) {
		log.append(logString + "\n");
	}

	/**
	 * 
	 * @return GamePanel object which consist portion of Game Play
	 */
	protected JPanel gameView() {

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width,
				Toolkit.getDefaultToolkit().getScreenSize().height);
		frame.setResizable(true);
		JPanel gamePanel = new JPanel();
		frame.setLayout(mainLayout);

		gamePanel.add(displayLog(), setGridBagConstraints(new Insets(25, 5, 5, 5), GridBagConstraints.BOTH,
				GridBagConstraints.LINE_START, 0.5, 0.5, 0, 0));
		gamePanel.add(eventWindow(), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,
				GridBagConstraints.CENTER, 0.5, 0.5, 1, 0));
		gamePanel.add(countryWindow(), setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH,
				GridBagConstraints.LINE_END, 0.5, 0.5, 2, 0));

		return gamePanel;
	}

	/**
	 * Display various portion of game play such as list of continent and territory.
	 * And also details about army in particular territory and also which player
	 * occupied it. Section for movement of Army for Fortification Phase.
	 * 
	 * @return countryPanel which consist detail view continent and territory
	 */
	protected JPanel countryWindow() {

		JPanel countryPanel = new JPanel();
		countryPanel.setPreferredSize(new Dimension(600, 600));
		GridBagLayout countryLayout = new GridBagLayout();
		countryPanel.setLayout(countryLayout);
		JLabel countryLabel = new JLabel("CONTINENTS");
		countryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		JLabel territoryLabel = new JLabel("TERRITORIES -- PLAYER -- ARMY");
		countryLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		continentInfoModel = new DefaultListModel<>();
		continentInfoList = new JList<>(continentInfoModel);
		for (Entry<String, Integer> entry : continent.getValueOfContinent().entrySet()) {
			continentInfoModel.addElement(entry.getKey());
		}
		continentInfoList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		continentInfoList.setLayoutOrientation(JList.VERTICAL);
		continentInfoList.setVisibleRowCount(100);
		continentInfoList.setPreferredSize(new Dimension(150, 300));
		continentInfoList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (StringUtils.isNotEmpty(continentInfoList.getSelectedValue())) {
					territoryInfoModel.removeAllElements();
					String continentSelected = continentInfoList.getSelectedValue().trim();
					ArrayList<String> tempContinentTerritory = continent.getContinentTerritories().get(continentSelected);
					for (int i = 0; i < tempContinentTerritory.size(); i++) {
						String territoryName = tempContinentTerritory.get(i).trim();
						territoryInfoModel.addElement(
								territoryName.trim() + " -- " + territory.getUserOfTerritory().get(territoryName));
					}
				}
			}
		});
		territoryInfoModel = new DefaultListModel<>();
		territoryInfoList = new JList<>(territoryInfoModel);
		territoryInfoList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		territoryInfoList.setLayoutOrientation(JList.VERTICAL);
		territoryInfoList.setVisibleRowCount(100);
		territoryInfoList.setPreferredSize(new Dimension(150, 300));
		territoryInfoList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				displayTerritoryDetails();
			}
		});

		JScrollPane continentInfoScrollPane = new JScrollPane(continentInfoList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		JScrollPane territoryInfoScrollPane = new JScrollPane(territoryInfoList,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		territoryDetails = new JTextArea(4, 1);
		Border border = BorderFactory.createLineBorder(Color.GRAY);
		territoryDetails
				.setBorder(BorderFactory.createCompoundBorder(border, BorderFactory.createEmptyBorder(10, 10, 10, 10)));

		countryPanel.add(countryLabel,
				setGridBagConstraints(new Insets(5, 2, 2, 5), GridBagConstraints.BOTH, 0.5, 1, 0, 0));
		countryPanel.add(territoryLabel,
				setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 1, 1, 0));
		countryPanel.add(continentInfoScrollPane,
				setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 3, 0, 1));
		countryPanel.add(territoryInfoScrollPane,
				setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 3, 1, 1));
		countryPanel.add(fortifyPanel(),
				setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 3, 0, 2));
		countryPanel.add(territoryDetails,
				setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 3, 1, 2));
		return countryPanel;
	}

	/**
	 * List of territory and it's adjacent territory of current Player
	 * 
	 * @return fortifyPanel for movement of army
	 */
	private JPanel fortifyPanel() {
		// TODO Auto-generated method stub
		JPanel fortificationPanel = new JPanel();
		fortificationPanel.setLayout(new GridLayout(9, 1));
		JLabel territoryALabel = new JLabel("Territory List");
		territoryADropDown = new JComboBox<>();
		JLabel territoryBLabel = new JLabel("Adjacent Territory List");
		territoryBDropDown = new JComboBox<>();
		fortErrorMsg = new JLabel("Select Army : ");
		selectArmyModel = new SpinnerNumberModel();
		JSpinner selectArmy = new JSpinner(selectArmyModel);
		fortificationPanel.add(new JLabel(""));
		fortificationPanel.add(territoryALabel);
		fortificationPanel.add(territoryADropDown);
		fortificationPanel.add(new JLabel(""));
		fortificationPanel.add(territoryBLabel);
		fortificationPanel.add(territoryBDropDown);
		fortificationPanel.add(new JLabel(""));
		fortificationPanel.add(fortErrorMsg);
		fortificationPanel.add(selectArmy);
		territoryADropDown.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				addTerritoryBDropDown();
			}
		});
		territoryBDropDown.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				enterArmyToMove();

			}
		});
		return fortificationPanel;
	}

	/**
	 * Panel consist various sections such as Reinforcement Button, Fortify Button,
	 * Attack Button, List of Territory and Adjacent Territory
	 * 
	 * @return EventPanel consist of various game play events
	 */
	protected JPanel eventWindow() {

		JPanel eventPanel = new JPanel();
		eventPanel.setPreferredSize(new Dimension(300, 600));
		GridBagLayout eventLayout = new GridBagLayout();
		eventPanel.setLayout(eventLayout);

		JLabel selectedLabel = new JLabel("SELECTED TERRITORY");
		JLabel targetLabel = new JLabel("ADJACENT TERRITORY");

		turnInButn = new JButton("Turn In Cards");
		turnInButn.setEnabled(false);
		reinforceButn = new JButton("Place Reinforcements");
		attackButn = new JButton("Attack!");
		fortifyButn = new JButton("Fortify");
		endTurnButn = new JButton("End Turn");
		if (reinforceButn.isEnabled()) {
			attackButn.setEnabled(false);
			fortifyButn.setEnabled(false);
			endTurnButn.setEnabled(false);
		}

		reinforceButn.setActionCommand("placeReinforcement");
		attackButn.setActionCommand("attackBtn");
		fortifyButn.setActionCommand("startFortification");
		endTurnButn.setActionCommand("endTurn");
		reinforceButn.addActionListener(this);
		fortifyButn.addActionListener(this);
		attackButn.addActionListener(this);
		endTurnButn.addActionListener(this);

		cardsList = new JList<>();
		cardsList.setLayoutOrientation(JList.VERTICAL_WRAP);
		cardsList.setVisibleRowCount(6);
		territoryAModel = new DefaultListModel<>();
		territoryAList = new JList<>(territoryAModel);
		for (Entry<String, String> entry : territory.getUserOfTerritory().entrySet()) {
			if (entry.getValue().equalsIgnoreCase(players.getPlayerPlaying().get(playerTurn))) {
				territoryAModel.addElement(entry.getKey() + " -- " + territory.getTerritoryArmy().get(entry.getKey()));
			}
		}

		territoryAList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		territoryAList.setLayoutOrientation(JList.VERTICAL);
		territoryAList.setVisibleRowCount(40);
		territoryBModel = new DefaultListModel<>();
		territoryBList = new JList<>(territoryBModel);
		territoryBList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		territoryBList.setLayoutOrientation(JList.VERTICAL);
		territoryBList.setVisibleRowCount(6);

		JScrollPane continentScrollPane = new JScrollPane(territoryAList);
		JScrollPane territoryScrollPane = new JScrollPane(territoryBList);
		territoryAList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				territoryBModel.removeAllElements();
				if (StringUtils.isNotEmpty(territoryAList.getSelectedValue())) {
					String[] territorySelected = territoryAList.getSelectedValue().split(" -- ");
					ArrayList<String> tempAdjacentTerritory = territory.getNeighborTerritories()
							.get(territorySelected[0]);
					for (int i = 0; i < tempAdjacentTerritory.size(); i++) {
						territoryBModel.addElement(tempAdjacentTerritory.get(i) + " -- "
								+ territory.getTerritoryArmy().get(tempAdjacentTerritory.get(i)));
					}
				}
			}
		});
		territoryBList.addListSelectionListener(this);

		// eventPanel.add(cardsList, setGridBagConstraints(new Insets(5, 5, 5, 5),
		// GridBagConstraints.BOTH, 0.5, 5, 0, 2));
		// eventPanel.add(turnInBtn, setGridBagConstraints(new Insets(5, 5, 5, 5),
		// GridBagConstraints.BOTH, 0.5, 0.5, 0, 3));
		eventPanel.add(selectedLabel,
				setGridBagConstraints(new Insets(25, 5, 21, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 4));
		eventPanel.add(continentScrollPane,
				setGridBagConstraints(new Insets(5, 5, 25, 5), GridBagConstraints.BOTH, 0.5, 10, 0, 5));
		eventPanel.add(reinforceButn,
				setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 6));
		eventPanel.add(targetLabel,
				setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 7));
		eventPanel.add(territoryScrollPane,
				setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 10, 0, 8));
		eventPanel.add(attackButn,
				setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 9));
		eventPanel.add(fortifyButn,
				setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 10));
		eventPanel.add(endTurnButn,
				setGridBagConstraints(new Insets(5, 5, 5, 5), GridBagConstraints.BOTH, 0.5, 0.5, 0, 11));

		return eventPanel;
	}

	/**
	 * Display number of players playing in Game. Enable user to select random maps.
	 * Enable user to select previously Edited Map
	 * 
	 * @param count number of player
	 * @return userPanel
	 */
	public JPanel userInfoWindow(int count) {
		JPanel userPanel = new JPanel();
		userPanel.setLayout(new GridLayout(6 + count, 1, 5, 5));
		mapOptionA = new JRadioButton("Choose Your Own Map");
		mapOptionA.setActionCommand("Own Map");
		JFileChooser chooseMap = new JFileChooser("D:");
		FileNameExtensionFilter filter = new FileNameExtensionFilter("MAP FILES", "map", "map");
		chooseMap.setFileFilter(filter);
		chooseMap.addChoosableFileFilter(new FileFilter() {
			public String getDescription() {
				return "MAP Documents (*.map)";
			}

			public boolean accept(File f) {
				if (f.isDirectory()) {
					return true;
				} else {
					return f.getName().toLowerCase().endsWith(".map");
				}
			}
		});
		mapOptionA.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (mapOptionA.isSelected() && chooseMap.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					mapFilePath = chooseMap.getSelectedFile().getPath();
					randomMap = true;
				}
			}
		});

		mapOptionB = new JRadioButton("Choose Previously Edited Map");
		mapOptionB.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				if (mapOptionB.isSelected()) {
					existingMapFilePath = "previous.map";
					randomMap = false;
				}
			}
		});
		ButtonGroup buttonGrp = new ButtonGroup();
		buttonGrp.add(mapOptionA);
		buttonGrp.add(mapOptionB);
		userPanel.add(mapOptionA);
		userPanel.add(mapOptionB);
		userPanel.add(new JLabel("Player Names are"));
		for (int i = 0; i < count; i++) {
			userPanel.add(new JLabel("Player " + (i + 1) + " : " + players.getPlayers(i)));
		}
		playersPlaying = count;
		startGameButn = new JButton("Start Game");
		backButn = new JButton("Back");
		startGameButn.setActionCommand("Start Game");
		backButn.setActionCommand(backButnName);
		startGameButn.addActionListener(this);
		backButn.addActionListener(this);
		userPanel.add(startGameButn);
		userPanel.add(backButn);
		return userPanel;
	}

	/**
	 * Frame consist of Start button, Edit Button and Quit Button
	 * 
	 * @param frame   current frame
	 * @param players player object
	 * @return menuPanel
	 */
	protected JPanel mainMenu(JFrame frame, RiskPlayers players) {
		this.players = players;
		this.frame = frame;
		frame.setBounds(0, 0, 300, 300);
		// frame.setPreferredSize(new Dimension(300, 300));
		// Creates the panel
		JPanel menuPanel = new JPanel();
		// Sets Layout
		mainLayout = new GridLayout(3, 1, 5, 5);
		menuPanel.setLayout(mainLayout);
		// Creates buttons
		newButn = new JButton("Play Game");
		editButn = new JButton("Edit map");
		exitButn = new JButton("Quit");

		menuPanel.add(newButn);
		menuPanel.add(editButn);
		menuPanel.add(exitButn);
		

		newButn.addActionListener(this);
		editButn.addActionListener(this);
		exitButn.addActionListener(this);

		newButn.setActionCommand(newButnName);
		editButn.setActionCommand(editMapButnName);
		exitButn.setActionCommand(exitButnName);
		return menuPanel;
	}

	/**
	 * The GridBagConstraints is used specifies constraints for components that are
	 * laid out using the GridBagLayout class. Initialize GridBagConstraint object
	 * with all of its fields set to their default value.
	 * 
	 * @param insets The initial insets value
	 * @param fill   The initial fill value
	 * @param anchor The initial anchor value.
	 * @param wx     The initial weightx value.
	 * @param wy     The initial weighty value.
	 * @param x      The initial gridx value.
	 * @param y      The initial gridy value.
	 * @return GridBagConstraints object with all of its fields set to the passed-in
	 *         arguments
	 */
	public GridBagConstraints setGridBagConstraints(Insets insets, int fill, int anchor, double wx, double wy, int x,
			int y) {
		con = new GridBagConstraints();
		con.fill = fill;
		con.anchor = anchor;
		con.insets = insets;
		con.weightx = wx;
		con.weighty = wy;
		con.gridx = x;
		con.gridy = y;
		return con;
	}

	/**
	 * The GridBagConstraints specifies constraints for components that are
	 * laid out using the GridBagLayout class. Initialize GridBagConstraint object
	 * with all of its fields set to their default value.
	 * 
	 * @param insets The initial insets value.
	 * @param fill   The initial fill value.
	 * @param wx     The initial weightx value.
	 * @param wy     The initial weighty value.
	 * @param x      The initial gridx value.
	 * @param y      The initial gridy value.
	 * @return GridBagConstraints object with all of its fields set to the passed-in
	 *         arguments
	 */
	public GridBagConstraints setGridBagConstraints(Insets insets, int fill, double wx, double wy, int x, int y) {
		con = new GridBagConstraints();
		con.fill = fill;
		con.insets = insets;
		con.weightx = wx;
		con.weighty = wy;
		con.gridx = x;
		con.gridy = y;
		return con;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		String actionName = arg0.getActionCommand();

		if (actionName.equalsIgnoreCase(newButnName)) {
			riskLogger("Play Game");
			players = new RiskPlayers();
			players.addPlayers("Nikunj");
			players.addPlayers("Hemil");
			frame.setContentPane(playersMenu());
			frame.invalidate();
			frame.validate();
		} else if (actionName.equals(editMapButnName)) {
			frame.setContentPane(editMapPanel());
			frame.invalidate();
			frame.validate();

		} else if (actionName.equals(editExistingMapButnName)) {
			riskLogger("Editing Existing Map");
			frame.setContentPane(editExistingMapPanel());
			frame.invalidate();
			frame.validate();

		} else if (actionName.equals(createNewMapButnName)) {
			riskLogger("Creating New Map");
			frame.setContentPane(createMapPanel());
			frame.invalidate();
			frame.validate();

		} else if (actionName.equals(exitButnName)) {
			riskLogger("Quit Game");
			System.exit(0);
			riskLogger("0");
		} else if (actionName.equals("Start Game")) {
			if (randomMap) {
				if (StringUtils.isNotEmpty(mapFilePath)) {
					SelectionOfArmies armies = new SelectionOfArmies(playersPlaying);
					System.out.println("Player Playing " + playersPlaying);
					DataInitialization initializeData = new DataInitialization(playersPlaying, players,
							armies.getArmiesOfPlayers(), mapFilePath);
					boolean isMapValid = initializeData.dataGeneration();
					if (isMapValid) {
						continent = initializeData.getContinent();
						players = initializeData.getPlayers();
						territory = initializeData.getTerritory();
						frame.setContentPane(gameView());
						frame.invalidate();
						frame.validate();
					} else {
						JOptionPane.showMessageDialog(frame, "Please Check data Again.", "Content Invalid",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "No Map Selected.", "Content Invalid",
							JOptionPane.ERROR_MESSAGE);
				}

			} else {
				if (StringUtils.isNotEmpty(existingMapFilePath)) {
					SelectionOfArmies armies = new SelectionOfArmies(playersPlaying);
					DataInitialization initializeData = new DataInitialization(playersPlaying, players,
							armies.getArmiesOfPlayers(), existingMapFilePath);
					boolean isEditMapValid = initializeData.dataGeneration();
					if (isEditMapValid) {
						continent = initializeData.getContinent();
						players = initializeData.getPlayers();
						territory = initializeData.getTerritory();
						frame.setContentPane(gameView());
						frame.invalidate();
						frame.validate();
					} else {
						JOptionPane.showMessageDialog(frame, "Please Check data Again.", "Content Invalid",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame, "No Map Edited Previously.", "Content Invalid",
							JOptionPane.ERROR_MESSAGE);
				}

			}

		} else if (actionName.equals(twoPlayersButnName)) {
			System.out.println("Two Player Game");
			riskLogger("Two Player Game");
//	    players.addPlayers("Neutral Player");
			frame.setContentPane(userInfoWindow(2));
			frame.invalidate();
			frame.validate();
		} else if (actionName.equals(threePlayersButnName)) {
			System.out.println("Three Player Game");
			riskLogger("Three Player Game");
			players.addPlayers("Varinder");
			frame.setContentPane(userInfoWindow(3));
			frame.invalidate();
			frame.validate();
		} else if (actionName.equals(fourPlayersButnName)) {
			System.out.println("Four Player Game");
			riskLogger("Four Player Game");
			players.addPlayers("Varinder");
			players.addPlayers("Charan");
			frame.setContentPane(userInfoWindow(4));
			frame.invalidate();
			frame.validate();
		} else if (actionName.equals(fivePlayersButnName)) {
			System.out.println("Five Player Game");
			riskLogger("Five Player Game");
			players.addPlayers("Varinder");
			players.addPlayers("Charan");
			players.addPlayers("Shahrzad");
			frame.setContentPane(userInfoWindow(5));
			frame.invalidate();
			frame.validate();
		} else if (actionName.equals("placeReinforcement")) {
			finalPhaseReinforcement(true);
		} else if (actionName.equals("attackBtn")) {
			finalPhaseAttack();
		} else if (actionName.equals("startFortification")) {
			finalPhaseFortification();
		} else if (actionName.equals("endTurn")) {
			riskLogger("Player Turn ended.");
			riskLogger("");
			changePlayerTurn();
		} else if (actionName.equals(backButnName)) {
			frame.setContentPane(mainMenu(frame, players));
			frame.invalidate();
			frame.validate();
		}
	}

	/**
	 * method used to do reinforcement on territory.
	 * 
	 * @param flag used to identify whether player can do reinforcement or not.
	 */
	public void finalPhaseReinforcement(boolean flag) {
		String name = players.getPlayerPlaying().get(playerTurn);
		Reinforcement reinforcement = new Reinforcement(name, continent, territory, players);
		players.setCurrentPhase("Reinforcement");
		updateLogArea();
		if (StringUtils.isNotEmpty(territoryAList.getSelectedValue())) {
			String[] terrName = territoryAList.getSelectedValue().split("--");
			String message = flag ? "Add Armies in " + terrName[0] : "Add Armies Again in " + terrName[0];
			int army = players.getPlayerArmy(name);
			String title = "Add Armies upto " + army;
			riskLogger("Player Name : " + players.getPlayerPlaying().get(playerTurn));
			String output = JOptionPane.showInputDialog(frame, message, title, JOptionPane.OK_CANCEL_OPTION);
			if (StringUtils.isNotEmpty(output) && StringUtils.isNumeric(output)) {
				if (Integer.parseInt(output) > 0 && Integer.parseInt(output) <= army) {
					reinforcement.startReinforcement(name, terrName[0].trim(), Integer.parseInt(output));
					setPlayers(reinforcement.getPlayers());
					setTerritory(reinforcement.getTerritory());
					riskLogger("Army on  " + terrName[0] + " " + territory.getTerritoryArmy().get(terrName[0].trim()));
					riskLogger("Remain Armies " + players.getPlayerArmy(name));
					territoryAModel.removeAllElements();
					territoryBModel.removeAllElements();
					territoryInfoModel.removeAllElements();
					continentInfoModel.removeAllElements();
					updateTerritoryList();
					updateContinentInfoList();
					enableReinforcementButn();
					updateLogArea();
				} else {
					riskLogger("Input armies are out of range ot not properly enter");
					finalPhaseReinforcement(false);
				}
			} else {
				riskLogger("Input armies  entered is null or cancel button is clicked");
				JOptionPane.showMessageDialog(null, "Input armies  entered is null or cancel button is clicked",
						"Invalid Content", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	/**
	 * Attack phase. Invoke the fortification phase.
	 */
	private void finalPhaseAttack() {
		players.setCurrentPhase("Attack");
		updateLogArea();
		JOptionPane.showMessageDialog(frame, "Attack Phase is in Progress");
		fortifyButn.setEnabled(true);
		endTurnButn.setEnabled(true);
		riskLogger("Fortification Phase Started");
		updateLogArea();
		startFortificationPhase();

	}

	/**
	 * method used to enable reinforcement button and disable fortify button if
	 * player has 1 or more army It also used to disable reinforcement button and
	 * enable fortify button if player has no army
	 */
	public void enableReinforcementButn() {
		players.setCurrentPhase("Reinforcement");
		updateLogArea();
		String name = players.getPlayerPlaying().get(playerTurn);
		if (StringUtils.isNotEmpty(name)) {
			if (players.getPlayerArmy(name) == 0) {
				reinforceButn.setEnabled(false);
				players.setCurrentPhase("Attack");
				riskLogger(players.getCurrentPhase() + " Phase Started");
				updateLogArea();
				attackButn.setEnabled(true);
				endTurnButn.setEnabled(true);
				fortifyButn.setEnabled(false);
			} else {
				attackButn.setEnabled(false);
				fortifyButn.setEnabled(false);
				endTurnButn.setEnabled(false);
				reinforceButn.setEnabled(true);
			}
		}
	}

	/**
	 * Method is used to change the Turn of player when End Turn Button is Clicked.
	 */
	public void changePlayerTurn() {
		playerTurn++;
		if (playerTurn < players.getPlayerList().size()) {
			Reinforcement reinforcement = new Reinforcement(players.getPlayers(playerTurn), continent, territory, players);
			players.updateArmy(players.getPlayers(playerTurn), reinforcement.armyGeneration(), "ADD");
			territoryAModel.removeAllElements();
			territoryBModel.removeAllElements();
			territoryInfoModel.removeAllElements();
			continentInfoModel.removeAllElements();
			territoryADropDown.removeAllItems();
			territoryBDropDown.removeAllItems();
			updateTerritoryList();
			updateContinentInfoList();
			enableReinforcementButn();
			updateLogArea();
		} else {
			playerTurn = 0;
			Reinforcement reinforcement = new Reinforcement(players.getPlayers(playerTurn), continent, territory, players);
			players.updateArmy(players.getPlayers(playerTurn), reinforcement.armyGeneration(), "ADD");
			territoryAModel.removeAllElements();
			territoryBModel.removeAllElements();
			territoryInfoModel.removeAllElements();
			continentInfoModel.removeAllElements();
			territoryADropDown.removeAllItems();
			territoryBDropDown.removeAllItems();
			updateTerritoryList();
			updateContinentInfoList();
			enableReinforcementButn();
			updateLogArea();
		}
	}

	/**
	 * method used to enable list of current territory owned by current player to
	 * move army from one territory to another.
	 */
	public void startFortificationPhase() {
		players.setCurrentPhase("Fortification");
		updateLogArea();
		attackButn.setEnabled(false);
		addTerritoryADropDown();

	}

	/**
	 *Displaying Details of Current Player.
	 */
	public void updateLogArea() {
		logArea.setText("");
		logArea.append("Current Player : " + players.getPlayerList().get(playerTurn) + "\n");
		logArea.append("Current Armies : " + players.getPlayerArmy(players.getPlayerList().get(playerTurn)) + "\n");
		logArea.append("Current Phase : " + players.getCurrentPhase());
	}

	/**
	 * Method Allows Player to do Fortification Phase.
	 */
	public void finalPhaseFortification() {
		Fortification fortification = new Fortification(territory);
		String fromTerritory = territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex());
		String toTerritory = territoryBDropDown.getItemAt(territoryBDropDown.getSelectedIndex());
		if (StringUtils.isNotEmpty(fromTerritory) && StringUtils.isNotEmpty(toTerritory)) {
			int fromArmy = territory.getTerritoryArmy().get(fromTerritory);
			int getArmySelect = (int) selectArmyModel.getValue();
			if (getArmySelect < fromArmy && getArmySelect >= 1) {
				fortification.startFortification(getArmySelect, fromTerritory, toTerritory);
				setTerritory(fortification.getTerritory());
				riskLogger("Armies moved from " + fromTerritory + " to " + toTerritory);
				riskLogger("Armies at :" + fromTerritory + " : " + territory.getTerritoryArmy().get(fromTerritory));
				riskLogger("Armies at :" + toTerritory + " : " + territory.getTerritoryArmy().get(toTerritory));
				riskLogger("");
				fortErrorMsg.setText(
						"You can Move upto " + (territory.getTerritoryArmy().get(fromTerritory) - 1) + " Army");
			} else {
				JOptionPane
						.showMessageDialog(frame,
								"Armies unable to move from " + fromTerritory + " to " + toTerritory
										+ ". Please enter no. of  Armies again",
								"Error Message", JOptionPane.ERROR_MESSAGE);
			}
			checkFortificationStatus();
		} else {
			JOptionPane.showMessageDialog(null, "Content Invalid");
		}
	}

	/**
	 * Method used to display complete details of territory such as the corresponding continent
	 * name and the player who has occupied it with the number of armies.
	 */
	public void displayTerritoryDetails() {
		try {
			territoryDetails.setText("");
			System.out.println("territoryInfoList.getSelectedValue() " + territoryInfoList.getSelectedValue());
			if (territoryInfoList.getSelectedValue() != null) {
				String[] territoryName = territoryInfoList.getSelectedValue().split("--");
				territoryDetails.append("Continent  : " + continentInfoList.getSelectedValue() + "\n");
				territoryDetails.append("Territory  : " + territoryName[0] + "\n");
				territoryDetails
						.append("Player     : " + territory.getUserOfTerritory().get(territoryName[0].trim()) + "\n");
				territoryDetails.append("Army       : " + territory.getTerritoryArmy().get(territoryName[0].trim()));
			}
		} catch (Exception ex) {
			riskLogger("Handles Null Values");
		}
	}

	/**
	 * Checking the eligibility of a Player to Fortify.
	 */
	public void checkFortificationStatus() {
		boolean flag = false;
		for (Entry<String, String> entry : territory.getUserOfTerritory().entrySet()) {
			if (entry.getValue().equalsIgnoreCase(players.getPlayerPlaying().get(playerTurn))) {
				if (territory.getTerritoryArmy().get(entry.getKey()) > 1) {
					flag = true;
					break;
				}
			}
		}
		if (!flag) {
			fortifyButn.setEnabled(false);
		} else {
			territoryAModel.removeAllElements();
			territoryBModel.removeAllElements();
			territoryInfoModel.removeAllElements();
			continentInfoModel.removeAllElements();
			updateTerritoryList();
			updateContinentInfoList();
			startFortificationPhase();
		}

	}

	/**
	 * method used to update list of territory of current player
	 */
	public void updateTerritoryList() {
		territoryAModel.removeAllElements();

		for (Entry<String, String> entry : territory.getUserOfTerritory().entrySet()) {
			if (entry.getValue().equals(players.getPlayerList().get(playerTurn))) {
				territoryAModel.addElement(entry.getKey() + " -- " + territory.getTerritoryArmy().get(entry.getKey()));
			}
		}
	}

	/**
	 * method used to update list of continent in CountryPanel
	 */
	public void updateContinentInfoList() {
		continentInfoModel.removeAllElements();
		for (Entry<String, Integer> entry : continent.getValueOfContinent().entrySet()) {
			continentInfoModel.addElement(entry.getKey());
		}
	}

	/**
	 * method used to display list of adjacent territory owned by the current player to
	 * move army from selected territory to adjacent one.
	 */
	public void addTerritoryBDropDown() {
		territoryBDropDown.removeAllItems();
		String dropDownAValue = territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex());
		if (StringUtils.isNotEmpty(dropDownAValue)) {
			for (int i = 0; i < territory.getNeighborTerritories().get(dropDownAValue).size(); i++) {
				String terrName = territory.getNeighborTerritories().get(dropDownAValue).get(i);
				if (players.getPlayerPlaying().get(playerTurn).equalsIgnoreCase(territory.getUserOfTerritory().get(terrName))) {
					territoryBDropDown.addItem(terrName);
				}
			}
		}
	}

	/**
	 * method use to display list of current territory owned by current player to
	 * move army from one territory to another.
	 */
	public void addTerritoryADropDown() {
		for (Entry<String, String> entry : territory.getUserOfTerritory().entrySet()) {
			if (entry.getValue().equals(players.getPlayerPlaying().get(playerTurn))) {
				territoryADropDown.addItem(entry.getKey());
			}
		}
	}

	/**
	 * method used to give input as no. of armies player want to move in
	 * fortification phase
	 */
	public void enterArmyToMove() {
		String fromTerritory = territoryADropDown.getItemAt(territoryADropDown.getSelectedIndex());
		String toTerritory = territoryBDropDown.getItemAt(territoryBDropDown.getSelectedIndex());
		if (StringUtils.isNotEmpty(fromTerritory) && StringUtils.isNotEmpty(toTerritory)) {
			int fromArmy = territory.getTerritoryArmy().get(fromTerritory) - 1;
			System.out.println("Current Army in  " + fromTerritory + " is " + fromArmy);
			if (fromArmy > 1) {
				fortErrorMsg.setText("You can Move upto " + fromArmy + " Army");
			} else {
				fortErrorMsg.setText("You can't move your Army");
			}
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
	}

	/**
	 * This Method Get Current Player Object
	 * 
	 * @return Current Player Object
	 */
	public RiskPlayers getPlayers() {
		return players;
	}

	/**
	 * This Method Set Current Player Object
	 * 
	 * @param players Current Player Object
	 */
	public void setPlayers(RiskPlayers players) {
		this.players = players;
	}

	/**
	 * This Method Get Current Territory Object
	 * 
	 * @param territory Current Territory Object
	 */
	public void setTerritory(Territories territory) {
		this.territory = territory;
	}

	/**
	 * This Method Set Current Continent Object
	 * 
	 * @param continent Get Current Continent Object
	 */
	public void setContinent(Continent continent) {
		this.continent = continent;
	}
	
	/**
	 * This Method Get Current Territory Object
	 * 
	 * @return Current Territory Object
	 */
	public Territories getTerritory() {
		return territory;
	}

	/**
	 * This Method Get Current Player Object
	 * 
	 * @return Current Continent Object
	 */
	public Continent getContinent() {
		return continent;
	}

}
