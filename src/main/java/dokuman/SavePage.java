package dokuman;

import com.vaadin.ui.*;
import dokuman.noteclient.SaveNoteClient;
import dokuman.dto.NoteDto;

/**
 * @author Kübra VARICI
 */
public class SavePage extends VerticalLayout {
    public SavePage() {
        buildSaveLayout();
    }

    public void buildSaveLayout(){
        VerticalLayout saveLayout = new VerticalLayout();
        saveLayout.setSpacing(true);

        Button geri = new Button("Geri");
        geri.setWidth("50%");
        geri.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                MainPage mainPage = new MainPage();
                MyUI.getCurrent().setContent(mainPage);
            }
        });

        TextField konu = new TextField();
        konu.setWidth("50%");
        konu.setCaption("Konu:");

        RichTextArea not = new RichTextArea();
        not.setHeight("500px");
        not.setWidth("50%");

        Button button = new Button("Kaydet");
        button.setWidth("50%");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                NoteDto noteDto = new NoteDto();
                noteDto.setKonu(konu.getValue());
                noteDto.setIcerik(not.getValue());

                SaveNoteClient saveNoteClient = new SaveNoteClient();
                saveNoteClient.saveNote(noteDto);
            }
        });

        saveLayout.addComponents(geri,konu,not, button);
        addComponent(saveLayout);
        saveLayout.setComponentAlignment(geri, Alignment.MIDDLE_CENTER);
        saveLayout.setComponentAlignment(konu, Alignment.MIDDLE_CENTER);
        saveLayout.setComponentAlignment(not, Alignment.MIDDLE_CENTER);
        saveLayout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);

        setMargin(true);
        setSpacing(true);

    }
}
