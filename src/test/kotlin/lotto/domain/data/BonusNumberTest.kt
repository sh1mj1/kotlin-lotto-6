package lotto.domain.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BonusNumberTest {

    @ParameterizedTest
    @ValueSource(ints = [-1, 0, 46])
    fun `보너스 번호가 1~45 가 아니라면 예외를 던진다`(number: Int) {
        val exception = assertThrows<IllegalArgumentException> {
            BonusNumber(number)
        }
        assertThat(exception.message).isEqualTo("보너스 번호는 1~45 사이입니다.")
    }
}