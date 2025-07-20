package cleancode.minesweeper.tobe.minesweeper.board.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FixedLandMinePositionSelector implements LandMinePositionSelector {

    private final List<CellPosition> fixedPositions;

    public FixedLandMinePositionSelector(List<CellPosition> fixedPositions) {
        this.fixedPositions = fixedPositions;
    }

    @Override
    public List<CellPosition> extractLandMinePositions(CellPositions candidates, int landMineCount) {
        return fixedPositions.subList(0, landMineCount);
    }
}
