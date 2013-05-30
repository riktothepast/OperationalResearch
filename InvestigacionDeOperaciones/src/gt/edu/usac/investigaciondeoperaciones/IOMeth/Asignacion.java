package gt.edu.usac.investigaciondeoperaciones.IOMeth;

public class Asignacion {
    int[][] costos;
    int numCol;
    int numFil;
    
    public Asignacion() {
    }

    public byte[][] resolver() {
        munkres m = new munkres();
        return m.solve();
    }
    private static byte NOT_MARKED = 0;
    private static byte STARRED_ZERO = 1;
    private static byte PRIMED_ZERO = 2;

    public class munkres {

        byte[][] marca;        //MARK ZEROS
        int[] rowIsCovered;
        int[] colIsCovered;

        public byte[][] solve() {
            marca = new byte[numFil][];
            for (int i = 0; i < numFil; i++) {
                marca[i] = new byte[numCol];
            }

            rowIsCovered = new int[numFil];
            colIsCovered = new int[numCol];

            reduceRows();
            starSomeZeros();
            while (true) {
                if (isDone()) {
                    break;
                }
                pair p = findAndPrimeUncoveredZero();
                new step5().main(p);
            }

            return marca;
        }

        public void reduceRows() {
            int minval;

            for (int i = 0; i < numFil; i++) {
                minval = costos[i][0];
                for (int j = 1; j < numCol; j++) {
                    if (minval > costos[i][j]) {
                        minval = costos[i][j];
                    }
                }

                for (int j = 0; j < numCol; j++) {
                    costos[i][j] -= minval;
                }

            }
        }

        public void starSomeZeros() {

            boolean[] colIsCovered = new boolean[numCol];

            A:
            for (int i = 0; i < numFil; i++) {
                for (int j = 0; j < numCol; j++) {
                    if (!colIsCovered[j] && costos[i][j] == 0) {
                        marca[i][j] = STARRED_ZERO;
                        colIsCovered[j] = true;
                        continue A;
                    }
                }
            }
        }

        public boolean isDone() {
            int count = 0;
            for (int j = 0; j < numCol; j++) {
                for (int i = 0; i < numFil; i++) {
                    if (marca[i][j] == STARRED_ZERO) {
                        colIsCovered[j] = 1;
                        count++;
                        break;
                    }
                }
            }

            if (count >= numFil) {
                return true;
            }
            return false;
        }

        public pair findAndPrimeUncoveredZero() {
            s4:
            while (true) {
                pair p = findUncoveredZero();
                if (p == null) {
                    makeSomeZeros();
                    continue s4;
                }

                marca[p.row][p.col] = PRIMED_ZERO;

                for (int j = 0; j < numCol; j++) {
                    if (marca[p.row][j] == STARRED_ZERO) {
                        rowIsCovered[p.row] = 1;
                        colIsCovered[j] = 0;
                        continue s4;
                    }
                }

                return p;
            }
        }

        public pair findUncoveredZero() {
            for (int i = 0; i < numFil; i++) {
                for (int j = 0; j < numCol; j++) {
                    if (costos[i][j] == 0 && rowIsCovered[i] == 0 && colIsCovered[j] == 0) {
                        return new pair(i, j);
                    }
                }
            }
            return null;
        }

        public void makeSomeZeros() {
            int minval = Integer.MAX_VALUE;
            for (int i = 0; i < numFil; i++) {
                if (rowIsCovered[i] == 1) {
                    continue;
                }
                for (int j = 0; j < numCol; j++) {
                    if (colIsCovered[j] == 0) {
                        if (minval > costos[i][j]) {
                            minval = costos[i][j];
                        }
                    }
                }
            }

            for (int i = 0; i < numFil; i++) {
                for (int j = 0; j < numCol; j++) {
                    if (rowIsCovered[i] == 1) {
                        costos[i][j] += minval;
                    }
                    if (colIsCovered[j] == 0) {
                        costos[i][j] -= minval;
                    }
                }
            }
        }

        class step5 {

            pair[] path = new pair[numFil * 2];

            public int find_star_in_col(int c) {
                for (int i = 0; i < numFil; i++) {
                    if (marca[i][c] == STARRED_ZERO) {
                        return i;
                    }
                }
                return -1;
            }

            public int find_prime_in_row(int r) {
                for (int j = 0; j < numCol; j++) {
                    if (marca[r][j] == PRIMED_ZERO) {
                        return j;
                    }
                }
                return -1;
            }

            public void clear_covers() {
                for (int i = 0; i < numFil; i++) {
                    rowIsCovered[i] = 0;
                    colIsCovered[i] = 0;
                }
            }

            public void clear_primes() {
                for (int i = 0; i < numFil; i++) {
                    for (int j = 0; j < numCol; j++) {
                        if (marca[i][j] == PRIMED_ZERO) {
                            marca[i][j] = NOT_MARKED;
                        }
                    }
                }
            }

            public void main(pair p) {
                int count = 0;
                path[count] = p;

                while (true) {
                    int r = find_star_in_col(path[count].col);
                    if (r == -1) {
                        break;
                    }

                    count++;
                    path[count] = new pair(r, path[count - 1].col);

                    int c = find_prime_in_row(path[count].row);

                    count++;
                    path[count] = new pair(path[count - 1].row, c);
                }

                for (int i = 0; i <= count; i++) {
                    if (marca[path[i].row][path[i].col] == STARRED_ZERO) {
                        marca[path[i].row][path[i].col] = NOT_MARKED;
                    } else {
                        marca[path[i].row][path[i].col] = STARRED_ZERO;
                    }
                }

                clear_covers();
                clear_primes();
            }
        }
    }
    
    public String setResolve(String data, int num){
    	int n = num;
    	int[][] matrix=new int[n][n];
        String[] filas = data.split(",");
        for (int i = 0; i < n; i++) {
            String[] casillas = filas[i].split(" ");
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(casillas[j]);
            }
        }
        numCol = matrix[0].length;
        numFil = matrix.length;
        if (numFil > numCol) {
            throw new IllegalArgumentException("Expecting more rows than columns");
        }
        this.costos = matrix;
        byte[][] sol = resolver();
        int coste = 0;
        String salida = "";
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (sol[i][j] == 1) {
                    coste += matrix[i][j];
                    salida += ("Origen" + (i + 1) + "->Destino" + (j + 1) + " (" + matrix[i][j] + ")") + "\n";
                }
            }
        }
        salida += ("Costo total = " + coste);
        return salida;        
    }
    
}

class pair {

    int row;
    int col;

    public pair(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
 
}