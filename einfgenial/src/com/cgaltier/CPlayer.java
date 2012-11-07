package com.cgaltier;

public class CPlayer 
{
	static public int s_iTileNumber = 6;
	private String m_sPlayerName ;
	private int m_iPlayerNumber ;
	private CScore m_cScore ;
	private CTile m_cTile[];
	private CTile m_cActiveTile = null;
	private CScore m_cPotentialScore;
	private boolean m_bCanPlay;
	private int m_iLastTurnPlayed;
	boolean m_bEmptyDeck ;
	
	public CPlayer (int iPlayerNumber)
	{
		m_bCanPlay = true ;
		m_sPlayerName = new String ("John Doe");
		m_iPlayerNumber = iPlayerNumber ;
		m_cScore = new CScore ();
		m_cPotentialScore = new CScore ();
		m_cTile = new CTile [s_iTileNumber];		
		m_iLastTurnPlayed = 0 ;
		m_bEmptyDeck = true ;
	}

	public boolean allScoreMax() 
	{
		return m_cScore.allScoreMax();
	}
	
	public boolean isDeckEmpty()
	{
		for (int iTile = 0 ; iTile < s_iTileNumber ; iTile++)
		{
			if (m_cTile [iTile] != null)
			{
				m_bEmptyDeck = false ;
				return m_bEmptyDeck ;
			}
		}
		return true ;
	}

	public boolean cannotPlay(CBoard cBoard) 
	{
		if (isDeckEmpty())
			return true ;
		if (cBoard.isFull())
			return true ;
		return false;
	}

}
