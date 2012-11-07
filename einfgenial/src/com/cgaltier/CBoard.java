package com.cgaltier;

import com.cgaltier.CTileSlot.eColour;
//------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------
public class CBoard  
{
	//board radius, from centre to border
	public static final int s_OnePlayerBoardTileRadius   = 6;
	public static final int s_TwoPlayerBoardTileRadius   = 6;
	public static final int s_ThreePlayerBoardTileRadius = 7;
	public static final int s_FourPlayerBoardTileRadius  = 8;
	
	//used board radius
	public int s_PlayerBoardTileRadius = 0;
	//2d array board representation side length (2*radius)-1
	public int s_PlayerBoardTileSlotSideLength = 0;

	//board special coloured cells (as defined for a 1/2 player game
	public static final CTileSlotPos s_Colour1StartSlotPos = new CTileSlotPos (0,0);
	public static final CTileSlotPos s_Colour2StartSlotPos = new CTileSlotPos (5,0);
	public static final CTileSlotPos s_Colour3StartSlotPos = new CTileSlotPos (0,5);
	public static final CTileSlotPos s_Colour4StartSlotPos = new CTileSlotPos (10,5);
	public static final CTileSlotPos s_Colour5StartSlotPos = new CTileSlotPos (5,10);
	public static final CTileSlotPos s_Colour6StartSlotPos = new CTileSlotPos (10,10);
	
	//number of unused cells in the board. cells that cannot be used/counted (left off from passing to real hexagonal shaped box to square array representation) 
	public static int s_UnusedSlots = 0;
	
	//board storage as a 2 dimension array. reserved according to 
	public CTileSlot m_ctBoardTileSlot [][];

	//number of usable slots on the board
	private int m_iSlotNumber = 0 ;
	//number of free usable slots left
	private int m_iFreeTileSlotNumber = 0;
	
	//static slot position for intermediate computation/value return
	public static CTileSlotPos s_TileSlotPos = new CTileSlotPos (); 
	
	//static valid directions to count cells scores
	public static CTileSlotPos s_tValidCountDirections[] = new CTileSlotPos[6];

	//flags defining if special start slot have been used for each colour.
	public boolean m_bColour1Slot1stPlayed = false ;
	public boolean m_bColour2Slot1stPlayed = false ;
	public boolean m_bColour3Slot1stPlayed = false ;
	public boolean m_bColour4Slot1stPlayed = false ;
	public boolean m_bColour5Slot1stPlayed = false ;
	public boolean m_bColour6Slot1stPlayed = false ;
	
