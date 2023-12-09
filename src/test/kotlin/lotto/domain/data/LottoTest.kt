package lotto.domain.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows


class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 아니면 예외가 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 6, 7))
        }
        assertThat(exception.message).isEqualTo("로또의 숫자는 6자리입니다.")
    }

    @Test
    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 5))
        }
        assertThat(exception.message).isEqualTo("로또에 중복된 숫자가 있습니다.")
    }

    @Test
    fun `각 로또 번호는 1에서 45 사이이다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 46))
        }
        assertThat(exception.message).isEqualTo("각 로또 번호는 1~45 사이입니다.")
    }

}
