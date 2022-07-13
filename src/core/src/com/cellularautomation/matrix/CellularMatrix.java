package com.cellularautomation.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.graphics.Color;
import com.cellularautomation.cell.Cell;

public class CellularMatrix implements Matrix {

    private static CellularMatrix instance = null;
    public static CellularMatrix GetInstance() { return instance; }

    private List<Cell> cells = new ArrayList<>();
    private List<Cell> cellMapList = new ArrayList<>();
    private HashMap<Cell, GridPoint2> reverseCellMap = new HashMap<Cell, GridPoint2>();
    private int width;
    private int height;

    public CellularMatrix(int w, int h) throws Exception {
        if(instance == null)
            instance = this;
        else
            throw new Exception("There is already an active cellular matrix.");

        width = w;
        height = h;
        cellMapList = Arrays.asList(new Cell[w * h]);
    }

    public boolean isEmpty(GridPoint2 position) {
        return cellMapList.get(position.x * height + position.y) == null;
    }

    public Cell getCell(GridPoint2 position) {
        return cellMapList.get(position.x * height + position.y);
    }

    public void setCell(GridPoint2 position, Cell c) {
        cellMapList.set(position.x * height + position.y, c);
    }

    public boolean isValidPosition(GridPoint2 position)
    {
        return !(position.x >= width || position.x < 0 || position.y >= height || position.y < 0);
    }

    public void AddCell(Cell newCell) {
        if (getCell(newCell.position) != null)
            RemoveCell(getCell(newCell.position));
        cells.add(newCell);
        setCell(newCell.position, newCell);
        reverseCellMap.put(newCell, newCell.position);
    }

    public void SwapCell(Cell a, Cell b)
    {
        setCell(a.position, null);
        setCell(b.position, null);
        GridPoint2 temp = a.position;
        a.position = b.position;
        b.position = temp;
        a.moveBehaviour.updated = true;
        b.moveBehaviour.updated = true;
        setCell(a.position, a);
        setCell(b.position, b);
        reverseCellMap.put(a, a.position);
        reverseCellMap.put(b, b.position);
    }

    public void ReplaceCell(Cell original, Cell replacement)
    {
        replacement.position = original.position;

        setCell(original.position, null);
        reverseCellMap.remove(original);
        cells.remove(original);
        setCell(replacement.position, replacement);
        reverseCellMap.put(replacement, replacement.position);
        cells.add(replacement);
    }

    public void RemoveCell(Cell removed)
    {
        setCell(removed.position, null);
        reverseCellMap.remove(removed);
        cells.remove(removed);
    }

    public int GetCellCount()
    {
        return cells.size();
    }

    public Cell GetCellByIndex(int index)
    {
        return cells.get(index);
    }

    @Override
    public Color getColor(int i, int j) {

        GridPoint2 position = new GridPoint2(i,j);
        Cell c = getCell(position);
        if (c != null)
            return c.color;
        else
            return Color.BLACK;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void update() {
        int size = cells.size();

        for(int i = size - 1; i >= 0; i --)
            cells.get(i).moveBehaviour.updated = false;

        for(int i = size - 1; i >= 0; i --)
        {
            if(i < cells.size())
            {
                setCell(cells.get(i).position, null);
                cells.get(i).UpdateCell();
            }
            if(i < cells.size())
            {
                setCell(cells.get(i).position, cells.get(i));
                reverseCellMap.put(cells.get(i), cells.get(i).position);
            }
        }
    }
}
