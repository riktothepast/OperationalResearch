package gt.edu.usac.investigaciondeoperaciones;

import gt.edu.usac.investigaciondeoperaciones.pgeneratedlayouts.ResolutionMethods;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Optimizacion extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_optimizacion);
		
		Button MODI = (Button) findViewById(R.id.Modi);
		MODI.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	MainActivity.Method=4;
                startActivity(new Intent(Optimizacion.this, ResolutionMethods.class));
            }
		});
		
		Button SStone = (Button) findViewById(R.id.SStone);
		SStone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	MainActivity.Method=5;
                startActivity(new Intent(Optimizacion.this, ResolutionMethods.class));
            }
		});
		
		Button Hun = (Button) findViewById(R.id.Hun);
		Hun.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	MainActivity.Method=6;
                startActivity(new Intent(Optimizacion.this, ResolutionMethods.class));
            }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.optimizacion, menu);
		return true;
	}

}
