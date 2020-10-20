package com.java.LuXingyu;

import androidx.annotation.NonNull;
import androidx.room.*;

import com.json.JSONArray;
import com.json.JSONObject;

import java.util.Arrays;

@Entity(tableName = "RegData")
@TypeConverters({StringConverter.class, IntegerConverter.class})
public class RegData {
    @PrimaryKey
    @NonNull
    private String Loc;
    private Integer[] Confirmed;
    private Integer[] Cured;
    private Integer[] Suspected;
    private Integer[] Dead;
    private String begin;
//    RegData(String S) {
//        length = S.length();
//        int m = S.indexOf("\"");
//        int n = S.indexOf("\"", m + 1);
//        this.Loc = S.substring(m + 1, n);
//        m = S.indexOf("\"", n + 1);
//        n = S.indexOf("\"", m + 1);
//
//        m = S.indexOf("\"", n + 1);
//        n = S.indexOf("\"", m + 1);
//        begin = S.substring(m + 1, n);//Begin time initilized
//        System.out.println(begin);
//        m = S.indexOf("\"", n + 1);
//        n = S.indexOf("\"", m + 1);
//        S = S.substring(n + 4, S.length() - 2);
//        update_info(S);
//    }
    RegData(JSONObject json, String loc) {
        this.begin = json.getString("begin");
        JSONArray datas = json.getJSONArray("data");
        this.Loc = loc;
        Confirmed = new Integer[datas.length()];
        Suspected = new Integer[datas.length()];
        Cured = new Integer[datas.length()];
        Dead = new Integer[datas.length()];
        for (int i = 0; i < datas.length(); i++) {
            String[] data = datas.get(i).toString().substring(1).split(",");
            if (!data[0].equals("null"))
                Confirmed[i] = Integer.parseInt(data[0]);
            else
                Confirmed[i] = -1;
            if (!data[1].equals("null"))
                Suspected[i] = Integer.parseInt(data[1]);
            else
                Suspected[i] = -1;
            if (!data[2].equals("null"))
                Cured[i] = Integer.parseInt(data[2]);
            else
                Cured[i] = -1;
            if (!data[3].equals("null"))
                Dead[i] = Integer.parseInt(data[3]);
            else
                Dead[i] = -1;
        }
    }
    public RegData() {
    }


//    public int update (String s) {
//        if (length == 0) {
//            new RegData(s);
//        }
//        if (s.length() <= length) {
//            return 0;//Already up to date
//        }
//        String todo = s.substring(length);
//        todo = todo.substring(1, todo.length() - 2);
//        update_info(todo);
//        return 1;
//    }
//    private void update_info(String S) {
//        ArrayList<Integer> Confirmed = new ArrayList<>();
//        ArrayList<Integer> Cured = new ArrayList<>();
//        ArrayList<Integer> Suspected = new ArrayList<>();
//        ArrayList<Integer> Dead = new ArrayList<>();
//        String[] info = S.split("\\],\\[");
//        for (int i = 0; i < info.length; i++) {
//            String[] nums = info[i].split(",");
//            if (!nums[0].equals("null"))
//                Confirmed.add(Integer.parseInt(nums[0]));
//            else
//                Confirmed.add(-1);
//            if (!nums[1].equals("null"))
//                Suspected.add(Integer.parseInt(nums[1]));
//            else
//                Suspected.add(-1);
//            if (!nums[2].equals("null"))
//                Cured.add(Integer.parseInt(nums[2]));
//            else
//                Cured.add(-1);
//            if (!nums[3].equals("null"))
//                Dead.add(Integer.parseInt(nums[3]));
//            else
//                Dead.add(-1);
//            //System.out.println(Confirmed.get(i) + ", " + Suspected.get(i) + ", " + Cured.get(i) + ", " + Dead.get(i));
//
//            this.Confirmed = new Integer[Confirmed.size()];
//            this.Confirmed = Confirmed.toArray(this.Confirmed);
//            this.Suspected = new Integer[Suspected.size()];
//            this.Suspected = Suspected.toArray(this.Suspected);
//            this.Cured = new Integer[Cured.size()];
//            this.Cured = Cured.toArray(this.Cured);
//            this.Dead = new Integer[Dead.size()];
//            this.Dead = Dead.toArray(this.Dead);
//
//        }
//    }
    public String getBegin() {
        return begin;
    }
    public String getLoc() {
        return Loc;
    }
    public Integer[] getConfirmed() {
        return Confirmed;
    }
    public Integer[] getSuspected() {
        return Suspected;
    }
    public Integer[] getCured() {
        return Cured;
    }
    public Integer[] getDead() {
        return Dead;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public void setConfirmed(Integer[] confirmed) {
        Confirmed = confirmed;
    }

    public void setCured(Integer[] cured) {
        Cured = cured;
    }

    public void setDead(Integer[] dead) {
        Dead = dead;
    }

//    public void setLength(int length) {
//        this.length = length;
//    }

    public void setSuspected(Integer[] suspected) {
        Suspected = suspected;
    }

    public void setLoc(@NonNull String loc) {
        Loc = loc;
    }

    //public int getLength() {
//        return length;
//    }

    @Override
    public String toString() {
        return "RegData{" +
                "Loc=" + Loc +
                ", Confirmed=" + Arrays.toString(Confirmed) +
                ", Cured=" + Arrays.toString(Cured) +
                ", Suspected=" + Arrays.toString(Suspected) +
                ", Dead=" + Arrays.toString(Dead) +
                ", begin=" + begin +
                '}';
    }
}
