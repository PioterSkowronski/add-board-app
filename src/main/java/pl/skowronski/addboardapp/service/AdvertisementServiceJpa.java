package pl.skowronski.addboardapp.service;

import org.springframework.stereotype.Service;
import pl.skowronski.addboardapp.model.Advertisement;
import pl.skowronski.addboardapp.service.AdvertisementService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Service
@Transactional
public class AdvertisementServiceJpa implements AdvertisementService {

    @PersistenceContext
    EntityManager entityManager;


    @Override
    public void updateAdvertisement(Advertisement advertisement) {
        entityManager.merge(advertisement);
    }
}
