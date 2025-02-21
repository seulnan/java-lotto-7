package lotto.model.dto;

import java.util.EnumMap;
import java.util.Map;
import lotto.enums.LottoRank;
import lotto.utils.NumberUtil;

public record LottoResultDto(Map<LottoRank, Integer> resultMap) {
    public LottoResultDto(Map<LottoRank, Integer> resultMap) {
        this.resultMap = new EnumMap<>(resultMap);
    }

    public double getProfitRate(int purchaseAmount) {
        int totalPrize = resultMap.entrySet().stream()
                .mapToInt(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();

        double profitRate = (double) totalPrize / purchaseAmount * 100;
        return NumberUtil.roundToTwoDecimalPlaces(profitRate);
    }
}