package com.cellularautomation.cell;

import com.badlogic.gdx.graphics.Color;
import com.cellularautomation.cell.Behaviour.*;

public class WaterFactory extends CellFactory
{
    @Override
    public String GetName()
    {
        return "Water";
    }

    @Override
    public Cell CreateCell()
    {
        Cell c = new Cell();
        c.name = GetName();
        c.density = 1;
        c.color = new Color(0,0.6f,1,1);
        c.moveBehaviour = new CellMove_Flow(1);
        c.moveBehaviour.InjectCell(c);

        CellPropertyBehaviour b = new CellProperty_Extinguisher("Steam");
        b.InjectCell(c);
        c.propertyBehaviours.add(b);

        b = new CellProperty_Freezable("Ice");
        b.InjectCell(c);
        c.propertyBehaviours.add(b);

        return c;
    }
}
