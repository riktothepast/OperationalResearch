package gt.edu.usac.investigaciondeoperaciones.pgeneratedlayouts;

import gt.edu.usac.investigaciondeoperaciones.MainActivity;
import gt.edu.usac.investigaciondeoperaciones.R;

import gt.edu.usac.investigaciondeoperaciones.IOMeth.Asignacion;
import gt.edu.usac.investigaciondeoperaciones.IOMeth.SuperModi;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResolutionMethods extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resolution_methods);
		TextView txtview;
		System.out.println(MainActivity.Method);
		switch(MainActivity.Method){
		case 1:
			txtview=(TextView) findViewById(R.id.resText);
			txtview.setText("Esquina Nor Oeste");
			break;
		case 2:
			txtview=(TextView) findViewById(R.id.resText);
			txtview.setText("Costo Minimo");
			break;
		case 3:
			txtview=(TextView) findViewById(R.id.resText);
			txtview.setText("Vogel");
			break;
		case 4:
			txtview=(TextView) findViewById(R.id.resText);
			txtview.setText("MODI");
			break;
		case 5:
			txtview=(TextView) findViewById(R.id.resText);
			txtview.setText("Stepping Stone");
			break;
		case 6:
			txtview=(TextView) findViewById(R.id.resText);
			txtview.setText("Hungarian");
			break;
		default:
			txtview=(TextView) findViewById(R.id.resText);
			txtview.setText("Hello World");
			break;
		}
		
		Button OptimizationButton = (Button) findViewById(R.id.Resultado);
		OptimizationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	SuperModi supermod= new SuperModi();
    			TextView txtv;
    			final Dialog dialog;
    			TextView txtVw;
    			Button dialogButton;
    			String filas, columnas,costos,oferta,demanda;
            	switch(MainActivity.Method){
        		case 1: //NorWest
        			txtv=(TextView) findViewById(R.id.Filas);
        			filas=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Columnas);
        			columnas=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Matriz);
        			costos=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Produccion);
        			oferta=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Destinos);
        			demanda=txtv.getText().toString();
        			supermod.input(filas, columnas, costos, oferta, demanda);
        			SuperModi.nwc();
        			dialog = new Dialog(ResolutionMethods.this);
                    dialog.setContentView(R.layout.activity_results);
                    dialog.setTitle("Resultado de Esquina Nor Oeste");
                    txtVw=(TextView) dialog.findViewById(R.id.ResTxt);
                    txtVw.setText(supermod.display());
                    dialogButton = (Button) dialog.findViewById(R.id.DisRes);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    dialog.dismiss();
                            }
                    });
                    dialog.show();
        			break;
        		case 2: //CMInimo
        			txtv=(TextView) findViewById(R.id.Filas);
        			filas=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Columnas);
        			columnas=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Matriz);
        			costos=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Produccion);
        			oferta=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Destinos);
        			demanda=txtv.getText().toString();
        			supermod.input(filas, columnas, costos, oferta, demanda);
        			SuperModi.lcm();
        			dialog = new Dialog(ResolutionMethods.this);
                    dialog.setContentView(R.layout.activity_results);
                    dialog.setTitle("Resultado de Costo Minimo");
                    txtVw=(TextView) dialog.findViewById(R.id.ResTxt);
                    txtVw.setText(supermod.display());
                    dialogButton = (Button) dialog.findViewById(R.id.DisRes);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    dialog.dismiss();
                            }
                    });
                    dialog.show();
        			break;
        		case 3:	//Vogel
        			txtv=(TextView) findViewById(R.id.Filas);
        			filas=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Columnas);
        			columnas=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Matriz);
        			costos=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Produccion);
        			oferta=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Destinos);
        			demanda=txtv.getText().toString();
        			supermod.input(filas, columnas, costos, oferta, demanda);
        			SuperModi.vam();
        			dialog = new Dialog(ResolutionMethods.this);
                    dialog.setContentView(R.layout.activity_results);
                    dialog.setTitle("Resultado de Vogel");
                    txtVw=(TextView) dialog.findViewById(R.id.ResTxt);
                    txtVw.setText(supermod.display());
                    dialogButton = (Button) dialog.findViewById(R.id.DisRes);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    dialog.dismiss();
                            }
                    });
                    dialog.show();
        			break;
        		case 4:	//MODI
        			txtv=(TextView) findViewById(R.id.Filas);
        			filas=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Columnas);
        			columnas=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Matriz);
        			costos=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Produccion);
        			oferta=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Destinos);
        			demanda=txtv.getText().toString();
        			supermod.input(filas, columnas, costos, oferta, demanda);
        			SuperModi.lcm();
        			SuperModi.modi();
        			dialog = new Dialog(ResolutionMethods.this);
                    dialog.setContentView(R.layout.activity_results);
                    dialog.setTitle("Resultado de MODI");
                    txtVw=(TextView) dialog.findViewById(R.id.ResTxt);
                    txtVw.setText(supermod.display());
                    dialogButton = (Button) dialog.findViewById(R.id.DisRes);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    dialog.dismiss();
                            }
                    });     
                    dialog.show();
                    break;
        		case 5:	//SSTone
        			txtv=(TextView) findViewById(R.id.Filas);
        			filas=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Columnas);
        			columnas=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Matriz);
        			costos=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Produccion);
        			oferta=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Destinos);
        			demanda=txtv.getText().toString();
        			supermod.input(filas, columnas, costos, oferta, demanda);
        			SuperModi.lcm();
        			SuperModi.banquillo();
        			dialog = new Dialog(ResolutionMethods.this);
                    dialog.setContentView(R.layout.activity_results);
                    dialog.setTitle("Resultado de Stepping Stone");
                    txtVw=(TextView) dialog.findViewById(R.id.ResTxt);
                    txtVw.setText(supermod.display());
                    dialogButton = (Button) dialog.findViewById(R.id.DisRes);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    dialog.dismiss();
                            }
                    });   
                    dialog.show();
        			break;
        		case 6: //Hungaro
        			txtv=(TextView) findViewById(R.id.Filas);
        			filas=txtv.getText().toString();
        			txtv=(TextView) findViewById(R.id.Columnas);
        			columnas=txtv.getText().toString();
        			int rows, columns;
        			rows=Integer.parseInt(filas);
        			columns=Integer.parseInt(columnas);
        			txtv=(TextView) findViewById(R.id.Matriz);
        			costos=txtv.getText().toString();   
        			if(rows!=columns){
        				dialog = new Dialog(ResolutionMethods.this);
                        dialog.setContentView(R.layout.activity_results);
                        dialog.setTitle("Error!!!");
                        txtVw=(TextView) dialog.findViewById(R.id.ResTxt);
                        txtVw.setText("El numero de filas y columnas no coinciden");
                        dialogButton = (Button) dialog.findViewById(R.id.DisRes);
                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                        dialog.dismiss();
                                }
                        });   
                        dialog.show();
                        break;
        			}
        			Asignacion asign=new Asignacion();
        			dialog = new Dialog(ResolutionMethods.this);
                    dialog.setContentView(R.layout.activity_results);
                    dialog.setTitle("Por Asignacion (Hungaro)");
                    txtVw=(TextView) dialog.findViewById(R.id.ResTxt);
                    txtVw.setText(asign.setResolve(costos, rows));
                    dialogButton = (Button) dialog.findViewById(R.id.DisRes);
                    // if button is clicked, close the custom dialog
                    dialogButton.setOnClickListener(new OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                    dialog.dismiss();
                            }
                    });   
                    dialog.show();
        			break;
        		}            	
            }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resolution_methods, menu);
		return true;
	}

}
