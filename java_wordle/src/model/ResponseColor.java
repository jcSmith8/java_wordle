package model;

import java.awt.Color;

public class ResponseColor {
	
	private final Color bgColor, fgColor;

	public ResponseColor(Color bgColor, Color fgColor) {
		this.bgColor = bgColor;
		this.fgColor = fgColor;
	}

	public Color getBGColor() {
		return bgColor;
	}

	public Color getFGColor() {
		return fgColor;
	}

}
