import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.Scanner;
import java.net.URL;
import java.io.File;
import java.util.Random;
import java.util.Calendar;
import java.io.PrintWriter;
import java.lang.StringBuilder;


public class Tanks extends JPanel {


public static void main(String[] args) {
	
	JFrame window = new JFrame("Tanks");
      Tanks content = new Tanks();
      window.setContentPane(content);
      window.pack();
      Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
      window.setLocation( (screensize.width - window.getWidth())/2,
            (screensize.height - window.getHeight())/2 );
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setResizable(false);  
      window.setVisible(true);
	  
}


public static class Applet extends JApplet {
      public void init() {
         setContentPane( new Tanks() );
      }
   }
   
   
   
   private JLabel message; 
   private JLabel roundMessage;
   private JLabel instructionMessage;
   private JLabel commandMessage;
   private JLabel commandList;
   private JLabel mode;
   
   
   private JButton startGame;
   
   private JButton saveGame;
   
   private JButton loadGame;
   
  
   
   private JLabel inputTheCommand;
   
   private JButton upMove;
   
   private JButton downMove;
   
   private JButton leftMove;
   
   private JButton rightMove;
   
   private JButton undoMove;
   
   private JButton startMove;
   
   private JButton shootUp;
   
   private JButton shootDown;
   
   private JButton shootLeft;
   
   private JButton shootRight;
   
   private JTextArea commandSequence;
   
   
   
   
   
   public Tanks() {
      
      setLayout(null); 
      setPreferredSize( new Dimension(1300,650) );
      
      setBackground(new Color(0,150,0)); 
      
     
      
      Board board = new Board(); 
	  
	  
      add(board);
      
      add(message);
	  add(roundMessage);
	  add(commandMessage);
	  add(commandList);
	  add(mode);
	  
	  add(startGame);
	  add(saveGame);
	  add(loadGame);
	  
	  
	  add(inputTheCommand);
	  add(startMove);
	  
	  add(upMove);
	  add(downMove);
	  add(leftMove);
	  add(rightMove);
	  
	  add(undoMove);
	  
	  add(shootUp);
	  add(shootDown);
	  add(shootLeft);
	  add(shootRight);
	  
      add(commandSequence);
      
      board.setBounds(170,100,544,544); 
      
      message.setBounds(500, 10, 200, 30);
	  roundMessage.setBounds(500,40, 200, 30);
	  mode.setBounds(450, 60, 300, 30);
	  commandMessage.setBounds(900,10,300,30);
	  
	  
	  startGame.setBounds(750, 100, 120, 30);
	  saveGame.setBounds(950, 100, 120, 30);
      loadGame.setBounds(1150, 100, 120, 30);
	  
	 
	  inputTheCommand.setBounds(750, 250, 120, 20);
	  startMove.setBounds(1110, 500, 120, 50);
	  
	  upMove.setBounds(750, 300, 120, 50);
	  downMove.setBounds(870, 300, 120, 50);
	  leftMove.setBounds(990, 300, 120, 50);
	  rightMove.setBounds(1110, 300, 120, 50);
	  shootUp.setBounds(750, 400, 120, 50);
	  shootDown.setBounds(870, 400, 120, 50);
	  shootLeft.setBounds(990, 400, 120, 50);
	  shootRight.setBounds(1110, 400, 120, 50);
	  
	  
	  undoMove.setBounds(750,500,120,50);
	  
	  commandList.setBounds(2,60,100,30);
	  commandSequence.setBounds(2,100,150,500);
	  
   } // end constructor
   
   
   
   
   
   private class Board extends JPanel implements ActionListener{
      
      
      TanksData board;  
      
      boolean gameInProgress;
      
     
      private String difficulty = "Easy";
	  
      int currentPlayer;  
	  
      int selectedRow, selectedCol;  
      
     
      
	  private int myTankX;
	  
	  private int myTankY;
	  
	  private int enemyTankX;
	  
	  private int enemyTankY; 
	  
	  private String myCurrentPosition;
	  
	  private String enemyCurrentPosition;
	  
	  private ArrayList<String> listOfCommand = new ArrayList<String>();
	  
