package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mdlaf.MaterialLookAndFeel;
import mdlaf.animation.MaterialUIMovement;
import mdlaf.utils.MaterialColors;

public class Main extends JFrame implements ActionListener {

	JPanel underPanel;
	JPanel timePanel;
	JLabel time;
	JLabel pomo;
	JLabel status;
	JButton startBtn;
	JButton pauseBtn;
	JButton stopBtn;
	Timer timer;
	int sec;
	int min;
	int minCnt;
	int intervalCnt;
	boolean intervalCheck;
	int pomoCnt;
	boolean isRun;

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(new MaterialLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		Main frame = new Main("PomoTimer");
		frame.setVisible(true);

	}

	Main(String title) {
		setTitle(title);
		setBounds(100, 100, 250, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		underPanel = new JPanel();
		startBtn = new JButton("Start");
		pauseBtn = new JButton("Pause");
		stopBtn = new JButton("Stop");
		timePanel = new JPanel();
		time = new JLabel("00:00");
		pomo = new JLabel("0 pomo");
		status = new JLabel("stop");
		timer = new Timer(1000, this);

		status.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		time.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 80));
		pomo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		underPanel.add(startBtn);
		underPanel.add(pauseBtn);
		underPanel.add(stopBtn);
		underPanel.setBackground(Color.WHITE);
		timePanel.setBackground(Color.WHITE);
		timePanel.add(pomo);
		timePanel.add(time);
		timePanel.add(status);
		timePanel.setLayout(new FlowLayout());

		startBtn.addActionListener(this);
		pauseBtn.addActionListener(this);
		stopBtn.addActionListener(this);
		MaterialUIMovement.add(startBtn, MaterialColors.GREEN_300);
		MaterialUIMovement.add(pauseBtn, MaterialColors.RED_300);
		MaterialUIMovement.add(stopBtn, MaterialColors.RED_300);

		Container contentPane = getContentPane();
		contentPane.add(underPanel, BorderLayout.SOUTH);
		contentPane.add(timePanel, BorderLayout.CENTER);

		sec = 0;
		min = 0;
		minCnt = 0;
		intervalCnt = 0;
		intervalCheck = false;
		pomoCnt = 0;
		isRun = false;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stopBtn) {
			sec = 0;
			min = 0;
			minCnt = 0;
			intervalCnt = 0;
			intervalCheck = false;
			isRun = false;
			status.setText("Stop");
			time.setText("00:00");
			timePanel.setBackground(Color.WHITE);
			timer.stop();
		} else if (e.getSource() == startBtn) {
			if (isRun == false) {
				time.setText("00:00");
				timePanel.setBackground(new Color(236, 104, 0));
			}
			status.setText("Started");
			timer.start();
		} else if (e.getSource() == pauseBtn) {
			timer.stop();
			isRun = true;
			status.setText("Pause");
		} else {
			sec++;

			if (sec > 59) {
				sec = 0;
				min++;
				minCnt++;
				if (intervalCheck) {
					intervalCnt++;
				}
			}

			if (minCnt == 25 & intervalCheck == false) {
				sec = 0;
				min = 0;
				minCnt = 0;
				intervalCheck = true;
				intervalCnt++;
				timePanel.setBackground(new Color(0, 164, 151));
			}

			if (intervalCnt > 5) {
				sec = 0;
				min = 0;
				minCnt = 0;
				intervalCnt = 0;
				pomoCnt++;
				intervalCheck = false;
				timePanel.setBackground(new Color(236, 104, 0));
				pomo.setText(pomoCnt + " pomo");
			}

			String strSec = "";
			String strMin = "";
			if (sec < 10) {
				strSec = "0" + sec;
			} else {
				strSec = String.valueOf(sec);
			}
			if (min < 10) {
				strMin = "0" + min;
			} else {
				strMin = String.valueOf(min);
			}

			time.setText(strMin + ":" + strSec);
		}
		//		System.out.println(timer.isRunning());
		//		System.out.println("minCnt:" + minCnt);
		//		System.out.println("intervalCnt:" + intervalCnt);
		//		System.out.println("pomoCnt:" + pomoCnt);
		//		System.out.println();
	}

}

// twitter連携
