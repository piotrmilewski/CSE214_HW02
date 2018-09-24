public class TrainCarNode{

    TrainCar currNode;
    TrainCar nextNode;
    TrainCar prevNode;

    public TrainCarNode(){
	currNode = null;
	nextNode = null;
	prevNode = null;
    }

    public TrainCarNode(TrainCar newNode){
	currNode = newNode;
	nextNode = null;
	prevNode = null;
    }

    public TrainCar getCurrNode(){
	return currNode;
    }

    public TrainCar setCurrNode(TrainCar newNode){
	TrainCar oldCurrNode = newNode;
	currNode = newNode;
	return oldCurrNode;
    }
    
    public TrainCar getNextNode(){
	return nextNode;
    }

    public TrainCar setNextNode(TrainCar newNode){
	TrainCar oldNextNode = nextNode;
	nextNode = newNode;
	return oldNextNode;
    }

    public TrainCar getPrevNode(){
	return prevNode;
    }
    
    public TrainCar setPrevNode(TrainCar newNode){
	TrainCar oldPrevNode = newNode;
	prevNode = newNode;
	return oldPrevNode;
    }
    
    public String toString(){
	String output = "";
	output += "\nCurrent Node: " + currNode;
	output += "\nNext Node: " + nextnode;
	output += "\nPrev Node: " + prevNode + "\n";
	return output;
    }
}
