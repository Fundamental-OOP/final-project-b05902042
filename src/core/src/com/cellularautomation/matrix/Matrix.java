package com.cellularautomation.matrix;

import com.badlogic.gdx.graphics.Color;

public interface Matrix{
	public Color getColor(int i,int j);//return a RGB888 int, RED:0xff0000ff, GREEN 0x00ff00ff, BLUE 0x0000ffff
	public int getWidth();
	public int getHeight();
	public void update();
}
