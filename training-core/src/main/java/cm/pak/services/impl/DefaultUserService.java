package cm.pak.services.impl;

import cm.pak.exceptions.ModelServiceException;
import cm.pak.models.security.UserModel;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.repositories.ModelService;
import cm.pak.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class DefaultUserService implements UserService {

    @Autowired
    private ModelService modelService;
    @Autowired
    private FlexibleSearch flexibleSearch;


    @Override
    public UserModel createOrUpdate(UserModel user) throws ModelServiceException {
        return modelService.createOrUpdate(user) ;
    }

    @Override
    public boolean exist(String code) {

        try {
            return Objects.nonNull(flexibleSearch.find(UserModel.class, "code", code));
        } catch (NoResultException | EmptyResultDataAccessException ex) {
            return false ;
        }

    }
}
