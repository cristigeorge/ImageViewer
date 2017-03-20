package org.proj.app.service;

import org.springframework.stereotype.Component;
import org.proj.app.domain.Image;
import org.proj.app.domain.User;

import java.util.Set;


@Component
public interface UserImageService  {
    void addImageToUser(Long d, Image s);
    void deleteImageFromUser(User e,int img);
    void deleteAllImagesFromUser(User u);
    Set<Image> getAllImagesFromUser(User u);

}
