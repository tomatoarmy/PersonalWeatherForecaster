package com.example.myapplication;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class CItyFragmentPagerAdapter extends FragmentStatePagerAdapter {
    List<Fragment>fragmentList;
    public CItyFragmentPagerAdapter(@NonNull FragmentManager fm, List<Fragment>fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    int childCount = 0; //作为ViewPager包含的页数

    //当ViewPager页数发生改变时，必须要重写两个方法
    @Override
    public int getItemPosition(@NonNull Object object) {
        Log.d("000000000000111111111", "getItemPosition: ");
        if (childCount > 0){
            childCount--;
            return POSITION_NONE;//该标识符表明Item项已经不存在于host view中了
        }
        return super.getItemPosition(object);
    }

    @Override
    public void notifyDataSetChanged() {
        Log.d("000000000000111111111", "notifyDataSetChanged: ");
        this.childCount = getCount();
        super.notifyDataSetChanged();
    }
}
