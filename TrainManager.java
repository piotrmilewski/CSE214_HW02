/**
 * The <code>TrainManager</code> class executes an interactive train manager in the terminal
 * utilizing the <code>TrainLinkedList</code> class to store each train and information pertaining
 * to each train car.
 *
 *
 * @author Piotr Milewski
 *    email: piotr.milewski@stonybrook.edu
 *    Stony Brook ID: 112291666
 **/

import java.io.*;

public class TrainManager{

    public static void main(String[] args) throws IOException{
	//Open an input stream for reading from the keyboard
	InputStreamReader inStream = new InputStreamReader(System.in);
	BufferedReader stdin = new BufferedReader(inStream);

	//Menu
	TrainLinkedList train = new TrainLinkedList();
	TrainCar newCar;
	ProductLoad newLoad;
	boolean running = true;
	String input, prompt, produceName;
	double carLength, carWeight, produceWeight, produceValue;
	boolean isDangerous = false;
	boolean innerLoop = false;
	while (running){
	    innerLoop = false;
	    prompt = "\n(F) Cursor Forward \n(B) Cursor Backward \n(I) Insert Car After Cursor \n";
	    prompt += "(R) Remove Car At Cursor \n(L) Set Product Load \n(S) Search For Product \n";
	    prompt += "(T) Display Train \n(M) Display Manifest \n(D) Remove Dangerous Cars \n(Q) Quit \n\n";
	    prompt += "Enter a selection: ";

	    System.out.print(prompt);
	    input = stdin.readLine().toUpperCase();

	    if (input.equals("F")){ //CURSOR FORWARD
		train.cursorForward();
	    }
	    else if (input.equals("B")){ //CURSOR BACKWARD
		train.cursorBackward();
	    }
	    else if (input.equals("I")){ //INSERT CAR AFTER CURSOR
		/*
		  try catch block here
		*/
		prompt = "\nEnter a car length in meters: ";
		System.out.print(prompt);
		carLength = Double.parseDouble(stdin.readLine());

		prompt = "Enter a car weight in tons: ";
		System.out.print(prompt);
		carWeight = Double.parseDouble(stdin.readLine());

		newCar = new TrainCar(carLength, carWeight);
		train.insertAfterCursor(newCar);
		
		prompt = "\nNew train car " + carLength + " meters " + carWeight + " tons inserted into train";
		System.out.println(prompt);
	    }
	    else if (input.equals("R")){ //REMOVE CAR AT CURSOR
		train.removeCursor();
	    }
	    else if (input.equals("L")){ //SET PRODUCT LOAD
		if (train.getCursorData() == null){
		    System.out.println("\nCan't set product load without any cars");
		}
		else{
		    /*
		      try catch block here
		    */
		    prompt = "\nEnter produce name: ";
		    System.out.print(prompt);
		    produceName = stdin.readLine();

		    prompt = "Enter product weight in tons: ";
		    System.out.print(prompt);
		    produceWeight = Double.parseDouble(stdin.readLine());

		    prompt = "Enter product value in dollars: ";
		    System.out.print(prompt);
		    produceValue = Double.parseDouble(stdin.readLine());

		    prompt = "Enter is product dangerous? (y/n): ";
		    System.out.print(prompt);
		    input = stdin.readLine();
		    if (input.equals("y"))
			isDangerous = true;
		    else if (input.equals("n"))
			isDangerous = false;
		
		    newLoad = new ProductLoad(produceName, produceWeight, produceValue, isDangerous);
		    newCar = new TrainCar(0.0, 0.0, newLoad);
		    train.setCursorData(newCar);
		
		    prompt = "\n" + produceWeight + " tons of " + produceName + " added to the current car.";
		    System.out.println(prompt);
		}
	    }
	    else if (input.equals("S")){ //SEARCH FOR PRODUCT
		/*
		  try catch block here
		*/
		prompt = "\nEnter product name: ";
		System.out.print(prompt);
		input = stdin.readLine();

		train.findProduct(input);
	    }
	    else if (input.equals("T")){ //DISPLAY TRAIN
		System.out.println("\n" + train);
	    }
	    else if (input.equals("M")){ //DISPLAY MANIFEST
		train.printManifest();
	    }
	    else if (input.equals("D")){ //REMOVE DANGEROUS CARS
		train.removeDangerousCars();
	    }
	    else if (input.equals("Q")){ //QUIT
		prompt = "\nProgram terminating successfully...\n";
		System.out.println(prompt);
		running = false;
	    }
	    else{
		prompt = "\nThat's not an option, please try again";
		System.out.println(prompt);
	    }
	}
    }
}
