package methods;

/*
 * Operational Research Minimum Cost Implementation
 * 2013 Ricardo Illescas
 */
/*MIT LICENSE

Copyright (c) <2012> <Ricardo Illescas, setfalse.blogspot.com, rik@502studios.net>

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
public class MinimumCost {
	private int [][] matrix_data;
	private int [][] asignments;
	private int [] destinations;
	private int [] production;
	private String result="";
	private Vector2 asignPos;
	private int minvalue,total;
	boolean asign=false;
	public MinimumCost(){}
	
	public String Calculate(int values[][],int dest[],int prod[]){
		this.matrix_data=values.clone();
		asignments=new int[values.length][values[0].length];
		this.destinations=dest.clone();
		this.production=prod.clone();
		PerformCalculus();
		return ArrageString();
	}
	/*
	 * Checks the minimum value of the matrix and keeps it's position; 
	 * adds the quantity of the production to the desired destination, when production reaches zero returns a computation of the minimum cost.
	 */
	private void PerformCalculus(){
		minvalue=9999999;
		while(ProductAvailable()){
			for (int x = 0; x < matrix_data.length; x++) {
				for (int y = 0; y < matrix_data[0].length; y++) {
					if(matrix_data[x][y]<=minvalue){
						//is possible to asign this space?
						if(!hasAsigned(x,y)&&production[y]>0&&destinations[x]>0){
							if(production[y]>0){
								minvalue=matrix_data[x][y];
								asignPos=new Vector2(x,y);
								asign=true;
							}
						}else{
							minvalue=9999999;
						}
					}
				}
			}
			if(asign){
			Asign(asignPos);}
			asign=false;
		}
	}
	
	private boolean ProductAvailable(){
		for(int x=0;x<production.length;x++){
			if(production[x]>0){
				return true;
			}
		}
		return false;
	}
	
	private boolean hasAsigned(int x, int y){
		if(asignments[x][y]!=0){return true;}
		return false;
	}
	
	private void Asign(Vector2 asp){
		int dx=destinations[asp.x];
		int dy=production[asp.y];
			if(dy>dx){
				asignments[asp.x][asp.y]=dx;
				destinations[asp.x]=0;
				production[asp.y]-=dx;
			}else if(dy<dx){
				asignments[asp.x][asp.y]=dy;				
				destinations[asp.x]-=dy;
				production[asp.y]=0;
			}else if(dx==dy){
				asignments[asp.x][asp.y]=dy;				
				destinations[asp.x]=0;
				production[asp.y]=0;
			}
	}
	
	private String ArrageString(){
		for (int x = 0; x < asignments.length; x++) {
			for (int y = 0; y < asignments[0].length; y++) {
				if(asignments[x][y]!=0){
					result+="+ ("+matrix_data[x][y]+")"+"("+asignments[x][y]+") ";
					total+=+matrix_data[x][y]*asignments[x][y];
				}
			}
		}
		return "CT = "+result+": "+total;
	}
	
}

