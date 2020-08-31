package pl.skowronski.addboardapp.advertisement;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class AdvertisementServiceJpa implements AdvertisementService{

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void updateAdvertisement(Advertisement advertisement) {
        entityManager.merge(advertisement);
    }
}
