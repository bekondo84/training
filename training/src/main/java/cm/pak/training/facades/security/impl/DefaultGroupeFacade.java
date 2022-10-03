package cm.pak.training.facades.security.impl;

import cm.pak.data.MenuData;
import cm.pak.data.ModuleData;
import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.AccesRigth;
import cm.pak.models.security.GroupeModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.services.JsonService;
import cm.pak.training.beans.security.GroupeData;
import cm.pak.training.facades.security.GroupeFacade;
import cm.pak.training.populators.security.GroupePopulaor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class DefaultGroupeFacade implements GroupeFacade {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultGroupeFacade.class);
    @Autowired
    private ModelService modelService;
    @Autowired
    private FlexibleSearch flexibleSearch;
    @Autowired
    private GroupePopulaor populator ;
    @Autowired
    private JsonService jsonService;


    @Override
    public List<GroupeData> getGroupes() {
        final List<GroupeModel> groupes = flexibleSearch.find("SELECT c FROM GroupeModel AS c");
        return groupes.stream().map(gr ->populator.populate(gr)).collect(Collectors.toList());
    }

    @Override
    public GroupeData geGroupe(Long pk) {
        final GroupeModel grp = modelService.find(GroupeModel.class, pk);
        return populator.populate(grp);
    }

    @Override
    @Transactional
    public void save(GroupeData grp) throws ModelServiceException, URISyntaxException, IOException {
        final GroupeModel groupe = populator.revert(grp);
        if (Objects.nonNull(groupe.getPlugin()) && CollectionUtils.isEmpty(grp.getRigths())) {
            getAccessRigths(groupe);
        }
        LOG.info(String.format("++++++++++++++++++++++++++++++++++ %s", groupe));
        modelService.createOrUpdate(groupe);
    }

    private void getAccessRigths(GroupeModel groupe) throws URISyntaxException, IOException {
        final ModuleData module =  jsonService.getModule(groupe.getPlugin().getCode());
        if (!CollectionUtils.isEmpty(module.getMenus())) {
            for (MenuData menu : module.getMenus()) {
               final AccesRigth access = new AccesRigth(new Date(), menu.getName(), menu.getLabel());
               groupe.addAccesRigth(access);
            }
        }
    }
}
