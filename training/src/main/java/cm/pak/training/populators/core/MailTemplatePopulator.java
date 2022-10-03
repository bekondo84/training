package cm.pak.training.populators.core;

import cm.pak.models.MailTemplateModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.core.MailTemplateData;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class MailTemplatePopulator implements Populator<MailTemplateModel, MailTemplateData> {
    @Override
    public MailTemplateData populate(MailTemplateModel source) {
        final MailTemplateData data = new MailTemplateData();
        populate(source, data);
        data.setCode(source.getCode());
        data.setIntitule(source.getIntitule());
        data.setText(source.getText());
        return data;
    }

    @Override
    public MailTemplateModel revert(MailTemplateData source) throws ParseException {
        final MailTemplateModel data = new MailTemplateModel();
        revert(source, data);
        data.setCode(source.getCode());
        data.setIntitule(source.getIntitule());
        data.setText(source.getText());
        return data;
    }
}
