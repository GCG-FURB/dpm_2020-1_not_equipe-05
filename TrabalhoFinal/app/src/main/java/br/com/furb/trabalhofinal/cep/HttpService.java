package br.com.furb.trabalhofinal.cep;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpService extends AsyncTask<Void, Void, CEP> {

    private final String cep;

    public HttpService(String cep) {
        this.cep = cep;
    }

    @Override
    protected CEP doInBackground(Void... voids) {
        if (this.cep != null && this.cep.length() == 8) {
            try {
                String url = "http://viacep.com.br/ws/" + this.cep + "/json/";

                JSONObject obj = new JSONObject(this.get(url));
                CEP cepret = new CEP();
                if (!obj.has("erro")) {
                    cepret.setCep(obj.getString("cep"));
                    cepret.setLogradouro(obj.getString("logradouro"));
                    cepret.setComplemento(obj.getString("complemento"));
                    cepret.setBairro(obj.getString("bairro"));
                    cepret.setCidade(obj.getString("localidade"));
                    cepret.setEstado(obj.getString("uf"));
                    cepret.setId();
                } else {
                    throw new ViaCEPException("Não foi possível encontrar o CEP", cep);
                }

                return cepret;

            } catch (ViaCEPException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public final String get(String urlToRead) throws ViaCEPException {
        StringBuilder result = new StringBuilder();

        try {
            URL url = new URL(urlToRead);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }

        } catch (MalformedURLException | ProtocolException ex) {
            throw new ViaCEPException(ex.getMessage());
        } catch (IOException ex) {
            throw new ViaCEPException(ex.getMessage());
        }

        return result.toString();
    }

}
