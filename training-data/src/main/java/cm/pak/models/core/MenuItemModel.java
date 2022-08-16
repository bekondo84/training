package cm.pak.models.core;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@Entity
//@DiscriminatorValue("I")
public class MenuItemModel extends AbstractMenu{

    public MenuItemModel(String type) {
        super(type);
    }

    public MenuItemModel() {
    }
}
