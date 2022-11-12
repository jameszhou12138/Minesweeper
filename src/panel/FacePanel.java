package panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import tools.StaticTool;

import main.MainFrame;

public class FacePanel extends JPanel {
	private final JLabel labelCountG = new JLabel();
	private final JLabel labelCountS = new JLabel();
	private final JLabel labelCountB = new JLabel();
	private final JLabel labelTimeG = new JLabel();
	private final JLabel labelTimeS = new JLabel();
	private final JLabel labelTimeB = new JLabel();
	private final JLabel labelFace = new JLabel();
	MainFrame mainFrame;

	public JLabel getLabelFace() {
		return labelFace;
	}

	public FacePanel(MainFrame frame) {
		this.mainFrame = frame;
		this.setLayout(new BorderLayout());
		init();

	}

	private void init() {
		JPanel panel = new JPanel();
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.LINE_AXIS);
		panel.setLayout(boxLayout);
		FaceLabelListener faceLabelListener;
		faceLabelListener = new FaceLabelListener();
		panel.addMouseListener(faceLabelListener);
		Icon icon0 = new ImageIcon("./image/d0.gif");
		Icon icon1 = new ImageIcon("./image/d" + StaticTool.allCount / 10 + ".gif");
		Icon icon2 = new ImageIcon("./image/d" + StaticTool.allCount % 10 + ".gif");
		Icon iconSmile = new ImageIcon("./image/face0.gif");
		labelCountG.setIcon(icon2);
		labelCountS.setIcon(icon1);
		labelCountB.setIcon(icon0);
		labelTimeS.setIcon(icon0);
		labelTimeG.setIcon(icon0);
		labelTimeB.setIcon(icon0);
		labelFace.setIcon(iconSmile);
		panel.add(Box.createHorizontalStrut(2));
		panel.add(labelCountB);
		panel.add(labelCountS);
		panel.add(labelCountG);
		panel.add(Box.createHorizontalGlue());
		panel.add(labelFace);
		panel.add(Box.createHorizontalGlue());
		panel.add(labelTimeB);
		panel.add(labelTimeS);
		panel.add(labelTimeG);
		panel.add(Box.createHorizontalStrut(2));

		Border borderLow = BorderFactory.createLoweredBevelBorder();

		Border borderEmpty = BorderFactory.createEmptyBorder(2, 2, 2, 2);
		Border borderCom1 = BorderFactory.createCompoundBorder(borderLow,
				borderEmpty);

		panel.setBorder(borderCom1);
		panel.setBackground(Color.LIGHT_GRAY);

		this.add(panel);
		Border borderEmpty2 = BorderFactory.createEmptyBorder(5, 5, 5, 5);

		this.setBorder(borderEmpty2);
		this.setBackground(Color.LIGHT_GRAY);

	}

	public void setTime(int count) {
		int g = count % 10;
		int s = count / 10 % 10;
		int b = count / 100;

		labelTimeG.setIcon(StaticTool.time[g]);
		labelTimeS.setIcon(StaticTool.time[s]);
		labelTimeB.setIcon(StaticTool.time[b]);

	}

	public void setNumber(int count) {
		int b = count >= 0 ? count / 100 : 10;
		int g = Math.abs(count) % 10;
		int s = Math.abs(count) / 10 % 10;
		labelCountG.setIcon(StaticTool.time[g]);
		labelCountS.setIcon(StaticTool.time[s]);
		labelCountB.setIcon(StaticTool.time[b]);
	}

	public class FaceLabelListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
				labelFace.setIcon(StaticTool.downSmileIcon);
				mainFrame.getTimer().stop();
			}
		}
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
				mainFrame.reStartGame();
			}
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			if (e.getModifiers() == InputEvent.BUTTON1_MASK) {
				mainFrame.getTimer().start();
				labelFace.setIcon(StaticTool.smileIcon);
			}
		}
	}
}
