package cleancode.minesweeper.tobe.minesweeper.board.position;

import cleancode.minesweeper.tobe.minesweeper.board.cell.Cell;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class CellPositionsTest {

    @DisplayName("지뢰찾기 게임 초기화를 위해, 게임 보드인 셀 이중 배열을 기반으로 CellPositions를 생성한다.")
    @Test
    void from() {
        // given
        Cell[][] board = new Cell[2][2];

        // when
        CellPositions cellPositions = CellPositions.from(board);

        // then
        assertThat(cellPositions.getPositions()).hasSize(4);
        assertThat(cellPositions.getPositions())
                .extracting("rowIndex", "colIndex")
                .containsExactlyInAnyOrder(
                        tuple(0, 0),
                        tuple(0, 1),
                        tuple(1, 0),
                        tuple(1, 1)
                );
    }

    @DisplayName("생성된 CellPositions에서 지정한 개수만큼 지뢰 좌표를 랜덤 추출한다.")
    @Test
    void extractLandMinePositions() {
        // given
        Cell[][] board = new Cell[2][2];
        CellPositions cellPositions = CellPositions.from(board);

        int count = 3;

        // when
        List<CellPosition> landMinePositions = cellPositions.extractLandMinePositions(count);

        // then
        assertThat(landMinePositions).hasSize(3);
        assertThat(cellPositions.getPositions()).containsAll(landMinePositions);
    }

    @DisplayName("생성된 CellPositions에서 지뢰 좌표를 제외한 나머지는 숫자 좌표 후보다.")
    @Test
    void subtract() {
        // given
        Cell[][] board = new Cell[2][2];
        CellPositions cellPositions = CellPositions.from(board);

        List<CellPosition> landMinePositions = cellPositions.extractLandMinePositions(2);

        // when
        List<CellPosition> numberPositionCandidates = cellPositions.subtract(landMinePositions);

        // then
        List<CellPosition> expected = cellPositions.getPositions();
        expected.removeAll(landMinePositions);

        assertThat(numberPositionCandidates).hasSize(2)
                .containsExactlyInAnyOrderElementsOf(expected);
    }

}
