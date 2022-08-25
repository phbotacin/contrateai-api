package br.com.bruno2code.contrateai.api;

import br.com.bruno2code.contrateai.util.Util;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LogApi {

    static String TOKEN = "1322091242:AAFF9VrRFoCRTtwTvppCJnMMp6g9w3GziHM";
    static String CHATID = "-1001274105955";

    public boolean sendLog(String classe, String metodo, String erro) {

        try {
            StringBuilder str = new StringBuilder();

            str.append("API Contrate Ai");
            str.append(" %0A");
            str.append("%0A");

            if (!new Util().vazio(classe)) {
                str.append("CLASSE: ");
                str.append(classe);
                str.append(" %0A");
            }

            if (!new Util().vazio(metodo)) {
                str.append("MÉTODO: ");
                str.append(metodo);
                str.append(" %0A");
            }

            if (!new Util().vazio(erro)) {
                str.append(" %0A");
                str.append(erro);
                str.append(" %0A");
            }
            OkHttpClient client = new OkHttpClient().newBuilder()
                    .build();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
            RequestBody body = RequestBody.create(mediaType, str.toString());
            Request request = new Request.Builder()
                    .url("https://api.telegram.org/bot" + TOKEN + "/sendMessage?text=" + str.toString() + "&chat_id=" + CHATID)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .build();
            Response response = client.newCall(request).execute();
            response.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
