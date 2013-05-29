package gt.edu.usac.investigaciondeoperaciones.IOMeth;


import java.util.ArrayList;

public class SuperModi {
	 String cadenacostos = "";
	//**************************** AQUI EMPIEZA EL CODIGO DE MODI ****************************        
	    static int a[][] = new int[10][10];
	    static int b[][] = new int[10][10];
	    static int a1[][] = new int[10][10];
	    static int x[][] = new int[10][10];
	    static int loop[][] = new int[10][10];
	    static int nbc[][] = new int[10][10];
	    static int s[] = new int[10];
	    static int s1[] = new int[10];
	    static int d[] = new int[10];
	    static int d1[] = new int[10];
	    static int pi[] = new int[10];
	    static int pj[] = new int[10];
	    static int ss = 0, sd = 0, min, min1, min2, maxp = 0;
	    static char sign[][] = new char[10][10];
	    static int u[] = new int[10];
	    static int v[] = new int[10];
	    static int r, c, i, j, k, lc = 0, mi, mj, max = 0, tc = 0;
	    static int flag = 0, opt;

	    /* To get input values */
	   public void input(String  num_filas, String num_columnas, String text_costos,String text_oferta,String text_demanda) {
	        try {
	            r = Integer.parseInt(num_filas);
	            c = Integer.parseInt(num_columnas);
	            String[] filas = text_costos.split(",");
	            for (i = 1; i <= r; i++) {
	                String[] casillas = filas[i - 1].split(" ");
	                for (j = 1; j <= c; j++) {
	                    a[i][j] = Integer.parseInt(casillas[j - 1]);
	                    a1[i][j] = b[i][j] = a[i][j];
	                }
	            }
	            
	            for (i = 0; i < 10; i++) {
	                for (j = 0; j < 10; j++) {
	                    x[i][j] = 0;
	                }
	            }
	            
	            String casillasOferta[] = text_oferta.split(" ");
	            for (i = 1; i <= r; i++) {
	                s[i] = Integer.parseInt(casillasOferta[i - 1]);
	                s1[i] = s[i];
	                ss += s[i];
	            }
	            
	            String casillasDemanda[] = text_demanda.split(" ");
	            for (j = 1; j <= c; j++) {
	                d[j] = Integer.parseInt(casillasDemanda[j - 1]);
	                d1[j] = d[j];
	                sd += d[j];
	            }

	            /* Checking for Balanced */
	            
	            if (ss == sd) {
	            	//AQUI VA UNA ALERTA RIK
	                //JOptionPane.showMessageDialog(this, "Esta balanceado");
	            }
	            if (ss < sd) {
	                r++;
	                s[r] = sd - ss;
	                ss += s[r];
	                for (j = 1; j <= c; j++) {
	                    a[r][j] = 0;
	                }
	                //AQUI VA UNA ALERTA RIK
	                //JOptionPane.showMessageDialog(this, "No esta balanceado");
	            }
	            if (ss > sd) {
	                c++;
	                d[c] = ss - sd;
	                sd += d[c];
	                for (i = 1; i <= r; i++) {
	                    a[i][c] = 0;
	                }
	                //AQUI VA UNA ALERTA RIK
	                //JOptionPane.showMessageDialog(this, "No esta balanceado");
	            }
	        } catch (Exception EX) {
	            //AQUI VA UNA ALERTA RIK
	        	//JOptionPane.showMessageDialog(this, "Algo anda mal con las entradas, verifique por favor");
	        }
	    }

	    /* Function of North West Corner Rule */
	   public static void nwc() {
	        k = 0;
	        i = 1;
	        j = 1;
	        while (k < (r + c) - 1) {
	            if (s[i] > d[j]) {
	                k++;
	                x[i][j] = d[j];
	                s[i] = s[i] - d[j];
	                ss -= d[j];
	                sd -= d[j];
	                d[j] = 0;
	                j++;
	            } else if (s[i] < d[j]) {
	                k++;
	                x[i][j] = s[i];
	                d[j] = d[j] - s[i];
	                ss -= s[i];
	                sd -= s[i];
	                s[i] = 0;
	                i++;
	            } else {
	                k++;
	                x[i][j] = s[i];
	                ss -= s[i];
	                sd -= s[i];
	                s[i] = 0;
	                d[j] = 0;
	                i++;
	                j++;
	            }
	            if ((ss == 0) && (sd == 0)) {
	                break;
	            }
	        }
	    }

