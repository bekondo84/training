package cm.pak.models.core;

import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//@Entity
//@DiscriminatorValue("M")
public class MenuModel extends AbstractMenu{

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "t_menu")
    private Set<AbstractMenu> menus ;

    public MenuModel(String type) {
        super(type);
        this.menus = new HashSet<>();
    }

    public MenuModel() {
        this.menus = new HashSet<>();
    }

    public Set<AbstractMenu> getMenus() {
        return Collections.unmodifiableSet(menus);
    }

    public void setMenus(Set<AbstractMenu> menus) {
        this.menus = menus;
    }

    public void addSubMenu(final AbstractMenu menu) {
        assert Objects.nonNull(menu) : "Menu is not null" ;
        this.menus.add(menu);
    }

    /**
     *
     * @param menus
     * @param ext
     */
    public void fill(Set<AbstractMenu> menus, final ExtensionModel ext) {
       /**
        for (AbstractMenu menu : menus) {
            menu.setModule(ext);
            if (menu instanceof MenuModel) {

                if (!CollectionUtils.isEmpty(((MenuModel)menu).getMenus())){

                    for (final AbstractMenu m : ((MenuModel)menu).getMenus()) {
                        m.setModule(ext);

                        if (m instanceof MenuModel) {
                            fill(((MenuModel)m).getMenus(), ext);
                        }
                    }
                }

            }

        }**/
    }
}
