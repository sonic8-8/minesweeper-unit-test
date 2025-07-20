package cleancode.minesweeper.tobe.minesweeper.board;

import cleancode.minesweeper.tobe.minesweeper.board.cell.CellSnapshotStatus;
import cleancode.minesweeper.tobe.minesweeper.board.position.CellPosition;
import cleancode.minesweeper.tobe.minesweeper.board.position.FixedLandMinePositionSelector;
import cleancode.minesweeper.tobe.minesweeper.board.position.LandMinePositionSelector;
import cleancode.minesweeper.tobe.minesweeper.board.position.RandomLandMinePositionSelector;
import cleancode.minesweeper.tobe.minesweeper.gamelevel.VeryBeginner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GameBoardTest {

    @DisplayName("주어진 난이도로 GameBoard를 초기화한다.")
    @Test
    void initializeGame() {
        // given
        VeryBeginner veryBeginner = new VeryBeginner();
        LandMinePositionSelector selector = new RandomLandMinePositionSelector();
        GameBoard gameBoard = new GameBoard(veryBeginner, selector);

        // when
        gameBoard.initializeGame();

        // then
        assertThat(gameBoard)
                .extracting("landMineCount", "gameStatus")
                .contains(2, GameStatus.IN_PROGRESS);
        assertThat(gameBoard.getRowSize()).isEqualTo(5);
        assertThat(gameBoard.getColSize()).isEqualTo(4);
    }

    @DisplayName("지뢰가 설치된 셀을 열면, 해당 셀은 지뢰 셀로 표시된다.")
    @Test
    void openAt_landMine() {
        // given
        VeryBeginner veryBeginner = new VeryBeginner();

        List<CellPosition> fixedPositions = List.of(CellPosition.of(0, 0), CellPosition.of(4, 3));
        LandMinePositionSelector selector = new FixedLandMinePositionSelector(fixedPositions);

        GameBoard gameBoard = new GameBoard(veryBeginner, selector);
        gameBoard.initializeGame();

        CellPosition cellPosition1 = CellPosition.of(0, 0);
        CellPosition cellPosition2 = CellPosition.of(4, 3);

        // when
        gameBoard.openAt(cellPosition1);
        gameBoard.openAt(cellPosition2);

        // then
        assertThat(gameBoard.getSnapshot(cellPosition1).getStatus()).isEqualTo(CellSnapshotStatus.LAND_MINE);
        assertThat(gameBoard.getSnapshot(cellPosition2).getStatus()).isEqualTo(CellSnapshotStatus.LAND_MINE);
    }

    @DisplayName("지뢰에 인접한 셀을 열면, 해당 셀은 숫자 셀로 표시된다.")
    @Test
    void openAt_number() {
        // given
        VeryBeginner veryBeginner = new VeryBeginner();

        List<CellPosition> fixedPositions = List.of(CellPosition.of(1, 1), CellPosition.of(4, 3));
        LandMinePositionSelector selector = new FixedLandMinePositionSelector(fixedPositions);

        GameBoard gameBoard = new GameBoard(veryBeginner, selector);
        gameBoard.initializeGame();

        CellPosition cellPosition1 = CellPosition.of(0, 0);
        CellPosition cellPosition2 = CellPosition.of(0, 1);
        CellPosition cellPosition3 = CellPosition.of(0, 2);
        CellPosition cellPosition4 = CellPosition.of(1, 0);
        CellPosition cellPosition5 = CellPosition.of(1, 2);
        CellPosition cellPosition6 = CellPosition.of(2, 0);
        CellPosition cellPosition7 = CellPosition.of(2, 1);
        CellPosition cellPosition8 = CellPosition.of(2, 2);

        // when
        gameBoard.openAt(cellPosition1);
        gameBoard.openAt(cellPosition2);
        gameBoard.openAt(cellPosition3);
        gameBoard.openAt(cellPosition4);
        gameBoard.openAt(cellPosition5);
        gameBoard.openAt(cellPosition6);
        gameBoard.openAt(cellPosition7);
        gameBoard.openAt(cellPosition8);

        // then
        assertThat(gameBoard.getSnapshot(cellPosition1).getStatus()).isEqualTo(CellSnapshotStatus.NUMBER);
        assertThat(gameBoard.getSnapshot(cellPosition2).getStatus()).isEqualTo(CellSnapshotStatus.NUMBER);
        assertThat(gameBoard.getSnapshot(cellPosition3).getStatus()).isEqualTo(CellSnapshotStatus.NUMBER);
        assertThat(gameBoard.getSnapshot(cellPosition4).getStatus()).isEqualTo(CellSnapshotStatus.NUMBER);
        assertThat(gameBoard.getSnapshot(cellPosition5).getStatus()).isEqualTo(CellSnapshotStatus.NUMBER);
        assertThat(gameBoard.getSnapshot(cellPosition6).getStatus()).isEqualTo(CellSnapshotStatus.NUMBER);
        assertThat(gameBoard.getSnapshot(cellPosition7).getStatus()).isEqualTo(CellSnapshotStatus.NUMBER);
        assertThat(gameBoard.getSnapshot(cellPosition8).getStatus()).isEqualTo(CellSnapshotStatus.NUMBER);
    }

    @DisplayName("지뢰에 인접하지 않은 셀을 열면, 해당 셀은 빈 셀로 표시된다.")
    @Test
    void openAt_empty() {
        // given
        VeryBeginner veryBeginner = new VeryBeginner();

        List<CellPosition> fixedPositions = List.of(CellPosition.of(1, 1), CellPosition.of(4, 3));
        LandMinePositionSelector selector = new FixedLandMinePositionSelector(fixedPositions);

        GameBoard gameBoard = new GameBoard(veryBeginner, selector);
        gameBoard.initializeGame();

        CellPosition cellPosition1 = CellPosition.of(0, 3);
        CellPosition cellPosition2 = CellPosition.of(1, 3);

        // when
        gameBoard.openAt(cellPosition1);
        gameBoard.openAt(cellPosition2);

        // then
        assertThat(gameBoard.getSnapshot(cellPosition1).getStatus()).isEqualTo(CellSnapshotStatus.EMPTY);
        assertThat(gameBoard.getSnapshot(cellPosition2).getStatus()).isEqualTo(CellSnapshotStatus.EMPTY);
    }

    @DisplayName("")
    @Test
    void flagAt() {
        // given

        // when

        // then
    }

    @DisplayName("")
    @Test
    void getSnapshot() {
        // given

        // when

        // then
    }
}
