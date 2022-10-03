package cm.pak.training.populators.core;

import cm.pak.models.core.MailModel;
import cm.pak.populators.Populator;
import cm.pak.training.beans.core.MailData;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class MailPopulator implements Populator<MailModel, MailData> {
    @Override
    public MailData populate(MailModel source) {
        final MailData data = new MailData();
        populate(source, data);
        data.setSubject(source.getSubject());
        data.setText(source.getText());
        data.setTo(source.getTo());
        return data;
    }

    @Override
    public MailModel revert(MailData source) throws ParseException {
        final MailModel data = new MailModel();
        revert(source, data);
        data.setSubject(source.getSubject());
        data.setText(source.getText());
        data.setTo(source.getTo());
        return data;
    }
}
