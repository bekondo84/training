package cm.pak.training.controllers;

import cm.pak.data.MetaData;
import cm.pak.repositories.FlexibleSearch;
import cm.pak.services.MetaService;
import cm.pak.training.beans.AbstractData;
import cm.pak.training.facades.core.ExtensionFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.net.URISyntaxException;

@Controller
@RequestMapping
public class TrainingController extends AbstractController{
    private static final Logger LOG = LoggerFactory.getLogger(TrainingController.class);

    @Autowired
    private ExtensionFacade extensionFacade;
    @Autowired
    private MetaService metaService ;
    @Autowired
    private FlexibleSearch flexibleSearch;


     @ResponseBody
     @GetMapping("/api/v1/meta/{name}")
     public ResponseEntity<MetaData> metaData(@PathVariable("name") String name) throws ClassNotFoundException {
         return ResponseEntity.ok(metaService.getMeta(name));
     }

    @ResponseBody
    @GetMapping("/api/v1/instance/{name}")
     public ResponseEntity emptyInstance(@PathVariable("name") String name) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return ResponseEntity.ok(metaService.getInstance(name));
    }

    @ResponseBody
    @GetMapping("/api/v1/search/{searchKey}/{value}/{target}")
    public ResponseEntity getItemBySearchKey(@PathVariable("searchKey") String key, @PathVariable("value") Object value, @PathVariable("target") String target) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        Object entity = Class.forName(target).newInstance();
        final String modelClassName = ((AbstractData)entity).getTargetEntity();
        final Class modelClass = Class.forName(modelClassName);
        try {
            Object result = flexibleSearch.find(modelClass, key, value);
            return ResponseEntity.ok(result);
        } catch (NoResultException e) {
            return ResponseEntity.ok(null);
        }


    }
}
