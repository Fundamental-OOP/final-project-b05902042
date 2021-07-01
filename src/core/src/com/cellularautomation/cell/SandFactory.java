package com.cellularautomation.cell;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Color;
import com.cellularautomation.cell.Behaviour.CellMove_Fall;
import com.cellularautomation.cell.Behaviour.CellMove_Flow;

public class SandFactory extends CellFactory
{
    @Override
    public String GetName()
    {
        return "Sand";
    }

    @Override
    public Cell CreateCell()
    {
        Cell c = new Cell();
        c.name = GetName();
        c.density = 2;
        c.color = CellHelper.RandomizeColor(Color.GOLDENROD,0.1f,false);
        c.moveBehaviour = new CellMove_Fall(1);
        c.moveBehaviour.InjectCell(c);
        return c;
    }
}
