package com.java.LuXingyu;

import androidx.room.*;

import com.json.JSONObject;

class StringConverter{
    @TypeConverter
    public static String[] fromTimestamp(String data){
        if (data==null || data.length() == 0) return new String[0];
        return data.split("#ZhanYiSplit#");
    }

    @TypeConverter
    public static String toTiemstamp(String[] data) {
        if (data == null || data.length == 0) {
            return "";
        }
        StringBuffer s = new StringBuffer();
        for (String d : data) {
            s.append(d);
            s.append("#ZhanYiSplit#");
        }
        return s.toString();
    }
}
class DoubleConverter{
    @TypeConverter
    public static Double[] fromTimestamp(String data){
        if (data==null || data.length() == 0) return new Double[0];
        String[] doubles = data.split("#ZhanYiSplit#");
        Double[] result = new Double[doubles.length];
        for (int i = 0; i < doubles.length; i++) {
            result[i] = new Double(doubles[i]);
        }
        return result;
    }

    @TypeConverter
    public static String toTiemstamp(Double[] data) {
        if (data == null || data.length == 0) {
            return "";
        }
        StringBuffer s = new StringBuffer();
        for (Double d : data) {
            s.append(d.toString());
            s.append("#ZhanYiSplit#");
        }
        return s.toString();
    }
}
//class EntitiesConverter{
////    @TypeConverter
////    public static Entities[] fromTimestamp(String data){
////        if (data == null || data.length() == 0) return new Entities[0];
////        String entities[] = data.split("#ZhanYiSplit#");
////        Entities[] res = new Entities[entities.length / 2];
////        for (int i =  0; i < res.length; i++) {
////            res[i].setLabel(entities[i << 1]);
////            res[i].setUrl(entities[(i << 1) + 1]);
////        }
////        return res;
////    }
//
//    @TypeConverter
//    public static String toTiemstamp(Entities[] data) {
//        StringBuilder ans = new StringBuilder();
//        if (data == null || data.length == 0)
//            return "";
//        for (Entities i : data){
//            ans.append(i.getLabel());
//            ans.append("#ZhanYiSplit#");
//            ans.append(i.getUrl());
//            ans.append("#ZhanYiSplit#");
//        }
//        return ans.toString();
//    }
//}

//class DataConverter {
//    protected static Integer[] parseArray(String s) {
//        String[] nums = s.split(", ");
//        Integer[] result = new Integer[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            result[i] = Integer.parseInt(nums[i]);
//        }
//        return result;
//    }
//    @TypeConverter
//    public static RegData[] fromTimestamp(String data){
//        if (data == null || data.length() == 0) return new RegData[0];
//        String regData[] = data.split("#ZhanYiSplit#");
//        RegData[] res = new RegData[regData.length];
//        for (int i =  0; i < res.length; i++) {
//            RegData Data = new RegData();
//            String source = regData[i].substring(9, regData[i].length() - 1);
//            String attributes[] = source.split(",");
//            for (int x = 0; x < 7; i++) {
//                String[] value = attributes[x].split("=");
//                switch (x){
//                    case 0:
//                        Data.setLoc(value[1]);
//                        break;
//                    case 1:
//                        Data.setConfirmed(parseArray(value[1].substring(1, value[1].length() - 1)));
//                        break;
//                    case 2:
//                        Data.setCured(parseArray(value[1].substring(1, value[1].length() - 1)));
//                        break;
//                    case 3:
//                        Data.setSuspected(parseArray(value[1].substring(1, value[1].length() - 1)));
//                        break;
//                    case 4:
//                        Data.setDead(parseArray(value[1].substring(1, value[1].length() - 1)));
//                        break;
//                    case 5:
//                        Data.setBegin(value[1]);
//                        break;
//                    case 6:
//                        Data.setLength(Integer.parseInt(value[1]));
//                        break;
//                }
//            }
//            res[i] = Data;
//        }
//        return res;
//    }
//
//    @TypeConverter
//    public static String toTiemstamp(RegData[] data) {
//        StringBuilder ans = new StringBuilder();
//        if (data == null || data.length == 0)
//            return "";
//        for (RegData i : data){
//            ans.append(i.toString());
//            ans.append("#ZhanYiSplit#");
//        }
//        return ans.toString();
//    }
//}

class booleanConverter {
    @TypeConverter
    public static boolean[] fromTimestamp(String data) {
        if (data == null || data.length() == 0) return new boolean[0];
        String[] source = data.split("#ZhanYiSplit#");
        boolean[] result = new boolean[source.length];
        for (int i = 0; i < source.length; i++) {
            if (source[i].equals("false"))
                result[i] = false;
            else
                result[i] = true;
        }
        return result;
    }

    @TypeConverter
    public static String toTiemstamp(boolean[] data) {
        if (data == null || data.length == 0) {
            return "";
        }
        StringBuffer s = new StringBuffer();
        for (boolean d : data) {
            s.append(d);
            s.append("#ZhanYiSplit#");
        }
        return s.toString();
    }
}

class IntegerConverter{
    @TypeConverter
    public static Integer[] fromTimestamp(String data) {
        if(data == null || data.equals(""))return new Integer[0];
        String[] s = data.split("#ZhanYiSplit#");
        Integer[] result = new Integer[s.length];
        for(int i = 0; i < s.length; i++){
            result[i] = Integer.valueOf(s[i]);
        }
        return result;
    }

    @TypeConverter
    public static String toTimestamp(Integer[] data){
        if(data == null || data.length==0){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < data.length; i++){
            sb.append(data[i].toString());
            sb.append("#ZhanYiSplit#");
        }
        return sb.toString();
    }
}

class ProfileConverter{
    @TypeConverter
    public static Profile fromTimestamp(String data) {
        if(data == null || data.equals(""))return null;
        JSONObject json = new JSONObject(data);

        return new Profile(json);
    }

    @TypeConverter
    public static String toTimestamp(Profile data){
        if(data == null){
            return "";
        }
        JSONObject json = new JSONObject(data);
        return json.toString();
    }
}

class IndiceConverter{
    @TypeConverter
    public static Indice fromTimestamp(String data) {
        if(data == null || data.equals(""))return null;
        JSONObject json = new JSONObject(data);

        return new Indice(json);
    }

    @TypeConverter
    public static String toTimestamp(Indice data){
        if(data == null){
            return "";
        }
        JSONObject json = new JSONObject(data);
        return json.toString();
    }
}

class EmailUConverter{
    @TypeConverter
    public static EmailsU fromTimestamp(String data) {
        if(data == null || data.equals(""))return null;
        JSONObject json = new JSONObject(data);

        return new EmailsU(json);
    }

    @TypeConverter
    public static String toTimestamp(EmailsU data){
        if(data == null){
            return "";
        }
        JSONObject json = new JSONObject(data);
        return json.toString();
    }
}