	  public  static void modi(){
	    	k = 0;
            for (i = 1; i <= r; i++) {
                for (j = 1; j <= c; j++) {
                    b[i][j] = a[i][j];
                    if (x[i][j] != 0) {
                        k++;
                    }
                }
            }
            for (i = 0; i < r; i++) {
                for (j = 0; j < c; j++) {
                    nbc[i][j] = 0;
                    loop[i][j] = 0;
                }
            }
            
            for (i = 0; i < r; i++) {
                u[i] = 0;
            }
            for (j = 0; j < c; j++) {
                v[j] = 0;
            }
            
            mi = 0;
            mj = 0;
            while (k == r + c - 1) {
                /* FOR BASIC CELL */
                /* Counting the no.of allocations in row & column */
                for (i = 1; i <= r; i++) {
                    for (j = 1; j <= c; j++) {
                        if (x[i][j] != 0) {
                            u[i]++;
                        }
                    }
                }
                for (j = 1; j <= c; j++) {
                    for (i = 1; i <= r; i++) {
                        if (x[i][j] != 0) {
                            v[j]++;
                        }
                    }
                }

                /* Selecting the row or column having max no.of allocations */
                max = 0;
                flag = 0;
                for (i = 1; i <= r; i++) {
                    if (max < u[i]) {
                        max = u[i];
                        mi = i;
                        flag = 1;
                    }
                }
                for (j = 1; j <= c; j++) {
                    if (max < v[j]) {
                        max = v[j];
                        mj = j;
                        flag = 2;
                    }
                }
                for (i = 1; i <= r; i++) {
                    u[i] = 0;
                }
                for (j = 1; j <= c; j++) {
                    v[j] = 0;
                }

                /* Assigning value for u and v */
                if (flag == 1) {
                    for (j = 1; j <= c; j++) {
                        if (x[mi][j] != 0) {
                            v[j] = b[mi][j];
                        }
                    }
                    for (k = 1; k <= r; k++) {
                        for (i = 1; i <= r; i++) {
                            for (j = 1; j <= c; j++) {
                                if (x[i][j] != 0 && v[j] != 0) {
                                    u[i] = b[i][j] - v[j];
                                }
                            }
                        }
                        for (j = 1; j <= c; j++) {
                            for (i = 1; i <= r; i++) {
                                if (x[i][j] != 0 && u[i] != 0) {
                                    v[j] = b[i][j] - u[i];
                                }
                            }
                        }
                    }
                }
                
                if (flag == 2) {
                    for (i = 1; i <= r; i++) {
                        if (x[i][mj] != 0) {
                            u[i] = b[i][mj];
                        }
                    }
                    for (k = 1; k <= r; k++) {
                        for (j = 1; j <= c; j++) {
                            for (i = 1; i <= r; i++) {
                                if (x[i][j] != 0 && u[i] != 0) {
                                    v[j] = b[i][j] - u[i];
                                }
                            }
                        }
                        for (i = 1; i <= r; i++) {
                            for (j = 1; j <= c; j++) {
                                if (x[i][j] != 0 && v[j] != 0) {
                                    u[i] = b[i][j] - v[j];
                                }
                            }
                        }
                    }
                }

                /* FOR NON BASIC CELL */
                max = 0;
                for (i = 1; i <= r; i++) {
                    for (j = 1; j <= c; j++) {
                        if (x[i][j] == 0) {
                            nbc[i][j] = b[i][j] - (u[i] + v[j]);
                            if (max > nbc[i][j]) {
                                max = nbc[i][j];
                                mi = i;
                                mj = j;
                            }
                        }
                    }
                }
                if (max >= 0) {
                    break;
                }

                /* Loop Formation */
                for (i = 1; i <= r; i++) {
                    for (j = 1; j <= c; j++) {
                        if (x[i][j] != 0) {
                            loop[i][j] = 1;
                        } else {
                            loop[i][j] = 0;
                        }
                        sign[i][j] = ' ';
                    }
                }
                
                for (k = 1; k <= r; k++) {
                    for (i = 1; i <= r; i++) {
                        for (j = 1; j <= c; j++) {
                            if (loop[i][j] == 1) {
                                lc++;
                            }
                        }
                        if (lc == 1 && i != mi) {
                            for (j = 1; j <= c; j++) {
                                loop[i][j] = 0;
                            }
                        }
                        lc = 0;
                    }
                    
                    lc = 0;
                    for (j = 1; j <= c; j++) {
                        for (i = 1; i <= r; i++) {
                            if (loop[i][j] == 1) {
                                lc++;
                            }
                        }
                        if (lc == 1 && j != mj) {
                            for (i = 1; i <= r; i++) {
                                loop[i][j] = 0;
                            }
                        }
                        lc = 0;
                    }
                }

                /* Assigning the Sign */
                sign[mi][mj] = '+';
                i = mi;
                for (k = 1; k <= 3; k++) {
                    for (j = 1; j <= c; j++) {
                        if (loop[i][j] == 1 && sign[i][j] == ' ') {
                            sign[i][j] = '-';
                            break;
                        }
                    }
                    for (i = 1; i <= r; i++) {
                        if (loop[i][j] == 1 && sign[i][j] == ' ') {
                            sign[i][j] = '+';
                            break;
                        }
                    }
                }

                /* Finding @ Value */
                min = 9999;
                for (i = 1; i <= r; i++) {
                    for (j = 1; j <= c; j++) {
                        if (sign[i][j] == '-' && min > x[i][j]) {
                            min = x[i][j];
                        }
                    }
                }
                for (i = 1; i <= r; i++) {
                    for (j = 1; j <= c; j++) {
                        if (sign[i][j] == '+') {
                            x[i][j] += min;
                        } else if (sign[i][j] == '-') {
                            x[i][j] -= min;
                        }
                    }
                }

                /* Checking m+n-1 Condition */
                k = 0;
                for (i = 1; i <= r; i++) {
                    for (j = 1; j <= c; j++) {
                        if (x[i][j] != 0) {
                            k++;
                        }
                    }
                }
            } /* End of While */
            
	    }
	    
