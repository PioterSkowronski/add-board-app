package pl.skowronski.addboardapp.advertisement;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.skowronski.addboardapp.user.User;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findAllByUser(User user);

    @Query(value = "select * from advertisements where description like %?1% or title like %?1%",
            nativeQuery = true)
    List<Advertisement> findByContent(String content);
}
