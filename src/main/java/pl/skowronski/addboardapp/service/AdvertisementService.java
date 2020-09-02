package pl.skowronski.addboardapp.service;

import pl.skowronski.addboardapp.model.Advertisement;

public interface AdvertisementService {

    public void updateAdvertisement(Advertisement advertisement);

    public String getEmailOfLoggedUser();
}
