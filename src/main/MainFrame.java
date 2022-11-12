package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import panel.MenuBar;
import panel.BombPanel;
import panel.FacePanel;
import timer.TimerListener;
import tools.StaticTool;

public class MainFrame extends JFrame {
	private final MenuBar menuBar = new MenuBar(this);
	private FacePanel facePanel = new FacePanel(this);
	private BombPanel bombPanel = new BombPanel(this);
	private final TimerListener timerListener = new TimerListener(this);
	private final Timer timer = new Timer(1000, timerListener);

	public MainFrame() {
		init();
		this.setIconImage(StaticTool.imageIcon.getImage());
		this.setTitle("É¨À×");
		this.setSize(new Dimension(220, 300));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.pack();
		this.setVisible(true);
	}

	private void init() {
		this.setJMenuBar(menuBar);
		this.add(facePanel, BorderLayout.NORTH);
		this.add(bombPanel);
	}

	public void reStartGame() {
		this.remove(facePanel);
		this.remove(bombPanel);
		StaticTool.bombCount = StaticTool.allCount;
		StaticTool.timeCount = 0;
		StaticTool.isStart = false;

		facePanel = new FacePanel(this);
		bombPanel = new BombPanel(this);
		this.add(facePanel, BorderLayout.NORTH);
		this.add(bombPanel);
		this.pack();
		this.validate();

		getTimer().stop();

	}

	public FacePanel getFaceJPanel() {
		return facePanel;
	}

	public BombPanel getBombJPanel() {
		return bombPanel;
	}

	public Timer getTimer() {
		return timer;
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
				 UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new MainFrame();
	}

}
