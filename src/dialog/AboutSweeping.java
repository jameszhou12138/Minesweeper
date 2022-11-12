package dialog;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import tools.StaticTool;
import main.MainFrame;

public class AboutSweeping extends JDialog {

	public AboutSweeping(MainFrame mainFrame) {
		super(mainFrame);
		this.setTitle("关于");
		this.add(getPanel());
		this.setSize(new Dimension(300, 200));
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setModal(true);
		this.setVisible(true);
	}

	private JPanel getPanel() {
		JPanel infoPanel = new JPanel();
		JLabel titleIcon = new JLabel(StaticTool.imageIcon);
		JLabel titleLabel = new JLabel("扫雷");
		titleLabel.setFont(new Font("黑体", Font.BOLD, 20));
		Box titleBox = Box.createHorizontalBox();
		titleBox.add(titleIcon);
		titleBox.add(Box.createHorizontalStrut(20));
		titleBox.add(titleLabel);
		JLabel versionLabel = new JLabel("版本：V1.0.0");
		Box versionBox = Box.createHorizontalBox();
		versionBox.add(versionLabel);
		JLabel developerLabel = new JLabel("开发者：周子涵");
		Box developerBox = Box.createHorizontalBox();
		developerBox.add(developerLabel);
		JLabel emailLabel = new JLabel("联系开发者：ZihanZhou@bjfu.edu.com");
		Box emailBox = Box.createHorizontalBox();
		emailBox.add(emailLabel);
		JLabel timeLabel = new JLabel("开发时间：2022.7.1 - 2022.7.9");
		Box timeBox = Box.createHorizontalBox();
		timeBox.add(timeLabel);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		infoPanel.add(titleBox);
		infoPanel.add(versionBox);
		infoPanel.add(developerBox);
		infoPanel.add(emailBox);
		infoPanel.add(timeBox);
		JPanel layoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		infoPanel.add(layoutPanel);
		Border border1 = BorderFactory.createEtchedBorder();
		infoPanel.setBorder(border1);
		JPanel totalPanel = new JPanel(new BorderLayout());
		Border border2 = BorderFactory.createEmptyBorder(10, 10, 10, 10);
		totalPanel.add(infoPanel);
		totalPanel.setBorder(border2);
		return totalPanel;
	}

}
