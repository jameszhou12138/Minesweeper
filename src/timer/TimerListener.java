package timer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import tools.StaticTool;
import main.MainFrame;

public class TimerListener implements ActionListener {
	MainFrame mainFrame;

	public TimerListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StaticTool.timeCount++;
		if (StaticTool.timeCount > 999) {
			StaticTool.timeCount = 999;
		}
		mainFrame.getFaceJPanel().setTime(StaticTool.timeCount);
	}

}