	    /* Function of Least Cost Method */
	   public static void lcm() {
	        
	        for (i = 1; i <= r; i++) {
	            for (j = 1; j <= c; j++) {
	                b[i][j] = a[i][j];
	            }
	        }
	        
	        k = 0;
	        mi = 1;
	        mj = 1;
	        while (k < (r + c) - 1) {
	            min = 9999;
	            for (i = 1; i <= r; i++) {
	                for (j = 1; j <= c; j++) {
	                    if (min > b[i][j] && b[i][j] != -1) {
	                        min = b[i][j];
	                        mi = i;
	                        mj = j;
	                    }
	                }
	            }
	            if (s[mi] > d[mj]) {
	                k++;
	                x[mi][mj] = d[mj];
	                s[mi] = s[mi] - d[mj];
	                ss -= d[mj];
	                sd -= d[mj];
	                d[mj] = 0;
	                for (i = 1; i <= r; i++) {
	                    b[i][mj] = -1;
	                }
	            }
	            if (s[mi] < d[mj]) {
	                k++;
	                x[mi][mj] = s[mi];
	                d[mj] = d[mj] - s[mi];
	                ss -= s[mi];
	                sd -= s[mi];
	                s[mi] = 0;
	                for (j = 1; j <= c; j++) {
	                    b[mi][j] = -1;
	                }
	            }
	            if (s[mi] == d[mj]) {
	                k++;
	                x[mi][mj] = s[mi];
	                ss -= s[mi];
	                sd -= s[mi];
	                s[mi] = 0;
	                d[mj] = 0;
	                for (i = 1; i <= r; i++) {
	                    b[i][mj] = -1;
	                }
	                for (j = 1; j <= c; j++) {
	                    b[mi][j] = -1;
	                }
	            }
	            if ((ss == 0) && (sd == 0)) {
	                break;
	            }
	        }
	    }

