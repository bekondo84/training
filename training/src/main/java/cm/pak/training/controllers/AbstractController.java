package cm.pak.training.controllers;

import cm.pak.data.ActionData;
import cm.pak.data.MenuData;
import cm.pak.repositories.ModelService;
import cm.pak.services.MetaService;
import cm.pak.training.beans.AbstractData;
import cm.pak.training.beans.core.ExtensionData;
import cm.pak.training.facades.core.ExtensionFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.Console;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public abstract class AbstractController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractController.class);
    @Autowired
    protected ExtensionFacade extensionFacade ;
    @Autowired
    protected MetaService metaService;


    @RequestMapping("/search/{searchKey}/{value}")
    public ResponseEntity getItemBySearchKey(final @PathVariable("searchKey") String seachKey, final @PathVariable("value")  Object value) {
        return ResponseEntity.ok(null);
    }

    @ResponseBody
    @DeleteMapping("/api/v1/{id}")
    public void delete(@PathVariable("id") Long id) {

    }

}
