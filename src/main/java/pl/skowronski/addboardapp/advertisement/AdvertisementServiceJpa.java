package pl.skowronski.addboardapp.advertisement;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Override
    public String getEmailOfLoggedUser() {
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return email;
    }
}
