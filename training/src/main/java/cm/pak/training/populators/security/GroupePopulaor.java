package cm.pak.training.populators.security;

import cm.pak.models.security.GroupeModel;
import cm.pak.populators.Populator;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.security.GroupeData;
import cm.pak.training.populators.core.ExtensionPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GroupePopulaor implements Populator<GroupeModel, GroupeData> {

    @Autowired
    private ModelService modelService;
    @Autowired
    private ExtensionPopulator populator;
    @Autowired
    private AccesRigthDataPopulator rigthPopulator;

    @Override
    public GroupeData populate(GroupeModel source) {
        final GroupeData groupe = new GroupeData();
        populate(source, groupe);
        groupe.setCode(source.getCode());
        groupe.setIntitule(source.getIntitule());
        groupe.setValue(source.getIntitule());
        if (Objects.nonNull(source.getPlugin())) {
            groupe.setPlugin(populator.populate(source.getPlugin()));
        }
        if (!CollectionUtils.isEmpty(source.getRigths())) {
             source.getRigths().forEach(rg -> groupe.addRigth(rigthPopulator.populate(rg)));
        }
        return groupe;
    }

    @Override
    public GroupeModel revert(GroupeData source) {
        GroupeModel model = new GroupeModel();
        revert(source, model);
        model.setCode(source.getCode());
        model.setIntitule(source.getIntitule());

        if (Objects.nonNull(source.getPlugin())) {
            model.setPlugin(populator.revert(source.getPlugin()));
        }
        if (!CollectionUtils.isEmpty(source.getRigths())) {
            model.setRigths(source.getRigths()
                    .stream()
                    .map(g -> rigthPopulator.revert(g))
                    .collect(Collectors.toSet()));
        }
        return model;
    }
}
