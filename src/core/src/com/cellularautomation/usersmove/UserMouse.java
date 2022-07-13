package com.cellularautomation.usersmove;

import com.badlogic.gdx.math.GridPoint2;
import com.cellularautomation.cell.*;
import com.cellularautomation.matrix.CellularMatrix;
import com.badlogic.gdx.Gdx;
import java.lang.Math;

public class UserMouse{
	private boolean lastState;
	private int lastX;
	private int lastY;
	public UserMouse(){
		lastState=false;
	}
	
	public void MouseInsertCell(CellularMatrix matrix,CellFactory cellType,int x,int y,int Height, int Width){
		if(x<0 || x>=Width||y<0||y>=Height){
			lastState=false;
			return;
		}
		
		
		if(lastState){
			int Xwidth=lastX-x;
			int Xdirection=-1;
			if(Xwidth<0){
				Xwidth*=-1;
				Xdirection=1;
			}
			int Ywidth=lastY-y;
			int Ydirection=-1;
			if(Ywidth<0){
				Ywidth*=-1;
				Ydirection=1;
			}
			
			if(Xwidth>Ywidth){
				for(int i=0;i<=Xwidth;i++){
					int insertx=lastX+i*Xdirection;
					int inserty=lastY+Math.round((i*Ywidth)/((float)(Xwidth)))*Ydirection;
					addCell(matrix, insertx, inserty,cellType);
				}
			}else{
				for(int j=0;j<=Ywidth;j++){
					int inserty=lastY+j*Ydirection;
					int insertx=lastX+Math.round((j*Xwidth)/((float)(Ywidth)))*Xdirection;
					addCell(matrix, insertx, inserty,cellType);
				}
			}
			
			
		}else{
			addCell(matrix, x, y,cellType);
			
		}
		lastState=true;
		lastX=x;
		lastY=y;
	}
	
	public void NoInput(){
		lastState=false;
	}
	
	private void addCell(CellularMatrix matrix, int x,int y,CellFactory cellType){
		if(cellType==null){
			if(matrix.getCell(new GridPoint2(x,y))!=null){
				matrix.RemoveCell(matrix.getCell(new GridPoint2(x,y)));
				
			}
			return;
		}
		Cell newCell = cellType.CreateCell();
		newCell.position = new GridPoint2(x,y);
		matrix.AddCell(newCell);
		return;
	}
}