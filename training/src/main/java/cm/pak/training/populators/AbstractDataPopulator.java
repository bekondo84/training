package cm.pak.training.populators;

import cm.pak.models.security.base.ItemModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.AbstractData;

import java.text.ParseException;

public class AbstractDataPopulator implements Populator<ItemModel, AbstractData> {
    @Override
    public AbstractData populate(ItemModel source) {
        return null;
    }

    @Override
    public ItemModel revert(AbstractData source) throws ParseException {
        return null;
    }
}
