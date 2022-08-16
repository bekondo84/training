package cm.pak.converters.impl;

import cm.pak.converters.Converter;
import cm.pak.data.MenuData;
import cm.pak.models.core.AbstractMenu;
import cm.pak.models.core.MenuItemModel;
import cm.pak.models.core.MenuModel;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.Objects;

@Component
public class MenuDataConverter implements Converter<MenuData, AbstractMenu> {
    @Override
    public AbstractMenu convert(MenuData source) {

        if (CollectionUtils.isEmpty(source.getChildren())) {
            final MenuItemModel menuItem = new MenuItemModel("I");
            menuItem.setName(source.getName());
            menuItem.setOrder(source.getOrder());
            menuItem.setPath(source.getPath());
            menuItem.setCreate(new Date());
            return menuItem;
        }
        //Here we hare Menu
        final MenuModel menu = new MenuModel("M");
        menu.setName(source.getName());
        menu.setOrder(source.getOrder());
        menu.setPath(source.getPath());
        menu.setCreate(new Date());
        source.getChildren().stream().filter(Objects::isNull)
                .forEach(data -> {
                       menu.addSubMenu(convert(data));
                });

        return menu;
    }
}
