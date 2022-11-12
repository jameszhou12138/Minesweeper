package panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dialog.AboutSweeping;
import dialog.UserDefinedJDialog;
import tools.StaticTool;
import main.MainFrame;

public class MenuBar extends JMenuBar {
	JMenu gameMenu = new JMenu("��Ϸ(G)");
	JMenu helpMenu = new JMenu("����(H)");
	JMenuItem startMenuItem = new JMenuItem("����(N)", KeyEvent.VK_N);
	JMenuItem primaryMenuItem = new JMenuItem("����(P)", KeyEvent.VK_P);
	JMenuItem intermediateMenuItem = new JMenuItem("�м�(I)", KeyEvent.VK_I);
	JMenuItem seniorMenuItem = new JMenuItem("�߼�(S)", KeyEvent.VK_S);
	JMenuItem customMenuItem = new JMenuItem("�Զ���(C)", KeyEvent.VK_C);
	JMenuItem quitMenuItem = new JMenuItem("�˳�(Q)", KeyEvent.VK_Q);
	JMenuItem aboutMenuItem = new JMenuItem("����(A)", KeyEvent.VK_A);
	JMenuItem backdoorMenuItem = new JMenuItem("����(B)", KeyEvent.VK_B);
	MainFrame mainFrame;

	public MenuBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		init();
	}

	private void init() {
		gameMenu.setMnemonic(KeyEvent.VK_G);
		helpMenu.setMnemonic(KeyEvent.VK_H);
		gameMenu.add(startMenuItem);
		startMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.reStartGame();
			}
		});
		gameMenu.addSeparator();
		gameMenu.add(primaryMenuItem);
		primaryMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StaticTool.totalRow = 9;
				StaticTool.totalColumn = 9;
				StaticTool.allCount = 10;
				mainFrame.reStartGame();
			}
		});
		gameMenu.add(intermediateMenuItem);
		intermediateMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StaticTool.totalRow = 16;
				StaticTool.totalColumn = 16;
				StaticTool.allCount = 40;
				mainFrame.reStartGame();
			}
		});
		gameMenu.add(seniorMenuItem);
		seniorMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StaticTool.totalRow = 16;
				StaticTool.totalColumn = 30;
				StaticTool.allCount = 99;
				mainFrame.reStartGame();
			}
		});
		gameMenu.addSeparator();// �˵�����ָ���
		gameMenu.add(customMenuItem);
		customMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new UserDefinedJDialog(mainFrame);
			}
		});
		gameMenu.addSeparator();// �˵�����ָ���
		gameMenu.add(quitMenuItem);
		quitMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(JFrame.EXIT_ON_CLOSE);
			}
		});
		helpMenu.add(aboutMenuItem);
		aboutMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutSweeping(mainFrame);
			}
		});
		helpMenu.add(backdoorMenuItem);
		backdoorMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StaticTool.isBackdoor = !StaticTool.isBackdoor;
			}
		});
		this.add(gameMenu);
		this.add(helpMenu);
	}
}
