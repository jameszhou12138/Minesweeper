package tools;

import java.util.Random;

import bean.MineLabel;

public class LayBomb {
	public static void lay(MineLabel[][] label, int row, int col) {
		int count = 0;
		Random random = new Random();
		while (count < StaticTool.allCount) {
			int x = random.nextInt(StaticTool.totalRow);
			int y = random.nextInt(StaticTool.totalColumn);
			if (!label[x][y].isMineTag() && !(x == row && y == col)) {
				label[x][y].setMineTag(true);
				label[x][y].setCountAround(9);
				if (StaticTool.isBackdoor) {
					label[x][y].setIcon(StaticTool.holeIcon);
				}
				count++;
			}
		}
		computeBomb(label);
	}

	public static void computeBomb(MineLabel[][] label) {
		for (int i = 0; i < label.length; i++) {
			for (int j = 0; j < label[i].length; j++) {
				if (!label[i][j].isMineTag()) {
					int count = 0;
					for (int x = Math.max(0, i - 1); x <= Math.min(StaticTool.totalRow - 1, i + 1); x++) {
						for (int y = Math.max(0, j - 1); y <= Math.min(StaticTool.totalColumn - 1, j + 1); y++) {
							if (label[x][y].isMineTag()) {
								count++;
							}
						}
					}
					label[i][j].setCountAround(count);
				}
			}
		}
	}
}
