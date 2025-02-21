package lotto.enums;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum LottoRank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int matchCount;
    private final boolean requiresBonus;
    private final int prize;
    private static final Map<Integer, LottoRank> RANK_MAP = Arrays.stream(values())
            .filter(rank -> !rank.requiresBonus)
            .collect(Collectors.toMap(LottoRank::getMatchCount, rank -> rank));

    LottoRank(int matchCount, boolean requiresBonus, int prize) {
        this.matchCount = matchCount;
        this.requiresBonus = requiresBonus;
        this.prize = prize;
    }

    public int getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public static LottoRank findRank(int matchCount, boolean hasBonus) {
        if (hasBonus && matchCount == 5) {
            return SECOND;
        }
        return RANK_MAP.getOrDefault(matchCount, NONE);
    }
}
