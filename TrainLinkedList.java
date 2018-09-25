public class TrainLinkedList{

    TrainCarNode head, tail, cursor;
    int size;
    int numOfDangerousCars = 0;
    int cursorSpot = 0;
    double totalLength, totalValue, totalWeight;
    boolean isDangerous;

    public TrainLinkedList(){
	//set TrainCarNode links
	head = null;
	tail = null;
	cursor = null;
	//set TrainLinkedList attributes
	size = 0;
	numOfDangerousCars = 0;
	totalLength = 0.0;
	totalValue = 0.0;
	totalWeight = 0.0;
	isDangerous = false;
    }

    public TrainCar getCursorData(){
	if (cursor == null)
	    return null;
	return cursor.getCurrNode();
    }

    public void setCursorData(TrainCar car){
	cursor.getCurrNode().setLoad(car.getLoad());
	//set TrainLinkedList attributes
	if (car.getLoad() != null){
	    totalValue += car.getLoad().getValue();
	    totalWeight += car.getLoad().getWeight();
	    if (car.getLoad().getDangerState()){ //if new car is dangerous
		numOfDangerousCars++;
		if (numOfDangerousCars > 0)
		    isDangerous = true;
	    }
	}
    }

    public void cursorForward(){
	if (cursor == null){
	    System.out.println("\nTrain is currently empty, cannot move forward");
	    return;
	}
	if (cursor.getNextNode() != null){
	    cursor = cursor.getNextNode();
	    cursorSpot++;
	}
	else{
	    System.out.println("\nNo next car, cannot move cursor forward");
	}
    }

    public void cursorBackward(){
	if (cursor == null){
	    System.out.println("\nTrain is currently empty, cannot move backward");
	    return;
	}
	if (cursor.getPrevNode() != null){
	    cursor = cursor.getPrevNode();
	    cursorSpot--;
	}
	else{
	    System.out.println("\nNo previous car, cannot move cursor backward");
	}
    }
    
    public void insertAfterCursor(TrainCar newCar){
	TrainCarNode newNode = new TrainCarNode(newCar);
	if (head == null){ //if DLL is empty
	    head = newNode;
	    tail = newNode;
	    cursor = newNode;
	    cursorSpot++;
	}
	else if (cursor == tail){ //if cursor is currently on the tail
	    cursor.setNextNode(newNode);
	    newNode.setPrevNode(cursor);
	    tail = newNode;
	    cursor = newNode;
	    cursorSpot++;
	}
	else{ //normal execution
	    cursor.getNextNode().setPrevNode(newNode);
	    newNode.setPrevNode(cursor);
	    newNode.setNextNode(cursor.getNextNode());
	    cursor.setNextNode(newNode);
	    cursor = newNode;
	    cursorSpot++;
	}
	//set TrainLinkedList attributes
	size++;
	totalLength += newCar.getLength();
	totalWeight += newCar.getWeight();
	if (newCar.getLoad() != null){
	    totalValue += newCar.getLoad().getValue();
	    totalWeight += newCar.getLoad().getWeight();
	    if (newCar.getLoad().getDangerState()){ //if new car is dangerous
		numOfDangerousCars++;
		if (numOfDangerousCars > 0)
		    isDangerous = true;
	    }
	}
    }

    public TrainCar removeCursor(){
	if (cursor == null){
	    System.out.println("\nThere are no cars to remove");
	    return null;
	}
	TrainCar removedCar = cursor.getCurrNode(); //stores removed Node
	if (cursor == head){
	    if (cursor.getNextNode() != null){
		head = cursor.getNextNode();
		cursor.getNextNode().setPrevNode(null);
		cursor = head;
	    }
	    else{
		head = null;
		cursor = null;
		tail = null;
		cursorSpot--;
	    }
	}
	else if (cursor == tail){
	    tail = cursor.getPrevNode();
	    cursor.getPrevNode().setNextNode(null);
	    cursor = tail;
	    cursorSpot--;
	}
	else{
	    cursor.getNextNode().setPrevNode(cursor.getPrevNode());
	    cursor = cursor.getPrevNode();
	    cursor.setNextNode(cursor.getNextNode().getNextNode()); //removes cursor's original node
	    if (cursor.getNextNode() != null)
		cursor = cursor.getNextNode();
	}
	//set TrainLinkedList attributes
	size--;
	totalLength -= removedCar.getLength();
	totalWeight -= removedCar.getWeight();
	if (removedCar.getLoad() != null){
	    totalValue -= removedCar.getLoad().getValue();
	    totalWeight -= removedCar.getLoad().getWeight();
	    if (removedCar.getLoad().getDangerState()){ //if removed car was dangerous
		numOfDangerousCars--;
		if (numOfDangerousCars == 0)
		    isDangerous = false;
	    }
	}
	return removedCar;
    }

    public int size(){
	return size;
    }

    public double getLength(){
	return totalLength;
    }

    public double getValue(){
	return totalValue;
    }

    public double getWeight(){
	return totalWeight;
    }

    public boolean isDangerous(){
	return isDangerous;
    }

    public void findProduct(String name){
	int sumOfWeight = 0;
	int sumOfValue = 0;
	int numOfCars = 0;
	boolean carDangerous = false;
	String dangerString;
	TrainCarNode innerCursor = head;
	ProductLoad currLoad;
	while (innerCursor != null){
	    currLoad = innerCursor.getCurrNode().getLoad();
	    if (name.equals(currLoad.getName())){
		sumOfWeight += currLoad.getWeight();
		sumOfValue += currLoad.getValue();
		numOfCars++;
		if (currLoad.getDangerState())
		    carDangerous = true;
	    }
	    innerCursor = innerCursor.getNextNode();
	}
	if (numOfCars == 0){
	    System.out.println("\nNo record of " + name + " on board train.");
	    return;
	}
	if (carDangerous)
	    dangerString = "YES";
	else
	    dangerString = "NO";
	String output = "\nThe following products were found on " + numOfCars + " cars:\n\n";
	output += "        Name      Weight (t)     Value ($)   Dangerous\n";
	output += "======================================================\n";
	output += String.format("%12s%16s%14s%12s", name, sumOfWeight, sumOfValue, dangerString);	
	System.out.println(output);
    }

    public void printManifest(){
	String output = "\nCAR:                                  LOAD:\n";
	output += "      Num   Length (m)   Weight (t)  |          Name    Weight (t)   Value ($)   Dangerous\n";
	output += "=====================================+====================================================\n";
	int manNum = 0;
	double manLength, manWeight, manLoadWeight, manLoadValue;
	String manName;
	String dangerString;
	boolean manDanger;
	TrainCarNode innerCursor = head;
	TrainCar currCar;
	ProductLoad currLoad;
	while (innerCursor != null){
	    currCar = innerCursor.getCurrNode();
	    currLoad = currCar.getLoad();
	    manNum++;
	    manLength = currCar.getLength();
	    manWeight = currCar.getWeight();
	    if (currLoad != null){
		manName = currLoad.getName();
		manLoadWeight = currLoad.getWeight();
		manLoadValue = currLoad.getValue();
		manDanger = currLoad.getDangerState();
	    }
	    else{
		manName = "Empty";
		manLoadWeight = 0.0;
		manLoadValue = 0.0;
		manDanger = false;
	    }
	    if (cursorSpot == manNum)
		output += "-->";
	    else
		output += "   ";
	    if (manDanger){
		dangerString = "YES\n";
	    }
	    else{
		dangerString = "NO\n";
	    }
	    output += String.format("%6s%13s%13s%3s", manNum, manLength, manWeight, "|");
	    output += String.format("%14s%14s%12s%13s", manName, manLoadWeight, manLoadValue, dangerString);
	    innerCursor = innerCursor.getNextNode();
	}
	System.out.println(output);	
    }

    public void removeDangerousCars(){
	if (numOfDangerousCars == 0){
	    System.out.println("\nThere are no dangerous cars");
	    return;
	}
	TrainCar removedCar;
	TrainCarNode innerCursor = head;
	ProductLoad currLoad;
	while (innerCursor != null && numOfDangerousCars > 0){
	    currLoad = innerCursor.getCurrNode().getLoad();
	    if (currLoad != null && currLoad.getDangerState()){
		removedCar = innerCursor.getCurrNode();
		if (innerCursor == head){
		    if (innerCursor.getNextNode() != null){
			head = innerCursor.getNextNode();
			innerCursor.getNextNode().setPrevNode(null);
			cursor = head;
		    }
		    else{
			head = null;
			cursor = null;
			tail = null;
		    }
		}
		else if (innerCursor == tail){
		    tail = innerCursor.getPrevNode();
		    innerCursor.getPrevNode().setNextNode(null);
		}
		else{
		    innerCursor.getNextNode().setPrevNode(innerCursor.getPrevNode());
		    innerCursor = innerCursor.getPrevNode();
		    innerCursor.setNextNode(innerCursor.getNextNode().getNextNode());
		}
		//set TrainLinkedList attributes
		size--;
		totalLength -= removedCar.getLength();
		totalWeight -= removedCar.getWeight();
		if (removedCar.getLoad() != null){
		    totalValue -= removedCar.getLoad().getValue();
		    totalWeight -= removedCar.getLoad().getWeight();
		    if (removedCar.getLoad().getDangerState()){ //if removed car was dangerous
			numOfDangerousCars--;
			if (numOfDangerousCars == 0)
			    isDangerous = false;
		    }
		}
		if (head != null){
		    cursor = head;
		    cursorSpot = 1;
		}
		else{
		    cursor = null;
		    cursorSpot = 0;
		}
	    }
	    innerCursor = innerCursor.getNextNode();
	}
	
	System.out.println("\nDangerous cars successfully removed from the train.");
    }
    
    public String toString(){
	String output = "Train: ";
	output += size + " cars, ";
	output += totalLength + " meters, ";
	output += totalWeight + " tons, ";
	output += totalValue + " dollars, ";
	if (isDangerous){
	    output += "DANGEROUS.";
	}
	else{
	    output += "not dangerous.";
	}
	System.out.println(numOfDangerousCars);
	return output;
    }
}
