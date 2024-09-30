package com.fram.controllers;

import com.fram.models.PropertyModel;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PropertyController {
    private List<PropertyModel> properties = new ArrayList<>(List.of(
            new PropertyModel(1, "Roysambu Heights", 400),
            new PropertyModel(2, "ilimani bedrocks", 400),
            new PropertyModel(3, "Riara Heights, Kiambu Road", 400),
             new PropertyModel(4, "Kilimanjaro View apartments", 400)
    ));
    @GetMapping("properties")
    public List<PropertyModel> getProperties(){
            return properties;
    }

    @PostMapping("properties")
    public PropertyModel addProperty(@RequestBody PropertyModel propertyModel){
         properties.add(propertyModel);
         return propertyModel;
    }

//A method to get a csrf token, for us to be able to send a POST,PUT,DELETE requests
    @GetMapping("csrf-token")
    CsrfToken getCsrfToken(HttpServletRequest req){
        return (CsrfToken) req.getAttribute("_csrf");
    }

}
