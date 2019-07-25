package dokuman;

import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.*;
import dokuman.dto.NoteDto;
import dokuman.noteclient.FindAllNoteClient;
import dokuman.noteclient.FindNoteByIdClient;
import dokuman.noteclient.FindNoteClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Gökçe DOĞANAY
 * @since 0.0.1
 */
public class MainPage extends VerticalLayout{

    private Table table;
    private Button kayit;
    private HorizontalLayout search;
    private TextField searchBox;
    private Button searchButton;
    private ComboBox searchCombo;
    private TextField konu;
    private TextArea icerik;

    public MainPage() {
        buildMainLayout();
    }

    public void buildMainLayout(){
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        addComponent(layout);

        VerticalLayout sideBar = new VerticalLayout();
        VerticalLayout content = new VerticalLayout();

        layout.addComponents(sideBar, content);

        layout.setExpandRatio(sideBar, 0.4f);
        layout.setExpandRatio(content, 0.6f);

        kayit = new Button("Yeni Kayıt");
        kayit.setWidth("100%");
        kayit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SavePage savePage = new SavePage();
                MyUI.getCurrent().setContent(savePage);
            }
        });

        search = new HorizontalLayout();
        searchBox = new TextField();
        searchBox.setWidth("100%");
        searchButton = new Button("ARA");
        searchButton.setWidth("100%");
        Collection<String> noteData = new ArrayList<String>();
        noteData.add("Konular");
        noteData.add("İçerik");
        searchCombo = new ComboBox();
        searchCombo.addItems(noteData);
        searchCombo.setWidth("50%");
        searchCombo.setTextInputAllowed(false);
        searchCombo.setNullSelectionAllowed(false);
        searchCombo.setValue(noteData.iterator().next());
        search.setCaption(" ");
        search.addComponents(searchCombo,searchBox,searchButton);
        search.setExpandRatio(searchBox, 0.6f);
        search.setExpandRatio(searchButton, 0.2f);
        search.setExpandRatio(searchCombo, 0.2f);

        searchButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                String comboValue = searchCombo.getValue().toString();
                String boxValue = searchBox.getValue();
                NoteDto noteDto = new NoteDto();
                if(boxValue.equals("")){
                    fillTable();
                }else{
                    if(comboValue.equals("Konular")){
                        noteDto.setKonu(boxValue);
                    }else if(comboValue.equals("İçerik")){
                        noteDto.setIcerik(boxValue);
                    }
                    fillTableByKonuOrIcerik(noteDto);
                }
            }
        });

        table = new Table(" ");
        table.addContainerProperty("Konu",  String.class, null);
        table.setWidth("100%");
        table.setSelectable(true);
        table.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                NoteDto noteDto = (NoteDto) event.getItemId();
                FindNoteByIdClient findNoteByIdClient = new FindNoteByIdClient();
                if(noteDto != null){
                    Long id = noteDto.getId();
                    NoteDto noteById = findNoteByIdClient.findNoteById(id);
                    konu.setValue(noteById.getKonu());
                    icerik.setValue(noteById.getIcerik());
                }
            }
        });

        sideBar.addComponents(kayit,search,table);

        sideBar.setComponentAlignment(kayit, Alignment.TOP_CENTER);
        sideBar.setComponentAlignment(search, Alignment.TOP_CENTER);
        sideBar.setComponentAlignment(table, Alignment.TOP_CENTER);

        fillTable();

        konu = new TextField();
        konu.setWidth("100%");
        konu.setEnabled(false);

        icerik = new TextArea();
        icerik.setWidth("100%");
        icerik.setHeight("600px");
        icerik.setCaption(" ");
        icerik.setEnabled(false);

        content.addComponents(konu, icerik);

        content.setComponentAlignment(konu, Alignment.TOP_LEFT);
        content.setComponentAlignment(icerik, Alignment.TOP_LEFT);

        sideBar.setMargin(true);
        content.setMargin(true);
    }

    public void fillTable(){
        table.removeAllItems();
        FindAllNoteClient findAllNoteClient = new FindAllNoteClient();
        List<NoteDto> noteDtoList = findAllNoteClient.findAllNote();
        for (NoteDto noteDto : noteDtoList) {
            Item item = table.addItem(noteDto);
            item.getItemProperty("Konu").setValue(noteDto.getKonu());
        }
    }

    public void fillTableByKonuOrIcerik(NoteDto noteDto){
        table.removeAllItems();
        FindNoteClient findNoteClient = new FindNoteClient();
        List<NoteDto> noteDtoList = findNoteClient.findNote(noteDto);
        for (NoteDto dto : noteDtoList) {
            Item item = table.addItem(dto);
            item.getItemProperty("Konu").setValue(dto.getKonu());
        }
    }

}
