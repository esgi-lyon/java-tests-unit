package com.app.View;

import com.framework.Services.IEntity;
import com.framework.Services.Layout;
import com.framework.SwingModules.Form;
import com.framework.SwingModules.FormBuilder;
import com.framework.SwingModules.TableList;

import javax.swing.*;
import java.util.List;

public class MusicalEventView {
    static String[] tableColumn = {"Intitule", "Nom", "Prix"};

    public String intitule = "Concert";
    
    public JComboBox user = new JComboBox();

    // Components
    public TableList productTableList;
    public Form serviceAdd;
    public Form serviceTerminate;

    public MusicalEventView(Layout ly) {
        productTableList = new TableList(tableColumn, ly, null);
        serviceAdd = this.startForm(ly);
        serviceTerminate = this.terminateForm(ly);
    }

    public void fillForm(List<IEntity> builders) {
        user.removeAllItems();
        user.addItem(FormBuilder.NO_SELECT);
        // The solution
        for (IEntity builder : builders) {
        	user.addItem(builder);
        }
    }

    public Form terminateForm(Layout ly) {
        FormBuilder builder = (new FormBuilder(true))
                .addField("User", user)
                .enableBackButton(ly, null)
                .disableListBtn();

        return builder.create(null);
    }

    public Form startForm(Layout ly) {
        FormBuilder builder = (new FormBuilder(true))
            .addField("User", user)
            .enableBackButton(ly, null)
            .disableListBtn();

        return builder.create(null);
    }
}
