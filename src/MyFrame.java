import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/*
  Anything that is related to GUI must be declared here otherwise marks will be deducted.
  You can include game logic here or you can create new classes to handle game logic – 
  this is optional you will not gain or lose marks for this. Add a comment to explain your choice.
*/
public class MyFrame extends JFrame implements ActionListener {
	
	JFrame welcomeFrame;
	JFrame gameFrame;
	JFrame scoreFrame;
	private int frameWidth = 500;
	private int frameHeight = 500;
	int gameNumber;
	int winCounter = 0;
	int loseCounter = 0;
	int input1;
	int totalInput;
	boolean play;
    int[] numbers = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 1};
    JButton button1, button2, button3, button4, button5, button6, button7, button8, button9;
	
	MyFrame() {
				//Creating the 'Welcome' Frame.
				welcomeFrame = new JFrame ("Math Game");
				welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				welcomeFrame.setSize(frameWidth, frameHeight);
				JPanel northPanelWelcomeFrame = new JPanel();
				JPanel centerPanelWelcomeFrame = new JPanel (new FlowLayout (FlowLayout.CENTER));
				
				//Creating 'Welcome' Text.
				JLabel welcomeLabel = new JLabel ("Welcome to mini math game!");
				northPanelWelcomeFrame.add(welcomeLabel);    			
	
    			//Creating a 'Start Game' Button.
    			JButton startButton = new JButton ("Start");
    			centerPanelWelcomeFrame.add(startButton);
    			startButton.addActionListener(this);
    			
    			//Add Text & Button to Frame, and display Frame.
    			welcomeFrame.add(northPanelWelcomeFrame, BorderLayout.NORTH);
    			welcomeFrame.add(centerPanelWelcomeFrame);
//    			centerPanel.setPreferredSize(new Dimension(200, 100)); Can't get resize to work, why?
    			welcomeFrame.setVisible(true);
    			
    }
	
	public void gameFrame () {
		
				//Creating the 'Math Game' Frame.
				gameFrame = new JFrame("Math Game");
				gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameFrame.setSize(frameWidth, frameHeight);
				JPanel northPanelGameFrame = new JPanel();
				northPanelGameFrame.setLayout(new BoxLayout(northPanelGameFrame, BoxLayout.Y_AXIS));
				JPanel centerPanelGameFrame = new JPanel();
				JPanel centerSouthPanelGameFrame = new JPanel((new FlowLayout()));
				centerPanelGameFrame.add(centerSouthPanelGameFrame, BorderLayout.CENTER); //Tried to create panel within panel, still can't buttons to center.
				
				generateRandom();
				
				//Creating 'Game Instructions' and 'Win-Lose Counters'.
				JLabel gameInstructions = new JLabel ("Select two numbers that add up to " + gameNumber);
				JLabel counters = new JLabel ("Win: " + winCounter + ", Lose: " + loseCounter);
				gameInstructions.setAlignmentX(Component.CENTER_ALIGNMENT);
				counters.setAlignmentX(Component.CENTER_ALIGNMENT);
				northPanelGameFrame.add(gameInstructions);
				northPanelGameFrame.add(counters);
				
				//Adding buttons
				button1 = new JButton ("1");
				button2 = new JButton ("2");
				button3 = new JButton ("3");
				button4 = new JButton ("4");
				button5 = new JButton ("5");
				button6 = new JButton ("6");
				button7 = new JButton ("7");
				button8 = new JButton ("8");
				button9 = new JButton ("9");
				JButton endButton = new JButton ("End Game");
				endButton.addActionListener(new EndButtonListener());
				//button1.addActionListener(this);
				//button1.addActionListener(new Button1Listener());
				//button2.addActionListener(this);
				//button2.addActionListener(new Button2Listener());
				//button3.addActionListener(this);
				//button3.addActionListener(new Button3Listener());
				//button4.addActionListener(this);
				//button4.addActionListener(new Button4Listener());
				//button5.addActionListener(this);
				//button5.addActionListener(new Button5Listener());
				//button6.addActionListener(this);
				//button6.addActionListener(new Button6Listener());
				//button7.addActionListener(this);
				//button7.addActionListener(new Button7Listener());
				//button8.addActionListener(this);
				//button8.addActionListener(new Button8Listener());
				//button9.addActionListener(this);
				//button9.addActionListener(new Button9Listener());
				thehandler handler = new thehandler();
				button1.addActionListener(handler);
				button2.addActionListener(handler);
				button3.addActionListener(handler);
				button4.addActionListener(handler);
				button5.addActionListener(handler);
				button6.addActionListener(handler);
				button7.addActionListener(handler);
				button8.addActionListener(handler);
				button9.addActionListener(handler);
				
				centerSouthPanelGameFrame.add(button1);
				centerSouthPanelGameFrame.add(button2);
				centerSouthPanelGameFrame.add(button3);
				centerSouthPanelGameFrame.add(button4);
				centerSouthPanelGameFrame.add(button5);
				centerSouthPanelGameFrame.add(button6);
				centerSouthPanelGameFrame.add(button7);
				centerSouthPanelGameFrame.add(button8);
				centerSouthPanelGameFrame.add(button9);
				
				gameFrame.add(northPanelGameFrame, BorderLayout.NORTH);
				gameFrame.add(centerPanelGameFrame, BorderLayout.CENTER);
				gameFrame.add(endButton, BorderLayout.SOUTH);
				gameFrame.setVisible(true);
				playGame();
	}
	
	public void scoreFrame () {
		
				//Creating the 'Score' Frame.
				scoreFrame = new JFrame("Math Game");
				scoreFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				scoreFrame.setSize(frameWidth, frameHeight);
				JPanel scoreFrameNorthPanel = new JPanel();
				JPanel scoreFrameCenterPanel = new JPanel();
				
				scoreFrameCenterPanel.setLayout(new GridLayout(4,1,0,0)); //how do I eliminate gap?
				JLabel gameAnalysisDisplay = new JLabel("Game Session Data Analysed:");
				JLabel numbersCorrectDisplay = new JLabel ("Numbers you got correct: ");
				JLabel numbersWrongDisplay = new JLabel ("Numbers you got wrong: ");
				JLabel percentWinLargeNrDisplay = new JLabel ("Percentage of win for numbers greater than or equal to 10: ");
				JLabel percentWinSmallNrDisplay = new JLabel ("Percentage of win for numbers less than 10: ");
				
				scoreFrameNorthPanel.add(gameAnalysisDisplay);
				scoreFrameCenterPanel.add(numbersCorrectDisplay);
				scoreFrameCenterPanel.add(numbersWrongDisplay);
				scoreFrameCenterPanel.add(percentWinLargeNrDisplay);
				scoreFrameCenterPanel.add(percentWinSmallNrDisplay);
				scoreFrame.add(scoreFrameNorthPanel, BorderLayout.NORTH);
				scoreFrame.add(scoreFrameCenterPanel, BorderLayout.CENTER);
				scoreFrame.setVisible(true);
	}
	
	public int generateRandom() {
				gameNumber = (int) (Math.floor(Math.random()*18)+1);
				if (gameNumber == 1) {
					gameNumber = (int) (1+(Math.floor(Math.random()*17)+1));
				}
				return gameNumber;
	}

	
	public void actionPerformed(ActionEvent e) {	//Upon clicking "Start" button, 'Welcome Frame' closes & 'Game Frame' opens.
				welcomeFrame.dispose();
				gameFrame();
		
	}
	
	public class EndButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
				play = false;
				gameFrame.dispose();
				scoreFrame();
		}
	}

	
	private class thehandler implements ActionListener{
		public void actionPerformed (ActionEvent event) {
				if (event.getSource()==button1) {
					input1 = 1;
//					System.out.println(input1);
				}
				if (event.getSource()==button2) {
					input1 = 2;
				}
				if (event.getSource()==button3) {
					input1 = 3;
				}
				if (event.getSource()==button4) {
					input1 = 4;
				}
				if (event.getSource()==button5) {
					input1 = 5;
				}
				if (event.getSource()==button6) {
					input1 = 6;
				}
				if (event.getSource()==button7) {
					input1 = 7;
				}
				if (event.getSource()==button8) {
					input1 = 8;
				}
				if (event.getSource()==button9) {
					input1 = 9;
				}
		}
	}
		
	void playGame() {
				if (input1 == gameNumber){
					winCounter++;
				}
				else
					loseCounter++;
	}
}
