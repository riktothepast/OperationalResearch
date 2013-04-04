package methods;

public class Main {

	public Main(){}
	
	public static void main(String args[]){
			
		MinimumCost MC=new MinimumCost();
		int[][] matrix=new int[4][3];
		int [] production= new int[3];
		int [] destination= new int[4];
		
		//adding data to the matrix?
		matrix[0][0]=2;
		matrix[0][1]=2;
		matrix[0][2]=3;
		matrix[1][0]=3;
		matrix[1][1]=1;
		matrix[1][2]=8;
		matrix[2][0]=5;
		matrix[2][1]=3;
		matrix[2][2]=4;
		matrix[3][0]=6;
		matrix[3][1]=5;
		matrix[3][2]=6;
		
		//destinations
		destination[0]=12;
		destination[1]=8;
		destination[2]=4;
		destination[3]=6;
		
		//production
		production[0]=5;
		production[1]=10;
		production[2]=15;

		System.out.println(MC.Calculate(matrix, destination, production));		
	}
	
}
