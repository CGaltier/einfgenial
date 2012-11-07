package com.cgaltier;

import com.cgaltier.CTileSlot.eColour;

public class CGame 
{
	static final int s_iMaxTileOnePlayer = 1 ;
	static final int s_iMaxTileMultiPlayers = 6 ;
	static final int s_iMaxScoreOnePlayer = 36 ;
	static final int s_iMaxScoreMultiPlayers = 18 ;
	public final int s_iTileNumber = 120 ;
	public int m_iNumberPlayer = 1;
	public CPlayer m_cActivePlayer = null;
	public CPlayer m_tPlayers[];
	public CBoard m_cBoard ;
	public CTile m_tFreeTiles[];
	public CTile m_tPlacedTiles[];
	public boolean m_bGameOver ;
	public int m_iCurrentTurn ;
	
	
	public CGame (int iNumberPlayer)
	{
		m_bGameOver = false ;
		if (iNumberPlayer == 1)
		{
			CScore.s_iScoreMax = s_iMaxTileOnePlayer;
			CPlayer.s_iTileNumber = s_iMaxTileOnePlayer;
		}
		else
		{
			CScore.s_iScoreMax = s_iMaxScoreMultiPlayers;
			CPlayer.s_iTileNumber = s_iMaxTileMultiPlayers;
		}
		m_iNumberPlayer = iNumberPlayer ;
		m_tPlayers = new CPlayer[m_iNumberPlayer];
		m_cActivePlayer = m_tPlayers[0];
		m_cBoard = new CBoard (iNumberPlayer);
		initTabsOfTiles ();		
	}
	private void initTabsOfTiles() 
	{
		m_tFreeTiles = new CTile [s_iTileNumber];
		//each colour double 5
		//each colour with each other 6
		//11,22,33,44,55,66 x5 ->30
		//12,13,14,15,16 x6	   ->30
		//23,24,25,26 x6	   ->24
		//34,35,36 x6		   ->18
		//45,46 x6			   ->12
		//56 x6        	 	   ->6
		//---------------------->120
		int indexLoop ;
		int index = 0 ;
		for ( indexLoop = 0 ; indexLoop < 5 ; indexLoop++)
		{
			m_tFreeTiles[index].set(eColour.eColour1,eColour.eColour1);index++;
			m_tFreeTiles[index].set(eColour.eColour2,eColour.eColour2);index++;
			m_tFreeTiles[index].set(eColour.eColour3,eColour.eColour3);index++;
			m_tFreeTiles[index].set(eColour.eColour4,eColour.eColour4);index++;
			m_tFreeTiles[index].set(eColour.eColour5,eColour.eColour5);index++;
			m_tFreeTiles[index].set(eColour.eColour6,eColour.eColour6);index++;
		}
		for ( indexLoop = 0 ; indexLoop < 6 ; indexLoop++)
		{
			m_tFreeTiles[index].set(eColour.eColour1,eColour.eColour2);index++;
			m_tFreeTiles[index].set(eColour.eColour1,eColour.eColour3);index++;
			m_tFreeTiles[index].set(eColour.eColour1,eColour.eColour4);index++;
			m_tFreeTiles[index].set(eColour.eColour1,eColour.eColour5);index++;
			m_tFreeTiles[index].set(eColour.eColour1,eColour.eColour6);index++;
			
			m_tFreeTiles[index].set(eColour.eColour2,eColour.eColour3);index++;
			m_tFreeTiles[index].set(eColour.eColour2,eColour.eColour4);index++;
			m_tFreeTiles[index].set(eColour.eColour2,eColour.eColour5);index++;
			m_tFreeTiles[index].set(eColour.eColour2,eColour.eColour6);index++;
			
			m_tFreeTiles[index].set(eColour.eColour3,eColour.eColour4);index++;
			m_tFreeTiles[index].set(eColour.eColour3,eColour.eColour5);index++;
			m_tFreeTiles[index].set(eColour.eColour3,eColour.eColour6);index++;
			
			m_tFreeTiles[index].set(eColour.eColour4,eColour.eColour5);index++;
			m_tFreeTiles[index].set(eColour.eColour4,eColour.eColour6);index++;			
			
			m_tFreeTiles[index].set(eColour.eColour5,eColour.eColour6);index++;
		}
			
	}
	public boolean isGameOver ()
	{
		if (m_tFreeTiles.length==0)
			return true ;
		int iCannotPlay = 0 ;
		for (int iPlayer = 0 ; iPlayer < m_iNumberPlayer ; iPlayer++ )
		{
			if (m_tPlayers[iPlayer].allScoreMax())
				return true ;
			if (m_tPlayers[iPlayer].cannotPlay(m_cBoard))
				iCannotPlay++;
		}
		if (iCannotPlay==m_iNumberPlayer)
			return true ;
		return false ;
	}
}
