package in.rajat_verma.cards.service;

import in.rajat_verma.cards.dto.CardDto;

public interface CardsService {


    /**
     * @param mobileNumber - Mobile Number of the Customer
     */
    void createCard(String mobileNumber);

    /**
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     */
    CardDto fetchCard(String mobileNumber);

    /**
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateCard(CardDto cardsDto);

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    boolean deleteCard(String mobileNumber);

}
