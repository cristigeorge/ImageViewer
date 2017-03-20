package org.proj.app.rest;

import org.proj.app.domain.Image;
import org.proj.app.domain.User;
import org.proj.app.service.UserImageService;
import org.proj.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;


@RestController
@RequestMapping("/api")
public class UserImageController {
    @Autowired
    UserService userService;
    @Autowired
    UserImageService userImageService;

    @RequestMapping(value = "/user/images", method = RequestMethod.POST)
    public void addImageToUser(@RequestBody Image image, Principal auth) {

        User loggedUser = userService.findByUsername(auth.getName());
        userImageService.addImageToUser(loggedUser.getId(), image);
    }

    @RequestMapping(value = "/user/images", method = RequestMethod.GET)
    public Set<Image> getImagesFromUser(Principal p) {
       User u = userService.findByUsername(p.getName());
        return userImageService.getAllImagesFromUser(u);
    }
    @RequestMapping(value = "/user/images/{id}", method = RequestMethod.DELETE)
    public void deleteImageUser(@PathVariable("id") int image,Principal p) {
        System.out.println("id: "+image);
        User e = userService.findByUsername(p.getName());
        userImageService.deleteImageFromUser(e,image );
    }


}
