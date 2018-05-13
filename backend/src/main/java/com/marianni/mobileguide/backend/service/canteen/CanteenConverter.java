package com.marianni.mobileguide.backend.service.canteen;

import com.marianni.mobileguide.backend.domain.Canteen;
import com.marianni.mobileguide.backend.domain.CanteenDailyOffer;
import com.marianni.mobileguide.interfaces.dto.CanteenDTO;
import com.marianni.mobileguide.interfaces.dto.CanteenDailyOfferDTO;

import java.util.Set;
import java.util.stream.Collectors;

public class CanteenConverter {

    public static CanteenDTO toDTO(final Canteen canteen){
        CanteenDTO dto = new CanteenDTO();
        dto.setId(canteen.getId());
        dto.setName(canteen.getName());
        dto.setDailyOffers(canteen.getDailyOffers().stream().map(dailyOffer -> toDTO(canteen.getId(), dailyOffer)).collect(Collectors.toSet()));
        return dto;
    }

    public static Canteen toEntity(final Canteen canteen, final CanteenDTO dto) {
        canteen.setName(dto.getName());
        toEntityDailyOffers(dto.getDailyOffers(), canteen); //todo skontrolovat ci treba konvertovat aj aj relacie, lebo oni sa na priamo updateuju z tabov
        return canteen;
    }

    public static CanteenDailyOfferDTO toDTO(Long canteenId, CanteenDailyOffer dailyOffer) {
        CanteenDailyOfferDTO dto = new CanteenDailyOfferDTO();
        dto.setCanteenId(canteenId);
        dto.setId(dailyOffer.getId());
        dto.setDayAndDate(dailyOffer.getDayAndDate());
        dto.setDishName(dailyOffer.getDishName());
        return dto;
    }

    public static CanteenDailyOffer toEntity(CanteenDailyOfferDTO dto, CanteenDailyOffer dailyOffer) {
        dailyOffer.setDayAndDate(dto.getDayAndDate());
        dailyOffer.setDishName(dto.getDishName());
        return dailyOffer;
    }

    public static void toEntityDailyOffers(Set<CanteenDailyOfferDTO> dtos, Canteen canteen) {
        Set<CanteenDailyOfferDTO> newDailyOffers = dtos.stream().filter(dto -> dto.getId() == null).collect(Collectors.toSet());
        for (CanteenDailyOfferDTO dto : newDailyOffers) {
            CanteenDailyOffer dailyOffer = new CanteenDailyOffer();
            toEntity(dto, dailyOffer);
            canteen.getDailyOffers().add(dailyOffer);
        }

        Set<CanteenDailyOfferDTO> dailyOffersForUpdate = dtos.stream().filter(dto -> dto.getId() != null).collect(Collectors.toSet());
        for (CanteenDailyOffer dailyOffer : canteen.getDailyOffers()) {
            CanteenDailyOfferDTO dto = dailyOffersForUpdate.stream().filter(pn -> pn.getId().equals(dailyOffer.getId())).findFirst().orElse(null);
            if (dto != null) {
                toEntity(dto, dailyOffer);
            }
        }
    }

}
