package net.codeforeurope.amsterdam.view;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


public class HelpPagerAdapter extends FragmentPagerAdapter {
	  private List<Fragment> fragments;

	  public HelpPagerAdapter(FragmentManager fragmentManager, List<Fragment> fragments) {
	    super(fragmentManager);
	    this.fragments = fragments;
	  }
	  @Override 
	  public Fragment getItem(int position) {
	    return this.fragments.get(position);
	  }

	  @Override
	  public int getCount() {
	    return this.fragments.size();
	  }
}
