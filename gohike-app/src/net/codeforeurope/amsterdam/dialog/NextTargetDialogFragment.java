package net.codeforeurope.amsterdam.dialog;

import net.codeforeurope.amsterdam.NavigateRouteActivity;
import net.codeforeurope.amsterdam.model.Waypoint;
import net.codeforeurope.amsterdam.util.DataConstants;
import es.festigo.festigo.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class NextTargetDialogFragment extends DialogFragment {

	Waypoint currentTarget;

	public NextTargetDialogFragment() {
		// TODO Auto-generated constructor stub
	}

	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		currentTarget = getArguments().getParcelable(DataConstants.CURRENT_TARGET);

		LayoutInflater inflater = getActivity().getLayoutInflater();

		LinearLayout waypointLayout = (LinearLayout) inflater.inflate(R.layout.dialog_found, null);
		TextView foundTitle = (TextView) waypointLayout.findViewById(R.id.next_target_title);
		ImageView targetImage = (ImageView) waypointLayout.findViewById(R.id.next_target_image);
		foundTitle.setText(currentTarget.name.getLocalizedValue());
		Bitmap photo = BitmapFactory.decodeFile(currentTarget.image.localPath);
		targetImage.setImageBitmap(photo);

		builder.setView(waypointLayout).setTitle(R.string.next_stop)
		// R.string.pick_color
		// Add action buttons
				.setPositiveButton(R.string.route_continue, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						// user wants to check in
						// store the current check-in somewhere
						// get the next
						// set the navigation to the next
						((NavigateRouteActivity) getActivity()).doDismissTargetHint();
					}
				});
		return builder.create();
	}

}
