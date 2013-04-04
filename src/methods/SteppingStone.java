package methods;

public class SteppingStone implements ShippingSolver {
	private double [][] matrix_data;
	private double [] destinations;
	private double [] production;
	private int n, m;
	private double ellipsis = 0.001;
	private String result;
	
	public String Calculate(int values[][],int dest[],int prod[]){
		result = "";
		matrix_data = new double [dest.length][prod.length];
		destinations = new double [dest.length];
		production  = new double [prod.length];
		cloneIntMatrixToDoubleMatrix(values, matrix_data);
		cloneRowIntToDouble(dest, destinations);
		cloneRowIntToDouble(prod, production);
		n = prod.length;
		m = dest.length;
		//To avoid degeneracy we add a small interference to the supply.
		destinations[0] += ellipsis;
		
		
		return result;
	}
	
	private void cloneRowIntToDouble(int iarray[], double oarray[]){
		for (int i=0; i<iarray.length; i++){
			oarray[i] = (double) iarray[i];
		}
	}
	
	private void cloneIntMatrixToDoubleMatrix(int imatrix[][], double omatrix[][]){
		for (int row=0; row<imatrix.length; row++){
			for (int col=0; col<imatrix[row].length; col++){
				omatrix[row][col] = (double) imatrix[row][col];
			}
		}
	}

}
