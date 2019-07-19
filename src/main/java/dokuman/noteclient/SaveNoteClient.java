package dokuman.noteclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dokuman.dto.NoteDto;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * @author Kübra VARICI
 * @since
 */
public class SaveNoteClient {
    public void saveNote(NoteDto noteDto) {
        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost("http://localhost:9090/dokuman/note/save");

        HttpResponse response = null;
        try {
            StringEntity input = new StringEntity(gson.toJson(noteDto), Charset.forName("utf-8"));
            post.setEntity(input);
            response = client.execute(post);

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
