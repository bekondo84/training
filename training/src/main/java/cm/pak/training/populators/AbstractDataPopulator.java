package cm.pak.training.populators;

import cm.pak.models.security.base.ItemModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.AbstractItemData;

import java.text.ParseException;

public class AbstractDataPopulator implements Populator<ItemModel, AbstractItemData> {
    @Override
    public AbstractItemData populate(ItemModel source) {
        return null;
    }

    @Override
    public ItemModel revert(AbstractItemData source) throws ParseException {
        return null;
    }
}
