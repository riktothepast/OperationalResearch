package gt.edu.usac.investigaciondeoperaciones.pgeneratedlayouts;

import gt.edu.usac.investigaciondeoperaciones.MainActivity;
import gt.edu.usac.investigaciondeoperaciones.R;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class Results extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resolution_methods);
		TextView txtview;
		System.out.println(MainActivity.Method);
		switch(MainActivity.Method){
		case 1:
			txtview =(TextView) findViewById(R.id.resText);
			txtview.setText("Esquina Nor Oeste");
		case 2:
			txtview =(TextView) findViewById(R.id.resText);
			txtview.setText("Costo Minimo");
		case 3:
			txtview =(TextView) findViewById(R.id.resText);
			txtview.setText("Vogel Aprox. Method");
		default:
			txtview=(TextView) findViewById(R.id.resText);
			txtview.setText("Hello World");
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.optimization_methods, menu);
		return true;
	}

}
