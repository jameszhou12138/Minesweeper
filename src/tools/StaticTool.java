package tools;

import javax.swing.Icon;
import javax.swing.ImageIcon;


public class StaticTool {

	public static int allCount = 10;
	public static int totalColumn = 9;
	public static int totalRow = 9;
	public static int timeCount = 0;
	public static int bombCount = allCount;

	public static boolean isStart = false;
	public static boolean isBackdoor = false;

	public static Icon[] num = new Icon[10];
	public static Icon[] time = new Icon[11];

	public static ImageIcon imageIcon = new ImageIcon("./image/icon.gif");
	public static Icon iconBlank = new ImageIcon("./image/blank.gif");
	public static Icon bloodIcon = new ImageIcon("./image/blood.gif");
	public static Icon icon0 = new ImageIcon("./image/0.gif");
	public static Icon clickIcon = new ImageIcon("./image/face2.gif");
	public static Icon smileIcon = new ImageIcon("./image/face0.gif");
	public static Icon faultFaceIcon = new ImageIcon("./image/face3.gif");
	public static Icon winFaceIcon = new ImageIcon("./image/face4.gif");
	public static Icon flagIcon = new ImageIcon("./image/flag.gif");
	public static Icon askIcon = new ImageIcon("./image/ask.gif");
	public static Icon askPressIcon = new ImageIcon("./image/ask1.gif");
	public static Icon downSmileIcon = new ImageIcon("./image/face1.gif");
	public static Icon errorBombIcon = new ImageIcon("./image/error.gif");
	public static Icon blackBombIcon = new ImageIcon("./image/mine.gif");
	public static Icon holeIcon = new ImageIcon("./image/hole.gif");

	static {
		for (int i = 0; i < num.length; i++) {
			num[i] = new ImageIcon("./image/" + i + ".gif");
		}
		for (int i = 0; i <= num.length; i++) {
			time[i] = new ImageIcon("./image/d" + i + ".gif");
		}
	}

}
