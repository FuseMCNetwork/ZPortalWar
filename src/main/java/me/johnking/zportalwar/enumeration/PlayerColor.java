package me.johnking.zportalwar.enumeration;

import org.bukkit.Material;

public enum PlayerColor {

	PLAYER_1(Material.WOOL, 1, RGBColor.ORANGE_DYE),
	PLAYER_2(Material.WOOL, 2, RGBColor.MAGENTA_DYE),
	PLAYER_3(Material.WOOL, 3, RGBColor.LIGHTBLUE_DYE),
	PLAYER_4(Material.WOOL, 4, RGBColor.DANDELION_YELLOW),
	PLAYER_5(Material.WOOL, 5, RGBColor.LIMEGREEN_DYE),
	PLAYER_6(Material.WOOL, 6, RGBColor.PINK_DYE),
	PLAYER_7(Material.WOOL, 9, RGBColor.CYAN_DYE),
	PLAYER_8(Material.WOOL, 10, RGBColor.PURPLE_DYE),
	PLAYER_9(Material.WOOL, 11, RGBColor.LAPIS_LAZULI),
	PLAYER_10(Material.WOOL, 12, RGBColor.COCOA_BEANS),
	PLAYER_11(Material.WOOL, 13, RGBColor.CACTUS_GREEN),
	PLAYER_12(Material.WOOL, 14, RGBColor.ROSE_RED),
	PLAYER_13(Material.CLAY_BRICK, 1, RGBColor.ORANGE_DYE),
	PLAYER_14(Material.CLAY_BRICK, 2, RGBColor.MAGENTA_DYE),
	PLAYER_15(Material.CLAY_BRICK, 3, RGBColor.LIGHTBLUE_DYE),
	PLAYER_16(Material.CLAY_BRICK, 4, RGBColor.DANDELION_YELLOW),
	PLAYER_17(Material.CLAY_BRICK, 5, RGBColor.LIMEGREEN_DYE),
	PLAYER_18(Material.CLAY_BRICK, 6, RGBColor.PINK_DYE),
	PLAYER_19(Material.CLAY_BRICK, 9, RGBColor.CYAN_DYE),
	PLAYER_20(Material.CLAY_BRICK, 10, RGBColor.PURPLE_DYE),
	PLAYER_21(Material.CLAY_BRICK, 11, RGBColor.LAPIS_LAZULI),
	PLAYER_22(Material.CLAY_BRICK, 12, RGBColor.COCOA_BEANS),
	PLAYER_23(Material.CLAY_BRICK, 13, RGBColor.CACTUS_GREEN),
	PLAYER_24(Material.CLAY_BRICK, 14, RGBColor.ROSE_RED);

	private Material material;
	private int subid;
	private RGBColor color;
	private static int next = 0;

	PlayerColor(Material material, int subid, RGBColor color) {
		this.material = material;
		this.subid = subid;
		this.color = color;
	}

	public Material getMaterial() {
		return material;
	}

	public int getSubId() {
		return subid;
	}
	
	public RGBColor getColor(){
		return color;
	}

	public static PlayerColor getNext() {
		PlayerColor pc = PlayerColor.values()[next];
		next++;
		return pc;
	}

	public static PlayerColor fromIndex(int index) {
		return PlayerColor.values()[index];
	}
}
