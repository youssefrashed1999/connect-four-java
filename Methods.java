package project;
import java.util.Scanner;
public class Methods {
	static public void printConnectFour(char[][] connectFour)
	{
		System.out.println(" 0 1 2 3 4 5 6");
	
		for(int i=0;i<connectFour.length;i++)
		{
			for(int j=0;j<connectFour[i].length;j++)
			{
				System.out.print("|");
				//if it has its default value print space 
				if(connectFour[i][j]=='\u0000')
					System.out.print(" ");
				else
					System.out.print(connectFour[i][j]);
			}
			System.out.println("|");
		}
	}
	
	static public int checkForError(int columnNumber,String message)
	{
		Scanner scan=new Scanner(System.in);
		//the loop will run untill the user enter a number from 0 to 6
		while(columnNumber<0 || columnNumber>6)
		{
			System.out.println("Wrong input!");
			System.out.print(message);
			columnNumber=scan.nextInt();
		}
		return columnNumber;
	}
	static public void fill(char[][] connectFour,int columnNumber,int turn)
	{
		char color=Methods.turnColor(turn);
		for(int i=connectFour.length-1;i>=0;i--)
		{
			//if it has the default value then it's an empty place
			if(connectFour[i][columnNumber]=='\u0000')
			{
				connectFour[i][columnNumber]=color;
				return;//don't need to continue the code if he find an empty place
			}
		}
		//if the return didn't work then this column is full
		columnNumber=Methods.WrongColumn(columnNumber, turn);
		//since we have another column number then go fill the array
		Methods.fill(connectFour, columnNumber, turn);
	}
	
	static public int WrongColumn(int columnNumber,int turn)
	{
		System.out.println("Column "+columnNumber+" is full! Please choose another column");
		//let the user choose another column
		columnNumber=Methods.chooseColumn(turn);
		return columnNumber;
	}
	
	static public int chooseColumn(int turn)
	{
		Scanner scan=new Scanner(System.in);
		int columnNumber;
		//if turn is odd then it's red's turn
		if(turn%2==1)
		{
			System.out.print("Drop a red disk at column(0-6) : ");
			columnNumber=scan.nextInt();
			columnNumber=Methods.checkForError(columnNumber,"Drop a red disk at column(0-6) : ");
		}
		else
		{
			System.out.print("Drop a yellow disk at column(0-6) : ");
			columnNumber=scan.nextInt();
			columnNumber=Methods.checkForError(columnNumber,"Drop a red disk at column(0-6) : ");
		}
		return columnNumber;
	}
	
	static public char turnColor(int turn)
	{
		char color;
		//if the turn is odd then it's red
		if(turn%2==1)
			color='R';
		else
			color='Y';
		return color;
	}
	
	static public void win(char[][] connectFour,int turn)
	{
		char color=Methods.turnColor(turn);
		Methods.winHorizontal(connectFour, color);
		Methods.winVertical(connectFour, color);
		Methods.winDiagonalRight(connectFour, color);
		Methods.winDiagonalLeft(connectFour, color);
	}
	
	static public void winHorizontal(char[][] connectFour,char color)
	{
		int count=0;
		//start from the bottom because it's the first to be filled
		for(int i=connectFour.length-1;i>=0;i--)
		{
			for(int j=0;j<connectFour[i].length;j++)
			{
				if(connectFour[i][j]==color)
				{
					count++;
					if(count==4)
					{
						Methods.winMessage(color, connectFour);
					}
				}	
				else//start from the beginning if they are not consecutive
					count=0;
			}
			count=0;
		}
	}
	static public void winVertical(char[][] connectFour,char color)
	{
		int count=0;
		int noOfRows=connectFour.length;
		for(int j=0;j<connectFour[noOfRows-1].length;j++)
		{
			for(int i=connectFour.length-1;i>=0;i--)
			{
				if(connectFour[i][j]==color)
				{
					count++;
					if(count==4)
					{
						Methods.winMessage(color, connectFour);
					}
				}	
				else//start from the beginning if they are not consecutive
					count=0;
			}
			count=0;
		}
	}
	static public void winDiagonalRight(char[][] connectFour,char color)
	{
		//starting from button left and ascending to the right
		int count=0;
		int saver;
		//3 is the limit no matter how big the connect4 is because if i<3 it can connect 4 diagonally
		for(int i=connectFour.length-1;i>=3;i--)
		{
			//to save i because it changes in the nested loop
			saver=i;
			for(int j=0;j<connectFour[i].length;j++)
			{
				//another for with same parameters
				//the loop below to get the diagonal shape and if the the diagonal shape isn't completed then the loop is over and j is incremented
				for(int k=j;k<connectFour[i].length;k++)
				{
					if(connectFour[i][k]==color)
					{
						count++;
						if(count==4)
						{
							Methods.winMessage(color, connectFour);
						}
						i--;
					}
					else//start from the beginning if they are not consecutive
					{
						count=0;
						i=saver;
						break;
					}
				}
				i=saver;
			}
			i=saver;
		}
	}
	static public void winDiagonalLeft(char[][] connectFour,char color)
	{
		//base is at bottom right and ascending to the left
		int count=0,saver;
		//3 is the limit no matter how big the connect4 is because if i<3 it can connect 4 diagonally
		for(int i=connectFour.length-1;i>=3;i--)
		{
			//to save i because it changes in the nested loop
			saver=i;
			for(int j=connectFour[i].length-1;j>=0;j--)
			{
				for(int k=j;k>=0;k--)
				{
					if(connectFour[i][k]==color)
					{
						count++;
						if(count==4)
						{
							Methods.winMessage(color, connectFour);
						}
						i--;
					}
					else//start from the beginning if they are not consecutive
					{
						count=0;
						i=saver;
						break;
					}
				}	
			}
			i=saver;
		}
	}

	static public void winMessage(char color,char[][] connectFour)
	{
		//first print the connect four to show the win
		Methods.printConnectFour(connectFour);
		if(color=='R')
		{
			System.out.println("Red wins!");
			System.exit(0);
		}
		else
		{
			System.out.println("Yellow wins!");
			System.exit(0);
		}
	}
}

























