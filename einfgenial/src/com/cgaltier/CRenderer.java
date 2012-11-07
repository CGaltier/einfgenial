package com.cgaltier;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.cgaltier.CTileSlot.eColour;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class CRenderer 
{
	private Mesh m_cColour1SlotMesh ;
	private Mesh m_cColour2SlotMesh ;
	private Mesh m_cColour3SlotMesh ;
	private Mesh m_cColour4SlotMesh ;
	private Mesh m_cColour5SlotMesh ;
	private Mesh m_cColour6SlotMesh ;
	private Mesh m_cColourEmptySlotMesh ;
	
	
	private Matrix4 MVPMatrix ;
	private Matrix4 InvMVPMatrix ;
	private Matrix4 MVMatrix;
	private Matrix4 MVMatrixInv;	
	
	private float m_fCenterYOffset;
	private float m_fCenterXOffset;
	private float m_slotSize = 1.0f;
	
	//private Matrix3 modelWorld = new Matrix3();
	private Matrix4 model = new Matrix4();
	private Matrix4 model2 = new Matrix4();
	//private Matrix4 model3 = new Matrix4();
	//private Vector3 zAxis = new Vector3(0, 0, 1).nor();
	//private Vector3 yAxis = new Vector3(0, 1, 0).nor();
	private Vector3 xAxis = new Vector3(1, 0, 0).nor();
	
	//private OrthographicCamera camera;
	private PerspectiveCamera camera;
	
	public CRenderer (CGame m_CGame)
	{
		m_fCenterYOffset = m_CGame.m_cBoard.s_PlayerBoardTileSlotSideLength/2.0f;
		m_fCenterXOffset = m_CGame.m_cBoard.s_PlayerBoardTileSlotSideLength/2.0f;
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		//camera = new OrthographicCamera(1, h/w);
		camera = new PerspectiveCamera(45, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.translate(0.0f, 0.0f, 3.0f);
		camera.lookAt(0, 0, 0);
   		camera.update();
   		
		MVPMatrix = new Matrix4 ();
		InvMVPMatrix = new Matrix4 ();
		MVMatrix = new Matrix4();
		MVMatrixInv = new Matrix4();
		
		
		m_cColour1SlotMesh = createMesh(eColour.eColour1);
		m_cColour2SlotMesh = createMesh(eColour.eColour2);
		m_cColour3SlotMesh = createMesh(eColour.eColour3);
		m_cColour4SlotMesh = createMesh(eColour.eColour4);
		m_cColour5SlotMesh = createMesh(eColour.eColour5);
		m_cColour6SlotMesh = createMesh(eColour.eColour6);
		m_cColourEmptySlotMesh = createMesh(eColour.eFree);		
	}
	private Mesh createMesh (eColour colour) 
	{
		Mesh newMesh ;
		int nTotalPoints = 6 ;
		int nTotalIndices = 13 ;
		newMesh = new Mesh (	true, 
    			nTotalPoints, 
				nTotalIndices, 
				new VertexAttribute (	VertexAttributes.Usage.Position, 
										3, 
										"a_Position"),
				/*new VertexAttribute (	VertexAttributes.Usage.Normal, 
										3, 
										"a_Normal"),*/
				/*new VertexAttribute (	VertexAttributes.Usage.Generic, 
										3, 
										"a_Tangent"),	*/
				/*new VertexAttribute (	VertexAttributes.Usage.Generic, 
										3, 
										"a_Binormal"),		*/											
				new VertexAttribute (	VertexAttributes.Usage.ColorPacked, 
										4, 
										"a_Color")										
				/*new VertexAttribute (	VertexAttributes.Usage.TextureCoordinates, 
										2, 
										"a_texCoord0")        */												
										);
		short indices [] = new short [nTotalIndices];
		indices  [0] = 0; indices  [1] = 1; indices  [2] = 2;
		indices  [3] = 0; indices  [4] = 2; indices  [6] = 3;
		indices  [7] = 0; indices  [8] = 3; indices  [9] = 5;
		indices [10] = 5; indices [11] = 3; indices [12] = 4;
		newMesh.setIndices(indices);
	  	float Vertices [] = new float [nTotalPoints];
	  	int R = 0 ;
	  	int G = 0 ;
	  	int B = 0 ;
	  	int A = 255 ;
	  	switch (colour)
	  	{
	  		case eColour1 :
	  			R = 255 ;
	  			break;
	  		case eColour2 :
  				R = 255 ;
	  			G = 255 ;
  				break;
	  		case eColour3 :
	  			G = 255;
  				break;
	  		case eColour4 :
	  			G = 255;
	  			B = 255;
  				break;
	  		case eColour5 :
	  			B = 255;
  				break;
	  		case eColour6 :
	  			B = 255;
	  			R = 255;
  				break;	
	  		case eFree :
	  			R = 128;
	  			G = 128;
	  			B = 128;
	  			break;
	  	}
		float color =  Color.toFloatBits(R, G, B, A);
		int indexPoint = 0 ;
		float angle = 30.0f;
		float angleDelta = 60.0f;
		short countIVertex = 0 ;
		for (;indexPoint < 6; indexPoint++)
		{
			
			Vertices [countIVertex++] = MathUtils.cosDeg (angle) ;
			Vertices [countIVertex++] = MathUtils.sinDeg (angle) ;
			Vertices [countIVertex++] = 0.0f ;
			Vertices [countIVertex++] = color ;
			
			angle += angleDelta ;
		}
		
		return null;
	}
	public void start() 
	{
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);		

	}
	private void updateMatrices()
	{
		MVPMatrix = camera.combined.mul(model);
		InvMVPMatrix = MVPMatrix.cpy();
		InvMVPMatrix.inv();
		InvMVPMatrix.tra();		
		MVMatrix = camera.view.mul(model);
		MVMatrixInv = MVMatrix.cpy();
		MVMatrixInv.inv();
	}			
	public void renderBoardGame(CGame m_CGame) 
	{
		CBoard board = m_CGame.m_cBoard ;
		int sideLength = board.s_PlayerBoardTileSlotSideLength ;
		int iRow ;
		int iCol ;
		for ( iRow = 0 ; iRow < sideLength ; iRow++)
		{
			for ( iCol = 0 ; iCol < sideLength ; iCol++)
			{
				displaySlot ( board, iRow, iCol);
			}
		}		
	}
	private void displaySlot(CBoard board, int iRow, int iCol) 
	{
		eColour colorSlot = board.m_ctBoardTileSlot[iRow][iCol].value();
	  	switch (colorSlot)
	  	{
	  		case eColour1 :
	  			displaySlotMesh (m_cColour1SlotMesh, iRow, iCol) ;
	  			break;
	  		case eColour2 :
	  			displaySlotMesh (m_cColour2SlotMesh, iRow, iCol) ;
  				break;
	  		case eColour3 :
	  			displaySlotMesh (m_cColour3SlotMesh, iRow, iCol) ;
  				break;
	  		case eColour4 :
	  			displaySlotMesh (m_cColour4SlotMesh, iRow, iCol) ;
  				break;
	  		case eColour5 :
	  			displaySlotMesh (m_cColour5SlotMesh, iRow, iCol) ;
  				break;
	  		case eColour6 :
	  			displaySlotMesh (m_cColour6SlotMesh, iRow, iCol) ;
  				break;	
	  		case eFree :
	  			displaySlotMesh  (m_cColourEmptySlotMesh, iRow, iCol) ;
	  			break;
	  	}		
	}
	private void displaySlotMesh (Mesh m_cColourEmptySlotMesh2, int iRow, int iCol) 
	{
		model.idt();
		model2.setToRotation(xAxis, 90);
		model.mul(model2);
		//TODO
		//float x = ;
		float y = (iCol*m_slotSize)-this.m_fCenterYOffset;
		float z=0.0f;
		//model.setToTranslation(x, y, z);
		updateMatrices();
		
	}
	public void end() 
	{
        camera.update();		
	}
}
