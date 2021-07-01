package com.cellularautomation.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.cellularautomation.cell.*;
import com.cellularautomation.matrix.CellularMatrix;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.cellularautomation.usersmove.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.Input;

import java.util.Arrays;
import java.util.ArrayList;


public class Game extends ApplicationAdapter {

	private CellularMatrix matrix;	//the 2D drawing matrix
	private final int scaling = 4; 	//size of a cell
	private final int matrixWidth = 240;	//width of matrix
	private final int matrixHeight = 135;	//height of matrix

	private OrthographicCamera camera;
	private Pixmap pixmap;
	private SpriteBatch batch;
	private Texture img;
	private BitmapFont font;
	
	private UserMouse userMouse;
	private ArrayList<String> inputCellTypeList;
	private int currInputCellTypeIndex;

	public int GetScreenWidth() { return matrixWidth * scaling; }
	public int GetScreenHeight() { return matrixHeight * scaling; }
	

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GetScreenWidth(), GetScreenHeight());
		font = new BitmapFont();
		font.getData().setScale(1.3f, 1.3f);
		userMouse=new UserMouse();
		try {
			matrix = new CellularMatrix(matrixWidth, matrixHeight);//change to the matrix we want
		} catch (Exception e) {

		}
		

		/*for(int i = 0; i < 25 ; i ++)
		{
			matrix.AddCell(new Rock(new GridPoint2(95 + i,100)));
			matrix.AddCell(new Rock(new GridPoint2(105 - i,50)));
		}*/

		CellFactory.AddMapEntry(new WaterFactory());
		CellFactory.AddMapEntry(new OilFactory());
		CellFactory.AddMapEntry(new SandFactory());
		CellFactory.AddMapEntry(new RockFactory());
		CellFactory.AddMapEntry(new WoodFactory());
		CellFactory.AddMapEntry(new IceFactory());
		CellFactory.AddMapEntry(new FireFactory());
		CellFactory.AddMapEntry(new SteamFactory());
		CellFactory.AddMapEntry(new SmokeFactory());
		
		inputCellTypeList = new ArrayList<String>(Arrays.asList(CellFactory.getCellTypeList()));
		inputCellTypeList.add("Null");
		currInputCellTypeIndex=0;
	}

	@Override
	public void render () {
		
		if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
			CellFactory inputCellType=CellFactory.FindFactory(inputCellTypeList.get(currInputCellTypeIndex));
			Vector3 mouse_position=camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
			
			userMouse.MouseInsertCell(matrix,inputCellType,((int)mouse_position.x)/scaling,((int)mouse_position.y)/scaling,matrixHeight,matrixWidth);
			
		}else{
			userMouse.NoInput();
		}
		
		if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
			currInputCellTypeIndex+=1;
			if(currInputCellTypeIndex>=inputCellTypeList.size()){
				currInputCellTypeIndex=0;
			}
		}
		
		matrix.update();

		ScreenUtils.clear(0f, 0, 0, 1);
		pixmap = new Pixmap( matrix.getWidth() * scaling, matrix.getHeight() * scaling, Format.RGB888);

		int cellCount = matrix.GetCellCount();
		for(int i = 0; i < cellCount; i++)
		{
			Cell c = matrix.GetCellByIndex(i);
			pixmap.setColor(c.color);
			pixmap.fillRectangle(c.position.x * scaling, GetScreenHeight() - (c.position.y + 1) * scaling, scaling, scaling);
		}
		/*for(int i=0;i<matrix.getWidth();i++){
			for(int j=0;j<matrix.getHeight();j++){
				pixmap.setColor(matrix.getColor(i,j));
				for(int k=0;k<scaling*scaling;k++){
					pixmap.drawPixel(i*scaling+k/scaling,j*scaling+k%scaling);
				}
			}
		}*/

		// tell the camera to update its matrices.
		camera.update();
		

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		batch.setProjectionMatrix(camera.combined);

		img = new Texture(pixmap);

		batch.begin();
		batch.draw(img, 0, 0);
		font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0, camera.viewportHeight-10);
		font.draw(batch, "Cell Input: " + inputCellTypeList.get(currInputCellTypeIndex), GetScreenWidth()-200, camera.viewportHeight-10);
		batch.end();

		pixmap.dispose();
		img.dispose();
	}
	
	@Override
	public void dispose () {
		pixmap.dispose();
		batch.dispose();
		img.dispose();
	}
}
