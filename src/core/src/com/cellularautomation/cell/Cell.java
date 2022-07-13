package com.cellularautomation.cell;
import java.util.ArrayList;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.graphics.Color;
import com.cellularautomation.cell.Behaviour.*;

public class Cell
{
    public String name;
    public GridPoint2 position;
    public float density;
    public Color color;
    public CellMoveBehaviour moveBehaviour = null;
    public ArrayList<CellPropertyBehaviour> propertyBehaviours = new ArrayList<>();

    public void UpdateCell()
    {
        if(moveBehaviour.updated == false)
            moveBehaviour.PerformCellAction();
        for (CellPropertyBehaviour c : propertyBehaviours)
            c.PerformCellAction();
    }
}
