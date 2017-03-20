package org.proj.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.proj.app.domain.Image;

import java.util.Set;


public interface ImageRepository extends JpaRepository<Image,Long> {
    Image findById(int id);

    @Query("SELECT i from Image i where i.date_added = (select max (im.date_added) from Image im)")
    Set<Image> findLastAdded();

}
