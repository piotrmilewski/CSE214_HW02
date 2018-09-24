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

    /*
      UNFINISHED LAST WORKED ON THIS
    */
    public void insertAfterCursor(TrainCar newCar){
	TrainCarNode newNode = TrainCarNode(newCar);
	if (head == null){
	    head = newNode;
	    tail = newNode;
	    cursor = newNode;
	}
	else if (cursor == tail){
	    
	}
	else{
	    newNode.setNextNode(cursor.getNextNode);
	    cursor.setNextNode(newCar);
	    cursor = newNode;
	}
	//set TrainLinkedList attributes
	size++;
	totalLength += newCar.getLength();
	totalValue += newCar.getValue();
	if (newCar.getLoad().getDangerState()){ //if new car is dangerous
	    numOfDangerousCars++;
	    if (numOfDangerousCars > 0)
		isDangerous = true;
	}
    }
    /*
      UNFINISHED LAST WORKED ON THIS
    */

    public TrainCar removeCursor(){
	TrainCar removedCar = cursor.getCurrNode(); //stores removed Node
	cursor = cursor.getPrevNode();
	cursor.setNextNode(cursor.getNextNode().getNextNode()); //removes cursor's original node
	if (cursor.getNextNode() != null)
	    cursor = cursor.getNextNode();
	//set TrainLinkedList attributes
	size--;
	totalLength -= removedCar.getLength();
	totalValue -= removedCar.getValue();
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

    public boolean isDangerous(){
	return isDangerous;
    }

    public void findProduct(String name){

    }

    public void printManifest(){

    }

    public void removeDangerousCars(){

    }

    public String toString(){

    }
}
