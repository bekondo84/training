package cm.pak.training.facades.core;

import cm.pak.data.ActionData;
import cm.pak.data.MenuData;
import cm.pak.data.MetaData;
import cm.pak.training.beans.core.ExtensionData;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public interface ExtensionFacade {

    List<ExtensionData> getInstallExtensions() throws URISyntaxException, IOException;

    List<MenuData> getActions(final ExtensionData extension) throws URISyntaxException, IOException;

    List<MenuData> getActions(final String name) throws URISyntaxException, IOException;

    List<ExtensionData> getExtensions();

    default List<ActionData> getAction(final String name, final List<MenuData> menus) {

        for( MenuData menu : menus) {
            if (menu.getName().equalsIgnoreCase(name)) {
                return CollectionUtils.isEmpty(menu.getActions()) ? new ArrayList<>() : menu.getActions() ;
            }
            if (!CollectionUtils.isEmpty(menu.getChildren())) {
                final List<ActionData> result = getAction(name, menu.getChildren());

                if (Objects.nonNull(result)) {
                    return result ;
                }
            }
        }
        return null;
    }
}
