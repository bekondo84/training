package cm.pak.training.facades.core;

import cm.pak.training.beans.core.MailData;

import java.text.ParseException;
import java.util.List;

public interface MailFacade {
   // MailData save(final MailData source) throws ParseException;
    List<MailData> getMails();
    MailData getMail(final Long pk);
    void remove(final Long pk);
}
