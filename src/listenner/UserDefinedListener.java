package listenner;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import tools.StaticTool;
import main.MainFrame;
import dialog.UserDefinedJDialog;

public class UserDefinedListener implements ActionListener {
	UserDefinedJDialog userDefinedJDialog;

	MainFrame mainFrame;

	public UserDefinedListener(UserDefinedJDialog userDefinedJDialog,
							   MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.userDefinedJDialog = userDefinedJDialog;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == userDefinedJDialog.getCancerButton()) {
			userDefinedJDialog.dispose();
			mainFrame.reStartGame();
		} else if (e.getSource() == userDefinedJDialog.getSureButton()) {
			String highT = userDefinedJDialog.getHeightText().getText();
			Pattern pattern = Pattern.compile("^[0-9]{1,3}$");
			Matcher matcher = pattern.matcher(highT);
			int row = 0;
			if (!matcher.matches()) {
				userDefinedJDialog.getMessageLabel().setText("输入的行数范围应在9-30之间");
				return;
			} else {
				row = Integer.parseInt(highT);
				if (row < 9 || row > 30) {
					userDefinedJDialog.getMessageLabel().setText("输入的行数范围应在9-30之间");
					return;
				}

			}
			String colT = userDefinedJDialog.getWidthText().getText();
			int col = 0;
			try {
				col = Integer.parseInt(colT);
				if (col < 9 || col > 30) {
					userDefinedJDialog.getMessageLabel().setText("输入的列数范围应在9-30之间");
					return;
				}
			} catch (Exception e2) {
				userDefinedJDialog.getMessageLabel().setText("列数应该为数字且范围应在9-30之间");
				return;
			}

			String mineT = userDefinedJDialog.getBombText().getText();
			int mine = 0;
			try {
				mine = Integer.parseInt(mineT);
				if (mine < 10) {
					mine = 10;
				} else if (mine > col * row) {
					userDefinedJDialog.getMessageLabel().setText("雷数应该小于行数乘以列数");
					return;
				} else {
					mine = Math.min(mine, StaticTool.totalRow * StaticTool.totalColumn * 4 / 5);
				}
			} catch (Exception e3) {
				userDefinedJDialog.getMessageLabel().setText("雷数应该为数字");
				return;
			}
			userDefinedJDialog.dispose();
			StaticTool.totalRow = row;
			StaticTool.totalColumn = col;
			StaticTool.allCount = mine;

			mainFrame.reStartGame();
		}

	}

}
