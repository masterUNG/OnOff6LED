package appewtc.masterung.onoff6led;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.ToggleButton;

import ioio.lib.api.DigitalOutput;
import ioio.lib.api.exception.ConnectionLostException;
import ioio.lib.util.BaseIOIOLooper;
import ioio.lib.util.IOIOLooper;
import ioio.lib.util.android.IOIOActivity;


public class MainActivity extends IOIOActivity {

    private ToggleButton myToggleButton1, myToggleButton2, myToggleButton3, myToggleButton4, myToggleButton5, myToggleButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialWidget();

    }   // onCreate

    private void initialWidget() {
        myToggleButton1 = (ToggleButton) findViewById(R.id.toggleButton);
        myToggleButton2 = (ToggleButton) findViewById(R.id.toggleButton2);
        myToggleButton3 = (ToggleButton) findViewById(R.id.toggleButton3);
        myToggleButton4 = (ToggleButton) findViewById(R.id.toggleButton4);
        myToggleButton5 = (ToggleButton) findViewById(R.id.toggleButton5);
        myToggleButton6 = (ToggleButton) findViewById(R.id.toggleButton6);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class Looper extends BaseIOIOLooper {

        private DigitalOutput myOutput1, myOutput2, myOutput3, myOutput4, myOutput5, myOutput6;

        @Override
        protected void setup() throws ConnectionLostException, InterruptedException {
//            super.setup();
            myOutput1 = ioio_.openDigitalOutput(1, false);
            myOutput2 = ioio_.openDigitalOutput(2, false);
            myOutput3 = ioio_.openDigitalOutput(3, false);
            myOutput4 = ioio_.openDigitalOutput(4, false);
            myOutput5 = ioio_.openDigitalOutput(5, false);
            myOutput6 = ioio_.openDigitalOutput(6, false);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(MainActivity.this, "Can Connected IOIO Board", Toast.LENGTH_LONG).show();
                }
            });

        }

        @Override
        public void loop() throws ConnectionLostException, InterruptedException {
//            super.loop();
            myOutput1.write(!myToggleButton1.isChecked());
            myOutput2.write(!myToggleButton2.isChecked());
            myOutput3.write(!myToggleButton3.isChecked());
            myOutput4.write(!myToggleButton4.isChecked());
            myOutput5.write(!myToggleButton5.isChecked());
            myOutput6.write(!myToggleButton6.isChecked());

        }
    }   // Looper Class

    protected IOIOLooper createIOIOLooper() {

        return new Looper();
    }


}   // Main Class