	    /* Function of Vogel's Approximation Method */
	   public static void vam() {
	        for (i = 1; i <= r; i++) {
	            for (j = 1; j <= c; j++) {
	                b[i][j] = a[i][j];
	            }
	        }
	        
	        k = 0;
	        mi = 0;
	        mj = 0;
	        while (k < (r + c) - 1) {

	            /* Selecting Penalty Value for Row */
	            for (i = 1; i <= r; i++) {
	                min1 = 9999;
	                min2 = 9999;
	                for (j = 1; j <= c; j++) {
	                    if (min1 > b[i][j] && b[i][j] != -1) {
	                        min1 = b[i][j];
	                        mj = j;
	                    }
	                }
	                for (j = 1; j <= c; j++) {
	                    if (min2 > b[i][j] && b[i][j] != -1 && min2 >= min1 && j != mj) {
	                        min2 = b[i][j];
	                    }
	                }
	                pi[i] = min2 - min1;
	                if (pi[i] > 9000) {
	                    pi[i] = min1;
	                }
	            }
	            /* Selecting Penalty Value for Column */
	            for (j = 1; j <= c; j++) {
	                min1 = 9999;
	                min2 = 9999;
	                for (i = 1; i <= r; i++) {
	                    if (min1 > b[i][j] && b[i][j] != -1) {
	                        min1 = b[i][j];
	                        mi = i;
	                    }
	                }
	                for (i = 1; i <= r; i++) {
	                    if (min2 > b[i][j] && b[i][j] != -1 && min2 >= min1 && i != mi) {
	                        min2 = b[i][j];
	                    }
	                }
	                pj[j] = min2 - min1;
	                if (pj[j] > 9000) {
	                    pj[j] = min1;
	                }
	            }
	            /* Selecting Max. Penalty Value */
	            maxp = 0;
	            flag = 0;
	            for (i = 1; i <= r; i++) {
	                if (maxp < pi[i]) {
	                    maxp = pi[i];
	                    mi = i;
	                    flag = 1;
	                }
	            }
	            
	            for (j = 1; j <= c; j++) {
	                if (maxp < pj[j]) {
	                    maxp = pj[j];
	                    mj = j;
	                    flag = 2;
	                }
	            }

	            /* Selecting min value in max penalty row or column */
	            min1 = 9999;
	            if (flag == 1) {
	                for (j = 1; j <= c; j++) {
	                    if (min1 > b[mi][j] && b[mi][j] != -1) {
	                        min1 = b[mi][j];
	                        mj = j;
	                    }
	                }
	            }
	            
	            if (flag == 2) {
	                for (i = 1; i <= r; i++) {
	                    if (min1 > b[i][mj] && b[i][mj] != -1) {
	                        min1 = b[i][mj];
	                        mi = i;
	                    }
	                }
	            }
	            /* Allocation */
	            if (s[mi] > d[mj]) {
	                k++;
	                x[mi][mj] = d[mj];
	                s[mi] = s[mi] - d[mj];
	                ss -= d[mj];
	                sd -= d[mj];
	                d[mj] = 0;
	                for (i = 1; i <= r; i++) {
	                    b[i][mj] = -1;
	                }
	            }
	            if (s[mi] < d[mj]) {
	                k++;
	                x[mi][mj] = s[mi];
	                d[mj] = d[mj] - s[mi];
	                ss -= s[mi];
	                sd -= s[mi];
	                s[mi] = 0;
	                for (j = 1; j <= c; j++) {
	                    b[mi][j] = -1;
	                }
	            }
	            if (s[mi] == d[mj]) {
	                k++;
	                x[mi][mj] = s[mi];
	                ss -= s[mi];
	                sd -= s[mi];
	                s[mi] = 0;
	                d[mj] = 0;
	                for (i = 1; i <= r; i++) {
	                    b[i][mj] = -1;
	                }
	                for (j = 1; j <= c; j++) {
	                    b[mi][j] = -1;
	                }
	            }
	            if ((ss == 0) && (sd == 0)) {
	                break;
	            }
	        }
	    }

	   public String display() {
	    	cadenacostos = "";
	    	int littlecontador = 0;
	        tc = 0;
	        for (i = 1; i <= r; i++) {
	            for (j = 1; j <= c; j++) {
	            	if(littlecontador!=0){
		            	cadenacostos += " + "+a[i][j]+"("+x[i][j]+")";
		                tc = tc + (a[i][j] * x[i][j]);
	            	}else{
		            	cadenacostos += a[i][j]+"("+x[i][j]+")";
		                tc = tc + (a[i][j] * x[i][j]);	            		
	            	}
	            	littlecontador++;
	            }
	        }
	        cadenacostos += " = "+tc+" \n";

	        return cadenacostos;
	    }
	//**************************** AQUI FINALIZA EL CODIGO DE MODI **************************** 