	  private int roundNumber = 1;
	  
	  
      /**
       * Constructor.  Create the buttons and lable.  Listens for mouse
        * clicks and for clicks on the buttons.  Create the board and
        * start the first game.
       */
      Board() {
         setBackground(Color.BLACK);
         
         message = new JLabel("",JLabel.CENTER);
         message.setFont(new  Font("Serif", Font.BOLD, 14));
         message.setForeground(Color.green);
		 message.setText("Welcome to Tanks");
		 
		 roundMessage = new JLabel("",JLabel.CENTER);
         roundMessage.setFont(new  Font("Serif", Font.BOLD, 14));
         roundMessage.setForeground(Color.yellow);
		 roundMessage.setText("Round:");
		
		 commandMessage = new JLabel("",JLabel.CENTER);
         commandMessage.setFont(new  Font("Serif", Font.BOLD, 14));
		 commandList = new JLabel("Commend List:",JLabel.CENTER);
         commandList.setFont(new  Font("Serif", Font.BOLD, 14));
		 
		 mode = new JLabel("Mode: " + difficulty,JLabel.CENTER);
         mode.setFont(new  Font("Serif", Font.BOLD, 14));
		 mode.setForeground(Color.orange);
		 
		 
		 
		 startGame = new JButton("Start Game");
		 startGame.addActionListener(this);
		 saveGame = new JButton("Save");
		 saveGame.addActionListener(this);
		 loadGame = new JButton("Load");
		 loadGame.addActionListener(this);
		 
		 
		 inputTheCommand = new JLabel("Select Movement: ");
		 startMove = new JButton("Start");
		 startMove.addActionListener(this);
		 
		 upMove = new JButton("Go Up");
		 upMove.addActionListener(this);
		 downMove = new JButton("Go Down");
		 downMove.addActionListener(this);
		 leftMove = new JButton("Go Left");
		 leftMove.addActionListener(this);
		 rightMove = new JButton("Go Right");
		 rightMove.addActionListener(this);
		 
		 undoMove = new JButton("Undo");
		 undoMove.addActionListener(this);
		 
		 shootUp = new JButton("Shoot Up");
		 shootUp.addActionListener(this);
		 shootDown = new JButton("Shoot Down");
		 shootDown.addActionListener(this);
		 shootLeft = new JButton("Shoot Left");
		 shootLeft.addActionListener(this);
		 shootRight = new JButton("Shoot Right");
		 shootRight.addActionListener(this);
		 
		 commandSequence = new JTextArea(10,20);
		
		 
         board = new TanksData();
         //doNewGame();
		 myTankX = 2;
	  
	     myTankY = 2;
	  
		 enemyTankX = 512;
	  
	     enemyTankY = 512;
		 
		 roundNumber = 1;
		 myCurrentPosition = "LOOK DOWN";
		 enemyCurrentPosition = "LOOK UP";
		 repaint();
      }
      
      
      /**
       * Respond to user's click on one of the two buttons.
       */
      public void actionPerformed(ActionEvent evt) {
         Object obj = evt.getSource();
         
		
		if (obj == startGame){
			 startTheGame();
		 }
		else if (obj == saveGame){
			try{
				saveTheGame();
			}
			catch(Exception e){}
		}
		else if (obj == loadGame){
			try{
				loadTheGame();
			}
			catch(Exception e){}
		}
		
		else if(obj == startMove){
			startTheRound();
			 
		}
		
		else if(obj == upMove){
			
			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				
				listOfCommand.add("Go Up");
				commandSequence.append("\n");
				commandSequence.append("Go Up");
				commandMessage.setText("");
			}
		}
		else if(obj == downMove){
			
			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				
				listOfCommand.add("Go Down");
				commandSequence.append("\n");
				commandSequence.append("Go Down");
				commandMessage.setText("");
			}
		}
		else if(obj == leftMove){
			
			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				
				listOfCommand.add("Go Left");
				commandSequence.append("\n");
				commandSequence.append("Go Left");
				commandMessage.setText("");
			}
		}
		else if(obj == rightMove){
			
			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				
				listOfCommand.add("Go Right");
				commandSequence.append("\n");
				commandSequence.append("Go Right");
				commandMessage.setText("");
			}
		}
		else if(obj == shootUp){
			
			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				
				listOfCommand.add("Shoot Up");
				commandSequence.append("\n");
				commandSequence.append("Shoot Up");
				commandMessage.setText("");
			}
		}
		else if(obj == shootDown){
			
			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				
				listOfCommand.add("Shoot Down");
				commandSequence.append("\n");
				commandSequence.append("Shoot Down");
				commandMessage.setText("");
			}
		}
		else if(obj == shootLeft){
			
			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				
				listOfCommand.add("Shoot Left");
				commandSequence.append("\n");
				commandSequence.append("Shoot Left");
				commandMessage.setText("");
			}
		}
		else if(obj == shootRight){
			
			if(listOfCommand.size() >= 18)
			{
				JOptionPane.showMessageDialog(null, "Command exceed 18!!", "Too Much Command", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				
				listOfCommand.add("Shoot Right");
				commandSequence.append("\n");
				commandSequence.append("Shoot Right");
				commandMessage.setText("");
			}
		}
		
		else if(obj == undoMove){
			
			
			if(listOfCommand.size() >= 1){
				listOfCommand.remove(listOfCommand.size() - 1);
				commandSequence.setText(null);
				if(listOfCommand.size() >= 1){
					int a = listOfCommand.size() - 1;
					for(int i = 0; i <= a; i++){
						commandSequence.append("\n");
						commandSequence.append(listOfCommand.get(i));
					}
				}
			}
			commandMessage.setText("");
		}
		
      }
	  
	public void startTheGame(){
		Object[] options = { "Easy", "Hard", "Cancel" };
		int choice = JOptionPane.showOptionDialog(null, 
		"Choose the game's difficulty", 
		"New Game", 
		JOptionPane.YES_NO_CANCEL_OPTION, 
		JOptionPane.QUESTION_MESSAGE, 
		null, options, 
		options[0]);
		int choiceOverwrite;
 
		if(choice == JOptionPane.YES_OPTION)
		{
			if(roundNumber > 1){
				Object[] optionsB = { "Yes", "No" };
				choiceOverwrite = JOptionPane.showOptionDialog(null, 
				"You will remove the current game. Continue?", 
				"Remove Current Game", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, optionsB, optionsB[0]);
				if(choiceOverwrite == JOptionPane.YES_OPTION){
					restart("Easy");
					
					
				}
		}
		else{
			restart("Easy");
		}
		}
		else if(choice == JOptionPane.NO_OPTION){
			if(roundNumber > 1){
				Object[] optionsB = { "Yes", "No" };
				choiceOverwrite = JOptionPane.showOptionDialog(null, 
				"You will remove the current game. Continue?", 
				"Remove Current Game", 
				JOptionPane.YES_NO_OPTION, 
				JOptionPane.QUESTION_MESSAGE, 
				null, optionsB, optionsB[0]);
				if(choiceOverwrite == JOptionPane.YES_OPTION){
					restart("Hard");
					
					
				}
		}
		else{
			restart("Hard");
		}
		}
	}
	public void restart(String difficulty){
		roundNumber = 1;
		myTankX = 2;
		myTankY = 2;
	  
		enemyTankX = 512;
		enemyTankY = 512;
		 
		myCurrentPosition = "LOOK DOWN";
		enemyCurrentPosition = "LOOK UP";
		
		mode.setText("Mode: " + difficulty);
		
		repaint();
	}
	public void saveTheGame() throws IOException{
		URL path = Tanks.class.getResource("command.txt");
		File f = new File(path.getFile());
		BufferedWriter bWriter = new BufferedWriter(new FileWriter(f));
		bWriter.write(Integer.toString(roundNumber) + "A");
		bWriter.write(Integer.toString(myTankX) + "A");
		bWriter.write(Integer.toString(myTankY) + "A");
		bWriter.write(Integer.toString(enemyTankX) + "A");
		bWriter.write(Integer.toString(enemyTankY));
		
		bWriter.close();
		
		
		
		
	}
	
	public void loadTheGame() throws IOException{
		ArrayList<Character> loadRoundNumber = new ArrayList<Character>();
		ArrayList<Character> loadMyTankX = new ArrayList<Character>();
		ArrayList<Character> loadMyTankY = new ArrayList<Character>();
		ArrayList<Character> loadEnemyTankX = new ArrayList<Character>();
		ArrayList<Character> loadEnemyTankY = new ArrayList<Character>();
		
		
		
		
		int loaded;
		URL path = Tanks.class.getResource("command.txt");
		File f = new File(path.getFile());
		BufferedReader loadReader = new BufferedReader(new FileReader(f));
		while((loaded = loadReader.read()) != -1){
			
			if((char)loaded != 'A'){
				loadRoundNumber.add((char)loaded);
			}
			
			else{
					break;
			}
		}
		
		while((loaded = loadReader.read()) != -1){
			
			if((char)loaded != 'A'){
				loadMyTankX.add((char)loaded);
			}
			
			else{
					break;
			}
		}
		
		while((loaded = loadReader.read()) != -1){
			
			if((char)loaded != 'A'){
				loadMyTankY.add((char)loaded);
			}
			
			else{
					break;
			}
		}
		
		while((loaded = loadReader.read()) != -1){
			
			if((char)loaded != 'A'){
				loadEnemyTankX.add((char)loaded);
			}
			
			else{
					break;
			}
		}
		
		while((loaded = loadReader.read()) != -1){
			
			if((char)loaded != 'A'){
				loadEnemyTankY.add((char)loaded);
			}
			
			else{
					break;
			}
		}
		
		StringBuilder roundBuilder = new StringBuilder(loadRoundNumber.size());
		for(Character ch: loadRoundNumber)
		{
			roundBuilder.append(ch);
		}
		
		StringBuilder myXBuilder = new StringBuilder(loadMyTankX.size());
		for(Character ch: loadMyTankX)
		{
			myXBuilder.append(ch);
		}
		
		StringBuilder myYBuilder = new StringBuilder(loadMyTankY.size());
		for(Character ch: loadMyTankY)
		{
			myYBuilder.append(ch);
		}
		StringBuilder enemyXBuilder = new StringBuilder(loadEnemyTankX.size());
		for(Character ch: loadEnemyTankX)
		{
			enemyXBuilder.append(ch);
		}
		StringBuilder enemyYBuilder = new StringBuilder(loadEnemyTankY.size());
		for(Character ch: loadEnemyTankY)
		{
			enemyYBuilder.append(ch);
		}
		
		roundNumber = Integer.parseInt(roundBuilder.toString());
		myTankX = Integer.parseInt(myXBuilder.toString());
		myTankY = Integer.parseInt(myYBuilder.toString());
		enemyTankX = Integer.parseInt(enemyXBuilder.toString());
		enemyTankY = Integer.parseInt(enemyYBuilder.toString());
		repaint();
	}
	
	public void startTheRound(){
		
		boolean correctPosition;
		correctPosition = checkMyPosition();
		if(listOfCommand.size() < 18){
			commandMessage.setText("You have to input 18 commands!!");
			return;
		}
				if(correctPosition){
					
					makeMove();
					
				}
				 
			 }
		
	  
	public boolean checkMyPosition(){
		  boolean inRange = true;
		
		  int expectedX = myTankX;
		  int expectedY = myTankY;
		  for(int i = 0; i <listOfCommand.size(); i++){
			  if(listOfCommand.get(i) == "Go Up"){
				  expectedY = expectedY - 30;
				  if(expectedY < 2){
					  commandMessage.setText("Out of board range!");
					  return false;
				  }
				  
			  }
			  else if(listOfCommand.get(i) == "Go Down"){
				  expectedY = expectedY + 30;
				  if(expectedY > 512){
					  commandMessage.setText("Out of board range!");
					  return false;
				  }
				  
			  }
			  else if(listOfCommand.get(i) == "Go Left"){
				  expectedX = expectedX - 30;
				  if(expectedX < 2){
					  commandMessage.setText("Out of board range!");
					  return false;
				  }
				  
			  }
			  else if(listOfCommand.get(i) == "Go Right"){
				  expectedX = expectedX + 30;
				  if(expectedX > 512){
					  commandMessage.setText("Out of board range!");
					  return false;
				  }
				  
				  
			  }
			  if(i==1){
				if((expectedX == enemyTankX) && (expectedY == enemyTankY)){
					  commandMessage.setText("First step clashing the enemy tank!");
					  return false;
			}
			  } 
		  }
		  return true;
	  }
	
	
	
	public void makeMove(){
		
		
        try{                  
		Thread t1 = new Thread(new Runnable() {
		public void run() {
			try{ 
				commandMessage.setText("");
				int expectedX = enemyTankX;
				int expectedY = enemyTankY;
				Random enemyDecision;
				int enemyMove;
				boolean validMove = false;
				boolean endOfGame = false;
		
		//char[] myMoveSet = inputCommand.getText().toCharArray();
				String winner;
		
				for(int i = 0; i < listOfCommand.size(); i++){
		
		
		if(listOfCommand.get(i) == "Go Up"){
				myTankY = myTankY - 30;
				myCurrentPosition = "GO UP";
		}
		else if(listOfCommand.get(i) == "Go Down"){
				myTankY = myTankY + 30;
				myCurrentPosition = "GO DOWN";
		}
		else if(listOfCommand.get(i) == "Go Left"){
				myTankX = myTankX - 30;
				myCurrentPosition = "GO LEFT";
		}
		else if(listOfCommand.get(i) == "Go Right"){
				myTankX = myTankX + 30;
				myCurrentPosition = "GO RIGHT";
		}
		else if(listOfCommand.get(i) == "Shoot Up"){
				myCurrentPosition = "SHOOT UP";
		}
		else if(listOfCommand.get(i) == "Shoot Down"){
				myCurrentPosition = "SHOOT DOWN";
		}
		else if(listOfCommand.get(i) == "Shoot Left"){
				myCurrentPosition = "SHOOT LEFT";
		}
		else if(listOfCommand.get(i) == "Shoot Right"){
				myCurrentPosition = "SHOOT RIGHT";
		}
		
		validMove = false;
		if(difficulty == "Easy"){
			while(validMove == false){
				expectedX = enemyTankX;
				expectedY = enemyTankY;
				enemyDecision = new Random();
				enemyMove = enemyDecision.nextInt((4 - 1) + 1) + 1;
				if(enemyMove == 1){
					expectedX = expectedX + 30;
					if(expectedX <= 512){
						enemyTankX = enemyTankX + 30;
						enemyCurrentPosition = "GO RIGHT";
						validMove = true;
					}
					else{
						validMove = false;
					}
					
				}
				else if(enemyMove == 2){
					expectedX = expectedX - 30;
					if(expectedX >= 2){
						enemyTankX = enemyTankX - 30;
						enemyCurrentPosition = "GO LEFT";
						validMove = true;
					}
					else{
						validMove = false;
					}
				}
				else if(enemyMove == 3){
					expectedY = expectedY + 30;
				if(expectedY <= 512){
						enemyTankY = enemyTankY + 30;
						enemyCurrentPosition = "GO DOWN";
						validMove = true;
					}
					else{
						validMove = false;
					}
				}
				else if(enemyMove == 4){
					expectedY = expectedY - 30;
					if(expectedY >= 2){
						enemyTankY = enemyTankY - 30;
						enemyCurrentPosition = "GO UP";
						validMove = true;
					}
					else{
						validMove = false;
					}
				}
		}
		}
		else{
			while(validMove == false){
				expectedX = enemyTankX;
				expectedY = enemyTankY;
				enemyDecision = new Random();
				enemyMove = enemyDecision.nextInt((8 - 1) + 1) + 1;
				if(enemyMove == 1){
					expectedX = expectedX + 30;
					if((expectedX <= 512)&&(enemyTankX <= (myTankX - 30))){
						enemyTankX = enemyTankX + 30;
						enemyCurrentPosition = "GO RIGHT";
						validMove = true;
					}
					else{
						validMove = false;
					}
					
				}
				else if(enemyMove == 2){
					expectedX = expectedX - 30;
					if((expectedX >= 2)&&(enemyTankX >= (myTankX + 30))){
						enemyTankX = enemyTankX - 30;
						enemyCurrentPosition = "GO LEFT";
						validMove = true;
					}
					else{
						validMove = false;
					}
				}
				else if(enemyMove == 3){
					expectedY = expectedY + 30;
				if((expectedY <= 512)&&(enemyTankY <= (myTankX - 30))){
						enemyTankY = enemyTankY + 30;
						enemyCurrentPosition = "GO DOWN";
						validMove = true;
					}
					else{
						validMove = false;
					}
				}
				else if(enemyMove == 4){
					expectedY = expectedY - 30;
					if((expectedY >= 2)&&(enemyTankY >= (myTankX + 30))){
						enemyTankY = enemyTankY - 30;
						enemyCurrentPosition = "GO UP";
						validMove = true;
					}
					else{
						validMove = false;
					}
				}
				else if(enemyMove == 5){
				if(((myTankX < (enemyTankX - 30))||(myTankX > enemyTankX + 30))||(enemyTankY <= myTankY)){
					validMove = false;
				}
				else{
					enemyCurrentPosition = "SHOOT UP";
					validMove = true;
					}
				
				}
				else if(enemyMove == 6){
				if(((myTankX < (enemyTankX - 30))||(myTankX > enemyTankX + 30))||(enemyTankY >= myTankY)){
					validMove = false;
				}
				else{
					enemyCurrentPosition = "SHOOT DOWN";
					validMove = true;
					}
				
				}
				else if(enemyMove == 7){
				if(((myTankY < (enemyTankY - 30))||(myTankY > enemyTankY + 30))||(enemyTankX <= myTankX)){
					validMove = false;
				}
				else{
					enemyCurrentPosition = "SHOOT LEFT";
					validMove = true;
					}
				
				}
				else if(enemyMove == 8){
				if(((myTankY < (enemyTankY - 30))||(myTankY > enemyTankY + 30))||(enemyTankX >= myTankX)){
					validMove = false;
				}
				else{
					enemyCurrentPosition = "SHOOT RIGHT";
					validMove = true;
					}
				
				}
			
					
							
			
			}
		}
					repaint();
					Thread.sleep(1000);
					
					
					winner = checkWinner();
						if((winner == "Player")||(winner == "Enemy")||(winner == "Draw")){
							gameOver(winner);
							endOfGame = true;
							i = 17;
						
						}
						
					
					
					
				
					
				
		}
				listOfCommand.clear();
				if(!endOfGame){
					roundNumber++;
					repaint();
				}
					}	
				
		catch(Exception e){
						e.printStackTrace();
												}
				}
		

	   	});
		t1.start();
		}
		catch(Exception e){
						
												}
				
			
       
	  
	   commandSequence.setText(null);
	   
	   
	   
	  }
	  
	  public void gameOver(String winner){
		if(winner == "Player"){
			JOptionPane.showMessageDialog(null, "You win!! Total Round: " + roundNumber, "Game Over", JOptionPane.INFORMATION_MESSAGE);

			//commandMessage.setText("You win!!"); 
		}
		else if(winner == "Enemy"){
			JOptionPane.showMessageDialog(null, "You lose!!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			//commandMessage.setText("You lose!!");
		}
		else if(winner == "Draw"){
			JOptionPane.showMessageDialog(null, "It is a draw!!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
			//commandMessage.setText("It is a draw!!");
		}
	  }
	  public String checkWinner(){
		  
		  String winner = "Pending";
		  if(myTankX == enemyTankX){
				if(myTankY < enemyTankY){
					if((myCurrentPosition == "SHOOT DOWN")&&(enemyCurrentPosition != "SHOOT UP")){
						winner = "Player";
					  
					}
					else if((myCurrentPosition != "SHOOT DOWN")&&(enemyCurrentPosition == "SHOOT UP")){
						winner = "Enemy";
					}
					else if((myCurrentPosition == "SHOOT DOWN")&&(enemyCurrentPosition == "SHOOT UP")){
						winner = "Draw";
					}
					
			
			  }
				else if(myTankY > enemyTankY){
				    if((myCurrentPosition == "SHOOT UP")&&(enemyCurrentPosition != "SHOOT DOWN")){
						winner = "Player";
					}
					else if((myCurrentPosition != "SHOOT UP")&&(enemyCurrentPosition == "SHOOT DOWN")){
						winner = "Enemy";
					}
					else if((myCurrentPosition == "SHOOT UP")&&(enemyCurrentPosition == "SHOOT DOWN")){
						winner = "Draw";
					}
				  
			  }
			  
		  }
		  else if(myTankY == enemyTankY){
				if(myTankX > enemyTankX){
					if((myCurrentPosition == "SHOOT LEFT")&&(enemyCurrentPosition != "SHOOT RIGHT")){
						winner = "Player";
					}
					else if((myCurrentPosition != "SHOOT LEFT")&&(enemyCurrentPosition == "SHOOT RIGHT")){
						winner = "Enemy";
					}
					else if((myCurrentPosition == "SHOOT LEFT")&&(enemyCurrentPosition == "SHOOT RIGHT")){
						winner = "Draw";
					}
					
			  }
				else if(myTankX < enemyTankX){
					if((myCurrentPosition == "SHOOT RIGHT")&&(enemyCurrentPosition != "SHOOT LEFT")){
						winner = "Player";
					}
					else if((myCurrentPosition != "SHOOT RIGHT")&&(enemyCurrentPosition == "SHOOT LEFT")){
						winner = "Enemy";
					}
					else if((myCurrentPosition == "SHOOT RIGHT")&&(enemyCurrentPosition == "SHOOT LEFT")){
						winner = "Draw";
					}
					
				}
		  }
		  return winner;
	  }
	  

		
	   
		 
	  public void paintComponent(Graphics g) {
         
         
         String myTank = "myTankDown.jpg";
		 String enemyTank = "enemyTankUp.jpg";
		 
         g.setColor(Color.black);
         g.drawRect(0,0,getSize().width-1,getSize().height-1);
         g.drawRect(1,1,getSize().width-3,getSize().height-3);
         
         
         
         for (int row = 0; row < 18; row++) {
            for (int col = 0; col < 18; col++) {
               if ( row % 2 == col % 2 )
                  g.setColor(Color.LIGHT_GRAY);
               else
                  g.setColor(Color.GRAY);
               g.fillRect(2 + col*30, 2 + row*30, 30, 30);
              
            }
         }
		 
        
        if(myCurrentPosition == "GO UP"){
			myTank = "myTankUp.jpg";
		}
		else if(myCurrentPosition == "GO DOWN"){
			myTank = "myTankDown.jpg";
		}
		else if(myCurrentPosition == "GO LEFT"){
			myTank = "myTankLeft.jpg";
		}
		else if(myCurrentPosition == "GO RIGHT"){
			myTank = "myTankRight.jpg";
		}
		else if(myCurrentPosition == "SHOOT UP"){
			myTank = "myTankShootUp.jpg";
		} 
		else if(myCurrentPosition == "SHOOT DOWN"){
			myTank = "myTankShootDown.jpg";
		} 
        else if(myCurrentPosition == "SHOOT LEFT"){
			myTank = "myTankShootLeft.jpg";
		}
		else if(myCurrentPosition == "SHOOT RIGHT"){
			myTank = "myTankShootRight.jpg";
		}
		 
		
		if(enemyCurrentPosition == "GO UP"){
			enemyTank = "enemyTankUp.jpg";
		} 
		else if(enemyCurrentPosition == "GO DOWN"){
			enemyTank = "enemyTankDown.jpg";
		} 
        else if(enemyCurrentPosition == "GO LEFT"){
			enemyTank = "enemyTankLeft.jpg";
		}
		else if(enemyCurrentPosition == "GO RIGHT"){
			enemyTank = "enemyTankRight.jpg";
		}
		else if(enemyCurrentPosition == "SHOOT UP"){
			enemyTank = "enemyTankShootUp.jpg";
		}
		else if(enemyCurrentPosition == "SHOOT DOWN"){
			enemyTank = "enemyTankShootDown.jpg";
		}
		else if(enemyCurrentPosition == "SHOOT LEFT"){
			enemyTank = "enemyTankShootLeft.jpg";
		}
		else if(enemyCurrentPosition == "SHOOT RIGHT"){
			enemyTank = "enemyTankShootRight.jpg";
		}
		
		Image muh = getToolkit().getImage(myTank);
		 g.drawImage(muh,myTankX,myTankY,30,30,this);
		muh = getToolkit().getImage(enemyTank);
		 g.drawImage(muh,enemyTankX,enemyTankY,30,30,this);
		 
		roundMessage.setText("Round:" + roundNumber);
		 }
      
	  
	  
	 
   }
   
    private static class TanksData {
      
     

      static final int
				TANKONE = 1,
				TANKTWO = 2,
				MISSLEONE = 3,
				MISSLETWO = 4;
                
      
      
      //int[][] board;  
	}
   
   
  
   
}