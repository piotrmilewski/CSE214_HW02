public class TrainLinkedList{

    TrainCarNode head, tail, cursor;
    int size, numOfDangerousCars;
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
	cursor.setCurrNode(car);
	if (head == null){
	    head = cursor;
	    tail = cursor;
	}
    }

    public void cursorForward(){
	cursor = cursor.getNextNode();
    }

    public void cursorBackward(){
	cursor = cursor.getPrevNode();
    }
    
    public void insertAfterCursor(TrainCar newCar){
	TrainCarNode newNode = new TrainCarNode(newCar);
	if (head == null){ //if DLL is empty
	    head = newNode;
	    tail = newNode;
	    cursor = newNode;
	}
	else if (cursor == tail){ //if cursor is currently on the tail
	    cursor.setNextNode(newNode);
	    newNode.setPrevNode(cursor);
	    tail = newNode;
	    cursor = newNode;
	}
	else{ //normal execution
	    cursor.getNextNode().setPrevNode(newNode);
	    newNode.setPrevNode(cursor);
	    newNode.setNextNode(cursor.getNextNode());
	    cursor.setNextNode(newNode);
	    cursor = newNode;
	}
	//set TrainLinkedList attributes
	size++;
	totalLength += newCar.getLength();
	totalValue += newCar.getLoad().getValue();
	if (newCar.getLoad().getDangerState()){ //if new car is dangerous
	    numOfDangerousCars++;
	    if (numOfDangerousCars > 0)
		isDangerous = true;
	}
    }

    public TrainCar removeCursor(){
	TrainCar removedCar = cursor.getCurrNode(); //stores removed Node
	cursor.getNextNode().setPrevNode(cursor.getPrevNode());
	cursor = cursor.getPrevNode();
	cursor.setNextNode(cursor.getNextNode().getNextNode()); //removes cursor's original node
	if (cursor.getNextNode() != null)
	    cursor = cursor.getNextNode();
	//set TrainLinkedList attributes
	size--;
	totalLength -= removedCar.getLength();
	totalValue -= removedCar.getLoad().getValue();
	if (removedCar.getLoad().getDangerState()){ //if removed car was dangerous
	    numOfDangerousCars--;
	    if (numOfDangerousCars == 0)
		isDangerous = false;
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
	boolean carDangerous = false;
	TrainCarNode innerCursor = head;
	ProductLoad currLoad;
	while (innerCursor != null){
	    currLoad = innerCursor.getCurrNode().getLoad();
	    if (name.equals(currLoad.getName())){
		sumOfWeight += currLoad.getWeight();
		sumOfValue += currLoad.getValue();
		if (currLoad.getDangerState())
		    carDangerous = true;
	    }
	    innerCursor = innerCursor.getNextNode();
	}
	String output = "Product of cars with the name: " + name + "\n";
	output += "Weight: " + sumOfWeight + " tons\n";
	output += "Value: " + sumOfValue + " dollars\n";
	if (carDangerous){
	    output += "Load is DANGEROUS\n";
	}
	else{
	    output += "Load is not dangerous\n";
	}
	System.out.println(output);
    }

    public void printManifest(){
	String output = "    CAR:                               LOAD:\n";
	output += "      Num   Length (m)    Weight (t)  |    Name      Weight (t)     Value ($)   Dangerous\n";
	output += "    ==================================+===================================================\n";
	int manNum = 0;
	double manLength, manWeight, manLoadWeight, manLoadValue;
	String manName; 
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
	    manName = currLoad.getName();
	    manLoadWeight = currLoad.getWeight();
	    manLoadValue = currLoad.getValue();
	    manDanger = currLoad.getDangerState();
	    output += "    \t" + manNum + "\t" + manLength + "\t" + manWeight + "  |\t";
	    output += manName + "\t" + manLoadWeight + "\t" + manLoadValue + "\t";
	    if (manDanger){
		output += "YES\n";
	    }
	    else{
		output += "NO\n";
	    }
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
	output += size + ", ";
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
