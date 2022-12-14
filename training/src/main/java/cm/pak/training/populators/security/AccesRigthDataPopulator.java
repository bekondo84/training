package cm.pak.training.populators.security;

import cm.pak.models.security.AccesRigth;
import cm.pak.populators.Populator;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.training.beans.security.AccesRigthData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccesRigthDataPopulator implements Populator<AccesRigth, AccesRigthData> {

    @Autowired
    private FlexibleSearch flexibleSearch;

    @Override
    public AccesRigthData populate(AccesRigth source) {
        final AccesRigthData access = new AccesRigthData();
        populate(source, access);
        access.setCancreate(source.isCancreate());
        access.setName(source.getName());
        access.setDelete(source.isCandelete());
        access.setRead(source.isCanread());
        access.setWrite(source.isCanwrite());
        access.setLabel(source.getLabel());
        return access;
    }

    @Override
    public AccesRigth revert(AccesRigthData source) {
        final AccesRigth access = flexibleSearch.find(AccesRigth.class, source.getPk());
         revert(source, access);
         access.setLabel(source.getLabel());
         access.setCandelete(source.isDelete());
         access.setCanread(source.isRead());
         access.setCanwrite(source.isWrite());
         access.setCancreate(source.isCancreate());
        return access;
    }
}
