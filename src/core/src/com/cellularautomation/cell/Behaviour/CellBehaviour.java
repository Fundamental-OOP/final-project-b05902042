package com.cellularautomation.cell.Behaviour;
import com.cellularautomation.cell.*;
import com.cellularautomation.matrix.CellularMatrix;

public abstract class CellBehaviour
{
    protected CellularMatrix Matrix() { return CellularMatrix.GetInstance(); }
    protected Cell cell;
    public void InjectCell(Cell c) { cell = c; }
    public abstract void PerformCellAction();
}
