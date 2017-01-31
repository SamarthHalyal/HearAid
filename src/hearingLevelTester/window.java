package hearingLevelTester;

import java.awt.BorderLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

@SuppressWarnings("serial")
public class window extends JFrame {
	static JComboBox<String> cb = new JComboBox<String>();
	static Vector<File> vec = new Vector<File>();
	static File file = null;
	static float fvalue = 0;
	@SuppressWarnings("rawtypes")
	static Vector graphpoints = new Vector();
	static int x[] = { 125, 250, 500, 750, 1000, 1500, 2000, 3000, 4000, 6000, 8000 };
	static double y[] = { 0.40000004, 0.05, 0.0, 0.05, -0.05, 0.0, 0.05, 0.05, 0.0, 0.05, 0.05 };
	static int count = 0;

	public static void main(String arg[]) {
		JFrame window = new JFrame();
		cb.addItem("125");
		cb.addItem("250");
		cb.addItem("500");
		cb.addItem("750");
		cb.addItem("1000");
		cb.addItem("1500");
		cb.addItem("2000");
		cb.addItem("3000");
		cb.addItem("4000");
		cb.addItem("6000");
		cb.addItem("8000");
		JPanel panel = new JPanel();
		JButton start = new JButton("Start");
		JButton yes = new JButton("Yes");
		JButton no = new JButton("No");
		TextField tf = new TextField("your mail id", 30);
		JButton dashboard = new JButton("DashBoard");

		panel.add(tf);
		panel.add(start);
		panel.add(yes);
		panel.add(no);
		panel.add(cb);
		panel.add(dashboard);

		String message1 = "In The Test Conducted on your ear, your ears have found to be working fine.";
		String message2 = "In The Test Conducted on your ear, your ears have found to be Little damaged.";
		String message3 = "In The Test Condunted on your ear, your ears have found to be completely damaged, Algorithms suggest that you refer a doctor!!";
		
		window.add(panel, BorderLayout.NORTH);
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.out.println("error");
		}
		window.setTitle("Hearit.");
		window.setSize(1000, 800);

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popup("Press \"Start\" to begin, After each \"no\" press wait for 3 seconds to completely heat the sound file!, Then press \"yes\", At last dashboard to view your results");
				f125();
				count++;
				setOutputVolume(0f);
				start.setEnabled(false);
				cb.setEnabled(false);
			}
		});

		yes.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent e) {
				fvalue = fvalue - 0.05f;
				setOutputVolume(fvalue);
				graphpoints.addElement(fvalue);
				count++;
				switch (count) {
				case 2:
					fvalue = 0;
					setOutputVolume(0f);
					break;
				case 3:
					fvalue = 0;
					setOutputVolume(0f);
					break;
				case 4:
					fvalue = 0;
					setOutputVolume(0f);
					break;
				case 5:
					fvalue = 0;
					setOutputVolume(0f);
					break;
				case 6:
					fvalue = 0;
					setOutputVolume(0f);
					break;
				case 7:
					fvalue = 0;
					setOutputVolume(0f);
					break;
				case 8:
					fvalue = 0;
					setOutputVolume(0f);
					break;
				case 9:
					fvalue = 0;
					setOutputVolume(0f);
					break;
				case 10:
					fvalue = 0;
					setOutputVolume(0f);
					break;
				case 11:
					fvalue = 0;
					setOutputVolume(0f);
					yes.setEnabled(false);
					no.setEnabled(false);
					break;
				}
			}
		});

		no.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fvalue = fvalue + 0.05f;
				setOutputVolume(fvalue);
				switch (count) {
				case 1:
					f125();
					break;
				case 2:
					f250();
					break;
				case 3:
					f500();
					break;
				case 4:
					f750();
					break;
				case 5:
					f1000();
					break;
				case 6:
					f1500();
					break;
				case 7:
					f2000();
					break;
				case 8:
					f3000();
					break;
				case 9:
					f4000();
					break;
				case 10:
					f6000();
					break;
				case 11:
					f8000();
					break;
				}
			}
		});

		XYSeries series = new XYSeries("Your Test");
		XYSeries series1 = new XYSeries("Recommended");
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		dataset.addSeries(series1);
		JFreeChart chart = ChartFactory.createXYLineChart("DashBoard", "frequency (Hertz)", "Decibels (db)", dataset);
		window.add(new ChartPanel(chart), BorderLayout.CENTER);

		dashboard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String user = tf.getText();
				int x = 0, o = 0, problem = 0;
				for (int i = 0; i < graphpoints.size(); i++, x++, o++) {
					String s = graphpoints.elementAt(i).toString();
					series.add(x, Float.valueOf(s).floatValue());
					series1.add(o, y[i]);
				}
				for (int c = 0; (c < graphpoints.size()) && (c < y.length); c++) {
					String s1 = graphpoints.elementAt(c).toString();
					if (Float.valueOf(s1).floatValue() > y.length) {
						problem++;
					}
				}
				if (problem < 5) {
					popup("you ears seem to be good.");
					if (mailer.sendmail("samarthhalyal@gmail.com", "roboticshooter", message1, user)) {
						System.out.println("Success!");
					}
				} else if (problem >= 5 && problem < 10) {
					popup("You may have ear problems.");
					if (mailer.sendmail("samarthhalyal@gmail.com", "roboticshooter", message2, user)) {
						System.out.println("Success!");
					}
				} else {
					popup("You Should refer a doctor.");
					if (mailer.sendmail("samarthhalyal@gmail.com", "roboticshooter", message3, user)) {
						System.out.println("Success!");
					}	
				}

			}
		});

		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}// End of main

	public static void popup(String s) {
		JOptionPane.showMessageDialog(null, s, "", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void setOutputVolume(float value) {
		String command = "set volume " + value;
		try {
			ProcessBuilder pb = new ProcessBuilder("osascript", "-e", command);
			pb.directory(new File("/usr/bin"));
			StringBuffer output = new StringBuffer();
			Process p = pb.start();
			p.waitFor();
			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				output.append(line + "\n");
			}
			System.out.println(output);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void f125() {
		file = new File("audiocheck.net_sin_125Hz_-3dBFS_3s.wav");
		try {
			FileInputStream f = new FileInputStream(file);
			AudioStream as = new AudioStream(f);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
		}
	}

	public static void f250() {
		file = new File("audiocheck.net_sin_250Hz_-3dBFS_3s.wav");
		try {
			FileInputStream f = new FileInputStream(file);
			AudioStream as = new AudioStream(f);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
		}
	}

	public static void f500() {
		file = new File("audiocheck.net_sin_500Hz_-3dBFS_3s.wav");
		try {
			FileInputStream f = new FileInputStream(file);
			AudioStream as = new AudioStream(f);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
		}
	}

	public static void f750() {
		file = new File("audiocheck.net_sin_750Hz_-3dBFS_3s.wav");
		try {
			FileInputStream f = new FileInputStream(file);
			AudioStream as = new AudioStream(f);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
		}
	}

	public static void f1000() {
		file = new File("3s1000Hz .wav");
		try {
			FileInputStream f = new FileInputStream(file);
			AudioStream as = new AudioStream(f);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
		}
	}

	public static void f1500() {
		file = new File("audiocheck.net_sin_1500Hz_-3dBFS_3s.wav");
		try {
			FileInputStream f = new FileInputStream(file);
			AudioStream as = new AudioStream(f);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
		}
	}

	public static void f2000() {
		file = new File("audiocheck.net_sin_2000Hz_-3dBFS_3s.wav");
		try {
			FileInputStream f = new FileInputStream(file);
			AudioStream as = new AudioStream(f);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
		}
	}

	public static void f3000() {
		file = new File("audiocheck.net_sin_3000Hz_-3dBFS_3s.wav");
		try {
			FileInputStream f = new FileInputStream(file);
			AudioStream as = new AudioStream(f);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
		}
	}

	public static void f4000() {
		file = new File("audiocheck.net_sin_4000Hz_-3dBFS_3s.wav");
		try {
			FileInputStream f = new FileInputStream(file);
			AudioStream as = new AudioStream(f);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
		}
	}

	public static void f6000() {
		file = new File("audiocheck.net_sin_6000Hz_-3dBFS_3s.wav");
		try {
			FileInputStream f = new FileInputStream(file);
			AudioStream as = new AudioStream(f);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
		}
	}

	public static void f8000() {
		file = new File("audiocheck.net_sin_8000Hz_-3dBFS_3s.wav");
		try {
			FileInputStream f = new FileInputStream(file);
			AudioStream as = new AudioStream(f);
			AudioPlayer.player.start(as);
		} catch (Exception e) {
		}
	}
}
