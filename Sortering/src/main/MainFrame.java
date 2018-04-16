package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtLength;
	private JTextField txtRes;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static int n;
	private static int select = 0;
	long totalTime;
	Comparable[] tall;
	int[] tallx;
	long startTime;
	long endTime;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 363, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// TextFields
		txtLength = new JTextField();
		txtLength.setBounds(143, 23, 146, 26);
		contentPane.add(txtLength);
		txtLength.setColumns(10);

		txtRes = new JTextField();
		txtRes.setBounds(143, 183, 146, 26);
		contentPane.add(txtRes);
		txtRes.setColumns(10);

		// Choice
		JRadioButton rdbtnQuick = new JRadioButton("Quick");
		rdbtnQuick.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select = 1;
			}
		});
		buttonGroup.add(rdbtnQuick);
		rdbtnQuick.setBounds(125, 61, 73, 29);
		contentPane.add(rdbtnQuick);

		JRadioButton rdbtnInsertion = new JRadioButton("Insertion");
		rdbtnInsertion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select = 2;
			}
		});
		buttonGroup.add(rdbtnInsertion);
		rdbtnInsertion.setBounds(231, 61, 95, 29);
		contentPane.add(rdbtnInsertion);

		JRadioButton rdbtnMerge = new JRadioButton("Merge");
		rdbtnMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				select = 3;
			}
		});
		buttonGroup.add(rdbtnMerge);
		rdbtnMerge.setBounds(125, 100, 87, 29);
		contentPane.add(rdbtnMerge);

		JRadioButton rdbtnRadix = new JRadioButton("Radix");
		rdbtnRadix.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				select = 4;
			}
		});
		buttonGroup.add(rdbtnRadix);
		rdbtnRadix.setBounds(231, 98, 95, 29);
		contentPane.add(rdbtnRadix);

		// Buttons
		JButton btnEstimate = new JButton("Estimate");
		btnEstimate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				totalTime = 0;
				for (int i = 0; i < 5; i++){
					makeArray();
					run();	
				}
				
				totalTime = totalTime/5;
				Double C = totalTime / (n*Math.log(n));
				txtRes.setText(Double.toString(C));
			}
		});
		btnEstimate.setBounds(54, 141, 115, 29);
		contentPane.add(btnEstimate);

		JButton btnRun = new JButton("Run");
		totalTime = 0;
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				makeArray();
				run();
				txtRes.setText(Double.toString(totalTime));
			}
			
		});
		btnRun.setBounds(209, 141, 115, 29);
		contentPane.add(btnRun);

		// Labels
		JLabel lblAlgorithme = new JLabel("Algorithme");
		lblAlgorithme.setBounds(15, 62, 87, 20);
		contentPane.add(lblAlgorithme);

		JLabel lblResult = new JLabel("Result in ms");
		lblResult.setBounds(15, 186, 105, 20);
		contentPane.add(lblResult);

		JLabel lblLength = new JLabel("Length");
		lblLength.setBounds(15, 26, 69, 20);
		contentPane.add(lblLength);

	}

	public void run() {
		startTime = System.currentTimeMillis();
		if (select == 0){
			System.out.println("ENTER A NUMBER FROM 1 TO INFINITYI(depending on your prossesor");
		} else if (select == 1) {
			Sort.quickSort(tall);
		} else if (select == 2) {
			Sort.insertionSort(tall);
		} else if (select == 3) {
			Sort.mergeSort(tall);
		} else if (select == 4) {
			Sort.radixSort(tallx, n);
		}
		endTime = System.currentTimeMillis();

		totalTime += (endTime - startTime);
	}
	
	public void makeArray(){
		n = Integer.parseInt(txtLength.getText());
		tall = new Integer[n];
		tallx = new int[n];

		for (int i = 0; i < n; i++) {
			if (select == 4) {
				tallx[i] = (int) (Math.random() * 1000);
			} else {
				tall[i] = (int) (Math.random() * (2 * n));
			}
		}
	}

}