	//static score for intermediate computation/value return
	public static CScore s_Score = new CScore ();
	//------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------
	public CBoard(int iNumberPlayer)
	{
		switch (iNumberPlayer)
		{
		case 1 :
			s_PlayerBoardTileRadius = s_OnePlayerBoardTileRadius;
			s_UnusedSlots = 30;
			break;
		case 2 :
			s_PlayerBoardTileRadius = s_TwoPlayerBoardTileRadius;
			s_UnusedSlots = 30;
			break;			
		case 3 :
			s_PlayerBoardTileRadius = s_ThreePlayerBoardTileRadius;
			s_Colour1StartSlotPos.add(1,1);
			s_Colour2StartSlotPos.add(1,1);
			s_Colour3StartSlotPos.add(1,1);
			s_Colour4StartSlotPos.add(1,1);
			s_Colour5StartSlotPos.add(1,1);
			s_Colour6StartSlotPos.add(1,1);
			s_UnusedSlots = 42;
			break;
		case 4 :
			s_PlayerBoardTileRadius = s_FourPlayerBoardTileRadius;
			s_Colour1StartSlotPos.add(2,2);
			s_Colour2StartSlotPos.add(2,2);
			s_Colour3StartSlotPos.add(2,2);
			s_Colour4StartSlotPos.add(2,2);
			s_Colour5StartSlotPos.add(2,2);
			s_Colour6StartSlotPos.add(2,2);			
			s_UnusedSlots = 56;
			break;
		}
		s_PlayerBoardTileSlotSideLength = s_PlayerBoardTileRadius*2-1;
		m_iSlotNumber = s_PlayerBoardTileSlotSideLength*s_PlayerBoardTileSlotSideLength-s_UnusedSlots;
		
		s_tValidCountDirections[0].set( 1,  0);
		s_tValidCountDirections[1].set( 0,  1);
		s_tValidCountDirections[2].set(-1,  1);
		s_tValidCountDirections[3].set(-1,  0);
		s_tValidCountDirections[4].set( 0, -1);
		s_tValidCountDirections[5].set( 1, -1);
		prepareBoard ();
	}
	//------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------
	private void prepareBoard() 
	{
		m_ctBoardTileSlot = new CTileSlot [s_PlayerBoardTileSlotSideLength][s_PlayerBoardTileSlotSideLength];
		
		int xCurrent ;
		int yCurrent ;
		for (xCurrent = 0 ; xCurrent < s_PlayerBoardTileSlotSideLength ; xCurrent ++)
		{
			for (yCurrent = 0 ; yCurrent < s_PlayerBoardTileSlotSideLength ; yCurrent ++)
			{
				m_ctBoardTileSlot [xCurrent][yCurrent].setValue ( eColour.eFree );
			}			
		}
		
		m_ctBoardTileSlot [s_Colour1StartSlotPos.x()][s_Colour1StartSlotPos.y()].setValue(eColour.eColour1);//colour1
		m_ctBoardTileSlot [s_Colour2StartSlotPos.x()][s_Colour2StartSlotPos.y()].setValue(eColour.eColour2);//colour2
		m_ctBoardTileSlot [s_Colour3StartSlotPos.x()][s_Colour3StartSlotPos.y()].setValue(eColour.eColour3);//colour3
		m_ctBoardTileSlot [s_Colour4StartSlotPos.x()][s_Colour4StartSlotPos.y()].setValue(eColour.eColour4);//colour4
		m_ctBoardTileSlot [s_Colour5StartSlotPos.x()][s_Colour5StartSlotPos.y()].setValue(eColour.eColour5);//colour5
		m_ctBoardTileSlot [s_Colour6StartSlotPos.x()][s_Colour6StartSlotPos.y()].setValue(eColour.eColour6);//colour6
		m_iFreeTileSlotNumber = m_iSlotNumber - 6;
		
		xCurrent = yCurrent = 0;
		int xStart=0;
		int xEnd=1;
		int yStart=s_PlayerBoardTileRadius;		
		int yEnd=s_PlayerBoardTileSlotSideLength;

		for (yCurrent = yStart ; yCurrent <= yEnd ; yCurrent++)
		{
			for (xCurrent = xStart ; xCurrent <= xEnd ; xCurrent++)
			{
				m_ctBoardTileSlot[xCurrent][yCurrent].setValue(eColour.eForbidden);//forbidden status
			}
			xEnd++;
		}
		
		xStart=s_PlayerBoardTileRadius;
		xEnd=s_PlayerBoardTileSlotSideLength;
		yStart=0;		
		yEnd=s_PlayerBoardTileRadius;
		for (yCurrent = yStart ; yCurrent <= yEnd ; yCurrent++)
		{
			for (xCurrent = xStart ; xCurrent <= xEnd ; xCurrent++)
			{
				m_ctBoardTileSlot[xCurrent][yCurrent].setValue(eColour.eForbidden);//forbidden status
			}
			xStart++;
		}		
	}
	//------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------
	public boolean isFull() 
	{
		if ( m_iFreeTileSlotNumber < 2 )
			return true ;
		return false;
	}
	//------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------
	public boolean canPlaceTile (CTile tile)
	{
		return canPlaceTile (tile.m_cPosition1, tile.m_cPosition2);
	}
	//------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------
	public boolean canPlaceTile (CTileSlotPos m_cPosition1, CTileSlotPos m_cPosition2)
	{
		if (getValue(m_cPosition1) == eColour.eFree && getValue (m_cPosition2) == eColour.eFree )
			return true ;
		return false ;
	}
	//------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------
	private eColour getValue(CTileSlotPos m_cPosition) 
	{
		if (m_cPosition.x() >= s_PlayerBoardTileRadius || m_cPosition.x() <= -s_PlayerBoardTileRadius)
			return eColour.eError;
		if (m_cPosition.y() >= s_PlayerBoardTileRadius || m_cPosition.y() <= -s_PlayerBoardTileRadius)
			return eColour.eError;
		return m_ctBoardTileSlot[m_cPosition.x()][m_cPosition.y()].value();
	}
	//------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------
	private int setValue(CTileSlotPos m_cPosition, eColour Colour) 
	{
		if (m_cPosition.x() >= s_PlayerBoardTileRadius || m_cPosition.x() <= -s_PlayerBoardTileRadius)
			return -2;
		if (m_cPosition.y() >= s_PlayerBoardTileRadius || m_cPosition.y() <= -s_PlayerBoardTileRadius)
			return -2;
		m_ctBoardTileSlot[m_cPosition.x()][m_cPosition.y()].setValue(Colour);
		return 0;
	}
	//------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------
	public boolean placeTile (CTile tile)
	{
		if ( !canPlaceTile (tile.m_cPosition1,tile.m_cPosition2))
			return false ;
		setValue(tile.m_cPosition1, tile.m_eColour1);
		setValue(tile.m_cPosition2, tile.m_eColour2);
		m_iFreeTileSlotNumber = m_iFreeTileSlotNumber-2;
		return true ;
	}
	//------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------
	public int countScore (CTileSlotPos tileToCount, CTileSlotPos tileToIgnore)
	{
		int iScore ;
		int iDirection;
		iScore = 0 ;
		//direction0 : on the same angle ->
		//direction1 : 
		for (iDirection = 0 ; iDirection < 6 ; iDirection++)
		{
			CTileSlotPos direction = s_tValidCountDirections[iDirection];
			iScore += countInDirection (tileToCount,direction,tileToIgnore);
		}
		return iScore ;
	}
	//------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------	
	private int countInDirection (CTileSlotPos tileToCount, CTileSlotPos direction, CTileSlotPos tileToIgnore) 
	{
		int iCount = 0 ;
		boolean bStop = false;
		CTileSlotPos next = new CTileSlotPos ();
		next.set(tileToCount);
		do
		{
			next.add(direction);
			if (next.x() == tileToIgnore.x() && next.y() == tileToIgnore.y())
				return 0 ;
			if (next.x() < 0 || next.x() >=s_PlayerBoardTileSlotSideLength )				
				return iCount ;
			if (next.y() < 0 || next.y() >=s_PlayerBoardTileSlotSideLength )
				return iCount ;			
			if (m_ctBoardTileSlot[tileToCount.x()][tileToCount.y()].value() == m_ctBoardTileSlot[next.x()][next.y()].value())
				iCount ++;
			else
				bStop = true ;
		}while (!bStop );
		return 0;
	}
	//------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------
	public CScore countScore (CTile tile)
	{
		eColour Colour1 = getValue ( tile.m_cPosition1 );
		eColour Colour2 = getValue ( tile.m_cPosition2 );
		int iScoreColour1 = 1+countScore ( tile.m_cPosition1, tile.m_cPosition2); //1 for the count of the tile
		int iScoreColour2 = 1+countScore ( tile.m_cPosition2, tile.m_cPosition1); //1 for the count of the tile

		s_Score.reset();
		s_Score.addScore(Colour1, iScoreColour1, Colour2, iScoreColour2);
		return s_Score ;
	}
	//------------------------------------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------------------------------------
}
