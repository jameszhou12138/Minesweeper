package dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import tools.StaticTool;
import listenner.UserDefinedListener;
import main.MainFrame;

public class UserDefinedJDialog extends JDialog {
	private final JLabel heightLabel = new JLabel("高度：");
	private final JLabel widthLabel = new JLabel("宽度：");
	private final JLabel bombLabel = new JLabel("雷数：");
	private final JLabel messageLabel = new JLabel("    ");
	private JTextField heightText;
	private JTextField widthText;
	private JTextField bombText;
	private JButton sureButton;
	private JButton cancerButton;
	MainFrame mainFrame;

	public UserDefinedJDialog(final MainFrame mainFrame) {
		super(mainFrame);
		this.mainFrame = mainFrame;
		messageLabel.setFont(new Font("楷书", Font.PLAIN, 12));
		messageLabel.setForeground(Color.red);
		this.setTitle("自定义雷区");
		this.add(getPanel());
		this.add(messageLabel, BorderLayout.NORTH);
		this.setSize(new Dimension(200, 250));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowDeactivated(WindowEvent e) {
				mainFrame.reStartGame();
			}
		});
		this.setModal(true);
		this.setVisible(true);
	}

	public JPanel getPanel() {
		JPanel totalPanel = new JPanel();
		Border border1 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(2, 1));
		Box boxHigh = Box.createHorizontalBox();
		heightText = new JTextField(StaticTool.totalRow + "");
		heightText.setPreferredSize(new Dimension(30, 20));
		heightText.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = heightText.getText();
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(text);
				if (!matcher.matches()) {
					messageLabel.setText("请输入数字，不能超过两位");
					if (text.length() >= 3) {
						heightText.setText(text.substring(0, 2));
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if ((ch < '0') || (ch > '9')) {
					messageLabel.setText("请输入数字，不能超过两位");
					e.setKeyChar((char) 8);
				} else {
					messageLabel.setText("    ");
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		boxHigh.add(heightLabel);
		boxHigh.add(heightText);
		Box boxWide = Box.createHorizontalBox();
		widthText = new JTextField(StaticTool.totalColumn + "");
		widthText.setPreferredSize(new Dimension(30, 20));
		widthText.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = widthText.getText();
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(text);
				if (!matcher.matches()) {
					messageLabel.setText("请输入数字，不能超过两位");
					if (text.length() >= 3) {
						widthText.setText(text.substring(0, 2));
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if ((ch < '0') || (ch > '9')) {
					messageLabel.setText("请输入数字，不能超过两位");
					e.setKeyChar((char) 8);
				} else {
					messageLabel.setText("    ");
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		boxWide.add(widthLabel);
		boxWide.add(widthText);
		Box boxBomb = Box.createHorizontalBox();
		bombText = new JTextField(StaticTool.bombCount + "");
		bombText.setPreferredSize(new Dimension(30, 20));
		bombText.addKeyListener(new KeyListener() {
			@Override
			public void keyReleased(KeyEvent e) {
				String text = bombText.getText();
				Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
				Matcher matcher = pattern.matcher(text);
				if (!matcher.matches()) {
					messageLabel.setText("请输入数字，不能超过两位");
					if (text.length() >= 3) {
						bombText.setText(text.substring(0, 2));
					}
				}
			}
			@Override
			public void keyTyped(KeyEvent e) {
				char ch = e.getKeyChar();
				if ((ch < '0') || (ch > '9')) {
					messageLabel.setText("请输入数字，不能超过两位");
					e.setKeyChar((char) 8);
				} else {
					messageLabel.setText("    ");
				}
			}
			@Override
			public void keyPressed(KeyEvent e) {

			}
		});
		boxBomb.add(bombLabel);
		boxBomb.add(bombText);
		Box infoBox = new Box(BoxLayout.Y_AXIS);
		infoBox.add(boxHigh);
		infoBox.add(Box.createVerticalStrut(12));
		infoBox.add(boxWide);
		infoBox.add(Box.createVerticalStrut(12));
		infoBox.add(boxBomb);
		infoBox.setBorder(border1);
		Box buttonBox = new Box(BoxLayout.X_AXIS);
		sureButton = new JButton("确定");
		UserDefinedListener definedListener = new UserDefinedListener(this, mainFrame);
		sureButton.setPreferredSize(new Dimension(80, 30));
		sureButton.addActionListener(definedListener);
		cancerButton = new JButton("取消");
		cancerButton.setSize(new Dimension(80, 30));
		cancerButton.addActionListener(definedListener);
		buttonBox.add(sureButton);
		buttonBox.add(Box.createHorizontalStrut(20));
		buttonBox.add(cancerButton);
		buttonBox.setBorder(border1);
		infoPanel.add(infoBox);
		infoPanel.add(buttonBox);
		totalPanel.add(infoPanel);
		return totalPanel;
	}

	public JLabel getMessageLabel() {
		return messageLabel;
	}

	public JTextField getHeightText() {
		return heightText;
	}

	public JTextField getWidthText() {
		return widthText;
	}

	public JTextField getBombText() {
		return bombText;
	}

	public JButton getSureButton() {
		return sureButton;
	}

	public JButton getCancerButton() {
		return cancerButton;
	}

}
