package lotto.domain.data

import lotto.domain.Lotto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class WinningLottoTest {

    @ParameterizedTest
    @ValueSource(ints = [1, 2, 3, 4, 5, 6])
    fun `당첨 번호와 보너스 번호는 같은 번호가 없다`(number: Int) {
        val exception = assertThrows<IllegalArgumentException> {
            WinningLotto(
                Lotto(listOf(1, 2, 3, 4, 5, 6)),
                BonusNumber(number)
            )
        }
        assertThat(exception.message).isEqualTo("당첨 번호와 보너스 번호는 같으면 안 됩니다.")
    }
}