	    private static boolean SaltoHorizontal(ArrayList<Punto> Circuito, int FilaActual, int ColumnaActual, Punto PuntoInicial) {
	        for (int j = 1; j <= c; j++) {
	          if ((j != ColumnaActual) && (x[FilaActual][j] != 0)) {
	            if (j == PuntoInicial.getColumna()) {
	              Punto Nuevo = new Punto(FilaActual, j);
	              Circuito.add(Nuevo);
	              return true;
	            }
	            if (SaltoVertical(Circuito, FilaActual, j, PuntoInicial)) {
	              Punto Nuevo = new Punto(FilaActual, j);
	              Circuito.add(Nuevo);
	              return true;
	            }
	          }
	        }
	        return false;
	      }
	    
	      private static boolean SaltoVertical(ArrayList<Punto> Circuito, int FilaActual, int ColumnaActual, Punto PuntoInicial) {
	        for (int i = 1; i <= r; i++) {
	          if ((i != FilaActual) && (x[i][ColumnaActual] != 0) && 
	            (SaltoHorizontal(Circuito, i, ColumnaActual, PuntoInicial))) {
	            Punto Nuevo = new Punto(i, ColumnaActual);
	            Circuito.add(Nuevo);
	            return true;
	          }
	        }
	    
	        return false;
	      }
	    
	      private static int CalcularCostoCircuito(ArrayList<Punto> Circuito) {
	        int Costo = 0;
	        for (int i = 0; i < Circuito.size(); i += 2) {
	          Costo += a[((Punto)Circuito.get(i)).getFila()][((Punto)Circuito.get(i)).getColumna()];
	        }
	        for (int i = 1; i < Circuito.size(); i += 2) {
	          Costo -= a[((Punto)Circuito.get(i)).getFila()][((Punto)Circuito.get(i)).getColumna()];
	        }
	        return Costo;
	      }
	    
	      private static void ReasignarValores(ArrayList<Punto> Circuito) {
	        int ValorBase = x[((Punto)Circuito.get(1)).getFila()][((Punto)Circuito.get(1)).getColumna()];
	        for (int i = 3; i < Circuito.size(); i += 2) {
	          if (x[((Punto)Circuito.get(i)).getFila()][((Punto)Circuito.get(i)).getColumna()] < ValorBase) {
	            ValorBase = x[((Punto)Circuito.get(i)).getFila()][((Punto)Circuito.get(i)).getColumna()];
	          }
	        }
	    
	   /*  74 */     for (int i = 0; i < Circuito.size(); i += 2) {
	   /*  75 */       x[((Punto)Circuito.get(i)).getFila()][((Punto)Circuito.get(i)).getColumna()] += ValorBase;
	        }
	   /*  77 */     for (int i = 1; i < Circuito.size(); i += 2)
	   /*  78 */       x[((Punto)Circuito.get(i)).getFila()][((Punto)Circuito.get(i)).getColumna()] -= ValorBase;
	      }
	    
	      public static  void banquillo()
	      {
	   /*  83 */     System.out.println("---------------------DATOS DE ENTRADA---------------------\n");
	   /*  84 */     ImprimirCostos();
	   /*  85 */     ImprimirValores();
	   /*  86 */     ImprimirCT();
	    
	   /*  88 */     System.out.println();
	   /*  89 */     EstablecerCircuitos(0);
	   /*  90 */     System.out.println();
	    
	   /*  92 */     System.out.println("---------------------DATOS DE SALIDA---------------------\n");
	   /*  93 */     ImprimirValores();
	   /*  94 */     ImprimirCT();
	      }
	    
