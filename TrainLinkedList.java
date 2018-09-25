public class TrainLinkedList{

    TrainCarNode head, tail, cursor;
    int size, numOfDangerousCars;
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
	return cursor.getCurrNode();
    }

    public void setCursorData(TrainCar car){
	cursor.getCurrNode().setLoad(car.getLoad());
    }

    public void cursorForward(){
	if (cursor.getNextNode() != null){
	    cursor = cursor.getNextNode();
	    cursorSpot++;
	}
    }

    public void cursorBackward(){
	if (cursor.getPrevNode() != null){
	    cursor = cursor.getPrevNode();
	    cursorSpot--;
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
	if (newCar.getLoad() != null){
	    totalValue += newCar.getLoad().getValue();
	    if (newCar.getLoad().getDangerState()){ //if new car is dangerous
		numOfDangerousCars++;
		if (numOfDangerousCars > 0)
		    isDangerous = true;
	    }
	}
    }

    public TrainCar removeCursor(){
	TrainCar removedCar = cursor.getCurrNode(); //stores removed Node
	cursor.getNextNode().setPrevNode(cursor.getPrevNode());
	cursor = cursor.getPrevNode();
	cursor.setNextNode(cursor.getNextNode().getNextNode()); //removes cursor's original node
	if (cursor.getNextNode() != null)
	    cursor = cursor.getNextNode();
	cursorSpot--;
	//set TrainLinkedList attributes
	size--;
	totalLength -= removedCar.getLength();
	if (removedCar.getLoad() != null){
	    totalValue -= removedCar.getLoad().getValue();
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
	if (numOfDangerousCars == 0)
	    return;
	TrainCarNode innerCursor = head;
	ProductLoad currLoad;
	while (innerCursor != null && numOfDangerousCars > 0){
	    currLoad = innerCursor.getCurrNode().getLoad();
	    if (currLoad.getDangerState()){
		if (innerCursor.getNextNode() != null)
		    innerCursor.getNextNode().setPrevNode(innerCursor.getPrevNode());
		innerCursor = innerCursor.getPrevNode();
		innerCursor.setNextNode(innerCursor.getNextNode().getNextNode());
		numOfDangerousCars--;
	    }
	}
    }
    
    public String toString(){
	String output = "Train: ";
	output += size + " cars, ";
	output += totalLength + " meters, ";
	output += totalWeight + " tons, ";
	output += totalValue + " dollars, ";
	if (isDangerous){
	    output += "DANGEROUS.\n";
	}
	else{
	    output += "not dangerous.\n";
	}
	return output;
    }
}
