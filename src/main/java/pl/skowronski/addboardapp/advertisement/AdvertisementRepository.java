package pl.skowronski.addboardapp.advertisement;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.skowronski.addboardapp.user.User;

import java.util.List;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {
    List<Advertisement> findAllByUser(User user);
}
