package listenner;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import main.MainFrame;
import tools.LayBomb;
import tools.StaticTool;
import bean.MineLabel;

public class Listener implements MouseListener {
	MineLabel[][] mineLabel;
	MainFrame mainFrame;
	private boolean isDoublePress = false;

	public Listener(MineLabel[][] mineLabel, MainFrame mainFrame) {
		this.mineLabel = mineLabel;
		this.mainFrame = mainFrame;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		MineLabel mineLabel = (MineLabel) e.getSource();

		int row = mineLabel.getRow();
		int col = mineLabel.getCol();
		if (e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK + InputEvent.BUTTON3_DOWN_MASK) {
			isDoublePress = true;
			doublePress(row, col);

		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK && !mineLabel.isFlagTag()) {
			if (!mineLabel.isExpendTag()) {
				mineLabel.setIcon(StaticTool.icon0);

			}
			mainFrame.getFaceJPanel().getLabelFace().setIcon(StaticTool.clickIcon);
		} else if (e.getModifiers() == InputEvent.BUTTON3_MASK && !mineLabel.isExpendTag()) {
			if (mineLabel.getRightClickCount() == 0) {
				mineLabel.setIcon(StaticTool.flagIcon);
				mineLabel.setRightClickCount(1);
				mineLabel.setFlagTag(true);
				StaticTool.bombCount--;
				mainFrame.getFaceJPanel().setNumber(StaticTool.bombCount);
			} else if (mineLabel.getRightClickCount() == 1) {
				mineLabel.setIcon(StaticTool.askIcon);
				mineLabel.setRightClickCount(2);
				mineLabel.setFlagTag(false);
				StaticTool.bombCount++;
				mainFrame.getFaceJPanel().setNumber(StaticTool.bombCount);
			} else {
				mineLabel.setIcon(StaticTool.iconBlank);
				mineLabel.setRightClickCount(0);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		MineLabel mineLabel = (MineLabel) e.getSource();
		int row = mineLabel.getRow();
		int col = mineLabel.getCol();
		if (isDoublePress) {
			isDoublePress = false;
			if (!mineLabel.isExpendTag() && !mineLabel.isFlagTag()) {
				backIcon(row, col);
			} else {
				if (isEquals(row, col)) {
					doubleExpend(row, col);
				} else {
					backIcon(row, col);
				}
			}
			mainFrame.getFaceJPanel().getLabelFace().setIcon(StaticTool.smileIcon);
		} else if (e.getModifiers() == InputEvent.BUTTON1_MASK && !mineLabel.isFlagTag()) {
			if (!StaticTool.isStart) {
				LayBomb.lay(this.mineLabel, row, col);
				StaticTool.isStart = true;
			}
			mainFrame.getTimer().start();
			if (mineLabel.isMineTag()) {
				bombAction(row, col);
				mineLabel.setIcon(StaticTool.bloodIcon);
				mainFrame.getFaceJPanel().getLabelFace().setIcon(StaticTool.faultFaceIcon);
			} else {
				mainFrame.getFaceJPanel().getLabelFace().setIcon(StaticTool.smileIcon);
				expand(row, col);
			}
		}
		isWin();
	}

	private void bombAction(int row, int col) {

		for (MineLabel[] mines : mineLabel) {
			for (MineLabel mine : mines) {
				if (mine.isMineTag()) {
					if (!mine.isFlagTag()) {
						mine.setIcon(StaticTool.blackBombIcon);
					}
				} else {
					if (mine.isFlagTag()) {
						mine.setIcon(StaticTool.errorBombIcon);
					}
				}
			}

		}

		mainFrame.getTimer().stop();

		for (MineLabel[] mines : mineLabel) {
			for (MineLabel mine : mines) {
				mine.removeMouseListener(this);

			}
		}

	}

	private void expand(int x, int y) {
		int count = mineLabel[x][y].getCountAround();
		if (!mineLabel[x][y].isExpendTag() && !mineLabel[x][y].isFlagTag()) {
			if (count == 0) {
				mineLabel[x][y].setIcon(StaticTool.num[count]);
				mineLabel[x][y].setExpendTag(true);
				for (int i = Math.max(0, x - 1); i <= Math.min(mineLabel.length - 1, x + 1); i++) {
					for (int j = Math.max(0, y - 1); j <= Math.min(mineLabel[x].length - 1, y + 1); j++) {
						expand(i, j);
					}
				}
			} else {
				mineLabel[x][y].setIcon(StaticTool.num[count]);
				mineLabel[x][y].setExpendTag(true);
			}
		}
	}

	private void backIcon(int i, int j) {
		for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.totalRow - 1, i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.totalColumn - 1, j + 1); y++) {
				if (!mineLabel[x][y].isFlagTag() && !mineLabel[x][y].isExpendTag()) {
					int rightClickCount = mineLabel[x][y].getRightClickCount();
					if (rightClickCount == 2) {
						mineLabel[x][y].setIcon(StaticTool.askIcon);
					} else {
						mineLabel[x][y].setIcon(StaticTool.iconBlank);
					}
				}
			}
		}
	}

	private boolean isEquals(int i, int j) {
		int count = mineLabel[i][j].getCountAround();
		int flagCount = 0;
		for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.totalRow - 1, i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.totalColumn - 1, j + 1); y++) {
				if (mineLabel[x][y].isFlagTag()) {
					flagCount++;
				}
			}
		}
		return count == flagCount;
	}

	private void doublePress(int i, int j) {
		for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.totalRow - 1, i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.totalColumn - 1, j + 1); y++) {
				if (!mineLabel[x][y].isExpendTag() && !mineLabel[x][y].isFlagTag()) {
					int rightClickCount = mineLabel[x][y].getRightClickCount();
					if (rightClickCount == 1) {
						mineLabel[x][y].setIcon(StaticTool.askPressIcon);

					} else {
						mineLabel[x][y].setIcon(StaticTool.icon0);

					}
				}
			}
		}
	}

	private void doubleExpend(int i, int j) {
		for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.totalRow - 1, i + 1); x++) {
			for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.totalColumn - 1, j + 1); y++) {
				if (mineLabel[x][y].isMineTag()) {
					if (!mineLabel[x][y].isFlagTag()) {
						bombAction(x, y);

					}
				} else {

					if (!mineLabel[x][y].isFlagTag()) {
						expand(x, y);
					}

				}

			}
		}

	}

	private void isWin() {
		int needCount = StaticTool.totalRow * StaticTool.totalColumn - StaticTool.allCount;
		int expendCount = 0;
		for (MineLabel[] mines : mineLabel) {
			for (MineLabel mine : mines) {
				if (mine.isExpendTag()) {
					expendCount++;
				}
			}
		}
		if (needCount == expendCount) {
			for (MineLabel[] mines : mineLabel) {
				for (MineLabel mine : mines) {
					if (mine.isMineTag() && !mine.isFlagTag()) {
						mine.setIcon(StaticTool.flagIcon);
						mine.setFlagTag(true);
					}
				}
			}
			mainFrame.getFaceJPanel().setNumber(0);
			mainFrame.getTimer().stop();
			for (MineLabel[] mines : mineLabel) {
				for (MineLabel mine : mines) {
					mine.removeMouseListener(this);
				}
			}
			mainFrame.getFaceJPanel().getLabelFace().setIcon(StaticTool.winFaceIcon);
		}
	}
}
