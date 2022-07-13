package com.cellularautomation.cell.Behaviour;

import com.cellularautomation.cell.Cell;
import com.cellularautomation.cell.CellFactory;

public class CellProperty_Freezable extends CellPropertyBehaviour
{
    public CellProperty_Freezable(String s)
    {
        replace = s;
    }

    private String replace;

    @Override
    public void PerformCellAction() { }

    public void OnFreeze()
    {
        if(CellFactory.CheckFactoryExist(replace))
        {
            Cell newCell = CellFactory.FindFactory(replace).CreateCell();
            Matrix().ReplaceCell(cell, newCell);
        }
        else
            Matrix().RemoveCell(cell);
    }
}
