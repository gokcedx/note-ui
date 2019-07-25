package dokuman.noteclient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dokuman.dto.NoteDto;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.List;

/**
 * @author Gökçe DOĞANAY
 * @since 0.0.1
 */
public class FindAllNoteClient {

    public List<NoteDto> findAllNote(){

        List<NoteDto> noteDtoList = null;
        try {
            URL url = new URL("http://localhost:9090/dokuman/note/findAll");
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
            Type collectionType = new TypeToken<Collection<NoteDto>>() {}.getType();
            noteDtoList = gson.fromJson(output, collectionType);

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return noteDtoList;
    }
}
