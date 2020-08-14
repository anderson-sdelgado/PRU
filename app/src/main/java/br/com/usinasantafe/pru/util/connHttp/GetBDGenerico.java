package br.com.usinasantafe.pru.util.connHttp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.usinasantafe.pru.util.AtualDadosServ;

import android.os.AsyncTask;
import android.util.Log;

public class GetBDGenerico extends AsyncTask<String, Void, String> {

	private static GetBDGenerico instance = null;
	private String tipo = null;
	
	private UrlsConexaoHttp urlsConexaoHttp;

	public GetBDGenerico() {
	}

    public static GetBDGenerico getInstance() {
        if (instance == null)
        instance = new GetBDGenerico();
        return instance;
    }

	@Override
	protected String doInBackground(String... arg) {
		
		String resultado = "";
		BufferedReader bufferedReader = null;
		
		tipo = arg[0];
		String url = "";
		
		try {
			
			Object o = new Object();
            Class<?> retClasse = Class.forName(urlsConexaoHttp.localUrl); 
			
            for (Field field : retClasse.getDeclaredFields()) {
                String campo = field.getName();
                if(campo.equals(tipo)){
                	url = "" + retClasse.getField(campo).get(o);
               }
            }

			Log.i("PRU", "URL = " + url);
			URL urlCon = new URL(url);
			HttpURLConnection connection = (HttpURLConnection) urlCon.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setDoOutput(false);
			connection.connect();

			bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			StringBuffer stringBuffer = new StringBuffer("");
			String line = "";
			String LS = System.getProperty("line.separator");
			while((line = bufferedReader.readLine()) != null){
				stringBuffer.append(line + LS);
			}
			bufferedReader.close();
			resultado = stringBuffer.toString();

			connection.disconnect();
            
		} catch (Exception e) {
			if(bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (Exception erro) {
					Log.i("PRU", "Erro conexão web = " + erro);
				}
			}
		}
		finally{
			
			if(bufferedReader != null){
				try {
					bufferedReader.close();
				} catch (Exception e) {
					Log.i("PRU", "FInal conexão = " + e);
				}
			}
			
		}
		
		return resultado;
	}
	
	protected void onPostExecute(String result) {

		try {
			
			AtualDadosServ.getInstance().manipularDadosHttp(tipo, result);
			
		} catch (Exception e) {
			Log.i("PRU", "Erro recebimento = " + e);
		}

    }

}
