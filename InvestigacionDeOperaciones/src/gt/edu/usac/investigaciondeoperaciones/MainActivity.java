package gt.edu.usac.investigaciondeoperaciones;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	/*
	 * For Resolution
	 * 1 NorWest
	 * 2 CMin
	 * 3 Vogel
	 * For Optimization
	 * 4 Modi
	 * 5 SStone
	 * 6 Assignment
	 */
	public static int Method=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button ResolutionButton = (Button) findViewById(R.id.ResButton);
		ResolutionButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Method=0;
                 startActivity(new Intent(MainActivity.this, Resolucion.class)); 
            }
		});
		
		Button OptimizationButton = (Button) findViewById(R.id.OptButton);
		OptimizationButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Optimizacion.class)); 
            }
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
