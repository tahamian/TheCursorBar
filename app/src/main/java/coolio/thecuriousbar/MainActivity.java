package coolio.thecuriousbar;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Console;


public class MainActivity extends AppCompatActivity {

    Button btn1, btn2, btn3, btn4, btn5, btn6, connectButton;
    ImageButton Notepad, Explorer, CMD;
    TextView response;
    EditText editTextAddress, editTextPort;
    Client myClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        connectButton = (Button) findViewById(R.id.connectButton);
        Notepad = (ImageButton) findViewById(R.id.notepad);
        Explorer = (ImageButton) findViewById(R.id.Explorer);
        CMD = (ImageButton) findViewById(R.id.cmd);

        response = (TextView) findViewById(R.id.responseTextView);
        editTextAddress = (EditText) findViewById(R.id.addressEditText);
        editTextPort = (EditText) findViewById(R.id.portEditText);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    myClient = new Client(editTextAddress.getText()
                            .toString(), Integer.parseInt(editTextPort
                            .getText().toString()), response);
                    myClient.execute();
                    Toast.makeText(getApplicationContext(),
                            "Connected", Toast.LENGTH_LONG).show();
                    editTextAddress.setVisibility(View.GONE);
                    editTextPort.setVisibility(View.GONE);
                    connectButton.setVisibility(View.GONE);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        myClient.WriteToSocket("ctrl+c");
                    }
                }).start();

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        myClient.WriteToSocket("ctrl+v");
                    }
                }).start();

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        myClient.WriteToSocket("ctrl+esc");
                    }
                }).start();

            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        myClient.WriteToSocket("ctrl+a");
                    }
                }).start();

            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        myClient.WriteToSocket("ctrl+z");
                    }
                }).start();

            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        myClient.WriteToSocket("ctrl+y");
                    }
                }).start();

            }
        });
        Notepad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        myClient.WriteToSocket("notepad");
                    }
                }).start();

            }
        });
        Explorer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        myClient.WriteToSocket("explorer");
                    }
                }).start();

            }
        });

        CMD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        myClient.WriteToSocket("cmd");
                    }
                }).start();

            }
        });
    }
}
