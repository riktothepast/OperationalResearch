package gt.edu.usac.investigaciondeoperaciones;

import gt.edu.usac.investigaciondeoperaciones.pgeneratedlayouts.ResolutionMethods;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Resolucion extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resolucion);
		
		Button NorOeste = (Button) findViewById(R.id.Nor);
		NorOeste.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	MainActivity.Method=1;
                startActivity(new Intent(Resolucion.this, ResolutionMethods.class));
                 
            }
		});
		
		Button CMin = (Button) findViewById(R.id.Cmin);
		CMin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	MainActivity.Method=2;
                startActivity(new Intent(Resolucion.this, ResolutionMethods.class));
                 
            }
		});
		
		Button Vogel = (Button) findViewById(R.id.Vogel);
		Vogel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	MainActivity.Method=3;
                startActivity(new Intent(Resolucion.this, ResolutionMethods.class));
            }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resolucion, menu);
		return true;
	}

}
