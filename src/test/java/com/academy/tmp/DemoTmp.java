package com.academy.tmp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DemoTmp implements Comparable<DemoTmp> {
    public static void main(String[] args) {
        List<DemoTmp> list = new ArrayList<>();
        Collections.sort(list);
    }

    @Override
    public int compareTo(DemoTmp o) {
        return 0;
    }
}
