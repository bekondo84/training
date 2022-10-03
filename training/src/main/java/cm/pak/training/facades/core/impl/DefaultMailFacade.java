package cm.pak.training.facades.core.impl;

import cm.pak.models.core.MailModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.core.MailData;
import cm.pak.training.facades.core.MailFacade;
import cm.pak.training.populators.core.MailPopulator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultMailFacade implements MailFacade {
    @Autowired
    private MailPopulator populator;
    @Autowired
    private ModelService modelService;
    @Autowired
    private FlexibleSearch flexibleSearch;

    /**
    @Override
    public MailData save(MailData source) throws ParseException {
        final MailModel data = populator.revert(source);

        return source;
    }
     **/

    @Override
    public List<MailData> getMails() {
        final List<MailModel> datas = flexibleSearch.find("SELECT m FROM MailModel AS m");

        if (!CollectionUtils.isEmpty(datas)) {
            return datas.stream().map(m -> populator.populate(m)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public MailData getMail(Long pk) {
        return populator.populate(modelService.find(MailModel.class, pk));
    }

    @Override
    public void remove(Long pk) {
       final MailModel data = modelService.find(MailModel.class, pk);
       modelService.remove(data);
    }
}
