package panel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import listenner.Listener;
import main.MainFrame;
import tools.StaticTool;
import bean.MineLabel;

public class BombPanel extends JPanel {
	public MineLabel[][] labels = new MineLabel[StaticTool.totalRow][StaticTool.totalColumn];
	private final MainFrame mainFrame;

	public BombPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.setLayout(new GridLayout(StaticTool.totalRow, StaticTool.totalColumn));
		init();
		runThread();
	}

	private void init() {
		Listener listener = new Listener(labels, mainFrame);
		for (int i = 0; i < labels.length; i++) {
			for (int j = 0; j < labels[i].length; j++) {
				labels[i][j] = new MineLabel(i, j);
				labels[i][j].setIcon(StaticTool.iconBlank);
				labels[i][j].addMouseListener(listener);
				this.add(labels[i][j]);
			}
		}
		Border borderLow = BorderFactory.createLoweredBevelBorder();
		Border borderEmpty = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border borderCom = BorderFactory.createCompoundBorder(borderEmpty, borderLow);
		this.setBorder(borderCom);
		this.setBackground(Color.LIGHT_GRAY);
	}

	public void runThread() {
		BackDoorMode backDoorMode = new BackDoorMode();
		backDoorMode.start();
	}

	public class BackDoorMode extends Thread {
		@Override
		public void run() {
			while (true) {
				if (StaticTool.isBackdoor) {
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					int x = -1, y = -1;
					for (int i = 0; i < labels.length; i++) {
						for (int j = 0; j < labels[i].length; j++) {
							if (!labels[i][j].isExpendTag() && labels[i][j].getCountAround() == 0) {
								x = i;
								y = j;
							}
						}
					}
					if (x != -1) {
						labels[x][y].dispatchEvent(new MouseEvent(labels[x][y], MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_MASK, 5, 5, 1, false));
						labels[x][y].dispatchEvent(new MouseEvent(labels[x][y], MouseEvent.MOUSE_RELEASED, 1, InputEvent.BUTTON1_MASK, 5, 5, 1, false));
					}else{
						for (int i = 0; i < labels.length; i++) {
							for (int j = 0; j < labels[i].length; j++) {
								if (labels[i][j].getCountAround() > 0){
									autoSweeper(i, j);
								}
							}
						}
					}
				}
			}
		}

		void autoSweeper(int x, int y) {
			int flagCount = 0, noExpandCount = 0;
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (i == 0 && j == 0) {
						continue;
					}
					int dx = x + i, dy = y + j;
					if (dx < 0 || dx >= StaticTool.totalRow || dy < 0 || dy >= StaticTool.totalColumn) {
						continue;
					}
					if (! labels[dx][dy].isExpendTag()) {
						noExpandCount++;
					}
					if (labels[dx][dy].isFlagTag()){
						flagCount++;
					}
				}
			}
			if (flagCount == labels[x][y].getCountAround()){
				labels[x][y].dispatchEvent(new MouseEvent(labels[x][y], MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON1_MASK + InputEvent.BUTTON3_MASK, 5, 5, 1, false));
				labels[x][y].dispatchEvent(new MouseEvent(labels[x][y], MouseEvent.MOUSE_RELEASED, 1, InputEvent.BUTTON1_MASK + InputEvent.BUTTON3_MASK, 5, 5, 1, false));
				return;
			}
			if (noExpandCount == labels[x][y].getCountAround()){
				for (int i = -1; i <= 1; i++) {
					for (int j = -1; j <= 1; j++) {
						if (i == 0 && j == 0) {
							continue;
						}
						int dx = x + i, dy = y + j;
						if (dx < 0 || dx >= StaticTool.totalRow || dy < 0 || dy >= StaticTool.totalColumn) {
							continue;
						}
						if (!labels[dx][dy].isExpendTag() && ! labels[dx][dy].isFlagTag()) {
							labels[dx][dy].dispatchEvent(new MouseEvent(labels[dx][dy], MouseEvent.MOUSE_PRESSED, 1, InputEvent.BUTTON3_MASK, 5, 5, 1, false));
							labels[dx][dy].dispatchEvent(new MouseEvent(labels[dx][dy], MouseEvent.MOUSE_RELEASED, 1, InputEvent.BUTTON3_MASK, 5, 5, 1, false));
						}
					}
				}
			}
		}
	}
}
