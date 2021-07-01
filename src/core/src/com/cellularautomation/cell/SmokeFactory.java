package com.cellularautomation.cell;

import com.badlogic.gdx.graphics.Color;
import com.cellularautomation.cell.Behaviour.*;

public class SmokeFactory extends CellFactory
{
    public String GetName()
    {
        return "Smoke";
    }

    @Override
    public Cell CreateCell()
    {
        Cell c = new Cell();
        c.name = GetName();
        c.density = 0.01f;
        c.color = CellHelper.RandomizeColor(new Color(0.4f,0.4f,0.4f,0.4f),0.1f,true);
        c.moveBehaviour = new CellMove_Ascend(1);
        c.moveBehaviour.InjectCell(c);

        CellPropertyBehaviour b = new CellProperty_Volatile(100, 0.05f,"");
        b.InjectCell(c);
        c.propertyBehaviours.add(b);

        return c;
    }
}
