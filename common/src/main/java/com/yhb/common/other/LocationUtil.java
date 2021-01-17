package com.yhb.common.other;

/**
 * @author LiChun
 * @version $Id$
 * @since 2020年03月09日 19:35
 */
public class LocationUtil {

    /**
     * 地球赤道半径
     */
    private static final double EARTH_RADIUS = 6378137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 经纬度获取距离，单位为米
     *
     * @param lon1
     * @param lat1
     * @param lon2
     * @param lat2
     *
     * @return
     */
    public static double getDistance(double lon1, double lat1, double lon2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lon1) - rad(lon2);
        double s = 2 * Math.asin(Math.sqrt(
                Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        return s;
    }

    public static void main(String[] args) {
        System.out.println(getDistance(120.1561081400, 30.2789338400,120.1598590000,30.2749810000));
    }
}
