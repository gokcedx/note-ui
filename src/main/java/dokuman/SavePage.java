package dokuman;

import com.vaadin.ui.*;

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

        TextField konu = new TextField();
        konu.setWidth("33%");
        konu.setCaption("Konu:");

        RichTextArea not = new RichTextArea();
        not.setHeight("750px");
        not.setWidth("33%");

        Button button = new Button("Kaydet");
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {

            }
        });

        saveLayout.addComponents(konu,not, button);
        addComponent(saveLayout);
        saveLayout.setComponentAlignment(konu, Alignment.MIDDLE_CENTER);
        saveLayout.setComponentAlignment(not, Alignment.MIDDLE_CENTER);
        saveLayout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);

        setMargin(true);
        setSpacing(true);

    }
}
