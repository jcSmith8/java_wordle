package model;

import java.awt.Color;

public class WordleResponse {
	
	private final char c;
	
	private final ResponseColor responseColor;

	public WordleResponse(char c, Color bgColor, Color fgColor) {
		this.c = c;
		this.responseColor = new ResponseColor(bgColor, fgColor);
	}

	public char getLetter() {
		return c;
	}

	public Color getBGColor() {
		return responseColor.getBGColor();
	}

	public Color getFGColor() {
		return responseColor.getFGColor();
	}
	
}
