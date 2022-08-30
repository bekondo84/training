package cm.pak.training.populators.security;

import cm.pak.models.security.GroupeModel;
import cm.pak.populators.Populator;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.security.GroupeData;
import cm.pak.training.populators.core.ExtensionPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

@Component
public class GroupePopulaor implements Populator<GroupeModel, GroupeData> {

    @Autowired
    private ModelService modelService;
    @Autowired
    private ExtensionPopulator populator;
    @Autowired
    private AccesRigthDataPopulator rigthPopulator;

    @Override
    public GroupeData popule(GroupeModel source) {
        final GroupeData groupe = new GroupeData();
        groupe.setPk(source.getPk());
        groupe.setCreate(source.getCreate());
        groupe.setUpdate(source.getUpdate());
        groupe.setCode(source.getCode());
        groupe.setIntitule(source.getIntitule());
        groupe.setValue(source.getIntitule());
        if (Objects.nonNull(source.getPlugin())) {
            groupe.setPlugin(populator.popule(source.getPlugin()));
        }
        if (!CollectionUtils.isEmpty(source.getRigths())) {
             source.getRigths().forEach(rg -> groupe.addRigth(rigthPopulator.popule(rg)));
        }
        return groupe;
    }

    @Override
    public GroupeModel revert(GroupeData source) {
        GroupeModel model = new GroupeModel();

        if (Objects.nonNull(source.getPk())) {
            model = modelService.find(GroupeModel.class, source.getPk());
        }
        model.setCode(source.getCode());
        model.setIntitule(source.getIntitule());

        if (Objects.nonNull(source.getPlugin())) {
            model.setPlugin(populator.revert(source.getPlugin()));
        }
        if (!CollectionUtils.isEmpty(source.getRigths())) {
            source.getRigths().forEach(grp -> rigthPopulator.revert(grp));
        }
        return model;
    }
}
