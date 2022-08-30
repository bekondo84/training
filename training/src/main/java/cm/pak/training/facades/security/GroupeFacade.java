package cm.pak.training.facades.security;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.training.beans.security.GroupeData;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public interface GroupeFacade {

    List<GroupeData> getGroupes();
    GroupeData geGroupe(final Long pk) ;
    void save(GroupeData grp) throws ModelServiceException, URISyntaxException, IOException;
}
