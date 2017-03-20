package org.proj.app.service;

import org.proj.app.domain.Image;
import org.proj.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.proj.app.domain.User;

import java.util.Iterator;
import java.util.Set;


@Component
public class UserImageServiceImpl implements UserImageService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void addImageToUser(Long id, Image s) {
        User user = userRepository.findById(id);
        Set<Image> set = user.getSet();
        set.add(s);
        userRepository.save(user);
    }

    @Override
    public void deleteAllImagesFromUser(User u) {
        userRepository.findById(u.getId()).getSet().clear();
    }

    @Override
    public Set<Image> getAllImagesFromUser(User u) {
      return      userRepository.findById(u.getId()).getSet();
    }

    @Override
    public void deleteImageFromUser(User e,int img) {
        userRepository.findAll().forEach(System.out::print);
        User user = userRepository.findById(e.getId());
        System.out.println(user);
        Set<Image> set = user.getSet();
        System.out.println(set);
        Iterator<Image> it = set.iterator();

        while (it.hasNext()){
            Image current = it.next();
            System.out.println(current);
            if (current.getId()==img){
                it.remove();
            }
        }
        System.out.println("removed");
        System.out.println(user.getSet());
        userRepository.save(user);
    }
}
