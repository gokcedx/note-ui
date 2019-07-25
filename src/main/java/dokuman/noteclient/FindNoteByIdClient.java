package dokuman.noteclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dokuman.dto.NoteDto;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Gökçe DOĞANAY
 * @since 0.0.1
 */
public class FindNoteByIdClient {

    public NoteDto findNoteById(Long id){
        NoteDto noteDto = null;
        try {
            URL url = new URL("http://localhost:9090/dokuman/note/findById/"+id);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            InputStream inputStream = conn.getInputStream();
            String output = IOUtils.toString(inputStream, conn.getContentEncoding() == null ? "UTF-8" : conn.getContentEncoding());
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            noteDto = gson.fromJson(output, NoteDto.class);

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return noteDto;
    }
}
