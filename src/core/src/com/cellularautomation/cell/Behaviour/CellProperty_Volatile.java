package com.cellularautomation.cell.Behaviour;

import com.cellularautomation.cell.Cell;
import java.lang.Math;
import com.cellularautomation.cell.CellFactory;

public class CellProperty_Volatile extends CellPropertyBehaviour
{
    public CellProperty_Volatile(int time, float p, String s)
    {
        aliveTime = time;
        replaceProbability = p;
        replace = s;
    }

    private int aliveTime;
    private float replaceProbability;
    private String replace;

    @Override
    public void PerformCellAction() {

        boolean change = ((float)Math.random() < replaceProbability);

        if(aliveTime <= 0 && change)
        {
            if(CellFactory.CheckFactoryExist(replace))
            {
                Cell newCell = CellFactory.FindFactory(replace).CreateCell();
                Matrix().ReplaceCell(cell, newCell);
            }
            else
                Matrix().RemoveCell(cell);
        }
        aliveTime --;
    }
}
