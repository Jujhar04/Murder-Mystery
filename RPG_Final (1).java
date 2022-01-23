//Name: Ranpo's Investigation 
//Due Date: 11/3/21 
//Purpose: Simulates a murder mystery type game.

import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.beans.*;
import java.text.*;

import java.util.*;
import java.util.Arrays;
import java.util.Random;

import java.awt.*;
import java.awt.event.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

// RPG class game
public class RPG_Final extends JPanel implements ActionListener, PropertyChangeListener {

	// GLOBAL VARIABLES --------------------------------
	// count times lost
	int timesLost = 0;
	// will be used to display date/time at the beginning/end of game and as timer
	LocalDateTime start, end;
	//format DateTime as shown in quotes when you use dtf
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	// decides if dialog window should close
	Boolean exit = false;
	// decides if player quit and if they are playing again, to ignore the current
	// task and go to intro method
	Boolean quitConclusion = false;
	// variables for task 7
	char a = ' ';
	char b = ' ';
	char c = ' ';
	char d = ' ';
	char e = ' ';
	char f = ' ';
	char g = ' ';
	char h = ' ';
	char i = ' ';
	char turn = 'X';

	// MAIN METHOD -------------------------------------
	public static void main(String args[]) {
		new RPG_Final();
	}

	// DEFAULT CONSTRUCTOR ------------------------------
	public RPG_Final() {
		// Start the time 
		start = LocalDateTime.now(); 
		password();
	}

