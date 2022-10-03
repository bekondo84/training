package cm.pak.training.facades.core;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.core.MailTemplateData;

import java.text.ParseException;
import java.util.List;

public interface MailTemplateFacade {
    MailTemplateData  save(final MailTemplateData source) throws ParseException, ModelServiceException;
    List<MailTemplateData> getMailTemplates() ;
    MailTemplateData getMailTemplate(final Long pk);
    void remove(final Long pk);
}
