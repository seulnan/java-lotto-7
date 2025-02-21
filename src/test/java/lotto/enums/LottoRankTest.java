package lotto.enums;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class LottoRankTest {

    @Test
    void 당첨_등수_찾기_테스트() {
        assertThat(LottoRank.findRank(6, false)).isEqualTo(LottoRank.FIRST);
        assertThat(LottoRank.findRank(5, true)).isEqualTo(LottoRank.SECOND);
        assertThat(LottoRank.findRank(5, false)).isEqualTo(LottoRank.THIRD);
        assertThat(LottoRank.findRank(4, false)).isEqualTo(LottoRank.FOURTH);
        assertThat(LottoRank.findRank(3, false)).isEqualTo(LottoRank.FIFTH);
        assertThat(LottoRank.findRank(2, false)).isEqualTo(LottoRank.NONE);
    }
}
