package com.mrivals_builder.Mrivals_Builder.dtos.MarvelRivalsAccountDTOs;

import com.mrivals_builder.Mrivals_Builder.entities.MarvelRivalsAccount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarvelRivalsAccountDTO {

    private Long id;
    private Long userId;
    private String mrivalsAccount;
    private String statsRawJson;

    public MarvelRivalsAccountDTO(MarvelRivalsAccount marvelRivalsAccount) {
        this.id = marvelRivalsAccount.getId();
        this.userId = marvelRivalsAccount.getUser().getId();
        this.mrivalsAccount = marvelRivalsAccount.getMrivalsAccount();
        this.statsRawJson = marvelRivalsAccount.getStatsRawJson();
    }
}
