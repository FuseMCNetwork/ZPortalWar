package me.johnking.zportalwar.enumeration;

import org.bukkit.Color;

public enum RGBColor {

	BONEMEAL(Color.fromRGB(255, 255, 255)),
	ORANGE_DYE(Color.fromRGB(175, 105, 33)),
	MAGENTA_DYE(Color.fromRGB(175, 75, 215)),
	LIGHTBLUE_DYE(Color.fromRGB(125, 150, 210)),
	DANDELION_YELLOW(Color.fromRGB(220, 220, 45)),
	LIMEGREEN_DYE(Color.fromRGB(127, 255, 0)),
	PINK_DYE(Color.fromRGB(250, 120, 165)),
	GREY_DYE(Color.fromRGB(80, 80, 80)),
	LIGHTGREY_DYE(Color.fromRGB(160, 160, 160)),
	CYAN_DYE(Color.fromRGB(85, 130, 165)),
	PURPLE_DYE(Color.fromRGB(150, 50, 200)),
	LAPIS_LAZULI(Color.fromRGB(70, 70, 240)),
	COCOA_BEANS(Color.fromRGB(100, 75, 48)),
	CACTUS_GREEN(Color.fromRGB(113, 130, 45)),
	ROSE_RED(Color.fromRGB(150, 50, 50)),
	INK_SAC(Color.fromRGB(25, 25, 25));

	private Color color;

	RGBColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}
}
