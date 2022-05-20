package project;
import java.util.Scanner;
public class ConnectFour {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		char[][] connectFour=new char[6][7];
		int columnNumber;
		System.out.println("Welcome to the game\n");
		for(int turn=1;turn<=7*6;turn++)
		{
			Methods.printConnectFour(connectFour);
			//get the column number from the user
			columnNumber=Methods.chooseColumn(turn);
			//fill array with color in the column that the user just entered
			Methods.fill(connectFour, columnNumber, turn);
			//the least number that winning starts from
			if(turn>=7)
			{
				Methods.win(connectFour,turn);
			}
		}
		//if the for loop is ended that means the game is over and on one wins
		System.out.println("It's a tie!");
	}

}
