package cm.pak.training.facades.core.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.MailTemplateModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.training.beans.core.MailTemplateData;
import cm.pak.training.facades.core.MailTemplateFacade;
import cm.pak.training.populators.core.MailTemplatePopulator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DefaultMailTemplateFacade implements MailTemplateFacade {
    private static final Logger LOG = LoggerFactory.getLogger(MailTemplateFacade.class);
    @Autowired
    private MailTemplatePopulator populator;
    @Autowired
    private ModelService modelService;
    @Autowired
    private FlexibleSearch flexibleSearch;


    @Transactional
    @Override
    public MailTemplateData save(MailTemplateData source) throws ParseException, ModelServiceException {
        final MailTemplateModel data = populator.revert(source);
        modelService.createOrUpdate(data);
        //LOG.info(String.format("------------------------------Inside %s", data));
        return populator.populate(flexibleSearch.find(MailTemplateModel.class, "code", source.getCode()));
    }

    @Override
    public List<MailTemplateData> getMailTemplates() {
        List<MailTemplateModel> datas = flexibleSearch.find("SELECT t FROM MailTemplateModel AS t");

        if (!CollectionUtils.isEmpty(datas)) {
            return datas.stream().map(m -> populator.populate(m)).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    @Override
    public MailTemplateData getMailTemplate(final Long pk) {
        return populator.populate(modelService.find(MailTemplateModel.class, pk));
    }

    @Transactional
    @Override
    public void remove(Long pk) {
         final MailTemplateModel data = modelService.find(MailTemplateModel.class, pk);
         modelService.remove(data);
    }
}
