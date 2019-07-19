package dokuman;

import com.vaadin.data.Item;
import com.vaadin.ui.*;

/**
 * @author Gökçe DOĞANAY
 * @since 0.0.1
 */
public class MainPage extends VerticalLayout{
    public MainPage() {
        buildMainLayout();
    }

    public void buildMainLayout(){
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        addComponent(layout);
        VerticalLayout sideBar = new VerticalLayout();
        layout.addComponent(sideBar);
        layout.setExpandRatio(sideBar, 0.4f);
        VerticalLayout content = new VerticalLayout();
        layout.addComponent(content);
        layout.setExpandRatio(content, 0.6f);

        Button kayit = new Button("Yeni Kayıt");
        kayit.setWidth("100%");
        sideBar.addComponent(kayit);
        sideBar.setComponentAlignment(kayit, Alignment.TOP_CENTER);
        kayit.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                SavePage savePage = new SavePage();
                MyUI.getCurrent().setContent(savePage);
            }
        });


        Table table = new Table(" ");
        table.addContainerProperty("#", Long.class, null);
        table.addContainerProperty("Konu",  String.class, null);
        table.setWidth("100%");
        sideBar.addComponent(table);
        sideBar.setMargin(true);
        sideBar.setComponentAlignment(table, Alignment.TOP_CENTER);
        //deneme
        Object newItemId = table.addItem();
        Item row1 = table.getItem(newItemId);
        row1.getItemProperty("#").setValue(1L);
        row1.getItemProperty("Konu").setValue("Spring Boot ile Deneme Proje");
        //deneme


        TextField konu = new TextField();
        konu.setEnabled(false);
        konu.setWidth("100%");
        content.addComponent(konu);
        content.setComponentAlignment(konu, Alignment.TOP_LEFT);

        TextArea icerik = new TextArea();
        icerik.setEnabled(false);
        icerik.setWidth("100%");
        icerik.setHeight("600px");
        icerik.setCaption(" ");
        content.addComponent(icerik);
        content.setComponentAlignment(icerik, Alignment.TOP_LEFT);

        content.setMargin(true);
    }

}