	// GAME START AND TASK METHODS ----------------------
	// create password GUI and show it
	public void password() {
		// Create and set up the window.
		JFrame frame = new JFrame("Password");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		final Password newContentPane = new Password(frame);
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Make sure the focus goes to the right component
		// whenever the frame is initially given the focus.
		frame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				// if exit is true, exit the password dialog box
				if (exit) {
					exit = false;
					frame.dispose();
				}
				newContentPane.resetFocus();
			}
		});
		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	// Menu
	public void menu() {

		// Start game by welcoming and displaying time and starting the timer
		// Dialog box 
		JOptionPane.showMessageDialog(null, "Welcome to the Armed Detective Agency game!", dtf.format(start),
				JOptionPane.INFORMATION_MESSAGE);

		// Begin the game by pressing enter
		String start = IO.inputString("PRESS 'ENTER' TO START THE GAME ");
		if (start.equals(""))
			progressBar();
	}

	// Introduction
	public void introduction() {
		// Show intro ascii art
		introArt();

		// Dialog box with image showing the agency's office
		ImageIcon office = new ImageIcon("office.jpeg");
		JOptionPane.showMessageDialog(null, "This is the Agency's office!", null, JOptionPane.INFORMATION_MESSAGE,
				office);

		// Slowly print introduction paragraph
		IO.printSlow("\n\nHey Ranpo, welcome back to the Armed Detective Agency.\n", 25);
		System.out.println(
				"We have a very important mission for you to accomplish that the rest of us were not able to do.\n"
						+ "It is a murder case on the murder of your detective assistant's wife.\n"
						+ "You have to solve a murder case within 6 hours or the murderer will most likely escape!\n"
						+ "Are you ready for this adventure?");
		pause();
		System.out.println(
				"Your trustworthy detective assistant, who you have been working with for several years will be assisting you.\n"
						+ "This is the same assistant whose wife has just been murdered.\n");
		IO.printSlow("Keep in mind, if you lose THREE times, you will lose the game!");
		pause();
		// Start the first task in the game from the intro
		task1();
	}

	// Task 1 - destination -------------------------------
	public void task1() {
		// Print title "DESTINATION"
		System.out.println("██████╗░███████╗░██████╗████████╗██╗███╗░░██╗░█████╗░████████╗██╗░█████╗░███╗░░██╗");
		System.out.println("██╔══██╗██╔════╝██╔════╝╚══██╔══╝██║████╗░██║██╔══██╗╚══██╔══╝██║██╔══██╗████╗░██║");
		System.out.println("██║░░██║█████╗░░╚█████╗░░░░██║░░░██║██╔██╗██║███████║░░░██║░░░██║██║░░██║██╔██╗██║");
		System.out.println("██║░░██║██╔══╝░░░╚═══██╗░░░██║░░░██║██║╚████║██╔══██║░░░██║░░░██║██║░░██║██║╚████║");
		System.out.println("██████╔╝███████╗██████╔╝░░░██║░░░██║██║░╚███║██║░░██║░░░██║░░░██║╚█████╔╝██║░╚███║");
		System.out.println("╚═════╝░╚══════╝╚═════╝░░░░╚═╝░░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝░░░╚═╝░░░╚═╝░╚════╝░╚═╝░░╚══╝");

		IO.printSlow("\nHere is a device that will tell you where to go.", 25);

		// Question
		String ans = IO.inputString("Ask it \"Where should I go?\"\n\n");

		// If the user asks the right question, show the dropDown box to select where to go
		if (ans.length() >= 17 && (ans.equals("Where should I go?") || ans.equals("where should I go?")
				|| ans.equals("where should i go?") || ans.equals("Where should i go?"))) {
			dropDown();
		} // if ans
			// Else if the user asks the wrong question, print out "Wrong Question!" and "Try Again!"
			// User loses a chance and has the chance to quit or try task 1 again
		else {
			IO.printSlow("\nWrong Answer!", 25);
			lose();
			if (!quitConclusion)
				task1();
			quitConclusion = false;
		} // else
	} // task1

	// Task 2 - injuries cause -------------------------------
	public void task2() {
		// Print title "INJURIES CAUSE'
		System.out.println(
				"██╗███╗░░██╗░░░░░██╗██╗░░░██╗██████╗░██╗███████╗░██████╗  ░█████╗░░█████╗░██╗░░░██╗░██████╗███████╗");
		System.out.println(
				"██║████╗░██║░░░░░██║██║░░░██║██╔══██╗██║██╔════╝██╔════╝  ██╔══██╗██╔══██╗██║░░░██║██╔════╝██╔════╝");
		System.out.println(
				"██║██╔██╗██║░░░░░██║██║░░░██║██████╔╝██║█████╗░░╚█████╗░  ██║░░╚═╝███████║██║░░░██║╚█████╗░█████╗░░");
		System.out.println(
				"██║██║╚████║██╗░░██║██║░░░██║██╔══██╗██║██╔══╝░░░╚═══██╗  ██║░░██╗██╔══██║██║░░░██║░╚═══██╗██╔══╝░░");
		System.out.println(
				"██║██║░╚███║╚█████╔╝╚██████╔╝██║░░██║██║███████╗██████╔╝  ╚█████╔╝██║░░██║╚██████╔╝██████╔╝███████╗");
		System.out.println(
				"╚═╝╚═╝░░╚══╝░╚════╝░░╚═════╝░╚═╝░░╚═╝╚═╝╚══════╝╚═════╝░  ░╚════╝░╚═╝░░╚═╝░╚═════╝░╚═════╝░╚══════╝");

		// Print "This is task two"
		System.out.println("\nThis is task two.\n");
		// Print "You have to find out how the victim got injuries"
		System.out.println("You have to find out how the victim got injuries.");
		pause();
		// Slowly print the hint
		IO.printSlow("The victim had a broken jaw, had bruises, fatal brain bleeding, and a twisted spine.\n");
		// Print multiple Choice
		System.out.println("(a) Wrench ");
		System.out.println("(b) Candlestick ");
		System.out.println("(c) Curb stomped ");
		System.out.println("(d) Bat ");

		// If statement for 2 strings, use .equals; for char, use single quote
		// Options for multiple choice
		char choice = IO.inputChar("a/b/c/d? ");
		// Print what user chose
		IO.printSlow("\nYou answered: " + choice);

		// Use method to determine if user input char is the correct answer or not and whether user quits, tries again, or moves on to next task
		mcChoice(choice);
	} // task2

	// Task 3 - unscramble -------------------------------
	public void task3() {
		// Print title "UNSCRAMBLE"
		System.out.println("██╗░░░██╗███╗░░██╗░██████╗░█████╗░██████╗░░█████╗░███╗░░░███╗██████╗░██╗░░░░░███████╗");
		System.out.println("██║░░░██║████╗░██║██╔════╝██╔══██╗██╔══██╗██╔══██╗████╗░████║██╔══██╗██║░░░░░██╔════╝");
		System.out.println("██║░░░██║██╔██╗██║╚█████╗░██║░░╚═╝██████╔╝███████║██╔████╔██║██████╦╝██║░░░░░█████╗░░");
		System.out.println("██║░░░██║██║╚████║░╚═══██╗██║░░██╗██╔══██╗██╔══██║██║╚██╔╝██║██╔══██╗██║░░░░░██╔══╝░░");
		System.out.println("╚██████╔╝██║░╚███║██████╔╝╚█████╔╝██║░░██║██║░░██║██║░╚═╝░██║██████╦╝███████╗███████╗");
		System.out.println("░╚═════╝░╚═╝░░╚══╝╚═════╝░░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚═╝░░░░░╚═╝╚═════╝░╚══════╝╚══════╝");

		// Slowly print "This is task three"
		IO.printSlow("\nThis is task three.");
		// Slowly print the following
		System.out.println(
				"\nWe found out that the victim did not die just by being curb stomped, but by something else as well."
						+ "\nThe curb stomp was most likely used because\n"
						+ "the murderer didn't want the victim to give information by talking.");

		// While quit is 'n' do the following
		do {
			System.out.println("\nUnscramble the following word to find out the murder weapon:\n");

			// Revolver scrambled
			String word = IO.inputString("veorelr\n");

			// If what the user types is "revolver" or "Revolver", they move on
			if (word.equals("revolver") || word.equals("Revolver") || word.equals("REVOLVER")) {
				System.out.println("\nCorrect!\n" + "The murder weapon was a revolver!");
				pause();
				task4();
			}
			// Else, the answer is wrong
			else {
				// User loses one of their chances
				System.out.println();
				lose();
			}
		} while (!quitConclusion); // while
		quitConclusion = false;
	} // task3

	// Task 4 - fill in the line -------------------------------
	public void task4() {
		// Print "FILL IN THE BLANK"
		System.out.println("\n" + "███████╗██╗██╗░░░░░██╗░░░░░  ██╗███╗░░██╗  ████████╗██╗░░██╗███████╗\n"
				+ "██╔════╝██║██║░░░░░██║░░░░░  ██║████╗░██║  ╚══██╔══╝██║░░██║██╔════╝\n"
				+ "█████╗░░██║██║░░░░░██║░░░░░  ██║██╔██╗██║  ░░░██║░░░███████║█████╗░░\n"
				+ "██╔══╝░░██║██║░░░░░██║░░░░░  ██║██║╚████║  ░░░██║░░░██╔══██║██╔══╝░░\n"
				+ "██║░░░░░██║███████╗███████╗  ██║██║░╚███║  ░░░██║░░░██║░░██║███████╗\n"
				+ "╚═╝░░░░░╚═╝╚══════╝╚══════╝  ╚═╝╚═╝░░╚══╝  ░░░╚═╝░░░╚═╝░░╚═╝╚══════╝\n" + "\n"
				+ "██████╗░██╗░░░░░░█████╗░███╗░░██╗██╗░░██╗\n" + "██╔══██╗██║░░░░░██╔══██╗████╗░██║██║░██╔╝\n"
				+ "██████╦╝██║░░░░░███████║██╔██╗██║█████═╝░\n" + "██╔══██╗██║░░░░░██╔══██║██║╚████║██╔═██╗░\n"
				+ "██████╦╝███████╗██║░░██║██║░╚███║██║░╚██╗\n" + "╚═════╝░╚══════╝╚═╝░░╚═╝╚═╝░░╚══╝╚═╝░░╚═╝");

		// Print "This is task four"
		System.out.println("\nThis is task four.\n");

		// Slowly print the following
		IO.printSlow("The murderer may have touched the weapon!\n"
				+ "What information do you think we can gather from this?\n"
				+ "Fill in the missing word from the sentence below:");
		// Make user input their answer
		String fill = IO.inputString("\nI think we can get the murderer's ____________.\n");

		// If their answer is the following
		if (fill.equals("Finger print") || fill.equals("finger print") || fill.equals("Fingerprint")
				|| fill.equals("FingerPrint") || fill.equals("fingerprint") || fill.equals("Finger Print")
				|| fill.equals("FINGERPRINT") || fill.equals("FINGER PRINT")) {
			// Slowly print "Correct!"
			System.out.println("\nCorrect! We can get the murderer's fingerprint from the revolver!");
			pause();
			task5();
		} else {
			System.out.println("\nWrong answer!\n");
			// User loses one of their chances
			lose();
			if (!quitConclusion)
				task4();
			quitConclusion = false;
		}

	} // end task4

	// Task 5 - maze -------------------------------
	public void task5() {
		// Print title "MAZE"
		System.out.println("\n" + "███╗░░░███╗░█████╗░███████╗███████╗\n" + "████╗░████║██╔══██╗╚════██║██╔════╝\n"
				+ "██╔████╔██║███████║░░███╔═╝█████╗░░\n" + "██║╚██╔╝██║██╔══██║██╔══╝░░██╔══╝░░\n"
				+ "██║░╚═╝░██║██║░░██║███████╗███████╗\n" + "╚═╝░░░░░╚═╝╚═╝░░╚═╝╚══════╝╚══════╝");

		// Print "This is task five"
		System.out.println("\nThis is task five.\n");

		// Slowly print the following
		IO.printSlow("You now have to find the fingerprint scanner. It's somewhere in this place.\n");

		// Slowly print maze instructions
		IO.printSlow("This is a maze to find the fingerprint scanner.\n" + "You are at (a).\n" + "Get to (j).\n"
				+ "NOTE: To quit at any time while in the maze, type (1)\n");

		// Print maze
		System.out.println("----------");
		System.out.println("-a b    c-");
		System.out.println("--- ---- -");
		System.out.println("--- --d e-");
		System.out.println("-f g---- -");
		System.out.println("---h    i-");
		System.out.println("-j k----l-");
		System.out.println("----------\n");

		// Go to the starting position in the maze
		placeA();
	} // end task5

	// Task 6 - fingerprint -------------------------------
	public void task6() {
		// Print title "Fingerprint"
		System.out.println("\n" + "███████╗██╗███╗░░██╗░██████╗░███████╗██████╗░██████╗░██████╗░██╗███╗░░██╗████████╗\n"
				+ "██╔════╝██║████╗░██║██╔════╝░██╔════╝██╔══██╗██╔══██╗██╔══██╗██║████╗░██║╚══██╔══╝\n"
				+ "█████╗░░██║██╔██╗██║██║░░██╗░█████╗░░██████╔╝██████╔╝██████╔╝██║██╔██╗██║░░░██║░░░\n"
				+ "██╔══╝░░██║██║╚████║██║░░╚██╗██╔══╝░░██╔══██╗██╔═══╝░██╔══██╗██║██║╚████║░░░██║░░░\n"
				+ "██║░░░░░██║██║░╚███║╚██████╔╝███████╗██║░░██║██║░░░░░██║░░██║██║██║░╚███║░░░██║░░░\n"
				+ "╚═╝░░░░░╚═╝╚═╝░░╚══╝░╚═════╝░╚══════╝╚═╝░░╚═╝╚═╝░░░░░╚═╝░░╚═╝╚═╝╚═╝░░╚══╝░░░╚═╝░░░");

		// Print "This is task six"
		System.out.println("\nThis is task six.");
		pause();

		// Show the third fingerprint
		fingerprint3();

		// Slowly print the following
		IO.printSlow("\nWe found the murderer's fingerprint. This is it.\n"
				+ "Now match this with a fingerprint from the following:", 25);
		pause();

		IO.printSlow("This is the first person's fingerprint.\n", 25);
		// Show the first fingerprint
		fingerprint1();
		pause();

		IO.printSlow("This is the second person's fingerprint.\n", 25);
		// Show the second fingerprint
		fingerprint2();
		pause();

		IO.printSlow("This is the third fingerprint, your assistant's fingerprint.\n", 25);
		// Show the third fingerprint
		fingerprint3();
		pause();

		IO.printSlow("This is the fourth fingerprint, your fingerprint.\n", 25);
		// Show the third fingerprint
		fingerprint4();
		pause();

		// Slowly print the following questions
		IO.printSlow("Whose fingerprint does the murderer's fingerprint match?", 25);

		// Method takes the user's choice of fingerprint and sees if it is the correct one and next steps if so or not
		matchFingerprint();
	}

	// Task 7 - tic tac toe ---------------------------
	public void task7() {
		System.out.println("████████╗██╗░█████╗░  ████████╗░█████╗░░█████╗░  ████████╗░█████╗░███████╗\n"
				+ "╚══██╔══╝██║██╔══██╗  ╚══██╔══╝██╔══██╗██╔══██╗  ╚══██╔══╝██╔══██╗██╔════╝\n"
				+ "░░░██║░░░██║██║░░╚═╝  ░░░██║░░░███████║██║░░╚═╝  ░░░██║░░░██║░░██║█████╗░░\n"
				+ "░░░██║░░░██║██║░░██╗  ░░░██║░░░██╔══██║██║░░██╗  ░░░██║░░░██║░░██║██╔══╝░░\n"
				+ "░░░██║░░░██║╚█████╔╝  ░░░██║░░░██║░░██║╚█████╔╝  ░░░██║░░░╚█████╔╝███████╗\n"
				+ "░░░╚═╝░░░╚═╝░╚════╝░  ░░░╚═╝░░░╚═╝░░╚═╝░╚════╝░  ░░░╚═╝░░░░╚════╝░╚══════╝");

		// Print "This is task seven"
		System.out.println("\nThis is task seven.\n");

		// Print out story/game instructions
		IO.printSlow("We were all surprised that it was him.\n"
				+ "He has a final wish before he reveals all the grisly details about his wife's murder to us.\n"
				+ "He'd like to play a game of Tic Tac Toe with you.\n"
				+ "It's a classic game he used to play with his wife all the time.");
		pause();
		IO.printSlow("Your assistant tells you that you may take the first turn.\n"
				+ "The x-coordinates you can choose from are written across the top of the tic tac toe board.\n"
				+ "The y coordinates are written on the leftmost side.\n"
				+ "In order to win this game, you must have 3 'O's in a row, column, or diagonally before your assistant.\n"
				+ "Good luck!");
		pause();
		
		// While the game isn't done, print the board and have the assistant or user take a turn
		while (!isDone()) {
			printboard();
			// assistant takes a turn if 'X' and turn becomes 'O'
			if (turn == 'X') {
				assistant(turn);
				turn = 'O';
			// else user takes a turn and turn becomes 'X'
			} else {
				makemove(turn);
				turn = 'X';
			}
		}
		printboard();

		// Method to determine who the winner is and next steps if the user wins/loses
		determineWinner();
	}

	// METHODS USED IN TASK METHODS ---------------------
	// Conclusion
	public void conclusion() {
		// conclusion ascii art
		concArt();

		// Print unique separator line using unicode character/rounding usinf a for loop
		System.out.println("\n");
		for (int i = 0; i < 20; i++) {
			System.out.print("\u0905" + IO.round(((Math.random()*9)+1),1) + "\u0905");
		}
		System.out.println("\n");
		
		// Duration the entire game was played 
		end = LocalDateTime.now();
		System.out.println("You played this game for " + timeElapsed (start, end) + " minutes.\n");
		// Ask user if they'd like to play again
		char playAgain = IO.inputChar("Play Again? (y/n): ");

		// If else to determine true/false
		Boolean play = (playAgain == 'y' || playAgain == 'Y') ? true : false;

		// If the user wants to play the game again, reset times lost and begin at the introduction method
		if (play) {
			timesLost = 0;
			System.out.println();
			introduction();
		}
		// Otherwise thank the user for playing and exit
		else {
			System.out.println("\nThanks for playing this game by Jujhar Grewal!\n\nGoodbye!");
			System.exit(0);
		}
	}
	
	// Method to display time elapsed to end of game - timer
	public long timeElapsed(LocalDateTime a, LocalDateTime b) {
		Duration duration = Duration.between(a, b);
		long diff = Math.abs(duration.toMinutes());
		return diff;
	}

	// Method is called for the text to pause in the the screen
	// When the user presses a key, the story continues
	public void pause() {
		System.out.println("");
		char c = IO.inputChar("<press the \"Enter\" key to continue>");
		System.out.println("");
	}

	// Create progress bar GUI and show it
	public void progressBar() {
		// Create and set up the window.
		JFrame frame = new JFrame("ProgressBar");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		JComponent newContentPane = new ProgressBar();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Make sure the focus goes to the right component
		// Whenever the frame is initially given the focus.
		frame.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
				// If exit is true, exit the password dialog box
				if (exit) {
					exit = false;
					frame.dispose();
				}
			}
		});

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	// create progress bar GUI and show it
	public void dropDown() {
		// create a JFrame called Dropdown using dropdown constructor and set it to visible
		DropDown frame = new DropDown();
		frame.setVisible(true);
	}

	// Method called once the user wins the game - it ends the storyline with the assistant in prison
	public void win() {
		// Reason for the murder
		IO.printSlow("Good job detective! You solved the murder case!\n\n"
				+ "We never expected it to be your assistant.\n"
				+ "After you beat him in Tic Tac Toe, he finally revealed the details about why he killed his wife to us.");
		pause();
		IO.printSlow("He said that he loved his wife, yet he did such a horendous deed.\n"
				+ "He told us that while they were fighting, he threatened to shoot himself.\n"
				+ "As she was walking up to him, she tripped.\n");
		pause();
		IO.printSlow("Startled, your assistant accidentally pulled the trigger, leaving his wife to bleed out.\n"
				+ "It drove him crazy and he could not admit to his misdeed.\n"
				+ "So instead of taking her to the hospital, he stomped her head on the curb out of frustration.\n"
				+ "He assumed that no one would think that a kind person like him would do something so indecent.");
		pause();
		IO.printSlow("Your assistant is now in prison", 25);
		// Go to conclusion once the ending story has been revealed
		conclusion();
	}

	// Method counting losses and if user has lost more than 3 times or wants to quit, call the appropriate method
	public void lose() {
		// Increment times lost by 1
		timesLost++;

		// If times lost is 3 times, they lose, else tell the player how many times
		// They've lost and ask if they'd like to continue playing
		if (timesLost == 3) {
			timesLost = 0;
			System.out.println("You Lose!\n");
			IO.printSlow("The Agency finds out that YOU are the MURDERER!!!\n"
					+ "Your assistant and everyone in the agency is very sad and dissapointed.\n"
					+ "We thought you were a great and noble detective.", 25);
			conclusion();
		} 
		// If user has lost less than 3 times, ask if they'd like to quit
		else {
			IO.printSlow("Oh, no! You lost " + timesLost + " time(s).\n");
			quit();
		}
	}

	// Method to see if the user would like to quit the game
	public void quit() {
		System.out.println("Would you like to quit or continue playing?");
		// Ask user if they'd like to quit
		char quit = IO.inputChar("Quit? (y/n): ");

		// If else to determine true/false
		quitConclusion = (quit == 'y' || quit == 'Y') ? true : false;

		// If the user wants to quit, go to the conclusion method
		if (quitConclusion)
			conclusion();
		System.out.println();
	}

	// TASK 4 METHODS ----------------------------------
	// Method that helps decide if the char entered is correct and you can move forward to task 3 or lose a chance and quit or try again
	public void mcChoice(char choice) {
		// If the answer chosen is 'c'
		if (choice == 'c') {
			// Print the following
			IO.printSlow("\nYou are correct.\n" + "The murderer made the victim bite the curb.\n"
					+ "He stomped on her head and shattered her jaw.\n"
					+ "This caused brain bleeding, twisted her spine, and caused bruising as well.\n");
			// Go to task 3
			task3();
		}
		// Else, the answer is wrong
		else {
			// Slowly print "Incorrect"
			IO.printSlow("\nIncorrect.");
			// User loses one of their chances
			lose();
			// If the user wants to try again, restart task 2
			if (!quitConclusion)
				task2();
			quitConclusion = false;
		}
	}
	
	// TASK 5 METHODS -----------------------------------
	// placeA() - placeJ() methods for all places the user can go to in the maze 
	// At each one, have the user input a char and if anything else is inputted or the place itself, call the same method or 1 to quit
	// If you are on 'a', you can stay or move to 'b'
	public void placeA() {
		char choice = IO.inputChar("You can (a) stay here or (b) move left: ");
		if (choice == 'b')
			placeB();
		else if (choice == '1') {
			quit();
			if (!quitConclusion)
				placeA();
			quitConclusion = false;
		} else
			placeA();
	}

	// If you are on 'b', you can stay, move back to 'a', move to 'c', or move down
	// to 'g'
	public void placeB() {
		char choice = IO.inputChar("You can (b) stay here or (a) move right or (c) move left or (g) move down: ");
		if (choice == 'c')
			placeC();
		else if (choice == 'g')
			placeG();
		else if (choice == 'a')
			placeA();
		else if (choice == '1') {
			quit();
			if (!quitConclusion)
				placeB();
			quitConclusion = false;
		} else
			placeB();
	}

	// If you are on 'c', you can stay, move back to 'b',or move down to 'e'
	public void placeC() {
		char choice = IO.inputChar("You can (c) stay here or (b) move right or (e) move down: ");
		if (choice == 'b')
			placeB();
		else if (choice == 'e')
			placeE();
		else if (choice == '1') {
			quit();
			if (!quitConclusion)
				placeC();
			quitConclusion = false;
		} else
			placeC();
	}

	// If you are on 'd', you can stay, move back to 'e'
	public void placeD() {
		char choice = IO.inputChar("You can (d) stay here or (e) move right: ");
		if (choice == 'e')
			placeE();
		else if (choice == '1') {
			quit();
			if (!quitConclusion)
				placeD();
			quitConclusion = false;
		} else
			placeD();
	}

	// If you are on 'e', you can stay, move back to 'c', move left to 'd', or move
	// down to 'i'
	public void placeE() {
		char choice = IO.inputChar("You can (e) stay here or (c) move up or (d) move left or (i) move down: ");
		if (choice == 'c')
			placeC();
		else if (choice == 'd')
			placeD();
		else if (choice == 'i')
			placeI();
		else if (choice == '1') {
			quit();
			if (!quitConclusion)
				placeE();
			quitConclusion = false;
		} else
			placeE();
	}

	// If you are on 'f', you can stay, move right to 'g'
	public void placeF() {
		char choice = IO.inputChar("You can (f) stay here or (g) move right: ");
		if (choice == 'g')
			placeG();
		else if (choice == '1') {
			quit();
			if (!quitConclusion)
				placeF();
			quitConclusion = false;
		} else
			placeF();
	}

	// If you are on 'g', you can stay, move back to 'h', or move left to 'f'
	public void placeG() {
		char choice = IO.inputChar("You can (g) stay here or (h) move down or (f) move left: ");
		if (choice == 'f')
			placeF();
		else if (choice == 'h')
			placeH();
		else if (choice == '1') {
			quit();
			if (!quitConclusion)
				placeG();
			quitConclusion = false;
		} else
			placeG();
	}

	// If you are on 'h', you can stay, move back to 'g', move to 'k', or move right
	// to 'i'
	public void placeH() {
		char choice = IO.inputChar("You can (h) stay here or (g) move up or (k) move down or (i) move right: ");
		if (choice == 'g')
			placeG();
		else if (choice == 'k')
			placeK();
		else if (choice == 'i')
			placeI();
		else if (choice == '1') {
			quit();
			if (!quitConclusion)
				placeH();
			quitConclusion = false;
		} else
			placeH();
	}

	// If you are on 'i', you can stay, move back to 'e', move left to 'h', or move
	// down to 'n'
	public void placeI() {
		char choice = IO.inputChar("You can (i) stay here or (e) move up or (h) move left or (l) move down: ");
		if (choice == 'e')
			placeE();
		else if (choice == 'h')
			placeH();
		else if (choice == 'l')
			placeL();
		else if (choice == '1') {
			quit();
			if (!quitConclusion)
				placeI();
			quitConclusion = false;
		} else
			placeI();
	}

	// If you are on 'k',you can stay, move back to 'h', or move left to 'j'
	public void placeK() {
		char choice = IO.inputChar("You can (k) stay here or (h) move up or (j) move left: ");
		if (choice == 'h')
			placeH();
		else if (choice == 'j')
			placeJ();
		else if (choice == '1') {
			quit();
			if (!quitConclusion)
				placeK();
			quitConclusion = false;
		} else
			placeK();
	}

	// If you are on 'n', you can stay or move back to 'i'
	public void placeL() {
		char choice = IO.inputChar("You can (l) stay here or (i) move up: ");
		if (choice == 'i')
			placeI();
		else if (choice == '1') {
			quit();
			if (!quitConclusion)
				placeL();
			quitConclusion = false;
		} else
			placeL();
	}

	// When you get to 'j' it prints, "You found the fingerprint scanner"
	public void placeJ() {
		System.out.println("You found the fingerprint scanner!");
		pause();
		task6();
	}

	// TASK 6 METHODS -----------------------------
	// Method to have the user select the correct fingerprint to move forward else they lose and can quit or try again
	public void matchFingerprint() {
		int select = 0;
		// While select is not 3
		while (select != 3) {
			// Ask user to input a number
			select = IO.inputInt("Select (1), (2), (3), or (4) to match the murderer's fingerprint: ");

			// If select is 3
			if (select == 3) {
				// Slowly print "You are correct!"
				IO.printSlow("\nYou are correct!\n", 25);
				// Slowly print the following
				IO.printSlow("Your assistant looks very nervous.\n"
						+ "As you interrogate him, your assistant admits to his guilt.\n"
						+ "He murdered his own wife.\n" + "Your assistant was the murderer!\n", 25);
				pause();
				task7();
			}
			// Else, the answer is wrong
			else {
				// Slowly print, please try again
				IO.printSlow("\nPlease try again!", 25);
				// User loses one of their chances
				lose();
				quitConclusion = false;
			}
		}
	}

	// TASK 7 METHODS ----------------------------
	// Print the board with the updated X/O positions
	public void printboard() {
		System.out.println("                      1   2   3");
		System.out.println("                   1  " + a + " | " + b + " | " + c);
		System.out.println("                    ------------");
		System.out.println("                   2  " + d + " | " + e + " | " + f);
		System.out.println("                    ------------");
		System.out.println("                   3  " + g + " | " + h + " | " + i);
	}

	// Method for you to make your move during your turn
	public void makemove(char piece) {
		System.out.println("Your turn!");
		// Have the user input the x and y coordinates
		int x = IO.inputInt("\nEnter the x-coordinate: ");
		int y = IO.inputInt("Enter the y-coordinate: ");
		System.out.println();
		// if either x or y are not valid, have the user input again until it is correct
		while (!isValid(x, y)) {
			System.out.println("Error - invalid coordinate. Try again.");
			x = IO.inputInt("\nEnter the x-coordinate: ");
			y = IO.inputInt("Enter the y-coordinate: ");
		}
		// depending on x and y, place an 'O' on the print board in that location
		if (x == 1 && y == 1)
			a = piece;
		else if (x == 2 && y == 1)
			b = piece;
		else if (x == 3 && y == 1)
			c = piece;
		else if (x == 1 && y == 2)
			d = piece;
		else if (x == 2 && y == 2)
			e = piece;
		else if (x == 3 && y == 2)
			f = piece;
		else if (x == 1 && y == 3)
			g = piece;
		else if (x == 2 && y == 3)
			h = piece;
		else if (x == 3 && y == 3)
			i = piece;
	}

	// Method returns false if x or y invalid
	// Eg, x and y must be between 1 and 3
	// Also the position can not be full (eg. if x==1 && y==1 && a!=' ') - then they
	// Picked postion a AND it is full
	public boolean isValid(int x, int y) {
		if (x < 1 || x > 3 || y < 1 || y > 3)
			return false;
		if (x == 1 && y == 1 && a != ' ')
			return false;
		else if (x == 2 && y == 1 && b != ' ')
			return false;
		else if (x == 3 && y == 1 && c != ' ')
			return false;
		else if (x == 1 && y == 2 && d != ' ')
			return false;
		else if (x == 2 && y == 2 && e != ' ')
			return false;
		else if (x == 3 && y == 2 && f != ' ')
			return false;
		else if (x == 1 && y == 3 && g != ' ')
			return false;
		else if (x == 2 && y == 3 && h != ' ')
			return false;
		else if (x == 3 && y == 3 && i != ' ')
			return false;
		else
			return true;
	}

	// Tests to see if the game is ended
	public boolean isDone() {
		// The first line a-b-c is a match
		if (a == b && a == c && a != ' ')
			return true;
		else if (d == e && d == f && d != ' ')
			return true;
		else if (g == h && g == i && g != ' ')
			return true;
		else if (a == d && a == g && a != ' ')
			return true;
		else if (b == e && b == h && b != ' ')
			return true;
		else if (c == f && c == i && c != ' ')
			return true;
		else if (a == e && a == i && a != ' ')
			return true;
		else if (c == e && c == g && c != ' ')
			return true;
		else if (a != ' ' && b != ' ' && c != ' ' && d != ' ' && e != ' ' && f != ' ' && h != ' ' && i != ' ')
			return true;
		else
			return false;
	}

	// Returns who is in the winning condition
	// Returns 't' if it is a tie or cat's game
	public char printWinner() {
		// Top horizontal a-b-c is the win
		if (a == b && a == c && a != ' ')
			return a;
		else if (d == e && d == f && d != ' ')
			return d;
		else if (g == h && g == i && g != ' ')
			return g;
		else if (a == d && a == g && a != ' ')
			return a;
		else if (b == e && b == h && b != ' ')
			return b;
		else if (c == f && c == i && c != ' ')
			return c;
		else if (a == e && a == i && a != ' ')
			return a;
		else if (c == e && c == g && c != ' ')
			return c;
		else
			return 't';

	}

	// This method picks a random place for assistant
	public void assistant(char piece) {
		int x = (int) (Math.random() * 3) + 1;
		int y = (int) (Math.random() * 3) + 1;
		while (!isValid(x, y)) {
			x = (int) (Math.random() * 3) + 1;
			y = (int) (Math.random() * 3) + 1;
		}
		System.out.println("\nAssistant's turn!");
		System.out.println("He picked position (" + x + ", " + y + ")\n");
		if (x == 1 && y == 1)
			a = piece;
		else if (x == 2 && y == 1)
			b = piece;
		else if (x == 3 && y == 1)
			c = piece;
		else if (x == 1 && y == 2)
			d = piece;
		else if (x == 2 && y == 2)
			e = piece;
		else if (x == 3 && y == 2)
			f = piece;
		else if (x == 1 && y == 3)
			g = piece;
		else if (x == 2 && y == 3)
			h = piece;
		else if (x == 3 && y == 3)
			i = piece;
	}

	// Method to determine who won tic tac toe or if it was a tie, reset variables, and next steps (quit, try again, or go to win() method
	public void determineWinner() {
		// Print message according to win scenario
		char w = printWinner();
		if (w == 'X') {
			IO.printSlow("\nOh, no! Your assistant won the game!\n" + "He outsmarted you. Try to beat him again.\n");
		} else if (w == 't') {
			IO.printSlow("\nOh, no! You tied with your assistant!\n" + "He outsmarted you. Try to beat him again.\n");
		} else {
			IO.printSlow("\nCongratulations! You are the winner!\n" + "You outsmarted your assistant one final time.");
		}

		// Reset global variables
		a = ' ';
		b = ' ';
		c = ' ';
		d = ' ';
		e = ' ';
		f = ' ';
		g = ' ';
		h = ' ';
		i = ' ';
		turn = 'X';

		// If it is a tie or the user loses, 
		if (w == 'X' || w == 't') {
			// User loses one of their chances
			lose();
			// If user doesn't quit, try again
			if (!quitConclusion)
				task7();
			quitConclusion = false;
		} 
		else {
			pause();
			win();
		}
	}

	// CLASSES USED IN METHODS -----------------------------
	// PASSWORD, PROGRESS BAR, DROP DOWN DIALOG BOX CODE USED/MODIFIED FROM ONLINE
	// SOURCES
	// GUI CODE BELOW FROM:
	// https://www.codejava.net/java-se/swing/jcombobox-basic-tutorial-and-examples#DemoProgram
	// https://docs.oracle.com/javase/tutorial/uiswing/examples/components/index.html#PasswordDemo

	// password class
	public class Password extends JPanel implements ActionListener {
		// "ok" button
		private String OK = "ok";
		// "help" button
		private String HELP = "help";

		private JFrame controllingFrame; // needed for dialogs
		private JPasswordField passwordField;

		public Password(JFrame f) {
			// Use the default FlowLayout.
			controllingFrame = f;

			// Create everything.
			passwordField = new JPasswordField(10);
			passwordField.setActionCommand(OK);
			passwordField.addActionListener(this);

			JLabel label = new JLabel("Enter the password: ");
			label.setLabelFor(passwordField);

			JComponent buttonPane = createButtonPanel();

			// Lay out everything.
			JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
			textPane.add(label);
			textPane.add(passwordField);

			add(textPane);
			add(buttonPane);
		}

		// Method ot create button panel
		protected JComponent createButtonPanel() {
			JPanel p = new JPanel(new GridLayout(0, 1));
			JButton okButton = new JButton("OK");
			JButton helpButton = new JButton("Help");

			okButton.setActionCommand(OK);
			helpButton.setActionCommand(HELP);
			okButton.addActionListener(this);
			helpButton.addActionListener(this);

			p.add(okButton);
			p.add(helpButton);

			return p;
		}

		// Method to perform actions based if there is an event
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();

			if (OK.equals(cmd)) { // Process the password.
				char[] input = passwordField.getPassword();
				// if pass correct, start the game
				if (isPasswordCorrect(input)) {
					exit = true;
					menu();
				} else {
					JOptionPane.showMessageDialog(controllingFrame, "Invalid password. Try again.", "Error Message",
							JOptionPane.ERROR_MESSAGE);
				}

				// Zero out the possible password, for security.
				Arrays.fill(input, '0');

				passwordField.selectAll();
				resetFocus();
			} else { // The user has asked for help.
				JOptionPane.showMessageDialog(controllingFrame, "The password is \"Detective\"");
			}
		}

		/**
		 * Checks the passed-in array against the correct password. After this method
		 * returns, you should invoke eraseArray on the passed-in array.
		 */
		public boolean isPasswordCorrect(char[] input) {
			boolean isCorrect = true;
			char[] correctPassword = { 'D', 'e', 't', 'e', 'c', 't', 'i', 'v', 'e' };

			if (input.length != correctPassword.length) {
				isCorrect = false;
			} else {
				isCorrect = Arrays.equals(input, correctPassword);
			}

			// Zero out the password.
			Arrays.fill(correctPassword, '0');

			return isCorrect;
		}

		// Must be called from the event dispatch thread.
		protected void resetFocus() {
			passwordField.requestFocusInWindow();
		}
	} // end password

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	// Progress bar
	public class ProgressBar extends JPanel implements ActionListener, PropertyChangeListener {

		private ProgressMonitor progressMonitor;
		private JButton startButton;
		private JTextArea taskOutput;
		private Task task;

		// Task class inside progress bar randomly progresses bar
		class Task extends SwingWorker<Void, Void> {
			@Override
			public Void doInBackground() {
				Random random = new Random();
				int progress = 0;
				setProgress(0);
				try {
					Thread.sleep(1000);
					while (progress < 100 && !isCancelled()) {
						// Sleep for up to one second.
						Thread.sleep(random.nextInt(1000));
						// Make random progress.
						progress += random.nextInt(10);
						setProgress(Math.min(progress, 100));
					}
				} catch (InterruptedException ignore) {
				}
				return null;
			}

			// Method for when progress bar done at 100%
			@Override
			public void done() {
				Toolkit.getDefaultToolkit().beep();
				startButton.setEnabled(true);
				// ProgressMonitor.setProgress(0);
				// Close Progress bar when it is done at 100%
				progressMonitor.close();
			}
		}

		// Constructor to create the JFrame
		public ProgressBar() {
			// super(new BorderLayout());
			JPanel t = new JPanel(new BorderLayout());
			// Create the demo's UI.
			startButton = new JButton("Start");
			startButton.setActionCommand("start");
			startButton.addActionListener(this);

			taskOutput = new JTextArea(5, 20);
			taskOutput.setMargin(new Insets(5, 5, 5, 5));
			taskOutput.setEditable(false);

			add(t);
			add(startButton, BorderLayout.PAGE_START);
			add(new JScrollPane(taskOutput), BorderLayout.CENTER);
			setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		}

		/**
		 * Invoked when the user presses the start button.
		 */
		public void actionPerformed(ActionEvent evt) {
			progressMonitor = new ProgressMonitor(ProgressBar.this, "Loading", "", 0, 100);
			progressMonitor.setProgress(0);
			task = new Task();
			task.addPropertyChangeListener(this);
			task.execute();
			startButton.setEnabled(false);
		}

		/**
		 * Invoked when task's progress property changes.
		 */
		public void propertyChange(PropertyChangeEvent evt) {
			if ("progress" == evt.getPropertyName()) {
				int progress = (Integer) evt.getNewValue();
				progressMonitor.setProgress(progress);
				String message = String.format("Completed %d%%.\n", progress);
				progressMonitor.setNote(message);
				taskOutput.append(message);
				if (progressMonitor.isCanceled() || task.isDone()) {
					Toolkit.getDefaultToolkit().beep();
					if (progressMonitor.isCanceled()) {
						task.cancel(true);
						taskOutput.append("Task cancelled.\n");
					} else {
						//
						taskOutput.append("Task completed.\n");
						exit = true;
						introduction();
					}
					startButton.setEnabled(true);
				}
			}
		}
	} // end ProgressBar

	// Method activates if there is a property change
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
	}

	// DropDown class
	public class DropDown extends JFrame {

		private JButton buttonSelect = new JButton("Select");

		// Dropdown constructor to create frame
		public DropDown() {
			super("Drop Down");

			setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

			Integer[] nums = new Integer[] { 1, 2, 3, 4 };

			// create a combo box with items specified in the Integer array:
			final JComboBox<Integer> list = new JComboBox<Integer>(nums);

			// customize some appearance:
			list.setForeground(Color.BLUE);
			list.setFont(new Font("Arial", Font.BOLD, 14));
			list.setMaximumRowCount(10);

			// add an event listener for the combo box
			list.addActionListener(new ActionListener() {

				// Method when action event occurs
				@Override
				public void actionPerformed(ActionEvent event) {
					JComboBox<Integer> combo = (JComboBox<Integer>) event.getSource();
					Integer selectedNum = (Integer) combo.getSelectedItem();

					DefaultComboBoxModel<Integer> model = (DefaultComboBoxModel<Integer>) combo.getModel();

					Integer selectedIndex = model.getIndexOf(selectedNum);
				}
			});

			// add event listener for the button Select
			buttonSelect.addActionListener(new ActionListener() {
				//
				@Override
				public void actionPerformed(ActionEvent event) {
					// Get the number chosen from the dropdown box
					Integer selectedNum = (Integer) list.getSelectedItem();
					// Switch statement to decide where the number chosen leads to
					switch (selectedNum) {
					// If the random number is '1', print "Go to 123 Jump Street"
					case 1:
						IO.printSlow("Go to 123 Jump Street.", 25);
						break;
					// If the random number is '2', print "Go to the CN Tower"
					case 2:
						IO.printSlow("Go to the CN Tower.", 25);
						break;
					// If the number is '3', print "Go to Jane and Finch"
					case 3:
						IO.printSlow("Go to Jane and Finch.", 25);
						break;
					// If the number is '4', print "Go to Shoppers World"
					case 4:
						IO.printSlow("Go to Shoppers World.", 25);
						break;
					}
					// Once a number has been selected and location printed, dispose of frame and go to task 2
					dispose();
					System.out.println();
					task2();
				}
			});

			// add components to this frame
			add(list);
			add(buttonSelect);

			pack();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
		}
	} // end DropDown

	// ASCII ART USED IN GAME -----------------------------
	// Intro ascii art --------------------
	public void introArt() {
		// Print "WELCOME BACK TO THE"
		System.out.println(
				"           _______  _        _______  _______  _______  _______    ______   _______  _______  _         _________ _______   _________          _______             ");
		System.out.println(
				"|\\     /|(  ____ \\( \\      (  ____ \\(  ___  )(       )(  ____ \\  (  ___ \\ (  ___  )(  ____ \\| \\    /\\  \\__   __/(  ___  )  \\__   __/|\\     /|(  ____ \\");
		System.out.println(
				"| )   ( || (    \\/| (      | (    \\/| (   ) || () () || (    \\/  | (   ) )| (   ) || (    \\/|  \\  / /     ) (   | (   ) |     ) (   | )   ( || (    \\/       ");
		System.out.println(
				"| | _ | || (__    | |      | |      | |   | || || || || (__      | (__/ / | (___) || |      |  (_/ /      | |   | |   | |     | |   | (___) || (__                 ");
		System.out.println(
				"| |( )| ||  __)   | |      | |      | |   | || |(_)| ||  __)     |  __ (  |  ___  || |      |   _ (       | |   | |   | |     | |   |  ___  ||  __)                ");
		System.out.println(
				"| || || || (      | |      | |      | |   | || |   | || (        | (  \\ \\ | (   ) || |      |  ( \\ \\      | |   | |   | |     | |   | (   ) || (               ");
		System.out.println(
				"| () () || (____/\\| (____/\\| (____/\\| (___) || )   ( || (____/\\  | )___) )| )   ( || (____/\\|  /  \\ \\     | |   | (___) |     | |   | )   ( || (____/\\     ");
		System.out.println(
				"(_______)(_______/(_______/(_______/(_______)|/     \\|(_______/  |/ \\___/ |/     \\|(_______/|_/    \\/     )_(   (_______)     )_(   |/     \\|(_______/        ");

		// Print "ARMED DETECTIVE AGENCY"
		System.out.println(
				"▄▄▄       ██▀███   ███▄ ▄███▓▓█████ ▓█████▄    ▓█████▄ ▓█████▄▄▄█████▓▓█████  ▄████▄  ▄▄▄█████▓ ██▓ ██▒   █▓▓█████     ▄▄▄        ▄████ ▓█████  ███▄    █  ▄████▄▓██   ██▓");
		System.out.println(
				"▒████▄    ▓██ ▒ ██▒▓██▒▀█▀ ██▒▓█   ▀ ▒██▀ ██▌   ▒██▀ ██▌▓█   ▀▓  ██▒ ▓▒▓█   ▀ ▒██▀ ▀█  ▓  ██▒ ▓▒▓██▒▓██░   █▒▓█   ▀    ▒████▄     ██▒ ▀█▒▓█   ▀  ██ ▀█   █ ▒██▀ ▀█ ▒██  ██▒");
		System.out.println(
				"▒██  ▀█▄  ▓██ ░▄█ ▒▓██    ▓██░▒███   ░██   █▌   ░██   █▌▒███  ▒ ▓██░ ▒░▒███   ▒▓█    ▄ ▒ ▓██░ ▒░▒██▒ ▓██  █▒░▒███      ▒██  ▀█▄  ▒██░▄▄▄░▒███   ▓██  ▀█ ██▒▒▓█    ▄ ▒██ ██░");
		System.out.println(
				"░██▄▄▄▄██ ▒██▀▀█▄  ▒██    ▒██ ▒▓█  ▄ ░▓█▄   ▌   ░▓█▄   ▌▒▓█  ▄░ ▓██▓ ░ ▒▓█  ▄ ▒▓▓▄ ▄██▒░ ▓██▓ ░ ░██░  ▒██ █░░▒▓█  ▄    ░██▄▄▄▄██ ░▓█  ██▓▒▓█  ▄ ▓██▒  ▐▌██▒▒▓▓▄ ▄██▒░ ▐██▓░");
		System.out.println(
				" ▓█   ▓██▒░██▓ ▒██▒▒██▒   ░██▒░▒████▒░▒████▓    ░▒████▓ ░▒████▒ ▒██▒ ░ ░▒████▒▒ ▓███▀ ░  ▒██▒ ░ ░██░   ▒▀█░  ░▒████▒    ▓█   ▓██▒░▒▓███▀▒░▒████▒▒██░   ▓██░▒ ▓███▀ ░░ ██▒▓░");
		System.out.println(
				" ▒▒   ▓▒█░░ ▒▓ ░▒▓░░ ▒░   ░  ░░░ ▒░ ░ ▒▒▓  ▒     ▒▒▓  ▒ ░░ ▒░ ░ ▒ ░░   ░░ ▒░ ░░ ░▒ ▒  ░  ▒ ░░   ░▓     ░ ▐░  ░░ ▒░ ░    ▒▒   ▓▒█░ ░▒   ▒ ░░ ▒░ ░░ ▒░   ▒ ▒ ░ ░▒ ▒  ░ ██▒▒▒ ");
		System.out.println(
				"  ▒   ▒▒ ░  ░▒ ░ ▒░░  ░      ░ ░ ░  ░ ░ ▒  ▒     ░ ▒  ▒  ░ ░  ░   ░     ░ ░  ░  ░  ▒       ░     ▒ ░   ░ ░░   ░ ░  ░     ▒   ▒▒ ░  ░   ░  ░ ░  ░░ ░░   ░ ▒░  ░  ▒  ▓██ ░▒░ ");
		System.out.println(
				"  ░   ▒     ░░   ░ ░      ░      ░    ░ ░  ░     ░ ░  ░    ░    ░         ░   ░          ░       ▒ ░     ░░     ░        ░   ▒   ░ ░   ░    ░      ░   ░ ░ ░       ▒ ▒ ░░  ");
		System.out.println(
				"    ░  ░   ░            ░      ░  ░   ░          ░       ░  ░           ░  ░░ ░                ░        ░     ░  ░         ░  ░      ░    ░  ░         ░ ░ ░     ░ ░     ");
		System.out.println(
				"                                     ░          ░                            ░                          ░                                                 ░       ░ ░     ");

		// Print a detective with suitcase, hat, and magnifying glass looking at footprints.
		// Also text saying "DETECTIVE AGENCY"
		System.out.println(
				"................................--..................................................................");
		System.out.println(
				".............................../mmmd:...............................................................");
		System.out.println(
				"............................:.+mmmmms-..............................................................");
		System.out.println(
				"...........................:mshmmmmdh+..............................................................");
		System.out.println(
				".................-oyyyssso+hmmmmmmd-................................................................");
		System.out.println(
				"................odmhsyhmmmmmmmmds+-...../+:.........................................................");
		System.out.println(
				".........:/...:hmy/...-dmmmmmmmd:......smd/.........................................................");
		System.out.println(
				"........smmdosmd-....-hmmmmmmmmmh...-hh/-...........................................................");
		System.out.println(
				"....../dmmmmmmdo-...:dmmmmmmmmdmmsohdy:.............................................................");
		System.out.println(
				"......odmmmmmmmmd-.+dmmmmmmmmd-hmmds/....-o:-:/:./o--:::/+://o--:.:/:--/:/:s:/-o::o/..//:++-::......");
		System.out.println(
				"......../ydmmmmh::hmmmmmmmmmm/..o/........o-...+/-s::/..:+..:o::/:o........s...s..:o../../+-::......");
		System.out.println(
				"..........-+hd+-smmmmmmmmmmmms:...........o-...+/-o.....:+..:+...:o........s...s.../+/-..//.........");
		System.out.println(
				"............../dmmmmmmmmmmmmmmmh+-.......-o/-://.:o-:::./+../o--:::/:-:::.-s-.-s-...+:.../+--/-.....");
		System.out.println(
				"............./dmmmmmmmmmmmmhdmmmmd+.........................................--.:--:--:.-------......");
		System.out.println(
				"..............:hmmmddmmmmmm:.-hmmm:.........................................:/-/.:/--:::/.../.......");
		System.out.println(
				"............-sdmmh/..://///...dmmy.........................................-..-.-.---....-..-.......");
		System.out.println(
				"..........:ymmds:............/mmd:..................................................................");
		System.out.println(
				"......../hmmy/...............smmo...................................................................");
		System.out.println(
				"........oyms----:::::::::::::hmdo/-.............:s+shhs......:s+shhs......:s+shhs......:s+shhs......");
		System.out.println(
				".......-oyhddmmmmmmmmmmmmmmmmmmmmmddhyo-..::::/:.......::::/:.......::::/:.......::::/:.............");
		System.out.println(
				"................---------------...........::/ooo/......::/ooo/......::/ooo/......::/ooo/............");
	}

	// Fingerprint ascii arts ----------------------------
	// Print the first fingerprint
	public void fingerprint1() {
		System.out.println("```````````````````````````````..--------..`````````````````````````````````````");
		System.out.println("`````````````````````````-:/+oo++//::::///+oo+/:-.``````````````````````````````");
		System.out.println("`````````````````````-/oo+:-.`````````````````-:/oo/:```````````````````````````");
		System.out.println("``````````````````-+o+:.````.-:/+/```.oo++/::.`````-/o+.````````````````````````");
		System.out.println("````````````````:o+:````-/oo+/:-.````````.--:+oo/:.```.`````````````````````````");
		System.out.println("```````````````.:.```:+o/-.```````......````````-/oo:.``````````````````````````");
		System.out.println("``````````````````.+o/-````.-/+ooo++++++ooo-```````.:o+-````//.`````````````````");
		System.out.println("````````````````.+o:````-/oo/:-```````````````+o/-````-oo:``.+o-````````````````");
		System.out.println("`````````-o:```/s/``````::.````.-::////::-.`````-+o/.```-o+.``:s/```````````````");
		System.out.println("````````:s/``.oo.```:-`````.:+o+/:------:/+oo/-````:o+.```/s:``-o+``````````````");
		System.out.println("```````-s/``-o+```:o+.``./oo:.``````````````.:+o/.```/s/```:o/``-s/`````````````");
		System.out.println("```````oo``-s+```/s:```/s/.````.:/++++++/:-`````::````.oo.``:o/``/s-````````````");
		System.out.println("``````/s-``oo```+s-``-o+.```-/o+/-..```.-:+o+:``````````oo.``/s-``oo````````````");
		System.out.println("``````+/``/s-``/s-``.o/```-oo:`````.````````-+o:````o+``.oo```oo``:s:```````````");
		System.out.println("``````````oo``.s+````````/s:```-/o+/.```:+:```-oo.``-s/``.-```:s-``s+```````````");
		System.out.println("`````````-s:``/s-``:-```/s-``-oo-````````-+s/``.oo```/s.``````.s/``oo```````````");
		System.out.println("`````.:``:s:``+s``.s/``-s/``:s:```-:///-```-o+``-s/``.s/```````s+``oo```````````");
		System.out.println("`````/s.`:s:``os``:s:``/s.`.s+``.oo:..-+o:``:s:``oo```s+``/s```s+``oo```````````");
		System.out.println("`````/s.`:s-``/+``:s:``/s.`-s:``+s``````+s.`.s/``+o```s+``+s.``s+``os```````````");
		System.out.println("`````/o.`:s-``````:s:``/s.`-s:``os``:+``/s.`.s/```````s+``/s.`.s+``os```````````");
		System.out.println("`````/s.`:s-``..``:s:``+s.`-s:``oo``+s``/o.`.s/```````s+``/s.``s+``os```````````");
		System.out.println("`````/o``:s-``os``:s:``/s.`-s:``oo``oo``/s.`.s/```.```s+``+s.``s+``+o```````````");
		System.out.println("````````.oo```o+``/s-``+s``:s-``oo``oo``/s.`.s/``oo``.s/``+s```+/```````````````");
		System.out.println("``````:+o/```+s.``oo```s+``````.s/`.s/``+s``-s:``so``.o/``os````````````````````");
		System.out.println("``````..```:oo.``+o.``/s.``-```/s-`:s:``s+``/s-`.s/``:s-``so``.+.```````````````");
		System.out.println("```````:/+o+-``-oo.``/s:``+o.`.so``+s``:s-``oo``:s-``+s``-s/``/s.```````````````");
		System.out.println("```````--.```-+o:```+s:``+s-``+o.`.s/``oo``-s:``oo``.s+``/s.``oo````````````````");
		System.out.println("````````.:/+o+-```:o+.``+o-``+s-```.``/s-``.:``:s:```.```oo``-s/````````````````");
		System.out.println("````````-:-.```-/o+-``:o+.``+o-``````:s:```````oo```````/s-``+o`````````````````");
		System.out.println("``````````.-:+o+:.``:oo-``-o+.`.++``:s/```````+s.``````.s+``-o/`````````````````");
		System.out.println("``````````-/:.```-/o+-``.+o:``:o+``:s/``:o-``/s-``.````+o.``oo``````````````````");
		System.out.println("`````````````-/+o+:````.+:``-oo-``+o:``/s/``/s:``:s:``/s-``/s-``````````````````");
		System.out.println("````````````-/:.```--`````:oo:``-o+.``+s:``/s:``:s/``:s:``:s/```````````````````");
		System.out.println("```````````````./+o+:``./o+-``-+o:``-oo.``+o-``:s/``:s/```+/````````````````````");
		System.out.println("````````````````-.``.:oo:.``-+o:``.+o:``-o+.``/s:``/s/``````````````````````````");
		System.out.println("```````````````````.+/.``.:oo:``./s/```..-``.oo.`.+s:```````````````````````````");
		System.out.println("````````````````````````.o/.``-+o/.``/s/```/s/```.:.````````````````````````````");
		System.out.println("`````````````````````````````:o:```/o+.```oo.```````````````````````````````````");
	}

	// Print the second fingerprint
	public void fingerprint2() {
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMNNmdhhyyyyssssssssyyyhhdmmNMMMMMMMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMmdhysoooooooooooooooooooooooooooosyhmNMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMmhysoooooooooooooooooooooooooooooooooooooosydmMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMNdysooooooooossyhhdmmNNNMMMMMNNNmmddhyysoooooooooosydNMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMNmysooooooosyhdNMMMMMMMMMMNNNNNNNNNMMMMMMMMNmdhyoooooooosydNMMMMMMMMMM");
		System.out.println("MMMMMMMMNhsoooooooyhmNMMMMNmmdhyyssssssssssssssyyhdmNMMMMMNmhyooooooosymMMMMMMMM");
		System.out.println("MMMMMMNhsooooooydNMMMNmdysssoooooooooooooooooooooooossyhmNMMMMNdyooooooohMMMMMMM");
		System.out.println("MMMMMdsoooooohmMMMNdysoooooooooooossssssysssssooooooooooosshmNMMMNhsoooodMMMMMMM");
		System.out.println("MMMMMsooooohNMMNdysooooooossyhdmmNNNNNNNMNNNNNNmdhyysoooooooosydNMMNmddNMMMMMMMM");
		System.out.println("MMMMMmysyymMMmysoooooosyhmNNMMMMMMNNNmmmmmNNNNMMMMMMNmdysoooooooshmMMMMMMMMMMMMM");
		System.out.println("MMMMMMMNMMMmyoooooosydNMMMMNmmhyyssssssssosssssyhdmmNMMMNmhyoooooooydNMMMMMMMMMM");
		System.out.println("MMMMMMMMMmyoooooosdNMMMMmdyssoooooooooooooooooooooossyhmNMMMmhsoooooosdNMMMMMMMM");
		System.out.println("MMMMMMMNhsooooosdNMMMmhysoooooooosssyyyyyyysssooooooooooshmNMMNdyoooooosdMMMMMMM");
		System.out.println("MMMMMMmsooooosdNMMMmysoooooosshdmNNNMMMMMMMNNNNmdhysoooooooshNMMMmyooooooymMMMMM");
		System.out.println("MMMMNhooooooyNMMMmyoooooosydNMMMMMNNmmmmmmmNNNMMMMMNmhsooooooshNMMMmyoooooshMMMM");
		System.out.println("MMMNyooooosdMMMmyoooooosdNMMMNmdyyssoooooooossyhdNNMMMNmysooooosdMMMMdsooooomMMM");
		System.out.println("MMdsoooooyNMMNhsooooosmMMMNdysooooooooooooooooooooshmNMMMNhsoooooyNMMMNyssshMMMM");
		System.out.println("MdooooosdMMMNyooooosdMMMmhsooooooossyyyhhyysssooooooosdNMMMmyooooosmMMMMNNMMMMMM");
		System.out.println("MdsooosmMMMmsoooooyNMMmyoooooosyhmNMMMMMMMMMNmdhsooooooshNMMMdoooooodMMMNmdmNMMM");
		System.out.println("MMNddmNMMMdoooooodMMNhooooooshNMMMMNNNmmmmNNMMMMMmysooooosmMMMmsooooodMNsooosmMM");
		System.out.println("MMMMMMMMNyooooosmMMmsooooosdNMMMNdysooooooooshmNMMMmyoooooodMMMmsoooosNmooooosNM");
		System.out.println("MMMMMMMmsoooooyNMMdooooooyNMMMdyoooooooooooooooshNMMMdoooooomMMMdoooooyMhooooohM");
		System.out.println("MMMMMmyoooooohNMMdooooosdMMMmsooooooosyyyysoooooosmMMMmooooosNMMMsoooooNNsoooooN");
		System.out.println("MMNdsoooooosmMMMdooooosmMMMdooooooydNMMMMMMmyoooooomMMMhooooodMMMyooooomMhoooood");
		System.out.println("NyooooooosdMMMMdoooooomMMMdooooosmMMMMMMMMMMMmooooooNMMNoooooyMMMyooooomMmoooooy");
		System.out.println("yooooooymMMMMNhoooooomMMMdooooosmMMMMNhyydMMMMdooooohMMMoooooyMMMyoooooNMNooooos");
		System.out.println("NhssydNMMMMMdsooooosmMMMdoooooomMMMMmoooooyMMMMsoooooMMMoooooyMMMsoooooMMMsooooo");
		System.out.println("MMMMMMMMMMdsoooooohMMMMhooooosmMMMMmsooooodMMMMsoooooMMmooooohMMNoooooyMMMsooooo");
		System.out.println("MMMMMMMNdsooooooymMMMNyooooosNMMMMdoooooodMMMMdoooooyMMhooooomMMhooooodMMMsooooo");
		System.out.println("MMMMMmysooooooymMMMMdsoooooyNMMMMhoooooodMMMMNsoooooNMMsoooooNMMsoooooNMMMooooos");
		System.out.println("MMmhsoooooooymMMMMmyoooooodMMMMmsooooosmMMMMNsooooodMMmoooooyMMNooooosMMMmoooooy");
		System.out.println("msoooooooshNMMMMmyooooooyNMMMNhooooooyNMMMMmsooooohMMMyooooomMMdooooohMMMdoooooh");
		System.out.println("yoooooshmMMMMNdsoooooosdMMMMdsooooosdMMMMMhoooooodMMMNooooosMMMhooooomMMMyooooom");
		System.out.println("NhyyhmNMMMMmhsooooooydNMMMmyooooooyNMMMMmyooooosmMMMMmooooohMMMyoooooNMMNooooosM");
		System.out.println("MMMMMMMMNdsoooooooymMMMMNyooooooymMMMMNhsoooooyNMMMMMhooooodMMMyoooooNMMdooooohM");
		System.out.println("MMMMMNdysooooooshNMMMMNhsooooosdNMMMMdsooooosdMMMMMMMhooooomMMMhooooohMMhoooosNM");
		System.out.println("MMNmhsoooooosymNMMMMmysooooosdNMMMMmyooooooyNNdyyymMMdooooodMMMNsooooodMNdyyhmMM");
		System.out.println("NhsoooooooydNMMMMMmysoooooshNMMMMmyooooooymMMyoooooNMNooooosMMMMmsooooohNMMMMMMM");
		System.out.println("yooooooshmMMMMMNdyooooooshNMMMMmyoooooosdMMMdoooooyMMMyooooodMMMMmsooooosmMMMMMM");
		System.out.println("msssshmNMMMMMNhsoooooosdNMMMNmyoooooosdNMMMMsoooooNMMMmooooosNMMMMNyooooooymMMMM");
		System.out.println("MMNNNMMMMMNmysooooooydNMMMNdyoooooosdNMMMMMNooooosMMMMMdooooosmMMMMMmsooooooNMMM");
		System.out.println("MMMMMMMMmhsoooooosymMMMMNhsoooooosdMMMMMMMMMsoooosMMMMMMhoooooshNMMMMMdssosyNMMM");
		System.out.println("MMMMMNdyooooooosdNMMMMmysooooooymMMMMMMMMMMMhooooohMMMMMMdsooooosydmNMMMNmNMMMMM");
		System.out.println("MMMMMyooooooshmMMMMNdyooooooshmMMNNmmNNMMMMMNsooooodMMMMMMNhsooooooosymMMMMMMMMM");
		System.out.println("MMMMMhsoosydNMMMMmhsoooooosdNMNhysoooosshmMMMmsoooooyNMMMMMMNhssoooooosMMMMMMMMM");
		System.out.println("MMMMMMNmmNMMMMNdsoooooosymMNdyoooooooooooosmMMNyoooooohNMMMMMMMNdhyssymMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMhoooooooshNMMdsoooooossysoooooohMMMhsooooooymMMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMsoooosymMMMdsooooooydNMMMmsoooooyMMMNhsoooooodMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMNhyydNMMMMNooooooyNMMMMMMMNsooooodMMMMNdsooosNMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMhsooymMMMMMMMMMMNooooosMMMMMMMNmmMMMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMNMMMMMMMMMMMMMMmyooymMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
	}

	// Print the third fingerprint
	public void fingerprint3() {
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMmdyso+/::--------::/+osdMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMmyo/------//+ossyyyssso+/:--:MMNyshNMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMy----/oyhmNMMMNmmddddddmmNMMMMMMMm/:::/omMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMNyydNMMMMMs/:--------------:/oshmMMNds+/hMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMNhs//hMMy+osyhhddddddhyss+/-----/shNMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMds/:::+smMMMMNmddhhyyyhhhdNMMMMNdyo:---/smMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMms/::/sdNMNdhso+/////////////yMMhoshmMMNy+---/yNMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMd+:::sdMMmyo///+oyhdmmNMMMMMNNNMMMy/::::oymMMh+---omMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMd+::/hMMmy+//+ydNMMNmmMMMdyyyyyhhdmNMMMmy+:::/yNMNs---+NMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMm+::/hMMho//+ymMMmhs+///yMMhyyyyyssssssyhdNMNdo::dMMMNs+oNMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMM+::hMMh+//sdMMms+//+oydmMMMMMMMMMMMNmdhysssydNMMMMNhymMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMNMMmo//smMMMMy+oymMMNmdhyssmMMdyhhdmNMMNmhsssymMMN+//sNMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMy//+dMMdyyNMMMMmhsssssyhhNMMmdhhyyyymMMMMmyssymMMy+/+dMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMNo//sNMmssshMMNhsssshmNMMMNNmmNNMMMNmmMMNddMMmyssyNMm+//hMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMNo//yMMhsssmMNhssshNMMmhyssssssssssydNMMMhsssdMMdsssmMNo//yMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMo//yMMhssyMMmssshMMNhsssshddmmmmdhyssssdNMNyssyNMNsssdMNo//hMMMMMMMMM");
		System.out.println("MMMMMMMMMMh//sMMhssyMMdsssNMNysssdNMMNmmddmmNMMmhssshMMdsssmMNsssmMN+/+MMMMMMMMM");
		System.out.println("MMMMMMMMMN+/+NMmsssMMdssyNMmsssdMMmhyyyyyyyyyydNMNhssyNMmsssNMNsssNMNdmMMMMMMMMM");
		System.out.println("MMMMMMMMMh//yMMyssmMNsssNMmsssNMNhyyhdNNMMNmdyyydMMdsssNMmssyMMhsshMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMo//dMNsssMMhssdMMsssNMNyyymMMNmddmNMMdyyhMMdsshMMyssmMNsssMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMM+//NMdsshMMsssNMmssyMMhyymMMdhhdhhhdMMhyymMMsssMMdsshMMsssNMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMmhdMMdssdMMsssMMdsshMMyyyMMmhhdMMhhhNMmyydMMsssNMdssyMMyssNMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMdssdMMMNMMMdsshMMyyyMMmhhdMMhhhNMmyydMMsssmMdssyMMyssNMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMdssdMMdhdMMdsshMMyyyMMmhhdMMhhhNMmyydMMsssmMdssyMMyssNMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMyssdMMyyyMMhsshMMyyyMMmhhmMMhhhNMmyydMMsssNMdssyMMhssNMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMdssshMMdyydMMyssmMNyyyMMdhhmMMhhhMMdyydMMsssNMdsshMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMysyNMNhyyhMMdssyMMdyydMMhhhNMNhhdMMhyymMNsssMMhssdMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMdyyydMMdsssNMmyyyMMmhhdMMdhhmMMyyyNMdsshMMyssmMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMmyyydNMNyssyNMmyyyNMNhhhNMNhhhMMmyyhMMyssmMMMmNMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMmhhNMNhsssdMMdyyyNMNhhhNMMdhhmMMyyyNMmsssMMNmNMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMdssshMMNhyyhNMNhhhNMMmhhdMMhyydMMyssmMNyyyMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMhyhNMNhyyymMMmhhhNMMmhhdMMdyyhMMdssyMMhyydMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMdyyymMMNhhhdMMMdhhdMMmyyyNMmsssNMmyyhMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMNhydMMNdhhdNMMNhhhmMMdyyhNMmsssNMMmyhNMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMNdhhdNMMNdhhdNMNhyyhMMmsssNMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMmhhhmMMMdhhhmMMmyyymMMhsssNMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMNdmMMMmhhhmMMmhyyhNMNyssyMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMNhhhmMMNhyyhmMMdsssdMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMNdmNMNdyyymMMdssshNMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
		System.out.println("MMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMdyydMMMNssymMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM");
	}

	// Print the fourth fingerprint
	public void fingerprint4() {
		System.out.println("``  ``  `  ``  ``  `:/shdmNNMMMMMMMMMMMMNNNmdhyo/:-` ``  ``  ``  `` ``  ``  ``");
		System.out.println("``  ``  `` ``  ```:ohNMMMMMMMNNmmmmmmmmmmNNNMMMMMMMMNNds+:`  ``  ``  `  ``  ``");
		System.out.println("``  ``  `  `` -mMMMMNhs+:...``````````````..-/+sydNMMMMMN:`  ``  `` ``  ``  ``");
		System.out.println("``  ``  `` ``  `.sdyo-```  ```.---------..``  ``  ```-/ohdy. ``  ``  `  ``  ``");
		System.out.println("``  ``  `  ``  ``` `-:+shdmNNNNNNNNNNNNNNmdhso+:.``  ``````  ``  `` ``  ``  ``");
		System.out.println("``  ``  `` ``  ``./sdmNMMMMMMNmmddddddddmmNMMMMMMNNmhs/-```  ``  ``  `  ``  ``");
		System.out.println("``  ``  `  `:sdNMMMMmdyo/:-...````````....-/+shdNMMMMNmy/-`  ``  `` ``  ``  ``");
		System.out.println("``  ``  `` -smNMMNdy+-.``  `` `..--::::::---.`````.:+ydNMMMNh+-  ``  `  ``  ``");
		System.out.println("``  ` `:hNMMNdo-.  `` `.:/shdmmNNNMMMMMNNNmmdys/-.```-/ymMMMNy:` `` ``  ``  ``");
		System.out.println("``  `` .hNMMNs-``  ``.:sdmNMMMMNmmdddddddddmmNMMMMNmho:` `./yNMMNh-  `   `  ``");
		System.out.println("``  `mMMNs-``  `./ymMMMNmhs+:-..`````````..-:+sdmMMMNms-```-sNMMN.   `  ``  ``");
		System.out.println("``  `/ys:` ```:smMMMmh+:.` ```.--:::::---.``  ``.:sdNMMNh:` `-sy+`::.`  ``  ``");
		System.out.println("``  ``  `  ```:hNMMNh/.`` `-/shdmNMMMMMMMNNmdho/-``  `:smMMMh:`  ``sMMm/``  ``");
		System.out.println("``  ``  ``-hMMMNs-```./ydNMMMNNmdhhhhhdmmNNMMMNdy:.` `.oNMMNs.`  :mMMN+```  ``");
		System.out.println("``  ``  ```oNMMNs. ``-ymMMMNds/:.```  `` `.-:/sdNMMMms-` `-yMMMm:`` -mMMM+` ``");
		System.out.println("``  `` .hMMMd- ``:hMMMNh+-`` `..-:////:-..`` `-+hNMMMh:` `+NMMN+```.mMMM/`  ``");
		System.out.println("``  ``  -mMMMs```.yMMMNs-  `.:ohmNMMMMMMMMNmhs:.  `-sNMMMy.  -dMMMy` `-NMMm ``");
		System.out.println("``  `.mMMM+`` :mMMMy-  `-smMMMNNmhyyyyhmNNMMMmy-```.yMMMN/` `yMMMd- `-oo:`  ``");
		System.out.println("``  ```dMMM+`  /NMMN/  `.yMMMNdo:```  ``  .:+hNMMMh- ``:mMMM+`` +NMMN/` ``  ``");
		System.out.println("``  oMMMy  `:MMMm- ``/NMMMy:` ``.:+oo+:.` ``.sNMMMo` `-mMMM/ ``-mMMMh-` ``  ``");
		System.out.println("``  `.NMMN````NMMN-`` /MMMN/` `-smMMMMMMMMms-```.dMMMy`  -NMMN/` ``sNMMMho. ``");
		System.out.println("`` oMMMo`  oMMMo`  :MMMN.  .yMMMMmyssymMMMMy` ``hMMMo`` :NMMMm+` `.sNMMMm`  ``");
		System.out.println("``  `dMMM- ``NMMN` ``dMMM: ``dMMMy-`  `` .yMMMm`  .mMMM/ ```+mMMMNs.```:+o- ``");
		System.out.println("`` MMMN`` .MMMh`` .MMMm`` +MMMy`  -oo.`` oMMMh`` -mMMMy` `` :hMMMMh/``  ``  ``");
		System.out.println("``  `NMMd` `-MMMy  `:MMMh  `yMMM: ``dMMN. ``yMMMy`` `yMMMNo` `` .sNMMMmo.`  ``");
		System.out.println("`` .+/` ` `MMMd`` -MMMd`` +MMMy`  /MMMm.  `yMMMd-```:dMMMNo` `` `+dMMMNy-`  ``");
		System.out.println("``  ``  `` ``mMMM` ``NMMM` ``dMMM/`` oMMMm-` `+NMMNo````:dMMMNs- `` `:yNMMm ``");
		System.out.println("`` :so- `  oMMMo`  sMMM+`  -NMMN: ``+MMMN:`` -hMMMm+````:hMMMNh:``` `.//.`  ``");
		System.out.println("``  `NMMm` ``.NMMN```-MMMd `` -NMMN/  `/NMMMs.```/mMMMd/````-sNMMMd+.`  ``  ``");
		System.out.println("`` yMMMo`  `yMMM:  `dMMM.  ` -mMMMs`  .yMMMm+````+mMMMd/``` .omMMMNs-`  ``  ``");
		System.out.println("``  `.mMMM:`` oMMM+`` oMMM/`` ``.hMMMd-` `/mMMMm/`` .+mMMMm+.`` `/hMMMNh/`  ``");
		System.out.println("``  :mMMs  `yMMM/  `/MMMs  `  ``oNMMNo.` `omMMMd/`` `+dMMMNs-`` `-smMMd```  ``");
		System.out.println("``  `` .::` `.NMMN``` :MMMh`  ``  `-hMMMm/`` .omMMMm+.` `:hNMMNh/`` `.::.`  ``");
		System.out.println("``  ``  ` -dMMM+`  `:MMMd -yy+``  `/mMMMd:`` .+dMMMms:` `-smMMMdo-` ``  ``  ``");
		System.out.println("``  ``  ``-sNMMN+` `` /MMMy`sMMM/ ``  .omMMMh:`  `/hNMMNh+. `.+dMMMNy.   `  ``");
		System.out.println("``  `/ydNMMNy-` `  `yMMM/ /MMMs`  ``  .omMMMh/`  `-smMMMmy:` `:ymMN/ `  ``  ``");
		System.out.println("``  :MMMNdo-`   ` `+MMMm` :MMMs`  ``  ``.omMMMd+. ``./hNMMNd+.``.:-` `  ``  ``");
		System.out.println("``  ```:+/-``  `` `-yMMMm- `+MMMo ``  ``  ``.+mMMMms-`` `-smMMMms``  `   `  ``");
		System.out.println("``  `   `  ``.:+hNMMNy.```dMMM-`  `` ```  ```/dNMMNy:``  ./hNNm. `` ``  ``  ``");
		System.out.println("``  ``  `` ``sdmMMMMms-``  +MMMy  ``-oyhhhs+-````:yNMMMd+.`  `-:.``  `  ``  ``");
		System.out.println("``  ``  ` `mNNmho:.``  `:NMMm.```sNMMMNMMMNh/``` -omMMMNs-`  ``  `` ``  ``  ``");
		System.out.println("``  ``  `` ```--`  ``  ``+NMMN-` `hMMMh/:/smMMMd:``  `/hNMMh ``  ``  `  ``  ``");
		System.out.println("``  ``  `  ``  ``  `./hMMMd-  `oMMMh` `` `oNMMMs.``  `-//.`  ``  `` ``  ``  ``");
		System.out.println("``  ``  `` ``  ``  `.ymMMMNo` ``.NMMN.``  `` .hMMMm- ``  ``  ``  ``  `  ``  ``");
		System.out.println("``  ``  `  ``  `` :NNNho.  `  +MMMs`  ``  `` /mNN+`  ``  ``  ``  `` ``  ``  ``");
		System.out.println("``  ``  `` ``  ``  ```.``  `` ``hMMM- ``  ``  `` ..  ``  ``  ``  ``  `  ``  ``");
		System.out.println("``  ``  `  ``  ``  ``  ``  `  oNMh``  ``  ``  `` ``  ``  ``  ``  `` ``  ``  ``");
		System.out.println("``  ``  `` ``  ``  ``  ``  `` `` `.`  ``  ``  ``  `  ``  ``  ``  ``  `  ``  ``");

	}

	// Conclusion ascii art -----------------
	// Ascii art of person in jail
	public void concArt() {
		System.out.println("\n...               ...               ...      ..      `..`                     ");
		System.out.println("--:               :-:---.`          :--   :  -d-     `--.                     ");
		System.out.println("--:           ./sh:-+dddddhhso/:--..:-::+ys `ymh     `--.                     ");
		System.out.println("--:         -sdddm/-+mmmmmmmmmmmdddh:-odmd++hmmm.    `--.                     ");
		System.out.println("--:        odddmmm/-+mmmmmmmmmmddddd:-ommmmmmmmy     `--.                     ");
		System.out.println("--:       +ddddmmm/-+mmmmmmmmmmmmmdd:-odddddmmd-     `--.                     ");
		System.out.println("--:       ydddddmm/-+mmmmmmmmmmmmddd:-oddddmmm/`     `--.                     ");
		System.out.println("--:     `:hmddddmm/-+mmmmmmmmmmmmmmd:-oddmmmmmdh+.   `--.                     ");
		System.out.println("--:    `ymmmmdddmm/-+mmmmmmmmmmmmmdd:-omdddmmmmmmh:  `--.                     ");
		System.out.println("--:    ymmmmmdhddm/-+mmmmmmmmmmmmmmd:-+o/:/sdmmmmmd- `--.                     ");
		System.out.println("`````````--:```-yyyyyy+:/sy:-/yyyyyyyyyyyyys+:-:-----/yyyyyy+`.:--````````    ");
		System.out.println(".---------------:::::----------------------------------::::::------------.    ");
		System.out.println("`````````--:```:hhhhy-.----:-:---------------:-:-----..+hhhhs`.:-.````````    ");
		System.out.println("--:   `ddddy......:-:...............:-:.......+dddd/ `--.                     ");
		System.out.println("--:    /dddd......:-:...............:-:.......odddy  `--.                     ");
		System.out.println("--:   `.ymmm/.....:-:...........-...:-:......-hmmd:` `--.                     ");
		System.out.println("--:  .--:hmmy-....:-:+++/....../ooo/:-:......+mmd+---.--.                     ");
		System.out.println("--:  --:::hmm+....:-::/:......::-//::-:.....-dmd+-:-----.                     ");
		System.out.println("--:  .--:-:ydy....:-:hmd.....-:.sdN::-:...../dh/--:--.--.                     ");
		System.out.println("--:   .-------....:-:/s+.....::.:so-:-:......-------``--.                     ");
		System.out.println("--:     `.........:-:........:/-....:-:...........`  `--.                     ");
		System.out.println("--:        `-.....:-:........-/:.-.-:-:........`     `--.                     ");
		System.out.println("--:       ``..-.--:-:.-.-.--:::-.-.-:-:.-.-.-.-`     `--.                     ");
		System.out.println("--:        `......:-:..............-:-:.......-`     `--.                     ");
		System.out.println("--:        `-.....:-:...............:-:.......-      `--.                     ");
		System.out.println("--:         -.....:-:....-::::::-...:-:........      `--.                     ");
		System.out.println("--:         ......:-:...-++///+o+:..:-:......-`      `--.                     ");
		System.out.println("--:         `--...:-:...............:-:.....-.       `--.                     ");
		System.out.println("--:          `--..:-:...............:-:....-.        `--.                     ");
		System.out.println("--:           `.-.:-:...............:-:..-.`         `--.                     ");
		System.out.println("--:             `.:-:..............-:-:..`           `--.                     ");
		System.out.println("--:               :-:`...--------...:--              `--.                     ");
		System.out.println("--:               :--    --------`  :--              `--.                     ");
		System.out.println(".........--:...............:--....---------.-:--..............----........    ");
		System.out.println(".........--:.............-::-::::::------:::::-:::-...........----........    ");
		System.out.println("--:            `:+:-/++++////////+++:-/+/:`          `--.                     ");
		System.out.println("--:           `/+/:-/+++++++++++++++:-:/++/`         `--.                     ");
		System.out.println("--:           /+:.-.-/++++++++++++/--.--+++:         `--.                     ");
		System.out.println("--:          `++-....:++++++++++++/...../+++`        `--.                     ");
		System.out.println("--:          -++-....:++++++++++++/...../+++-        `--.                     ");
		System.out.println("--:          -++/:-.-/++++++++++++/:-.://+++-        `--.                     ");
		System.out.println("--:           ....:-:...............:--.....         `--.                     ");
		System.out.println("...               ...               ...              `..`                     ");
	}
} // end RPG_Final