	      private static void EstablecerCircuitos(int ContadorCorrida) {
	   /*  98 */     System.out.println("\n\n                     CORRIDA #" + ContadorCorrida + "\n");
	   /*  99 */     int tmpCostoCircuito = 0;
	   /* 100 */     ArrayList<Punto> tmpCircuito = null;
	   /* 101 */     int ContadorCircuito = 0;
	   /* 102 */     for (int i = 1; i <= r; i++) {
	   /* 103 */       for (int j = 1; j <= c; j++) {
	   /* 104 */         if (x[i][j] == 0) {
	   /* 105 */           ArrayList<Punto> Circuito = new ArrayList<Punto>();
	   /* 106 */           Punto PuntoInicial = new Punto(i, j);
	   /* 107 */           Circuito.add(PuntoInicial);
	   /* 108 */           SaltoHorizontal(Circuito, i, j, PuntoInicial);
	   /* 109 */           int CostoCircuito = CalcularCostoCircuito(Circuito);
	   /* 110 */           if (CostoCircuito < tmpCostoCircuito) {
	   /* 111 */             tmpCostoCircuito = CostoCircuito;
	   /* 112 */             tmpCircuito = Circuito;
	              }
	   /* 114 */           ImprimirCircuito(Circuito, CostoCircuito, ContadorCircuito++);
	            }
	          }
	        }
	   /* 118 */     if ((tmpCostoCircuito < 0) && (tmpCircuito != null)) {
	   /* 119 */       ReasignarValores(tmpCircuito);
	   /* 120 */       EstablecerCircuitos(ContadorCorrida + 1);
	        }
	      }
	    
	      public static void ImprimirCostos() {
	   /* 125 */     System.out.println("                     MATRIZ DE COSTOS");
	   /* 126 */     ImprimirMatriz(a);
	      }
	    
	      public static void ImprimirValores() {
	   /* 130 */     System.out.println("                     MATRIZ DE VALORES");
	   /* 131 */     ImprimirMatriz(x);
	      }
	    
	      public static void ImprimirCT() {
	   /* 135 */     System.out.println("EL COSTO TOTAL ES: " + CalcularCT());
	      }
	    
	      private static void ImprimirCircuito(ArrayList<Punto> Circuito, int CostoCircuito, int ContadorCircuito) {
	   /* 139 */     String Salida = "";
	   /* 140 */     for (int i = 1; i < Circuito.size(); i++) {
	   /* 141 */       Salida = Salida + "[" + ((Punto)Circuito.get(i)).getFila() + "," + ((Punto)Circuito.get(i)).getColumna() + "]->";
	        }
	   /* 143 */     System.out.print("CIRCUITO #" + ContadorCircuito + ": " + Salida + "[" + ((Punto)Circuito.get(0)).getFila() + "," + ((Punto)Circuito.get(0)).getColumna() + "]");
	   /* 144 */     System.out.println(" => COSTO DEL CIRCUITO: " + CostoCircuito);
	      }
	    
	      public static int CalcularCT() {
	   /* 148 */     int Salida = 0;
	   /* 149 */     for (int i = 1; i <= r; i++) {
	   /* 150 */       for (int j = 1; j <= c; j++) {
	   /* 151 */         Salida += a[i][j] * x[i][j];
	          }
	        }
	   /* 154 */     return Salida;
	      }
	    
	      private static void ImprimirMatriz(int[][] Matriz)
	      {
	   /* 159 */     for (int i = 1; i <= r; i++) {
	   /* 160 */       String Salida = "|";
	   /* 161 */       for (int j = 1; j <= c; j++) {
	   /* 162 */         Salida = Salida + Matriz[i][j] + "|";
	          }
	   /* 164 */       System.out.println(Salida);
	        }
	   /* 166 */     System.out.println();
	      }
	    
	      public static int[][] geta() {
	   /* 170 */     return a;
	      }
	    
	      public static int[][] getx() {
	   /* 174 */     return x;
	      }
	    
	      public static void seta(int[][] a) {
	   /* 178 */     SuperModi.a= a;
	      }
	    
	      public static void setx(int[][] x) {
	   /* 182 */     SuperModi.x = x;
	      }
	    }

/*     */ class Punto
/*     */ {
/*     */   private int Fila;
/*     */   private int Columna;
/*     */ 
/*     */   public Punto(int Fila, int Columna)
/*     */   {
/* 190 */     this.Fila = Fila;
/* 191 */     this.Columna = Columna;
/*     */   }
/*     */ 
/*     */   public int getFila() {
/* 195 */     return this.Fila;
/*     */   }
/*     */ 
/*     */   public void setFila(int Fila) {
/* 199 */     this.Fila = Fila;
/*     */   }
/*     */ 
/*     */   public int getColumna() {
/* 203 */     return this.Columna;
/*     */   }
/*     */ 
/*     */   public void setColumna(int Columna) {
/* 207 */     this.Columna = Columna;
/*     */   }
/*     */ }



