package com.cellularautomation.cell.Behaviour;

import com.cellularautomation.cell.Cell;
import com.cellularautomation.cell.CellFactory;

public class CellProperty_Extinguisher extends CellPropertyBehaviour
{
    public CellProperty_Extinguisher(String s)
    {
        replace = s;
    }

    private String replace;

    @Override
    public void PerformCellAction() { }

    public void OnExtinguish()
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
