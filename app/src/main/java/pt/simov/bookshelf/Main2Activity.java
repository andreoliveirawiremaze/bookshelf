package pt.simov.bookshelf;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.facebook.login.widget.LoginButton;

public class Main2Activity extends AppCompatActivity {

    private EditText texto;
    private Button pesquisar;

    //private LinearLayout linlaHeaderProgress;
    private ProgressDialog pd = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        texto = (EditText) findViewById(R.id.editText);
        pesquisar = (Button) findViewById(R.id.button);

        pesquisar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String conteudo = texto.getText().toString();
                if(conteudo==null || conteudo.equals("")){
                    Toast.makeText(getBaseContext(), "Preencha o nome do livro!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("TERMINAR", "terminar");
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            Helpers centoedoze = new Helpers();
            return centoedoze.GET(urls[0]);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd.show();
        }

        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            //linlaHeaderProgress.setVisibility(View.INVISIBLE);
            final EditText et = (EditText)findViewById(R.id.editText);
            if (pd != null) {
                if (pd.isShowing()) {
                    pd.dismiss();
                }
            }

            /*Intent i = new Intent(getApplicationContext(), SearchMovieListActivity.class);
            i.putExtra("search_query", et.getText().toString());
            i.putExtra("search_results", result);
            startActivity(i);*/
            super.onPostExecute(result);
        }
    }
}
