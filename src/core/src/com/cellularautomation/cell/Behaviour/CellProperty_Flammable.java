package com.cellularautomation.cell.Behaviour;

import com.badlogic.gdx.math.GridPoint2;
import com.cellularautomation.cell.Cell;
import com.cellularautomation.cell.CellFactory;

import java.lang.Math;
import com.badlogic.gdx.graphics.Color;

public class CellProperty_Flammable extends CellPropertyBehaviour
{
    public CellProperty_Flammable(float spread, float ignite)
    {
        spreadProbability = spread;
        igniteProbability = ignite;
    }

    private boolean heated = false;
    public boolean isHeated() { return heated; }
    private float spreadProbability;
    private float igniteProbability;
    private String igniteCellName;
    private CellFactory igniter = null;

    @Override
    public void PerformCellAction()
    {
        if(heated)
        {
            GridPoint2 up = new GridPoint2(cell.position.x, cell.position.y + 1);
            GridPoint2 down = new GridPoint2(cell.position.x, cell.position.y - 1);
            GridPoint2 left = new GridPoint2(cell.position.x - 1, cell.position.y);
            GridPoint2 right = new GridPoint2(cell.position.x + 1, cell.position.y);

            TryHeatPosition(up);
            TryHeatPosition(down);
            TryHeatPosition(left);
            TryHeatPosition(right);

            if((float)Math.random() <= igniteProbability)
            {
                Cell newCell = igniter.CreateCell();
                Matrix().ReplaceCell(cell, newCell);
            }
        }
    }

    public void Heated(String heater)
    {
        heated = true;
        igniteCellName = heater;
        igniter = CellFactory.FindFactory(heater);
        cell.color = new Color(cell.color.r + 0.2f, cell.color.g, cell.color.b, cell.color.a);
    }

    private void TryHeatPosition(GridPoint2 position)
    {
        if(!Matrix().isValidPosition(position))
            return;
        Cell c = Matrix().getCell(position);
        if(c == null)
            return;

        for (CellPropertyBehaviour b : c.propertyBehaviours)
        {
            if (b instanceof CellProperty_Flammable)
            {
                if((float)Math.random() <= spreadProbability)
                    ((CellProperty_Flammable) b).Heated(igniteCellName);
                break;
            }
        }
        return;
    }
}
