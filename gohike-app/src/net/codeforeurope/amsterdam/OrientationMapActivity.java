package net.codeforeurope.amsterdam;

import java.util.Iterator;

import net.codeforeurope.amsterdam.model.Route;
import net.codeforeurope.amsterdam.model.Waypoint;
import es.festigo.festigo.R;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class OrientationMapActivity extends AbstractGameActivity implements OnMyLocationChangeListener {

	private GoogleMap map;

	LatLng myPosition;

	private LatLng targetPosition;

	@Override
	protected void onCreate(Bundle savedInstance) {
		super.onCreate(savedInstance);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.map);

		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.orientation_map)).getMap();
		map.setMyLocationEnabled(true);
		map.setOnMyLocationChangeListener(this);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.enter_from_left, R.anim.leave_to_right);
	}

	protected void setupActionBar() {
		super.setupActionBar();
		actionBar.setTitle(getCurrentRoute().name.getLocalizedValue());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; go home
			onBackPressed();
			return true;
		case R.id.menu_show_map:
			Intent i = new Intent(getBaseContext(), OrientationMapActivity.class);
			startActivity(i);

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		Waypoint currentTarget = getCurrentTarget();
		Route currentRoute = getCurrentRoute();
		
		targetPosition = new LatLng(currentTarget.latitude, currentTarget.longitude);

		map.addMarker(new MarkerOptions().position(targetPosition).draggable(false)
				.title(currentTarget.name.getLocalizedValue())
				.icon(BitmapDescriptorFactory.fromResource(R.drawable.red_map_marker)));

		for(Iterator<Waypoint> i= currentRoute.waypoints.iterator(); i.hasNext(); ) {
		    Waypoint wp = i.next();
		    System.out.println(wp);
		    if(wp.rank != currentTarget.rank){
		    	//add a small map marker
		    	LatLng wpPosition = new LatLng(wp.latitude, wp.longitude);
				map.addMarker(new MarkerOptions().position(wpPosition).draggable(false)
						.title(wp.name.getLocalizedValue())
						.icon(BitmapDescriptorFactory.fromResource(R.drawable.red_map_marker_small)));
		    }
		}
		// zoomIn();
	}

	private void zoomIn() {

		if (targetPosition != null || myPosition != null) {
			// Calculate the markers to get their position
			LatLngBounds.Builder b = new LatLngBounds.Builder();
			if (targetPosition != null) {
				b.include(targetPosition);
			}
			if (myPosition != null) {
				b.include(myPosition);
			}

			LatLngBounds bounds = b.build();
			// Change the padding as per needed
			CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 100);
			map.animateCamera(cu);
		}
	}

	@Override
	public void onMyLocationChange(Location myLocation) {
		myPosition = new LatLng(myLocation.getLatitude(), myLocation.getLongitude());
		zoomIn();
	}

}
