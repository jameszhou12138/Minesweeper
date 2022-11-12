package bean;

import javax.swing.JLabel;

public class MineLabel extends JLabel {
	private boolean mineTag;
	private boolean expendTag;
	private boolean flagTag;
	private int row;
	private int col;
	private int countAround;
	private int rightClickCount;

	public MineLabel(int x, int y) {
		this.row = x;
		this.col = y;

	}

	@Override
	public String toString() {
		return "MineLabel{" +
				"mineTag=" + mineTag +
				", expendTag=" + expendTag +
				", flagTag=" + flagTag +
				", row=" + row +
				", col=" + col +
				", countAround=" + countAround +
				", rightClickCount=" + rightClickCount +
				'}';
	}

	public boolean isMineTag() {
		return mineTag;
	}

	public void setMineTag(boolean mineTag) {
		this.mineTag = mineTag;
	}

	public boolean isExpendTag() {
		return expendTag;
	}

	public void setExpendTag(boolean expendTag) {
		this.expendTag = expendTag;
	}

	public boolean isFlagTag() {
		return flagTag;
	}

	public void setFlagTag(boolean flagTag) {
		this.flagTag = flagTag;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public int getCountAround() {
		return countAround;
	}

	public void setCountAround(int countAround) {
		this.countAround = countAround;
	}

	public int getRightClickCount() {
		return rightClickCount;
	}

	public void setRightClickCount(int rightClickCount) {
		this.rightClickCount = rightClickCount;
	}

}
