package cleancode.minesweeper.tobe.minesweeper.board.position;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class CellPositionTest {

    @DisplayName("사용자 입력 좌표를 기반으로 셀의 위치 정보(CellPosition)을 생성한다.")
    @Test
    void of() {
        // given
        int rowIndex = 0;
        int colIndex = 0;

        // when
        CellPosition cellPosition = CellPosition.of(rowIndex, colIndex);

        // then
        assertThat(cellPosition)
                .extracting("rowIndex", "colIndex")
                .containsExactlyInAnyOrder(0, 0);
    }

    @DisplayName("사용자가 입력한 좌표가 0 미만일 경우, 예외가 발생한다.")
    @Test
    void ofWithNegative() {
        // given
        int rowIndex = -1;
        int colIndex = -1;

        // when // then
        assertThatThrownBy(() -> CellPosition.of(rowIndex, colIndex))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("올바르지 않은 좌표입니다.");
    }

    @DisplayName("셀 위치 정보(CellPosition)의 Row 인덱스가 게임 보드 행 크기(rowSize) 내에 들어오는지 검증한다.")
    @Test
    void isRowIndexMoreThanOrEqual() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isRowIndexMoreThanOrEqual(0);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("셀 위치 정보(CellPosition)의 Row 인덱스가 게임 보드 행 크기(rowSize)를 초과할 경우 false를 반환한다.")
    @Test
    void isRowIndexMoreThanOrEqual_whenOutOfBounds() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isRowIndexMoreThanOrEqual(1);

        // then
        assertThat(result).isFalse();
    }

    @DisplayName("셀 위치 정보(CellPosition)의 Col 인덱스가 게임 보드 열 크기(colSize) 내에 들어오는지 검증한다.")
    @Test
    void isColIndexMoreThanOrEqual() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isColIndexMoreThanOrEqual(0);

        // then
        assertThat(result).isTrue();
    }

    @DisplayName("셀 위치 정보(CellPosition)의 Cell 인덱스가 게임 보드 열 크기(ColSize)를 초과할 경우 false를 반환한다.")
    @Test
    void isColIndexMoreThanOrEqual_whenOutOfBounds() {
        // given
        CellPosition cellPosition = CellPosition.of(0, 0);

        // when
        boolean result = cellPosition.isColIndexMoreThanOrEqual(1);

        // then
        assertThat(result).isFalse();
    }
}
