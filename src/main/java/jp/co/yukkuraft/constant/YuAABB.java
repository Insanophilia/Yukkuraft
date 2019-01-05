package jp.co.yukkuraft.constant;

import net.minecraft.util.math.AxisAlignedBB;

/**
 * このクラスは AxisAlignedBB の定義を行います。
 *
 * @author Insanophilia
 *
 */
public class YuAABB
{
    private static final double UNIT = 1.0D / 16.0D;
    private static final double D00  = UNIT * 0.0D;
    private static final double D01  = UNIT * 1.0D;
    private static final double D02  = UNIT * 2.0D;
    private static final double D03  = UNIT * 3.0D;
    private static final double D04  = UNIT * 4.0D;
    private static final double D05  = UNIT * 5.0D;
    private static final double D06  = UNIT * 6.0D;
    private static final double D08  = UNIT * 8.0D;
    private static final double D10  = UNIT * 10.0D;
    private static final double D11  = UNIT * 11.0D;
    private static final double D12  = UNIT * 12.0D;
    private static final double D13  = UNIT * 13.0D;
    private static final double D15  = UNIT * 15.0D;
    private static final double D16  = UNIT * 16.0D;

    // 1 ブロック分の立方体の定義
    public static final AxisAlignedBB FULL_BLOCK = new AxisAlignedBB(D00, D00, D00, D16, D16, D16);
    // 16 分の 1 の隙間のある立方体
    public static final AxisAlignedBB GAP_BLOCK = new AxisAlignedBB(D01, D01, D01, D15, D15, D15);
    // 厚さ 16 分の 1 ブロックの壁の定義
    public static final AxisAlignedBB WALL_UP    = new AxisAlignedBB(D00, D15, D00, D16, D16, D16);
    public static final AxisAlignedBB WALL_DOWN  = new AxisAlignedBB(D00, D00, D00, D16, D01, D16);
    public static final AxisAlignedBB WALL_NORTH = new AxisAlignedBB(D00, D00, D00, D16, D16, D01);
    public static final AxisAlignedBB WALL_SOUTH = new AxisAlignedBB(D00, D00, D15, D16, D16, D16);
    public static final AxisAlignedBB WALL_EAST  = new AxisAlignedBB(D15, D00, D00, D16, D16, D16);
    public static final AxisAlignedBB WALL_WEST  = new AxisAlignedBB(D00, D00, D00, D01, D16, D16);
    // 厚さ 16 分の 1 ブロックの床の接地面の定義
    public static final AxisAlignedBB ON_FLOOR = new AxisAlignedBB(D00, D01, D00, D16, D02, D16);
    // 子ゆっくりサイズの立方体の定義
    public static final AxisAlignedBB YUKKURI       = new AxisAlignedBB(D03, D00, D03, D13, D10, D13);
    public static final AxisAlignedBB CHILD_YUKKURI = new AxisAlignedBB(D05, D00, D05, D11, D06, D11);
}
