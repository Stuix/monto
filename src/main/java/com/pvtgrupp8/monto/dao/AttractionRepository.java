package com.pvtgrupp8.monto.dao;

import com.pvtgrupp8.monto.entities.Attraction;
import com.pvtgrupp8.monto.entities.Creator;
import com.pvtgrupp8.monto.entities.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.web.bind.annotation.CrossOrigin;

/*@Projection(name = "inlinePosition", types = { Attraction.class })
interface InlinePosition {
    int getId();
    String getDescription();
    String getTitle();
    String getTitleEnglish();
    String getPicture();
    Position getPosition();
    List<Creator> getCreators();
}*/

//@RepositoryRestResource(excerptProjection = InlinePosition.class)
//@CrossOrigin(origins = "http://localhost:8100")
public interface AttractionRepository extends JpaRepository<Attraction, Integer> {

    // @Query("from Attraction where title like %?1")
    List<Attraction> findByTitleIgnoreCaseContainingAndCategory_Name(String title,String category);

    Attraction findById(int id);

    List<Attraction> findAllByCategory_Name(String category);

}