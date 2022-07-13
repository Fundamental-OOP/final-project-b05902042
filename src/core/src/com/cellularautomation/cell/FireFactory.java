package com.cellularautomation.cell;

import com.badlogic.gdx.graphics.Color;
import com.cellularautomation.cell.Behaviour.*;

public class FireFactory extends CellFactory
{
    @Override
    public String GetName()
    {
        return "Fire";
    }

    @Override
    public Cell CreateCell()
    {
        Cell c = new Cell();
        c.name = GetName();
        c.density = 0.1f;
        c.color = CellHelper.RandomizeColor(Color.RED,0.1f,false);
        c.moveBehaviour = new CellMove_Ascend(1);
        c.moveBehaviour.InjectCell(c);

        CellPropertyBehaviour b = new CellProperty_Volatile(15, 0.15f,"Smoke");
        b.InjectCell(c);
        c.propertyBehaviours.add(b);

        b = new CellProperty_Igniter();
        b.InjectCell(c);
        c.propertyBehaviours.add(b);
        return c;
    }
}
