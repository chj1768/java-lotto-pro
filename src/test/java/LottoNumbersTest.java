import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class LottoNumbersTest {
    @Test()
    void LottoNumbers_는_문자열_포맷으로_생성할_수_있다() {
        assertDoesNotThrow(() -> new LottoNumbers("1, 2, 3, 4, 5, 6", Application.SEPARATOR));
    }


    @Test
    void LottoNumbers_는_6개의_LottoNumber_를_포함할_수_있다() {
        assertDoesNotThrow(
                () -> new LottoNumbers(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(6)
                )
        );
    }

    @Test
    void LottoNumbers_는_6개의_LottoNumber_를_포함하지_않으면_예외를_던진다() {
        assertThatThrownBy(
                () -> new LottoNumbers(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5)
                )
        ).isInstanceOf(RuntimeException.class);

        assertThatThrownBy(LottoNumbers::new)
                .isInstanceOf(RuntimeException.class);
    }

    @Test()
    void LottoNumbers_는_적절한_문자열_포맷이_아니면_생성할_수_없다() {
        assertThatThrownBy(() -> new LottoNumbers("1, 2, 3, 4, 5: 6", Application.SEPARATOR))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void LottoNumbers_는_중복된_LottoNumber_를_포함할_수_없다() {
        assertThatThrownBy(
                () -> new LottoNumbers(
                        new LottoNumber(1),
                        new LottoNumber(1),
                        new LottoNumber(1),
                        new LottoNumber(1),
                        new LottoNumber(1),
                        new LottoNumber(1)
                )
        ).isInstanceOf(RuntimeException.class);

        assertThatThrownBy(
                () -> new LottoNumbers(
                        new LottoNumber(1),
                        new LottoNumber(2),
                        new LottoNumber(3),
                        new LottoNumber(4),
                        new LottoNumber(5),
                        new LottoNumber(1)
                )
        ).isInstanceOf(RuntimeException.class);
    }

    @ParameterizedTest
    @CsvSource(
            value = { "1:true", "2:true", "3:true", "4:true", "5:true", "6:true", "7:false", "45:false" },
            delimiterString = ":"
    )
    void LottoNumbers는_LottoNumber_가_포함되어_있는지_여부를_알려줄_수_있다(int number, boolean expected) {
        LottoNumbers lottoNumbers = new LottoNumbers(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        assertThat(lottoNumbers.contains(new LottoNumber(number))).isEqualTo(expected);
    }

    @Test
    void LottoNumbers는_Iterable_객체이다() {
        LottoNumbers lottoNumbers = new LottoNumbers(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        assertThat(lottoNumbers).containsExactlyInAnyOrder(
                new LottoNumber(6),
                new LottoNumber(5),
                new LottoNumber(4),
                new LottoNumber(3),
                new LottoNumber(2),
                new LottoNumber(1)
        );
    }

    @Test
    void LottoNumbers는_사이즈가_동일하고_같은_원소를_순서_상관없이_모두_포함하고_있으면_같다고_생각한다() {
        LottoNumbers lottoNumbers = new LottoNumbers(
                new LottoNumber(1),
                new LottoNumber(2),
                new LottoNumber(3),
                new LottoNumber(4),
                new LottoNumber(5),
                new LottoNumber(6)
        );

        assertThat(lottoNumbers).isEqualTo(new LottoNumbers(
                new LottoNumber(6),
                new LottoNumber(5),
                new LottoNumber(4),
                new LottoNumber(3),
                new LottoNumber(2),
                new LottoNumber(1)
        ));
    }
}
